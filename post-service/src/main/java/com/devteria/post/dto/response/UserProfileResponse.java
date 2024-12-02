package com.devteria.post.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileResponse {
    String id;
    String username;
    String email;
    String firstName;
    String lastName;
    String stageName;
    LocalDate dob;
    String city;
    String img;
    Boolean isFollowing;
    String userId;
    Long numberOfFollowers;
}
