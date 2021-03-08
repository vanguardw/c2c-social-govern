package com.vanguard.c2c.social.govern.reward.service;

import com.vanguard.c2c.social.govern.reward.api.RewardService;
import com.vanguard.c2c.social.govern.reward.domain.RewardCoin;
import com.vanguard.c2c.social.govern.reward.repository.RewardCoinRepository;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Title: 奖励服务接口实现
 * @Description:
 * @Author: vanguard
 * @Version: 1.0
 * @Date: 2020/10/08
 */
@Service(
        version = "1.0.0",
        interfaceClass = RewardService.class,
        cluster = "failfast",
        loadbalance = "roundrobin"
)
public class RewardServiceImpl implements RewardService {

    @Autowired
    private RewardCoinRepository rewardCoinRepository;

    @Override
    public void giveReward(List<Long> reviewerIds) {
        for(Long reviewerId : reviewerIds) {
            RewardCoin rewardCoin = new RewardCoin();
            rewardCoin.setReviewerId(reviewerId);
            rewardCoin.setCoins(10L);
            rewardCoinRepository.save(rewardCoin);
        }
    }
}
