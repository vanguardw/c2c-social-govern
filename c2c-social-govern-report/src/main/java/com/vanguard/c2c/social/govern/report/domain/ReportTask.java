package com.vanguard.c2c.social.govern.report.domain;

import javax.persistence.*;

/**
 * @Title: 举报任务
 * @Description:
 * @Author: vanguard
 * @Version: 1.0
 * @Date: 2020/10/09
 */
@Entity
@Table(name = "t_report_task")
public class ReportTask {

    public static final Integer VOTE_RESULT_UNKNOW = -1;

    public static final Integer VOTE_RESULT_APPROVED = 1;

    public static final Integer VOTE_RESULT_UNAPPROVED = 0;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String type;

    @Column(name = "report_user_id")
    private Long reportUserId;

    @Column(name = "report_content")
    private String reportContent;

    @Column(name = "target_id")
    private Long targetId;

    @Column(name = "vote_result")
    private Integer voteResult;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getReportUserId() {
        return reportUserId;
    }

    public void setReportUserId(Long reportUserId) {
        this.reportUserId = reportUserId;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Integer getVoteResult() {
        return voteResult;
    }

    public void setVoteResult(Integer voteResult) {
        this.voteResult = voteResult;
    }
}
