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

	Page<Song> findAllByUserId(String userId, Pageable pageable);

	Page<Song> findAll(Pageable pageable);

	List<Song> findByArtistIDsContaining(String artistID);

	@Query("SELECT s FROM Song s WHERE :artistId MEMBER OF s.artistIds")
	List<Song> findSongsByArtistId(@Param("artistId") String artistId);
}
