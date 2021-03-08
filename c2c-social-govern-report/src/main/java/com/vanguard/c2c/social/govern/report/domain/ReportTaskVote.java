package com.vanguard.c2c.social.govern.report.domain;

import javax.persistence.*;

/**
 * @Title: 举报任务投票
 * @Description:
 * @Author: vanguard
 * @Version: 1.0
 * @Date: 2020/10/19
 */
@Entity
@Table(name = "t_report_task_vote")
public class ReportTaskVote {

    /**
     * 未投票
     */
    public static final Integer UNKNOWN = -1;
    /**
     * 投票通过
     */
    public static final Integer APPROVED = 1;
    /**
     * 投票未通过
     */
    public static final Integer UNAPPROVED = 2;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_task_id")
    private Long reportTaskId;

    @Column(name = "reviewer_id")
    private Long reviewerId;

    @Column(name = "vote_result")
    private Integer voteResult;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReportTaskId() {
        return reportTaskId;
    }

    public void setReportTaskId(Long reportTaskId) {
        this.reportTaskId = reportTaskId;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public Integer getVoteResult() {
        return voteResult;
    }

    public void setVoteResult(Integer voteResult) {
        this.voteResult = voteResult;
    }
}
