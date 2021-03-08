package com.vanguard.c2c.social.govern.reward.api;

import java.util.List;

/**
 * @Title: 奖励服务接口
 * @Description:
 * @Author: vanguard
 * @Version: 1.0
 * @Date: 2020/10/08
 */
public interface RewardService {

    /**
     * 发放奖励
     * @param reviewerIds
     * @return void
     * @author Vanguard
     * @date 21/3/8 22:07
     */
    void giveReward(List<Long> reviewerIds);
}
