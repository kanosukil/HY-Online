/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : hy-online-be

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 27/12/2021 22:06:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` int(0) NOT NULL,
  `total_price` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `cart_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (1, 20);
INSERT INTO `cart` VALUES (2, 100);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(0) NOT NULL,
  `content` varchar(500) CHARACTER SET gbk COLLATE gbk_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `comment_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, '很棒!');
INSERT INTO `comment` VALUES (2, 'suki');
INSERT INTO `comment` VALUES (3, 'Well');
INSERT INTO `comment` VALUES (4, 'xxxx');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(0) NOT NULL,
  `name` varchar(40) CHARACTER SET gbk COLLATE gbk_bin NULL DEFAULT NULL,
  `img` varchar(100) CHARACTER SET gbk COLLATE gbk_bin NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `description` varchar(200) CHARACTER SET gbk COLLATE gbk_bin NULL DEFAULT NULL,
  `number` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `goods_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '商品', 'default', 7.14, '一件商品', NULL);
INSERT INTO `goods` VALUES (2, 'xxx', 'default', 2.2, 'xxx', 1);
INSERT INTO `goods` VALUES (4, 'xxx', 'default', 2.2, 'xxxxx', 1);

-- ----------------------------
-- Table structure for goods_cart
-- ----------------------------
DROP TABLE IF EXISTS `goods_cart`;
CREATE TABLE `goods_cart`  (
  `goods_key` int(0) NULL DEFAULT NULL,
  `cart_key` int(0) NULL DEFAULT NULL,
  INDEX `goods_key`(`goods_key`) USING BTREE,
  INDEX `goods_cart_ibfk_2`(`cart_key`) USING BTREE,
  CONSTRAINT `goods_cart_ibfk_1` FOREIGN KEY (`goods_key`) REFERENCES `goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `goods_cart_ibfk_2` FOREIGN KEY (`cart_key`) REFERENCES `cart` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_cart
-- ----------------------------
INSERT INTO `goods_cart` VALUES (2, 2);
INSERT INTO `goods_cart` VALUES (1, 2);

-- ----------------------------
-- Table structure for goods_comment
-- ----------------------------
DROP TABLE IF EXISTS `goods_comment`;
CREATE TABLE `goods_comment`  (
  `goods_key` int(0) NULL DEFAULT NULL,
  `comment_key` int(0) NULL DEFAULT NULL,
  INDEX `goods_comment_goods_id_fk`(`goods_key`) USING BTREE,
  CONSTRAINT `goods_comment_comment_id_fk` FOREIGN KEY (`goods_key`) REFERENCES `comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `goods_comment_goods_id_fk` FOREIGN KEY (`goods_key`) REFERENCES `goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_comment
-- ----------------------------
INSERT INTO `goods_comment` VALUES (1, 1);
INSERT INTO `goods_comment` VALUES (1, 1);
INSERT INTO `goods_comment` VALUES (1, 1);
INSERT INTO `goods_comment` VALUES (1, 3);
INSERT INTO `goods_comment` VALUES (1, 4);

-- ----------------------------
-- Table structure for goods_order
-- ----------------------------
DROP TABLE IF EXISTS `goods_order`;
CREATE TABLE `goods_order`  (
  `goods_key` int(0) NULL DEFAULT NULL,
  `order_key` int(0) NULL DEFAULT NULL,
  INDEX `goods_order_ibfk_1`(`goods_key`) USING BTREE,
  INDEX `goods_order_ibfk_2`(`order_key`) USING BTREE,
  CONSTRAINT `goods_order_ibfk_1` FOREIGN KEY (`goods_key`) REFERENCES `goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `goods_order_ibfk_2` FOREIGN KEY (`order_key`) REFERENCES `orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_order
-- ----------------------------
INSERT INTO `goods_order` VALUES (1, 1);
INSERT INTO `goods_order` VALUES (2, 2);
INSERT INTO `goods_order` VALUES (1, 3);
INSERT INTO `goods_order` VALUES (2, 4);
INSERT INTO `goods_order` VALUES (2, 5);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(0) NOT NULL,
  `number` int(0) NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET gbk COLLATE gbk_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 1, '南昌大学前湖校区天健园');
INSERT INTO `orders` VALUES (2, 10, '南昌大学青山湖校区');
INSERT INTO `orders` VALUES (3, 1, 'default');
INSERT INTO `orders` VALUES (4, 1, 'default');
INSERT INTO `orders` VALUES (5, 1, 'default');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(0) NOT NULL,
  `rank` varchar(10) CHARACTER SET gbk COLLATE gbk_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'USER');
INSERT INTO `role` VALUES (2, 'MASTER');
INSERT INTO `role` VALUES (3, 'ADMIN');

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `id` int(0) NOT NULL,
  `name` varchar(40) CHARACTER SET gbk COLLATE gbk_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `store_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES (1, '一间商店');
INSERT INTO `store` VALUES (2, 'JavaLearn');
INSERT INTO `store` VALUES (3, NULL);

-- ----------------------------
-- Table structure for store_goods
-- ----------------------------
DROP TABLE IF EXISTS `store_goods`;
CREATE TABLE `store_goods`  (
  `store_key` int(0) NULL DEFAULT NULL,
  `goods_key` int(0) NULL DEFAULT NULL,
  INDEX `store_key`(`store_key`) USING BTREE,
  INDEX `goods_key`(`goods_key`) USING BTREE,
  CONSTRAINT `store_goods_ibfk_1` FOREIGN KEY (`store_key`) REFERENCES `store` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `store_goods_ibfk_2` FOREIGN KEY (`goods_key`) REFERENCES `goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store_goods
-- ----------------------------
INSERT INTO `store_goods` VALUES (1, 1);
INSERT INTO `store_goods` VALUES (2, 2);
INSERT INTO `store_goods` VALUES (2, 4);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL,
  `username` varchar(20) CHARACTER SET gbk COLLATE gbk_bin NULL DEFAULT NULL,
  `password_hash` varchar(100) CHARACTER SET gbk COLLATE gbk_bin NULL DEFAULT NULL,
  `head_portrait` varchar(100) CHARACTER SET gbk COLLATE gbk_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'VHBin', 'eHh4eA==', 'default');
INSERT INTO `user` VALUES (2, '丿丶Sana', 'OTE3MDkyMDA3', 'default');
INSERT INTO `user` VALUES (3, 'admin', 'MTIzNDU2', 'default');
INSERT INTO `user` VALUES (4, 'lll', 'MTAyMDlzZGFk', 'default');

-- ----------------------------
-- Table structure for user_cart
-- ----------------------------
DROP TABLE IF EXISTS `user_cart`;
CREATE TABLE `user_cart`  (
  `user_key` int(0) NULL DEFAULT NULL,
  `cart_key` int(0) NULL DEFAULT NULL,
  INDEX `user_key`(`user_key`) USING BTREE,
  INDEX `user_cart_ibfk_2`(`cart_key`) USING BTREE,
  CONSTRAINT `user_cart_ibfk_1` FOREIGN KEY (`user_key`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_cart_ibfk_2` FOREIGN KEY (`cart_key`) REFERENCES `cart` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_cart
-- ----------------------------
INSERT INTO `user_cart` VALUES (1, 1);
INSERT INTO `user_cart` VALUES (2, 2);

-- ----------------------------
-- Table structure for user_comment
-- ----------------------------
DROP TABLE IF EXISTS `user_comment`;
CREATE TABLE `user_comment`  (
  `user_key` int(0) NULL DEFAULT NULL,
  `comment_key` int(0) NULL DEFAULT NULL,
  INDEX `user_key`(`user_key`) USING BTREE,
  INDEX `comment_key`(`comment_key`) USING BTREE,
  CONSTRAINT `user_comment_ibfk_1` FOREIGN KEY (`user_key`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_comment_ibfk_2` FOREIGN KEY (`comment_key`) REFERENCES `comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_comment
-- ----------------------------
INSERT INTO `user_comment` VALUES (1, 1);
INSERT INTO `user_comment` VALUES (2, 2);
INSERT INTO `user_comment` VALUES (1, 3);
INSERT INTO `user_comment` VALUES (1, 4);

-- ----------------------------
-- Table structure for user_order
-- ----------------------------
DROP TABLE IF EXISTS `user_order`;
CREATE TABLE `user_order`  (
  `customer_key` int(0) NULL DEFAULT NULL,
  `order_key` int(0) NULL DEFAULT NULL,
  INDEX `customer_key`(`customer_key`) USING BTREE,
  INDEX `order_key`(`order_key`) USING BTREE,
  CONSTRAINT `user_order_ibfk_1` FOREIGN KEY (`customer_key`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_order_ibfk_2` FOREIGN KEY (`order_key`) REFERENCES `orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_order
-- ----------------------------
INSERT INTO `user_order` VALUES (1, 1);
INSERT INTO `user_order` VALUES (2, 2);
INSERT INTO `user_order` VALUES (1, 3);
INSERT INTO `user_order` VALUES (1, 4);
INSERT INTO `user_order` VALUES (1, 5);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_key` int(0) NULL DEFAULT NULL,
  `role_key` int(0) NULL DEFAULT NULL,
  INDEX `user_key`(`user_key`) USING BTREE,
  INDEX `role_key`(`role_key`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_key`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_key`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (2, 2);
INSERT INTO `user_role` VALUES (3, 3);
INSERT INTO `user_role` VALUES (1, 2);
INSERT INTO `user_role` VALUES (1, 2);
INSERT INTO `user_role` VALUES (4, 1);
INSERT INTO `user_role` VALUES (2, 2);

-- ----------------------------
-- Table structure for user_store
-- ----------------------------
DROP TABLE IF EXISTS `user_store`;
CREATE TABLE `user_store`  (
  `master_key` int(0) NULL DEFAULT NULL,
  `store_key` int(0) NULL DEFAULT NULL,
  INDEX `master_key`(`master_key`) USING BTREE,
  INDEX `store_key`(`store_key`) USING BTREE,
  CONSTRAINT `user_store_ibfk_1` FOREIGN KEY (`master_key`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_store_ibfk_2` FOREIGN KEY (`store_key`) REFERENCES `store` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_store
-- ----------------------------
INSERT INTO `user_store` VALUES (1, 1);
INSERT INTO `user_store` VALUES (2, 2);
INSERT INTO `user_store` VALUES (2, 3);

SET FOREIGN_KEY_CHECKS = 1;
