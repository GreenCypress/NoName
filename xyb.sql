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

 Date: 10/03/2022 13:51:22
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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

SET FOREIGN_KEY_CHECKS = 1;
