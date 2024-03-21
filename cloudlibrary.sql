/*
 Navicat Premium Data Transfer

 Source Server         : tanxin
 Source Server Type    : MySQL
 Source Server Version : 50714
 Source Host           : localhost:3306
 Source Schema         : bookstore

 Target Server Type    : MySQL
 Target Server Version : 50714
 File Encoding         : 65001

 Date: 14/06/2022 11:01:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `book_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '图书编号',
  `book_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书名称',
  `book_isbn` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书标准ISBN编号',
  `book_press` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书出版社',
  `book_author` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书作者',
  `book_pagination` int(11) NULL DEFAULT NULL COMMENT '图书页数',
  `book_price` double(10, 0) NULL DEFAULT NULL COMMENT '图书价格',
  `book_uploadtime` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书上架时间',
  `book_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '图书状态（0：可借阅，1:已借阅，2：归还中，3：已下架）',
  `book_borrower` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书借阅人',
  `book_borrowtime` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书借阅时间',
  `book_returntime` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书预计归还时间',
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, 'Java基础案例教程（第2版）', '9787115547477', '人民邮电出版社', '传智播客', 291, 59, '2020-12-11', '0', '', '', '');
INSERT INTO `book` VALUES (2, '挪威的森林', '9787546205618', '上海译文出版社', '村上春树', 380, 34, '2020-12-12', '0', NULL, NULL, NULL);
INSERT INTO `book` VALUES (4, 'JavaWeb程序设计任务教程', '9787115439369', '人民邮电出版社', '传智播客', 419, 56, '2020-12-14', '1', '张三', '2022-05-23', '2022-05-25');
INSERT INTO `book` VALUES (5, 'Java基础入门（第2版）', '9787302511410', '清华大学出版社', '传智播客', 413, 59, '2020-12-15', '0', '', '', '');
INSERT INTO `book` VALUES (6, 'SpringCloud微服务架构开发', '9787115529046', '人民邮电出版社', '传智播客', 196, 43, '2020-12-16', '0', NULL, NULL, NULL);
INSERT INTO `book` VALUES (7, 'SpringBoot企业级开发教程', '9787115512796', '人民邮电出版社', '传智播客', 270, 56, '2020-12-17', '0', NULL, NULL, NULL);
INSERT INTO `book` VALUES (8, 'Spark大数据分析与实战', '9787302534327', '清华大学出版社', '传智播客', 228, 49, '2020-12-18', '0', NULL, NULL, NULL);
INSERT INTO `book` VALUES (10, '边城', '9787543067028', '武汉出版社', '沈从文', 368, 26, '2020-12-20', '0', '', '', '');
INSERT INTO `book` VALUES (12, '自在独行', '9787535488473', '长江文艺出版社', '贾平凹', 320, 39, '2020-12-22', '0', NULL, NULL, NULL);
INSERT INTO `book` VALUES (13, '沉默的巡游', '9787544280662', '南海出版公司', '东野圭吾', 400, 59, '2020-12-23', '0', '', '', '');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `record_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '借阅记录id',
  `record_bookname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '借阅的图书名称',
  `record_bookisbn` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '借阅的图书的ISBN编号',
  `record_borrower` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书借阅人',
  `record_borrowtime` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书借阅时间',
  `record_remandtime` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书归还时间',
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `user_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱（用户账号）',
  `user_role` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户角色',
  `user_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户状态（0:正常,1:禁用）',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '管理员', '000000', 'admin', 'ADMIN', '0');
INSERT INTO `user` VALUES (2, '张三', '000000', 'zhangsan', 'USER', '0');

SET FOREIGN_KEY_CHECKS = 1;
