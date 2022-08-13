package com.vanguard.c2c.social.govern.report.service;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.vanguard.c2c.social.govern.report.domain.ReportTask;
import com.vanguard.c2c.social.govern.report.repository.ReportRepository;
import com.vanguard.c2c.social.govern.reviewer.api.ReviewerService;
import com.vanguard.c2c.social.govern.reward.api.RewardService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @DubboReference(version = "1.0.0",
            interfaceClass = RewardService.class,
            cluster = "failfast")
    private RewardService rewardService;

    @DubboReference(version = "1.0.0",
            interfaceClass = ReviewerService.class,
            cluster = "failfast")
    private ReviewerService reviewerService;

    static {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        rule.setResource("ReviewerServiceResource");
        // 慢调用比例模式下为慢调用临界 RT（超出该值计为慢调用）；异常比例/异常数模式下为对应的阈值
        rule.setCount(200);
        // 慢调用比例阈值，仅慢调用比例模式有效（1.8.0 引入）
        rule.setSlowRatioThreshold(0.5);
        // 熔断策略，支持慢调用比例/异常比例/异常数策略
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        // 熔断时长，单位为 s
        rule.setTimeWindow(10);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }

    @Autowired
    private ReportTaskVoteService reportTaskVoteService;

    @GlobalTransactional
    public void addReport(ReportTask reportTask) {
        reportTask.setVoteResult(ReportTask.VOTE_RESULT_UNKNOW);
        reportRepository.save(reportTask);

        List<Long> reviewers = null;
        Entry entry = null;
        // 务必保证 finally 会被执行
        try {
            // 资源名可使用任意有业务语义的字符串，注意数目不能太多（超过 1K），超出几千请作为参数传入而不要直接作为资源名
            // EntryType 代表流量类型（inbound/outbound），其中系统规则只对 IN 类型的埋点生效
            entry = SphU.entry("ReviewerServiceResource");
            // 被保护的业务逻辑
            // 调用评审员服务，选择一批评审员，并对评审员状态进行初始化
            reviewers = reviewerService.selectReviewer(reportTask.getId());
        } catch (BlockException ex) {
            // 资源访问阻止，被限流或被降级
            // 进行相应的处理操作
        } catch (Exception ex) {
            // 若需要配置降级规则，需要通过这种方式记录业务异常
            Tracer.traceEntry(ex, entry);
        } finally {
            // 务必保证 exit，务必保证每个 entry 与 exit 配对
            if (entry != null) {
                entry.exit();
            }
        }

        // 初始化评审员投票任务的投票状态
        reportTaskVoteService.initVote(reviewers, reportTask.getId());

        // 模拟发送PUSH消息给评审员
        System.out.println("模拟发送push消息给评审员.....");
    }

    public ReportTask getReportTask(Long reportTaskId) {
        Optional<ReportTask> reportTaskOptional = reportRepository.findById(reportTaskId);
        return reportTaskOptional.get();
    }
}

