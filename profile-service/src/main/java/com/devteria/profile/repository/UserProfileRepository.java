package com.devteria.profile.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.devteria.profile.entity.UserProfile;

@Repository
public interface UserProfileRepository extends Neo4jRepository<UserProfile, String> {
    Optional<UserProfile> findByUserId(String userId);

    @Query("MATCH (u:UserProfile {userId: $userId}), (t:UserProfile {userId: $targetUserId}) "
            + "MERGE (u)-[:FOLLOWS]->(t)")
    void followUser(String userId, String targetUserId);

    @Query("MATCH (u:UserProfile {userId: $userId})-[r:FOLLOWS]->(t:UserProfile {userId: $targetUserId}) " + "DELETE r")
    void unfollowUser(String userId, String targetUserId);

    @Query("MATCH (u:UserProfile {userId: $userId}), (t:UserProfile {userId: $targetUserId}) "
            + "OPTIONAL MATCH (u)-[r:FOLLOWS]->(t) "
            + "WITH u, t, r "
            + "FOREACH (_ IN CASE WHEN r IS NULL THEN [1] ELSE [] END | CREATE (u)-[:FOLLOWS]->(t)) "
            + "FOREACH (_ IN CASE WHEN r IS NOT NULL THEN [1] ELSE [] END | DELETE r) "
            + "RETURN r IS NULL")
    Boolean followUserOrUnfollowUser(String userId, String targetUserId);
}
