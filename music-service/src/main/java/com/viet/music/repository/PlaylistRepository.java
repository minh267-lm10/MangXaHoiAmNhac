package com.viet.music.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.viet.music.entity.Playlist;

@Repository
public interface PlaylistRepository extends MongoRepository<Playlist, String> {
    Optional<Playlist> findByName(String Name);

    boolean existsByName(String name);

    Page<Playlist> findAll(Pageable pageable);
}
