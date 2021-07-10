/*
 Navicat MySQL Data Transfer

 Source Server         : project
 Source Server Type    : MySQL
 Source Server Version : 50515
 Source Host           : localhost:3306
 Source Schema         : project

 Target Server Type    : MySQL
 Target Server Version : 50515
 File Encoding         : 65001

 Date: 10/07/2021 22:06:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'll', '123', '2021-07-01 23:55:24');
INSERT INTO `admin` VALUES (2, 'lilong', '123456', NULL);
INSERT INTO `admin` VALUES (3, 'admin', 'admin', NULL);
INSERT INTO `admin` VALUES (4, '', '', '2021-07-06 00:00:00');

-- ----------------------------
-- Table structure for categorys
-- ----------------------------
DROP TABLE IF EXISTS `categorys`;
CREATE TABLE `categorys`  (
  `categoryid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `category_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `category_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`categoryid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of categorys
-- ----------------------------
INSERT INTO `categorys` VALUES ('1001', '床', NULL);
INSERT INTO `categorys` VALUES ('1002', '桌', NULL);
INSERT INTO `categorys` VALUES ('1003', '椅', NULL);
INSERT INTO `categorys` VALUES ('1004', '衣柜', NULL);
INSERT INTO `categorys` VALUES ('1005', '沙发', NULL);

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `productid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `productname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `income_price` decimal(10, 2) NULL DEFAULT NULL,
  `providerid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `quantity` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sales_price` decimal(10, 2) NULL DEFAULT NULL,
  `categoryid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `income_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`productid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES ('2001', '双人床', 1500.00, '0001', '5', 2000.00, '1001', NULL);
INSERT INTO `products` VALUES ('2002', '单人床', 1000.00, '0001', '10', 1200.00, '1001', NULL);
INSERT INTO `products` VALUES ('2003', '真皮沙发', 1800.00, '0002', '5', 1900.00, '1005', NULL);
INSERT INTO `products` VALUES ('2004', '全实木柜', 800.00, '0003', '9', 1300.00, '1004', NULL);

-- ----------------------------
-- Table structure for providers
-- ----------------------------
DROP TABLE IF EXISTS `providers`;
CREATE TABLE `providers`  (
  `providerid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `provider_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `provider_add` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `provider_tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`providerid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of providers
-- ----------------------------
INSERT INTO `providers` VALUES ('0001', '全友家私品牌', '北京', '123456', '100', NULL);
INSERT INTO `providers` VALUES ('0002', '宜家家居', '上海', '456789', '200', NULL);
INSERT INTO `providers` VALUES ('0003', '曲美家具', '武汉', '123789', '300', NULL);
INSERT INTO `providers` VALUES ('0004', '红苹果家具', '广州', '147258', '400', NULL);
INSERT INTO `providers` VALUES ('0005', '皇朝家私', '深圳', '258147', '500', NULL);
INSERT INTO `providers` VALUES ('0006', '联邦家私', '杭州', '258369', '600', NULL);
INSERT INTO `providers` VALUES ('0007', '美克美家', '重庆', '258456', '700', NULL);

SET FOREIGN_KEY_CHECKS = 1;
