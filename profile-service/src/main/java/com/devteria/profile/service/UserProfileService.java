package com.devteria.profile.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.devteria.profile.dto.PageResponse;
import com.devteria.profile.dto.request.ProfileCreationRequest;
import com.devteria.profile.dto.response.UserProfileResponse;
import com.devteria.profile.entity.UserProfile;
import com.devteria.profile.exception.AppException;
import com.devteria.profile.exception.ErrorCode;
import com.devteria.profile.mapper.UserProfileMapper;
import com.devteria.profile.repository.UserProfileRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserProfileService {
    UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;

    public UserProfileResponse createProfile(ProfileCreationRequest request) {
        UserProfile userProfile = userProfileMapper.toUserProfile(request);
        userProfile = userProfileRepository.save(userProfile);

        return userProfileMapper.toUserProfileReponse(userProfile);
    }

    public UserProfileResponse getProfile(String id) {

        UserProfile userProfile = userProfileRepository
                .findByUserId(id)
                .orElseThrow(() -> new AppException(ErrorCode.KHONG_TIM_THAY_PROFILE));
        UserProfileResponse userProfileResponse = userProfileMapper.toUserProfileReponse(userProfile);
        userProfileResponse.setNumberOfFollowers(userProfileRepository.countFollowers(id));

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            String userId = authentication.getName();
            userProfileResponse.setIsFollowing(userProfileRepository.isFollowing(userId, id));
        } else userProfileResponse.setIsFollowing(null);

        return userProfileResponse;
    }

    // @PreAuthorize("hasRole('ADMIN')")
    public List<UserProfileResponse> getAllProfiles() {
        var profiles = userProfileRepository.findAll();

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            String myUserId = authentication.getName();
            return profiles.stream()
                    .map(t -> {
                        UserProfileResponse userProfileResponse = userProfileMapper.toUserProfileReponse(t);
                        userProfileResponse.setIsFollowing(userProfileRepository.isFollowing(myUserId, t.getUserId()));
                        userProfileResponse.setNumberOfFollowers(userProfileRepository.countFollowers(t.getUserId()));
                        return userProfileResponse;
                    })
                    .toList();
        } else
            return profiles.stream()
                    .map(t -> {
                        UserProfileResponse userProfileResponse = userProfileMapper.toUserProfileReponse(t);
                        userProfileResponse.setNumberOfFollowers(userProfileRepository.countFollowers(t.getUserId()));
                        return userProfileResponse;
                    })
                    .toList();
    }

    public UserProfileResponse getMyProfile() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        var profile = userProfileRepository
                .findByUserId(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userProfileMapper.toUserProfileReponse(profile);
    }

    public Boolean followUserOrUnfollowUser(String targetUserId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        return userProfileRepository.followUserOrUnfollowUser(userId, targetUserId);
    }

    public PageResponse<UserProfileResponse> seachStageName(String stageName, int page, int size) {

        Sort sort = Sort.by("stageName").ascending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        var pageData = userProfileRepository.findByStageNameContainingIgnoreCase(stageName, pageable);

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            String myUserId = authentication.getName();
            return PageResponse.<UserProfileResponse>builder()
                    .currentPage(page)
                    .pageSize(pageData.getSize())
                    .totalPages(pageData.getTotalPages())
                    .totalElements(pageData.getTotalElements())
                    .data(pageData.getContent().stream()
                            .map(t -> {
                                UserProfileResponse userProfileResponse = userProfileMapper.toUserProfileReponse(t);
                                userProfileResponse.setIsFollowing(
                                        userProfileRepository.isFollowing(myUserId, t.getUserId()));
                                userProfileResponse.setNumberOfFollowers(
                                        userProfileRepository.countFollowers(t.getUserId()));
                                return userProfileResponse;
                            })
                            .toList())
                    .build();
        } else
            return PageResponse.<UserProfileResponse>builder()
                    .currentPage(page)
                    .pageSize(pageData.getSize())
                    .totalPages(pageData.getTotalPages())
                    .totalElements(pageData.getTotalElements())
                    .data(pageData.getContent().stream()
                            .map(t -> {
                                UserProfileResponse userProfileResponse = userProfileMapper.toUserProfileReponse(t);
                                userProfileResponse.setNumberOfFollowers(
                                        userProfileRepository.countFollowers(t.getUserId()));
                                return userProfileResponse;
                            })
                            .toList())
                    .build();
    }
}
