/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : spring-vue

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 25/06/2022 14:32:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '出版时间',
  `u_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (6, '风华血月', 333.00, '特务兔', '2022-06-23 00:00:00', NULL, 'http://localhost:8099/houcheng/file/009d2afa-08ee-418e-862f-953eb29b4cfa');
INSERT INTO `book` VALUES (7, '始皇传记', 2342.00, '特务兔', '2022-06-13 00:00:00', NULL, 'http://localhost:8099/houcheng/file/0d7a9c40-9b1b-4625-aca9-2860c028fc85');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (2, '是谁？', '', '特务兔', '2022-06-06 15:29:53');
INSERT INTO `news` VALUES (4, '杀猪刀', '<p>百年杀猪！</p>', '特务兔', '2022-06-06 16:38:10');
INSERT INTO `news` VALUES (5, '代码', '<p>里面有代码！</p>', '特务兔', '2022-06-06 16:43:13');
INSERT INTO `news` VALUES (6, '冰封', '<p>千里冰封，<img src=\"http://localhost:8099/file/e9b62f41-baa0-4d80-9837-6f05c7d945b1\" contenteditable=\"false\" style=\"color: var(--el-text-color-regular); font-size: var(--el-dialog-content-font-size); max-width: 100%;\"/></p>', '特务兔', '2022-06-06 17:05:41');
INSERT INTO `news` VALUES (7, '图片', '', '特务兔', '2022-06-06 18:11:53');
INSERT INTO `news` VALUES (8, '飞雪起源', '<p><img src=\"http://localhost:8099/file/0b1a5e45-4b47-49a1-8092-c54c7a8cb290\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '特务兔', '2022-06-07 17:29:50');
INSERT INTO `news` VALUES (9, '天上人间', '<p><img src=\"http://localhost:8099/houcheng/file/9acf1eac-1b06-4ea0-960e-8dea5aa3462e\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '特务兔', '2022-06-18 18:27:38');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `role` int(15) NULL DEFAULT NULL COMMENT '角色',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住址',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'tu', 'e10adc3949ba59abbe56e057f20f883e', '特务兔', 17, '男', 1, '地球', 'http://localhost:8099/houcheng/file/51ce7079-18cc-45dc-b67c-4a1bd0035de3');
INSERT INTO `user` VALUES (2, '小兔', 'e10adc3949ba59abbe56e057f20f883e', '特务兔', 18, '女', 2, '地球', NULL);
INSERT INTO `user` VALUES (3, 'test', 'e10adc3949ba59abbe56e057f20f883e', 'test', 17, '未知', 2, '未知', NULL);

SET FOREIGN_KEY_CHECKS = 1;
