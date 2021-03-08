package com.vanguard.c2c.social.govern.report.repository;

import com.vanguard.c2c.social.govern.report.domain.ReportTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Title:
 * @Description:
 * @Author: vanguard
 * @Version: 1.0
 * @Date: 2020/10/10
 */
@Repository
public interface ReportRepository extends JpaRepository<ReportTask, Long> {
}
