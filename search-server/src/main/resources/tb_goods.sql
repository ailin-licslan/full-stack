/*
 Navicat Premium Data Transfer

 Source Server         : local-databases
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:3306
 Source Schema         : licslan

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 11/11/2023 15:34:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods`  (
  `id` bigint NOT NULL,
  `goods_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `goods_price` decimal(10, 2) NULL DEFAULT NULL,
  `goods_title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `goods_shop` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `goods_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `del_flag` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_goods
-- ----------------------------
INSERT INTO `tb_goods` VALUES (1, 'https://img14.360buyimg.com/img/s260x260_jfs/t1/208153/6/33540/46531/65025f46Fc30d070b/d409cb3f1a4e10ea.jpg!cc_130x130.avif', 23.50, '手机xxxxxxxxxxxxxxxxxxxhfasfhsjfsfsafadddddddddddddddddddddddddddddddddddddddddddddddddddddddddf', '淘宝', '淘宝旗舰店', '2023-11-10 15:38:40', '2023-11-10 15:38:50', b'0');
INSERT INTO `tb_goods` VALUES (2, 'https://img14.360buyimg.com/img/s260x260_jfs/t1/208153/6/33540/46531/65025f46Fc30d070b/d409cb3f1a4e10ea.jpg!cc_130x130.avif', 222.00, '手机', '京东', '京东旗舰店', '2023-11-10 20:22:57', '2023-11-10 20:22:54', b'0');
INSERT INTO `tb_goods` VALUES (3, 'https://img14.360buyimg.com/img/s260x260_jfs/t1/208153/6/33540/46531/65025f46Fc30d070b/d409cb3f1a4e10ea.jpg!cc_130x130.avif', 10.00, '手机', '京东', '京东旗舰店', '2023-11-10 20:23:00', '2023-11-10 20:23:03', b'0');
INSERT INTO `tb_goods` VALUES (4, 'https://img14.360buyimg.com/img/s260x260_jfs/t1/208153/6/33540/46531/65025f46Fc30d070b/d409cb3f1a4e10ea.jpg!cc_130x130.avif', 11.00, '手机', '拼多多', '拼多多旗舰店', NULL, NULL, NULL);
INSERT INTO `tb_goods` VALUES (5, 'https://img14.360buyimg.com/img/s260x260_jfs/t1/188216/38/31078/70570/63af8fecFacfc35e9/90e7589125add7ab.jpg!cc_130x130.avif', 122.00, '酱油00', '拼多多', '拼多多旗舰店', NULL, NULL, NULL);
INSERT INTO `tb_goods` VALUES (6, 'https://img14.360buyimg.com/img/s260x260_jfs/t1/188216/38/31078/70570/63af8fecFacfc35e9/90e7589125add7ab.jpg!cc_130x130.avif', 122.00, '酱油01', '京东', '京东旗舰店', NULL, NULL, NULL);
INSERT INTO `tb_goods` VALUES (7, 'https://img14.360buyimg.com/img/s260x260_jfs/t1/188216/38/31078/70570/63af8fecFacfc35e9/90e7589125add7ab.jpg!cc_130x130.avif', 122.00, '酱油02', '拼多多', '拼多多旗舰店', NULL, NULL, NULL);
INSERT INTO `tb_goods` VALUES (8, 'https://img14.360buyimg.com/img/s260x260_jfs/t1/188216/38/31078/70570/63af8fecFacfc35e9/90e7589125add7ab.jpg!cc_130x130.avif', 122.00, '酱油03', '拼多多', '拼多多旗舰店', NULL, NULL, NULL);
INSERT INTO `tb_goods` VALUES (9, 'https://img14.360buyimg.com/img/s260x260_jfs/t1/188216/38/31078/70570/63af8fecFacfc35e9/90e7589125add7ab.jpg!cc_130x130.avif', 122.00, '酱油04', '京东', '京东旗舰店', NULL, NULL, NULL);
INSERT INTO `tb_goods` VALUES (10, 'https://img14.360buyimg.com/img/s260x260_jfs/t1/188216/38/31078/70570/63af8fecFacfc35e9/90e7589125add7ab.jpg!cc_130x130.avif', 122.00, '酱油05', '拼多多', '拼多多旗舰店', NULL, NULL, NULL);
INSERT INTO `tb_goods` VALUES (11, 'https://img14.360buyimg.com/img/s260x260_jfs/t1/188216/38/31078/70570/63af8fecFacfc35e9/90e7589125add7ab.jpg!cc_130x130.avif', 122.00, '酱油06', '拼多多', '拼多多旗舰店', NULL, NULL, NULL);
INSERT INTO `tb_goods` VALUES (12, 'https://img14.360buyimg.com/img/s260x260_jfs/t1/188216/38/31078/70570/63af8fecFacfc35e9/90e7589125add7ab.jpg!cc_130x130.avif', 122.00, '酱油07', '拼多多', '拼多多旗舰店', NULL, NULL, NULL);
INSERT INTO `tb_goods` VALUES (13, 'https://img14.360buyimg.com/img/s260x260_jfs/t1/188216/38/31078/70570/63af8fecFacfc35e9/90e7589125add7ab.jpg!cc_130x130.avif', 122.00, '酱油08', '拼多多', '拼多多旗舰店', NULL, NULL, NULL);
INSERT INTO `tb_goods` VALUES (14, 'https://img14.360buyimg.com/img/s260x260_jfs/t1/188216/38/31078/70570/63af8fecFacfc35e9/90e7589125add7ab.jpg!cc_130x130.avif', 122.00, '酱油09', '拼多多', '拼多多旗舰店', NULL, NULL, NULL);
INSERT INTO `tb_goods` VALUES (15, 'https://img14.360buyimg.com/img/s260x260_jfs/t1/188216/38/31078/70570/63af8fecFacfc35e9/90e7589125add7ab.jpg!cc_130x130.avif', 122.00, '酱油10', '拼多多', '拼多多旗舰店', NULL, NULL, NULL);
INSERT INTO `tb_goods` VALUES (16, 'https://img14.360buyimg.com/img/s260x260_jfs/t1/188216/38/31078/70570/63af8fecFacfc35e9/90e7589125add7ab.jpg!cc_130x130.avif', 122.00, '酱油11', '拼多多', '拼多多旗舰店', NULL, NULL, NULL);
INSERT INTO `tb_goods` VALUES (17, 'https://img14.360buyimg.com/img/s260x260_jfs/t1/188216/38/31078/70570/63af8fecFacfc35e9/90e7589125add7ab.jpg!cc_130x130.avif', 122.00, '酱油12', '拼多多', '拼多多旗舰店', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
