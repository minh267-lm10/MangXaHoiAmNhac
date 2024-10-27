package com.viet.music.controller;

import org.springframework.web.bind.annotation.RestController;


import com.viet.music.dto.ApiResponse;
import com.viet.music.dto.PageResponse;
import com.viet.music.dto.request.SongRequest;
import com.viet.music.dto.response.SongResponse;
import com.viet.music.service.SongService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@RestController
@RequiredArgsConstructor
//@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/songs")
public class SongController {
	SongService songService;

	@GetMapping
	public ApiResponse<PageResponse<SongResponse>> getAllSong(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
			) {
		return ApiResponse.<PageResponse<SongResponse>>builder()
				.result(songService.getAllSongs(page,size))
				.build();
	}
	@GetMapping("/{id}")
	public ApiResponse<SongResponse> getSong(@PathVariable String id) {
		return ApiResponse.<SongResponse>builder()
				.result(songService.getSong(id))
				.build();
	}
	@GetMapping("/ids")
	public ApiResponse<List<SongResponse>> getSongAllById(@RequestBody List<String> id) {
		return ApiResponse.<List<SongResponse>>builder()
				.result(songService.getSongAllById(id))
				.build();
	}
	
    @DeleteMapping("/{id}")
    ApiResponse<String> deleteSong(@PathVariable String id) {
        songService.deleteSong(id);
        return ApiResponse.<String>builder().result("song has been deleted").build();
    }
    
    @PostMapping("/createSong")
    ApiResponse<SongResponse> createSong(@RequestBody @Valid SongRequest request) {
        return ApiResponse.<SongResponse>builder()
                .result(songService.createSong(request))
                .build();
    }
	

}
