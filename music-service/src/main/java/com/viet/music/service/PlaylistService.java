package com.viet.music.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.viet.music.dto.PageResponse;
import com.viet.music.dto.request.PlaylistRequest;
import com.viet.music.dto.response.PlaylistResponse;
import com.viet.music.dto.response.SongResponse;
import com.viet.music.entity.Playlist;
import com.viet.music.exception.AppException;
import com.viet.music.exception.ErrorCode;
import com.viet.music.mapper.PlaylistMapper;
import com.viet.music.repository.PlaylistRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
// @Slf4j
public class PlaylistService {
    PlaylistRepository playlistRepository;
    SongService songService;
    PlaylistMapper mapper;

    public List<SongResponse> GetAllSongsInPlaylist(String id) {
        Playlist playlist =
                playlistRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.KHONG_TON_TAI_PLAYLIST));
        List<String> SongIDs = playlist.getSongIds();
        return songService.getSongAllById(SongIDs);
    }

    public List<SongResponse> addSongs(String playlistId, List<String> id) {
        Playlist playlist = playlistRepository
                .findById(playlistId)
                .orElseThrow(() -> new AppException(ErrorCode.KHONG_TON_TAI_PLAYLIST));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        if (userId.equals(playlist.getUserId())) {
            Set<String> vietSet = new HashSet<>(playlist.getSongIds());
            vietSet.addAll(id);
            playlist.setSongIds(new ArrayList<>(vietSet));
            playlist = playlistRepository.save(playlist);
            return GetAllSongsInPlaylist(playlistId);
        } else {
            throw new AppException(ErrorCode.PLAYLIST_KHONG_PHAI_CUU_CUA_BAN);
        }
    }

    public PageResponse<PlaylistResponse> getAllPlaylist(int page, int size) {
        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        var pageData = playlistRepository.findAll(pageable);

        return PageResponse.<PlaylistResponse>builder()
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .data(pageData.getContent().stream()
                        .map(t -> mapper.toPlaylistReponse(t))
                        .toList())
                .build();
    }

    public PlaylistResponse createPlaylist(PlaylistRequest playlistRequest) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        Playlist playlist = mapper.toPlaylist(playlistRequest);
        playlist.setUserId(userId);
        playlist = playlistRepository.save(playlist);

        return mapper.toPlaylistReponse(playlist);
    }
}
