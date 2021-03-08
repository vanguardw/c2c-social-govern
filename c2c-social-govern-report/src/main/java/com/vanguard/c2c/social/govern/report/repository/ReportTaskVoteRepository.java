package com.vanguard.c2c.social.govern.report.repository;

import com.vanguard.c2c.social.govern.report.domain.ReportTaskVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Title:
 * @Description:
 * @Author: vanguard
 * @Version: 1.0
 * @Date: 2020/10/19
 */
@Repository
public interface ReportTaskVoteRepository extends JpaRepository<ReportTaskVote, Long> {

    /**
     * 根据举报任务Id评审员Id查询举报任务投票信息
     * @param reportTaskId
     * @param reviewerId
     * @return com.vanguard.c2c.social.govern.report.domain.ReportTaskVote
     * @author vanguard
     * @date 20/10/21 22:20
     */
    ReportTaskVote findByReportTaskIdAndReviewerId(Long reportTaskId, Long reviewerId);

    /**
     * 查询举报任务所有的投票
     * @param reportTaskId
     * @return java.util.List<com.vanguard.c2c.social.govern.report.domain.ReportTaskVote>
     * @author Vanguard
     * @date 21/3/8 20:40
     */
    List<ReportTaskVote> findByReportTaskId(Long reportTaskId);

}
