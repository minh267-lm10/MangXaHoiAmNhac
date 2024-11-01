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
                UserProfile sonTung = userProfileRepository.save(UserProfile.builder()
                        .userId("1")
                        .username("sonTung")
                        .email("sontung@yopmail.com")
                        .firstName("Thanh Tùng")
                        .lastName("Nguyễn")
                        .stageName("Sơn Tùng MPT")
                        .dob(LocalDate.of(1994, 7, 5))
                        .city("vietnam")
                        .build());

                UserProfile phuongLy = userProfileRepository.save(UserProfile.builder()
                        .userId("2")
                        .username("phuongLy")
                        .email("phuongly@yopmail.com")
                        .firstName("Phương Ly")
                        .lastName("Trần")
                        .stageName("Phương Ly")
                        .dob(LocalDate.of(1990, 10, 28))
                        .city("vietnam")
                        .img("/data/img/222214_phuonglytrolaigoicamtruongthanhvoiamnhac1206.jpeg")
                        .build());

                UserProfile bichPhuong = userProfileRepository.save(UserProfile.builder()
                        .userId("3")
                        .username("bichPhuong")
                        .email("bichphuong@yopmail.com")
                        .firstName("Thị Bích Phương")
                        .lastName("Bùi")
                        .stageName("Bích Phương")
                        .dob(LocalDate.of(1989, 9, 30))
                        .city("vietnam")
                        .img("/data/img/bich_phuong.7ab4f334-5994-461d-9e58-4dbfb763c619.png")
                        .build());

                UserProfile justaTee = userProfileRepository.save(UserProfile.builder()
                        .userId("4")
                        .username("justaTee")
                        .email("justatee@yopmail.com")
                        .firstName("Thanh Tuấn")
                        .lastName("Nguyễn")
                        .stageName("JustaTee")
                        .dob(LocalDate.of(1990, 11, 1))
                        .city("vietnam")
                        .img("/data/img/JustaTee.1453715057717.jpg")
                        .build());

                UserProfile mrSiro = userProfileRepository.save(UserProfile.builder()
                        .userId("5")
                        .username("Mr.Siro")
                        .email("mrsiro@yopmail.com")
                        .firstName("Quốc Tuân")
                        .lastName("Vương")
                        .stageName("Mr.Siro")
                        .dob(LocalDate.of(1982, 11, 18))
                        .city("vietnam")
                        .img("/data/img/Mr.Siro.1505103180911.jpg")
                        .build());
            }
        };
    }
}
