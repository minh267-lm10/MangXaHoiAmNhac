package com.viet.music.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@Document(value = "playlist")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Playlist {
    @MongoId
    String id;

    String name;
    String desc;
    String image;
    String userId;
    List<String> songIds;
}
