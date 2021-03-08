/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50726
Source Host           : 127.0.0.1:3306
Source Database       : c2c_social_govern

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-10-19 20:49:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_report_task
-- ----------------------------
DROP TABLE IF EXISTS `t_report_task`;
CREATE TABLE `t_report_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `report_user_id` bigint(20) DEFAULT NULL,
  `report_content` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `target_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_report_task
-- ----------------------------
INSERT INTO `t_report_task` VALUES ('1', '违规', '1', '发布商品违规', '1');
INSERT INTO `t_report_task` VALUES ('2', '违规', '1', '发布商品违规', '1');

-- ----------------------------
-- Table structure for t_report_task_vote
-- ----------------------------
DROP TABLE IF EXISTS `t_report_task_vote`;
CREATE TABLE `t_report_task_vote` (
  `id` bigint(20) DEFAULT NULL COMMENT '举办任务投票id',
  `report_task_id` bigint(20) DEFAULT NULL COMMENT '举办任务id',
  `reviewer_id` bigint(20) DEFAULT NULL COMMENT '评审员id',
  `vote_result` tinyint(2) DEFAULT NULL COMMENT '投票结果'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_report_task_vote
-- ----------------------------

-- ----------------------------
-- Table structure for t_reviewer_task_status
-- ----------------------------
DROP TABLE IF EXISTS `t_reviewer_task_status`;
CREATE TABLE `t_reviewer_task_status` (
  `id` bigint(20) NOT NULL,
  `report_task_id` bigint(20) DEFAULT NULL,
  `reviewer_id` bigint(20) DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_reviewer_task_status
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '123456');
