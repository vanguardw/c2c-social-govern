package com.vanguard.c2c.social.govern.report.service;

import com.vanguard.c2c.social.govern.report.domain.ReportTaskVote;
import com.vanguard.c2c.social.govern.report.repository.ReportTaskVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: 举报任务投票业务
 * @Description:
 * @Author: vanguard
 * @Version: 1.0
 * @Date: 2020/10/19
 */
@Service
public class ReportTaskVoteService {

    @Autowired
    private ReportTaskVoteRepository reportTaskVoteRepository;

    public void initVote(List<Long> reviewers, Long reportTaskId) {
        for(Long reviewerId : reviewers) {
            ReportTaskVote reportTaskVote = new ReportTaskVote();
            reportTaskVote.setReportTaskId(reportTaskId);
            reportTaskVote.setReviewerId(reviewerId);
            reportTaskVote.setVoteResult(ReportTaskVote.UNKNOWN);
            reportTaskVoteRepository.save(reportTaskVote);
        }
    }

    /**
     * 投票操作 更新投票结果
     * @param reportTaskVote
     * @return void
     * @author vanguard
     * @date 20/10/21 22:22
     */
    public void vote(ReportTaskVote reportTaskVote) {
        ReportTaskVote taskVote = reportTaskVoteRepository.findByReportTaskIdAndReviewerId(reportTaskVote.getReportTaskId(), reportTaskVote.getReviewerId());
        taskVote.setVoteResult(reportTaskVote.getVoteResult());
        reportTaskVoteRepository.save(taskVote);
    }

    /**
     * 对举报任务进行归票
     * @param reportTaskId
     * @return void
     * @author Vanguard
     * @date 21/3/8 20:42
     */
    public void calculateVotes(Long reportTaskId) {
        List<ReportTaskVote> reportTaskVoteList = reportTaskVoteRepository.findByReportTaskId(reportTaskId);
        // 大多数的投票数
        Integer quorum = reportTaskVoteList.size() / 2 + 1;

        int approvedVotes = 0;
        int unapprovedVotes = 0;
        for(ReportTaskVote reportTaskVote : reportTaskVoteList) {
            if(ReportTaskVote.APPROVED.equals(reportTaskVote.getVoteResult())) {
                approvedVotes++;
            }
            if(ReportTaskVote.UNAPPROVED.equals(reportTaskVote.getVoteResult())) {
                unapprovedVotes++;
            }
        }
    }
}
