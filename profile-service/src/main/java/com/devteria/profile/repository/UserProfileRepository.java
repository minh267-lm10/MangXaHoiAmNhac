package com.devteria.profile.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.devteria.profile.entity.UserProfile;

@Repository
public interface UserProfileRepository extends Neo4jRepository<UserProfile, String> {

    /**
     * Tìm UserProfile bằng userId.
     *
     * @param userId User ID của người dùng.
     * @return Optional chứa UserProfile nếu tìm thấy.
     */
    Optional<UserProfile> findByUserId(String userId);

    /**
     * Thực hiện theo dõi hoặc hủy theo dõi giữa hai người dùng.
     * Nếu người dùng đang theo dõi, hủy theo dõi sẽ diễn ra; ngược lại sẽ tạo theo dõi mới.
     *
     * @param userId        ID của người dùng chính.
     * @param targetUserId  ID của người dùng mục tiêu.
     * @return true nếu quan hệ được tạo, false nếu quan hệ đã xóa.
     */
    @Query("MATCH (u:UserProfile {userId: $userId}), (t:UserProfile {userId: $targetUserId}) "
            + "OPTIONAL MATCH (u)-[r:FOLLOWS]->(t) "
            + "WITH u, t, r "
            + "FOREACH (_ IN CASE WHEN r IS NULL THEN [1] ELSE [] END | CREATE (u)-[:FOLLOWS]->(t)) "
            + "FOREACH (_ IN CASE WHEN r IS NOT NULL THEN [1] ELSE [] END | DELETE r) "
            + "RETURN r IS NULL")
    Boolean followUserOrUnfollowUser(String userId, String targetUserId);
    /**
     * Kiểm tra xem người dùng hiện tại có đang theo dõi người dùng mục tiêu không.
     *
     * @param userId        ID của người dùng hiện tại.
     * @param targetUserId  ID của người dùng mục tiêu.
     * @return true nếu đang theo dõi, false nếu không.
     */
    @Query("MATCH (u:UserProfile {userId: $userId})-[:FOLLOWS]->(t:UserProfile {userId: $targetUserId}) "
            + "RETURN COUNT(t) > 0")
    Boolean isFollowing(String userId, String targetUserId);

    Page<UserProfile> findByStageNameContaining(String StageName, Pageable pageable);
}
