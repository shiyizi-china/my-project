/*
 Navicat Premium Dump SQL

 Source Server         : our__websql
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : our_websql

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 04/04/2026 08:52:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
                            `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
                            `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发布人',
                            `create_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, '这是一个文章标题', '1112233饶玥是gay张晓强是gay陈雅琪是gay连陪嫁是gay陈柏兆是gay', '匿名用户', '2026-04-04 06:33:40');
INSERT INTO `article` VALUES (2, '测试测试测试', '看到这的都是gay', '匿名用户', '2026-04-04 06:34:48');
INSERT INTO `article` VALUES (3, 'testtest文章标题+1', '现在是2026年4月4日凌晨6：49', '匿名用户', '2026-04-04 06:49:51');
INSERT INTO `article` VALUES (4, '测试nnnn', '按理说应该在登录时通过所输入的username获取name赋值到文章发布者的地方，但测试下来一直是匿名用户，目前可能原因是login方法没有正确的获取到username而是只获取到了token，现在是2026年4月4日6：53', '匿名用户', '2026-04-04 06:53:55');
INSERT INTO `article` VALUES (9, '用户名显示功能实现', '现在是2026年4月4日7：01，成功解决了发布文章是匿名用户的问题，具体原因是在获取username时JWT中的username和数据库中的username不一致，一个是用户名称一个是登录账号，导致错误获取', '饶玥', '2026-04-04 07:05:49');
INSERT INTO `article` VALUES (11, 'test依照文章创建时间进行排序', '把最晚创建的文章放在最上方，现在是2026年4月4日', '饶玥', '2026-04-04 07:23:39');

-- ----------------------------
-- Table structure for barrage
-- ----------------------------
DROP TABLE IF EXISTS `barrage`;
CREATE TABLE `barrage`  (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '弹幕内容',
                            `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发送人',
                            `create_time` datetime NULL DEFAULT NULL COMMENT '发送时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of barrage
-- ----------------------------
INSERT INTO `barrage` VALUES (1, '123', '匿名用户', '2026-04-03 22:32:34');
INSERT INTO `barrage` VALUES (2, '饶玥：test11111111', '饶玥', '2026-04-03 22:42:52');
INSERT INTO `barrage` VALUES (3, '匿名用户：666666', '匿名用户', '2026-04-03 22:44:31');
INSERT INTO `barrage` VALUES (4, '匿名用户：test2222222222', '匿名用户', '2026-04-03 22:45:37');
INSERT INTO `barrage` VALUES (6, '匿名用户：12345shshhs', '匿名用户', '2026-04-03 23:05:23');
INSERT INTO `barrage` VALUES (8, '匿名用户：功能更新', '匿名用户', '2026-04-04 07:06:00');
INSERT INTO `barrage` VALUES (11, '饶玥：test123456', '饶玥', '2026-04-04 07:15:17');

-- ----------------------------
-- Table structure for deity
-- ----------------------------
DROP TABLE IF EXISTS `deity`;
CREATE TABLE `deity`  (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                          `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                          `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                          `gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '未知',
                          `Clazz` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                          `birthday` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                          `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                          `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                          PRIMARY KEY (`id`) USING BTREE,
                          UNIQUE INDEX `username`(`username` ASC) USING BTREE,
                          UNIQUE INDEX `phone`(`phone` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of deity
-- ----------------------------
INSERT INTO `deity` VALUES (1, '饶玥', 'raoyue', '123456', '女', '25软件5班', '2026-04-08', '13900139009', NULL);
INSERT INTO `deity` VALUES (2, '施溢梓', 'shiyizi', '123456', '男', '25软件8班', '2026-04-02', '13900139001', NULL);
INSERT INTO `deity` VALUES (3, '连培佳', 'lianpeijia', '123456', '男', '25软件5班', '2026-04-02', '13900139002', NULL);
INSERT INTO `deity` VALUES (4, '张晓琴', 'zhangxiaoqin', '123456', '女', '25软件8班', '2026-04-02', '13900139003', NULL);
INSERT INTO `deity` VALUES (5, '陈雅琪', 'chenyaqi', '123456', '女', '25软件8班', '2026-04-02', '13900139004', NULL);
INSERT INTO `deity` VALUES (6, '陈柏兆', 'chenbaizhao', '123456', '男', '25软件3班', '2026-04-02', '13900139005', NULL);
INSERT INTO `deity` VALUES (7, '陈兴活', 'chenxinghuo', '123456', '男', '25软件4班', '2026-04-02', '13900139006', NULL);
INSERT INTO `deity` VALUES (8, '汪海君', 'wanghaijun', '123456', '女', '25软件4班', '2026-04-02', '13900139007', NULL);
INSERT INTO `deity` VALUES (9, '陈林彬', 'chenlinbin', '123456', '男', '25软件5班', '2026-04-02', '13900139008', NULL);
INSERT INTO `deity` VALUES (21, '12314', '123', '123', '13123', '434', '2026-04-28', '13274859203', NULL);

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image`  (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `file_url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图片URL',
                          `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of image
-- ----------------------------
INSERT INTO `image` VALUES (2, 'https://home-pageimage.oss-cn-guangzhou.aliyuncs.com/2026/04/124c1327-b708-4dc2-8aff-406cdccc9315.jpg', '2026-04-03 21:47:41');
INSERT INTO `image` VALUES (3, 'https://home-pageimage.oss-cn-guangzhou.aliyuncs.com/2026/04/c58f7720-86de-45d4-853d-4d7beed253af.webp', '2026-04-03 21:56:16');
INSERT INTO `image` VALUES (4, 'https://home-pageimage.oss-cn-guangzhou.aliyuncs.com/2026/04/9a1d4136-83cc-4218-9859-3c65d856221c.png', '2026-04-03 23:03:56');

SET FOREIGN_KEY_CHECKS = 1;
