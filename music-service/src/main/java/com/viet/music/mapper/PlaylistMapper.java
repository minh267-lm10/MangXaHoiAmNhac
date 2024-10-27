package com.viet.music.mapper;

import org.mapstruct.Mapper;

import com.viet.music.dto.request.PlaylistRequest;
import com.viet.music.dto.response.PlaylistResponse;
import com.viet.music.entity.Playlist;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
    Playlist toPlaylist(PlaylistRequest request);

    PlaylistResponse toPlaylistReponse(Playlist entity);
}
