package com.frikiteam.frikievents.users.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserHistoryViewRepository extends JpaRepository<UserHistoryView, String> {
    @Query(value = "SELECT * FROM user_history_view WHERE user_history_id = (SELECT MAX(user_history_id) FROM user_history_view WHERE user_id = :userId)", nativeQuery = true)
    Optional<UserHistoryView> getLastByUserId(String userId);

    @Query(value = "SELECT * FROM user_history_view WHERE user_id = :userId ORDER BY created_at", nativeQuery = true)
    List<UserHistoryView> getHistoryByUserId(String userId);
}