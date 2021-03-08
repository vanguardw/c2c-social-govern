package com.vanguard.c2c.social.govern.reviewer.service;

import com.vanguard.c2c.social.govern.reviewer.api.ReviewerService;
import com.vanguard.c2c.social.govern.reviewer.domain.ReviewerTaskStatus;
import com.vanguard.c2c.social.govern.reviewer.repository.ReviewerTaskStatusRepository;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title:
 * @Description:
 * @Author: vanguard
 * @Version: 1.0
 * @Date: 2020/10/08
 */
@Service(
        version = "1.0.0",
        interfaceClass = ReviewerService.class,
        cluster = "failfast",
        loadbalance = "roundrobin"
)
public class ReviewerServiceImpl implements ReviewerService {

    @Autowired
    private ReviewerTaskStatusRepository reviewerTaskStatusRepository;

    @Override
    public List<Long> selectReviewer(Long reportTaskId) {

        // 模拟通过算法选择评审员
        List<Long> reviewers = new ArrayList<>();
        reviewers.add(1L);
        reviewers.add(2L);
        reviewers.add(3L);
        reviewers.add(4L);
        reviewers.add(5L);

        // 初始化评审员状态
        for(Long reviewerId : reviewers) {
            ReviewerTaskStatus reviewerTaskStatus = new ReviewerTaskStatus();
            reviewerTaskStatus.setReportTaskId(reportTaskId);
            reviewerTaskStatus.setReviewerId(reviewerId);
            reviewerTaskStatus.setStatus(ReviewerTaskStatus.PROCESSING);
            reviewerTaskStatusRepository.save(reviewerTaskStatus);
        }
        return reviewers;

    }

    @Override
    public void finishVote(Long reportTaskId, Long reviewerId) {
        ReviewerTaskStatus reviewerTaskStatus = reviewerTaskStatusRepository.findByReportTaskIdAndReviewerId(reportTaskId, reviewerId);
        reviewerTaskStatus.setStatus(ReviewerTaskStatus.FINISHED);
        reviewerTaskStatusRepository.save(reviewerTaskStatus);
    }
}
