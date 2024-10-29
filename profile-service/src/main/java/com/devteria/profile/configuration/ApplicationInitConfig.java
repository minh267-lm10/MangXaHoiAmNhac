package com.devteria.profile.configuration;

import java.time.LocalDate;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devteria.profile.entity.UserProfile;
import com.devteria.profile.repository.UserProfileRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    @Bean
    @ConditionalOnProperty(prefix = "spring", value = "neo4j.uri", havingValue = "bolt://localhost:7687")
    ApplicationRunner applicationRunner(UserProfileRepository userProfileRepository) {
        return args -> {
            if (userProfileRepository.findByUserId("1").isEmpty()) {
                UserProfile userProfile = UserProfile.builder()
                        .userId("1")
                        .username("tung2")
                        .email("sontung@yopmail.com")
                        .firstName("Thanh Tùng")
                        .lastName("Nguyễn")
                        .stageName("Sơn Tùng MPT")
                        .dob(LocalDate.of(1994, 7, 5))
                        .city("vietnam")
                        .build();
                userProfileRepository.save(userProfile);
            }
        };
    }
}
