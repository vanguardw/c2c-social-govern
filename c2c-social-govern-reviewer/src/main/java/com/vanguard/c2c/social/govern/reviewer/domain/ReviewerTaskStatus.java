package com.vanguard.c2c.social.govern.reviewer.domain;

import javax.persistence.*;

/**
 * @Title:
 * @Description:
 * @Author: vanguard
 * @Version: 1.0
 * @Date: 2020/10/18
 */
@Entity
@Table(name = "t_reviewer_task_status")
public class ReviewerTaskStatus {

    /**
     * 处理中
     */
    public final static Integer PROCESSING = 1;

    /**
     * 完成
     */
    public final static Integer FINISHED = 2;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_task_id")
    private Long reportTaskId;

    @Column(name = "reviewer_id")
    private Long reviewerId;

    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
