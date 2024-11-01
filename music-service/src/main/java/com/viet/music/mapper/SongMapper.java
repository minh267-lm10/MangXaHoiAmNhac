package com.viet.music.mapper;

import org.mapstruct.Mapper;

import com.viet.music.dto.request.SongRequest;
import com.viet.music.dto.response.SongResponse;
import com.viet.music.entity.Song;

@Mapper(componentModel = "spring")
public interface SongMapper {
    Song toSong(SongRequest request);

    SongResponse toSongResponse(Song entity);
}
