/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : xyb

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 07/04/2022 15:01:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_clock_in_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_clock_in_record`;
CREATE TABLE `tb_clock_in_record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `clock_in_task_id` int NOT NULL,
  `clock_status` int NOT NULL,
  `clock_in_time` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of tb_clock_in_record
-- ----------------------------
BEGIN;
INSERT INTO `tb_clock_in_record` (`id`, `clock_in_task_id`, `clock_status`, `clock_in_time`) VALUES (1, 14, 1, '2022-03-10 11:31:00');
INSERT INTO `tb_clock_in_record` (`id`, `clock_in_task_id`, `clock_status`, `clock_in_time`) VALUES (2, 17, 1, '2022-03-10 13:20:00');
INSERT INTO `tb_clock_in_record` (`id`, `clock_in_task_id`, `clock_status`, `clock_in_time`) VALUES (3, 16, 1, '2022-03-12 10:20:01');
INSERT INTO `tb_clock_in_record` (`id`, `clock_in_task_id`, `clock_status`, `clock_in_time`) VALUES (4, 17, 1, '2022-03-12 10:20:01');
INSERT INTO `tb_clock_in_record` (`id`, `clock_in_task_id`, `clock_status`, `clock_in_time`) VALUES (5, 17, 1, '2022-03-12 10:25:01');
INSERT INTO `tb_clock_in_record` (`id`, `clock_in_task_id`, `clock_status`, `clock_in_time`) VALUES (6, 16, 1, '2022-03-12 10:25:01');
COMMIT;

-- ----------------------------
-- Table structure for tb_clock_in_task
-- ----------------------------
DROP TABLE IF EXISTS `tb_clock_in_task`;
CREATE TABLE `tb_clock_in_task` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `xyb_account_id` int NOT NULL,
  `lat` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lon` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `device_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `trainee_id` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `clock_in_time_cron` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `clock_in_date` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `state` int NOT NULL,
  `create_time` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of tb_clock_in_task
-- ----------------------------
BEGIN;
INSERT INTO `tb_clock_in_task` (`id`, `user_id`, `xyb_account_id`, `lat`, `lon`, `device_name`, `trainee_id`, `address`, `clock_in_time_cron`, `clock_in_date`, `state`, `create_time`) VALUES (16, 1, 1, '24.4788', '118.153733', 'XiaoMi', '7586622', '莲前东路101号嘉盛豪园(莲前东路)', '10:25:00', '', 0, '2022-03-10 11:35:03');
INSERT INTO `tb_clock_in_task` (`id`, `user_id`, `xyb_account_id`, `lat`, `lon`, `device_name`, `trainee_id`, `address`, `clock_in_time_cron`, `clock_in_date`, `state`, `create_time`) VALUES (17, 2, 4, '24.62418', '118.222347', 'XiaoMi', '7641123', '窗东社区', '10:25:00', '', 0, '2022-03-10 13:18:41');
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `state` int NOT NULL,
  `expire_date` timestamp NOT NULL,
  `create_time` timestamp NOT NULL,
  `email` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` (`id`, `username`, `password`, `state`, `expire_date`, `create_time`, `email`) VALUES (1, 'admin', 'admin', 1, '2022-03-09 17:34:14', '2022-03-09 17:34:14', '67250679@qq.com');
INSERT INTO `tb_user` (`id`, `username`, `password`, `state`, `expire_date`, `create_time`, `email`) VALUES (2, '17605054304', '17605054304', 1, '2022-03-10 13:15:42', '2022-03-10 13:15:42', '17605054304@qq.com');
COMMIT;

-- ----------------------------
-- Table structure for tb_user_xyb_account
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_xyb_account`;
CREATE TABLE `tb_user_xyb_account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `xyb_username` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `xyb_password` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `state` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of tb_user_xyb_account
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_xyb_account` (`id`, `user_id`, `xyb_username`, `xyb_password`, `state`) VALUES (1, 1, '13124089198', 'Chq123123', 1);
INSERT INTO `tb_user_xyb_account` (`id`, `user_id`, `xyb_username`, `xyb_password`, `state`) VALUES (4, 2, '17605054304', 'hzq19990601', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
