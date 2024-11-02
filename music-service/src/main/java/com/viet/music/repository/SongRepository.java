package com.viet.music.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.viet.music.entity.Song;

@Repository
public interface SongRepository extends MongoRepository<Song, String> {
    Optional<Song> findByName(String name);

    boolean existsByName(String name);

    Page<Song> findAllById(String userId, Pageable pageable);

    Page<Song> findAll(Pageable pageable);

    List<Song> findByArtistIdsContaining(String artistId);

    @Query("{ 'artistIds': ?0 }")
    List<Song> findSongsByArtistId(@Param("artistId") String artistId);

    Page<Song> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
