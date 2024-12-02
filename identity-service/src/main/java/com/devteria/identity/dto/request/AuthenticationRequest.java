package com.devteria.identity.dto.request;

import jakarta.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
    @NotNull(message = "NOT_NULL_USERNAME")
    String username;

    @NotNull(message = "NOT_NULL_PASSWORD")
    String password;
}
