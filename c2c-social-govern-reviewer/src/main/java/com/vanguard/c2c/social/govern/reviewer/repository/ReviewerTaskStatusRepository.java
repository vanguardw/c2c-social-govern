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

    /**
     * 根据任务Id和陪审员Id查询陪审员装填
     * @param reportTaskId, reviewerId
     * @return com.vanguard.c2c.social.govern.reviewer.domain.ReviewerTaskStatus
     * @author Vanguard
     * @date 21/3/8 21:54
     */
    ReviewerTaskStatus findByReportTaskIdAndReviewerId(Long reportTaskId, Long reviewerId);
}
