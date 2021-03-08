package com.vanguard.c2c.social.govern.reviewer.repository;

import com.vanguard.c2c.social.govern.reviewer.domain.ReviewerTaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Title: 陪审员状态
 * @Description:
 * @Author: vanguard
 * @Version: 1.0
 * @Date: 2020/10/18
 */
@Repository
public interface ReviewerTaskStatusRepository extends JpaRepository<ReviewerTaskStatus, Long> {
}
