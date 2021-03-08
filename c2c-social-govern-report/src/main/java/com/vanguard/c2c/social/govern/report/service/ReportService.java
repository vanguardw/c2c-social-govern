package com.vanguard.c2c.social.govern.report.service;

import com.vanguard.c2c.social.govern.report.domain.ReportTask;
import com.vanguard.c2c.social.govern.report.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Title: 举报任务业务
 * @Description:
 * @Author: vanguard
 * @Version: 1.0
 * @Date: 2020/10/10
 */
@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public void addReport(ReportTask reportTask) {
        reportRepository.save(reportTask);
    }

    public ReportTask getReportTask(Long reportTaskId) {
        Optional<ReportTask> reportTaskOptional = reportRepository.findById(reportTaskId);
        return reportTaskOptional.get();
    }
}

