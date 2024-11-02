package com.viet.music.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viet.music.dto.ApiResponse;
import com.viet.music.dto.PageResponse;
import com.viet.music.dto.request.PlaylistRequest;
import com.viet.music.dto.response.PlaylistResponse;
import com.viet.music.dto.response.SongResponse;
import com.viet.music.service.PlaylistService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/playlists")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
// @Slf4j
public class PlaylistController {

    PlaylistService playlistService;

    @PostMapping("/addSongs/{playlistId}")
    ApiResponse<List<SongResponse>> addSongs(@PathVariable String playlistId, @RequestBody List<String> id) {
        return ApiResponse.<List<SongResponse>>builder()
                .result(playlistService.addSongs(playlistId, id))
                .build();
    }

    @PostMapping("/createPlaylist")
    ApiResponse<PlaylistResponse> createPlaylist(@RequestBody PlaylistRequest playlistRequest) {
        return ApiResponse.<PlaylistResponse>builder()
                .result(playlistService.createPlaylist(playlistRequest))
                .build();
    }

    @GetMapping("GetAllSongsInPlaylist/{id}")
    ApiResponse<List<SongResponse>> GetAllSongsInPlaylist(@PathVariable String id) {
        return ApiResponse.<List<SongResponse>>builder()
                .result(playlistService.GetAllSongsInPlaylist(id))
                .build();
    }

    @GetMapping
    public ApiResponse<PageResponse<PlaylistResponse>> getAllPlaylist(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        return ApiResponse.<PageResponse<PlaylistResponse>>builder()
                .result(playlistService.getAllPlaylist(page, size))
                .build();
    }

    @GetMapping("getMyPlaylist")
    public ApiResponse<PageResponse<PlaylistResponse>> getMyPlaylist(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        return ApiResponse.<PageResponse<PlaylistResponse>>builder()
                .result(playlistService.getMyPlaylist(page, size))
                .build();
    }
}
