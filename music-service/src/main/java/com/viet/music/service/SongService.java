package com.viet.music.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

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
//@Slf4j
public class SongService {
	SongMapper mapper;
	SongRepository songRepository;
	
//    @PreAuthorize("hasRole('ADMIN')")
	public List<SongResponse> getAllSong(){
		return  songRepository.findAll().stream().map(t -> mapper.toSongResponse(t)).toList();
	}
    
//    @PreAuthorize("hasRole('ADMIN')")
	public SongResponse getSong(String id){
		return  songRepository
				.findById(id)
				.map(t -> mapper.toSongResponse(t))
				.orElseThrow(() -> new AppException(ErrorCode.KHONG_TON_TAI_BAI_HAT));
	}
    
//    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSong(String songId) {
        songRepository.deleteById(songId);
    }

	public SongResponse createSong(SongRequest request) {
		Song song= mapper.toSong(request);
		songRepository.save(song);
        try {
        	song=songRepository.save(song);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.BAI_HAT_DA_TON_TAI);
        }
		return mapper.toSongResponse(song);
	}
	
	public List<SongResponse> getSongAllById(List<String> id) {
	    if (id == null || id.isEmpty()) {
	    	throw new AppException(ErrorCode.RONG_HOAC_NULL);
	    }
	    List<SongResponse> viet= songRepository.findAllById(id).stream().map(t -> mapper.toSongResponse(t)).toList();
	    if(viet.isEmpty()) {
	    	throw new AppException(ErrorCode.KHONG_TON_TAI_BAI_HAT);
	    }else
		return viet;

	}
}
