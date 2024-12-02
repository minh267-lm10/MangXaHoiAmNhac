package com.devteria.post.repository.http_client;

import com.devteria.post.dto.ApiResponse;
import com.devteria.post.dto.response.UserProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "profile-client", url = "${app.services.profile}")
public interface ProfileClient {
    @GetMapping(value = "/internal/users/getUserIdsFollowing")
    ApiResponse<List<UserProfileResponse>> getUserIdsFollowing(@RequestParam("userId") String userId);

}
