package com.vanguard.c2c.social.govern.reward.service;

import com.vanguard.c2c.social.govern.reward.api.RewardService;
import org.apache.dubbo.config.annotation.Service;

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


}
