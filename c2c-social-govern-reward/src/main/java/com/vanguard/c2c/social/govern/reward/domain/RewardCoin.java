package com.vanguard.c2c.social.govern.reward.domain;

import javax.persistence.*;

/**
 * @Title: 奖励金币
 * @Description:
 * @Author: Vanguard
 * @Version: 1.0
 * @Date: 2021/03/08
 */
@Entity
@Table(name = "t_reward_coin")
public class RewardCoin {

    /**
     * 奖励金币记录id
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /**
     * 评审员id
     */
    @Column(name = "reviewer_id")
    private Long reviewerId;
    /**
     * 奖励金币数量
     */
    @Column(name = "coins")
    private Long coins;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public Long getCoins() {
        return coins;
    }

    public void setCoins(Long coins) {
        this.coins = coins;
    }
}
