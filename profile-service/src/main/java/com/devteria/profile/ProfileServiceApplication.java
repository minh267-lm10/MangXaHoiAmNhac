package com.devteria.profile;

import java.time.LocalDate;

import jakarta.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devteria.profile.entity.UserProfile;
import com.devteria.profile.mapper.UserProfileMapper;
import com.devteria.profile.repository.UserProfileRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@SpringBootApplication
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileServiceApplication implements CommandLineRunner {
    UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;

    public static void main(String[] args) {
        SpringApplication.run(ProfileServiceApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (userProfileRepositoryz) {
            UserProfile userProfile = UserProfile.builder()
                    .userId("1")
                    .username("tung2")
                    .email("11")
                    .firstName("Thanh Tùng")
                    .lastName("Nguyễn")
                    .stageName("Sơn Tùng MPT")
                    .dob(LocalDate.of(1994, 7, 5))
                    .city("vietnam")
                    .build();
            userProfileRepository.save(userProfile);
        }
    }
}
