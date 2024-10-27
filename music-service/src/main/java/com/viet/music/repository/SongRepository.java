package com.viet.music.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.viet.music.entity.Song;

@Repository
public interface SongRepository extends MongoRepository<Song, String>{
	Optional<Song> findByName(String name);
	
	boolean existsByName(String name);
}
