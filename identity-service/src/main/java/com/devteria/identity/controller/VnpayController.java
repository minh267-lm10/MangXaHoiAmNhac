package com.devteria.identity.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import com.devteria.identity.repository.PaymentDataPointRepository;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.devteria.identity.configuration.Config;
import com.devteria.identity.constant.PredefinedRole;
import com.devteria.identity.dto.ApiResponse;
import com.devteria.identity.entity.PaymentDataPoint;
import com.devteria.identity.entity.Role;
import com.devteria.identity.entity.User;
import com.devteria.identity.exception.AppException;
import com.devteria.identity.exception.ErrorCode;
import com.devteria.identity.repository.RoleRepository;
import com.devteria.identity.repository.UserRepository;
import com.devteria.identity.service.UserService;

import lombok.AllArgsConstructor;

@RestController
// @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/vnpay")
@AllArgsConstructor
public class VnpayController {
    PaymentDataPointRepository paymentDataPointRepository;
    UserService userService;
    UserRepository userRepository;
    //	PlaylistRepository playlistR;
    //	ArtistRepository artistR;
    //	SongRepository songR;
    RoleRepository roleRepository;

    @GetMapping("/create_payment")
//    public ApiResponse<?> createPayment(HttpServletRequest req) throws UnsupportedEncodingException {
        public RedirectView createPayment(HttpServletRequest req) throws UnsupportedEncodingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        User viet = userRepository.findByid(userId);
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        //
        String orderType = "other";
        String amount = req.getParameter("amount") + "00";

        String bankCode = req.getParameter("bankCode");

        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_IpAddr = Config.getIpAddress(req);

        String vnp_TmnCode = Config.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);

        //		vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_Amount", amount);

        vnp_Params.put("vnp_CurrCode", "VND");

        if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", viet.getUsername());
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = req.getParameter("language");
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                // Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                // Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

//        return ApiResponse.builder().message(paymentUrl).build();
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(paymentUrl);
        return redirectView;

    }

    @GetMapping("/return")
    public RedirectView returnVNpay(
            @RequestParam String vnp_Amount,
            @RequestParam String vnp_BankCode,
            @RequestParam String vnp_BankTranNo,
            @RequestParam String vnp_CardType,
            @RequestParam String vnp_OrderInfo,
            @RequestParam String vnp_PayDate,
            @RequestParam String vnp_ResponseCode) {

        if (vnp_ResponseCode.equals("00")) {
            String username = vnp_OrderInfo;
            User viet = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
            Role role = roleRepository
                    .findById(PredefinedRole.SUBSCRIBER_ROLE)
                    .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

            viet.getRoles().add(role);
            userRepository.save(viet);


            paymentDataPointRepository.save(PaymentDataPoint.builder()
                    .message("Giao dịch thành công")
                    .vnp_Amount(vnp_Amount)
                    .vnp_BankCode(vnp_BankCode)
                    .vnp_BankTranNo(vnp_BankTranNo)
                    .vnp_CardType(vnp_CardType)
                    .vnp_OrderInfo(vnp_OrderInfo)
                    .vnp_PayDate(vnp_PayDate)
                    .vnp_ResponseCode(vnp_ResponseCode)
                    .build());

        } else {
            paymentDataPointRepository.save(PaymentDataPoint.builder()
                    .vnp_Amount(vnp_Amount)
                    .vnp_BankCode(vnp_BankCode)
                    .vnp_BankTranNo(vnp_BankTranNo)
                    .vnp_CardType(vnp_CardType)
                    .vnp_OrderInfo(vnp_OrderInfo)
                    .vnp_PayDate(vnp_PayDate)
                    .vnp_ResponseCode(vnp_ResponseCode)
                    .build());
        }
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:3000");
        return redirectView;
    }
}
