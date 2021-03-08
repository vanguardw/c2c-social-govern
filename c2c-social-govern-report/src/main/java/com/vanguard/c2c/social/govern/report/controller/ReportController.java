package com.vanguard.c2c.social.govern.report.controller;

import com.vanguard.c2c.social.govern.report.domain.ReportTask;
import com.vanguard.c2c.social.govern.report.domain.ReportTaskVote;
import com.vanguard.c2c.social.govern.report.service.ReportService;
import com.vanguard.c2c.social.govern.report.service.ReportTaskVoteService;
import com.vanguard.c2c.social.govern.reviewer.api.ReviewerService;
import com.vanguard.c2c.social.govern.reward.api.RewardService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title: 举报服务接口
 * @Description:
 * @Author: vanguard
 * @Version: 1.0
 * @Date: 2020/10/09
 */
@RestController
@RequestMapping("report")
public class ReportController {


    @Reference(version = "1.0.0",
            interfaceClass = ReviewerService.class,
            cluster = "failfast")
    private ReviewerService reviewerService;

    @Reference(version = "1.0.0",
            interfaceClass = RewardService.class,
            cluster = "failfast")
    private RewardService rewardService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportTaskVoteService reportTaskVoteService;

    @PostMapping("")
    public ResponseEntity<Void> report(@RequestBody ReportTask reportTask) {
        // 在本地新增一个评审任务
        reportService.addReport(reportTask);

        // 调用评审员服务，选择一批评审员，并对评审员状态进行初始化
        List<Long> reviewers = reviewerService.selectReviewer(reportTask.getId());

        // 初始化评审员投票任务的投票状态
        reportTaskVoteService.initVote(reviewers, reportTask.getId());

        // 模拟发送PUSH消息给评审员
        System.out.println("模拟发送push消息给评审员.....");

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{reportTaskId}")
    public ReportTask getReportTask(@PathVariable("reportTaskId") Long reportTaskId) {
        return reportService.getReportTask(reportTaskId);
    }

    @PostMapping("/vote")
    public void vote(@RequestBody ReportTaskVote reportTaskVote) {
        // 对举报任务进行投票
        reportTaskVoteService.vote(reportTaskVote);

        // 对举报任务进行归票
        reportTaskVoteService.calculateVotes(reportTaskVote.getReportTaskId());
    }

}
