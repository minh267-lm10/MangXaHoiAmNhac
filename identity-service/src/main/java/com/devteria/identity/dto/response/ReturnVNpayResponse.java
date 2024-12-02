package com.devteria.identity.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReturnVNpayResponse {
    String message;
    String vnp_Amount;
    String vnp_BankCode;
    String vnp_BankTranNo;
    String vnp_CardType;
    String vnp_OrderInfo;
    String vnp_PayDate;
    String vnp_ResponseCode;
}
