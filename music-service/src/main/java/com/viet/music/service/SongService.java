package com.viet.music.service;

import java.time.Instant;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.viet.music.dto.PageResponse;
import com.viet.music.dto.request.SongRequest;
import com.viet.music.dto.response.SongResponse;
import com.viet.music.entity.Song;
import com.viet.music.exception.AppException;
import com.viet.music.exception.ErrorCode;
import com.viet.music.mapper.SongMapper;
import com.viet.music.repository.SongRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
// @Slf4j
public class SongService {
    SongMapper mapper;
    SongRepository songRepository;

    //    @PreAuthorize("hasRole('ADMIN')")
    public PageResponse<SongResponse> getAllSongs(int page, int size) {

        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        var pageData = songRepository.findAll(pageable);

        return PageResponse.<SongResponse>builder()
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(pageData.getContent().stream()
                        .map(t -> mapper.toSongResponse(t))
                        .toList())
                .build();
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    public SongResponse getSong(String id) {
        return songRepository
                .findById(id)
                .map(t -> mapper.toSongResponse(t))
                .orElseThrow(() -> new AppException(ErrorCode.KHONG_TON_TAI_BAI_HAT));
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSong(String songId) {
        songRepository.deleteById(songId);
    }

    public SongResponse createSong(SongRequest request) {
        Song song = mapper.toSong(request);
        song.setCreatedDate(Instant.now());
        songRepository.save(song);
        try {
            song = songRepository.save(song);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.BAI_HAT_DA_TON_TAI);
        }
        return mapper.toSongResponse(song);
    }

    public List<SongResponse> getSongAllById(List<String> id) {
        if (id == null || id.isEmpty()) {
            throw new AppException(ErrorCode.RONG_HOAC_NULL);
        }
        List<SongResponse> viet = songRepository.findAllById(id).stream()
                .map(t -> mapper.toSongResponse(t))
                .toList();
        if (viet.isEmpty()) {
            throw new AppException(ErrorCode.KHONG_TON_TAI_BAI_HAT);
        } else return viet;
    }

    public List<SongResponse> getSongsByArtistId(String artistId) {

        return songRepository.findByArtistIdsContaining(artistId).stream()
                .map(t -> mapper.toSongResponse(t))
                .toList();
    }
}
