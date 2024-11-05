package com.viet.music.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
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
@Document(value = "song")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Song {
    @MongoId
    String id;

    @Indexed(unique = true)
    String name;

    String title;
    String album;
    int duration; // duration in seconds
    String file;
    String image;
    String desc;
    LocalDate releaseDate;
    List<String> artistIds;

    @CreatedDate
    Instant createdDate;

    @LastModifiedDate
    Instant modifiedDate;
}
