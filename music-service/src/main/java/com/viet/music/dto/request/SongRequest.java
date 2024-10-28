package com.viet.music.dto.request;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SongRequest {
	String id;
	String name;
	String title;
	String album;
	int duration; // duration in seconds
	String desc;
	LocalDate releaseDate;
	List<String> artistIds;
}
