package com.vanguard.c2c.social.govern.reviewer.api;

import java.util.List;

/**
 * @Title: 评审员服务接口
 * @Description:
 * @Author: vanguard
 * @Version: 1.0
 * @Date: 2020/10/08
 */
public interface ReviewerService {

    /**
     * 选择评审员，并初始化评审员状态
     * @param reportTaskId
     * @return java.util.List<java.lang.Long>
     * @author vanguard
     * @date 20/10/18 16:29
     */
    List<Long> selectReviewer(Long reportTaskId);

    /**
     * 调用评审员服务，标记本次投票结束
     * @param reportTaskId 投票任务Id
     * @param reviewerId 陪审员Id
     * @return void
     * @author Vanguard
     * @date 21/3/8 21:49
     */
    void finishVote(Long reportTaskId, Long reviewerId);


}
