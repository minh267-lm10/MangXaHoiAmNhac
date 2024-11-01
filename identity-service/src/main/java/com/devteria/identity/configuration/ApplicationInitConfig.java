package com.devteria.identity.configuration;

import java.util.Set;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.devteria.identity.entity.Permission;
import com.devteria.identity.entity.Role;
import com.devteria.identity.entity.User;
import com.devteria.identity.repository.PermissionRepository;
import com.devteria.identity.repository.RoleRepository;
import com.devteria.identity.repository.UserRepository;
import com.devteria.identity.service.PermissionService;
import com.devteria.identity.service.RoleService;
import com.devteria.identity.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(
            PermissionRepository permissionRepository,
            PermissionService permissionService,
            RoleRepository roleRepository,
            RoleService roleService,
            UserRepository userRepository,
            UserService userService) {
        return args -> {
            if (userRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {
                Permission listen_free_music = permissionRepository.save(Permission.builder()
                        .name("listen_free_music")
                        .description("Listen to free music")
                        .build());
                Permission listen_premium_music = permissionRepository.save(Permission.builder()
                        .name("listen_premium_music")
                        .description("Listen to premium music")
                        .build());

                Permission upload_music = permissionRepository.save(Permission.builder()
                        .name("upload_music")
                        .description("Upload new music to the system")
                        .build());

                Permission manage_own_music = permissionRepository.save(Permission.builder()
                        .name("manage_own_music")
                        .description("Manage the music uploaded by the user")
                        .build());

                Permission view_statistics = permissionRepository.save(Permission.builder()
                        .name("view_statistics")
                        .description("View listening statistics")
                        .build());

                Permission manage_users = permissionRepository.save(Permission.builder()
                        .name("manage_users")
                        .description("Manage users (add, edit, delete, suspend)")
                        .build());

                Permission manage_all_music = permissionRepository.save(Permission.builder()
                        .name("manage_all_music")
                        .description("Manage all music in the system")
                        .build());

                Permission manage_subscriptions = permissionRepository.save(Permission.builder()
                        .name("manage_subscriptions")
                        .description("Manage user subscription plans")
                        .build());

                Role GUEST = roleRepository.save(Role.builder()
                        .name("GUEST")
                        .description("Users with access to free music only")
                        .permissions(Set.of(listen_free_music))
                        .build());

                Role SUBSCRIBER = roleRepository.save(Role.builder()
                        .name("SUBSCRIBER")
                        .description("Users with access to premium and free music")
                        .permissions(Set.of(listen_free_music, listen_premium_music))
                        .build());

                Role ARTIST = roleRepository.save(Role.builder()
                        .name("ARTIST")
                        .description("Artists who can upload and manage their own music")
                        .permissions(Set.of(
                                listen_free_music,
                                listen_premium_music,
                                upload_music,
                                manage_own_music,
                                view_statistics))
                        .build());

                Role ADMIN = roleRepository.save(Role.builder()
                        .name("ADMIN")
                        .description("Administrators with full access to the system")
                        .permissions(Set.of(
                                listen_free_music,
                                listen_premium_music,
                                upload_music,
                                manage_own_music,
                                view_statistics,
                                manage_users,
                                manage_all_music,
                                manage_subscriptions))
                        .build());

                User user = userRepository.save(User.builder()
                        .id("0")
                        .username(ADMIN_USER_NAME)
                        .emailVerified(true)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .roles(Set.of(ADMIN))
                        .build());

                User sonTung = userRepository.save(User.builder()
                        .id("1")
                        .username("sontung")
                        .email("tung@yopmail.com")
                        .emailVerified(true)
                        .password(passwordEncoder.encode("123456"))
                        .roles(Set.of(ARTIST))
                        .build());
                User phuongLy = userRepository.save(User.builder()
                        .id("2")
                        .username("phuongLy")
                        .email("phuongly@yopmail.com")
                        .emailVerified(true)
                        .password(passwordEncoder.encode("123456"))
                        .roles(Set.of(ARTIST))
                        .build());

                User bichPhuong = userRepository.save(User.builder()
                        .id("3")
                        .username("bichPhuong")
                        .email("bichphuong@yopmail.com")
                        .emailVerified(true)
                        .password(passwordEncoder.encode("123456"))
                        .roles(Set.of(ARTIST))
                        .build());

                User justaTee = userRepository.save(User.builder()
                        .id("4")
                        .username("justaTee")
                        .email("justatee@yopmail.com")
                        .emailVerified(true)
                        .password(passwordEncoder.encode("123456"))
                        .roles(Set.of(ARTIST))
                        .build());

                User mrSiro = userRepository.save(User.builder()
                        .id("5")
                        .username("mrsiro")
                        .email("mrsiro@yopmail.com")
                        .emailVerified(true)
                        .password(passwordEncoder.encode("123456"))
                        .roles(Set.of(ARTIST))
                        .build());
            }
        };
    }
}
