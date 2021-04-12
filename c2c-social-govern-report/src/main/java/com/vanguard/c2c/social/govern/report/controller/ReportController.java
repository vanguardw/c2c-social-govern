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

import java.util.ArrayList;
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

    @Autowired
    private ReportService reportService;


    @Autowired
    private ReportTaskVoteService reportTaskVoteService;

    @PostMapping("")
    public ResponseEntity<Void> report(@RequestBody ReportTask reportTask) {
        // 在本地新增一个评审任务
        reportService.addReport(reportTask);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{reportTaskId}")
    public ReportTask getReportTask(@PathVariable("reportTaskId") Long reportTaskId) {
        return reportService.getReportTask(reportTaskId);
    }

    @PostMapping("/vote")
    public ResponseEntity<Void> vote(@RequestBody ReportTaskVote reportTaskVote) {
        // 对举报任务进行投票
        reportTaskVoteService.vote(reportTaskVote);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

}
