/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80021
Source Host           : localhost:3306
Source Database       : lvzz

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-02-23 15:04:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for l_admin
-- ----------------------------
DROP TABLE IF EXISTS `l_admin`;
CREATE TABLE `l_admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL,
  `pic` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

-- ----------------------------
-- Records of l_admin
-- ----------------------------
INSERT INTO `l_admin` VALUES ('1', 'admin', '222222', '/pics/80dde9a412cb49af8756c45c252a8c35.jpg');

-- ----------------------------
-- Table structure for l_comment
-- ----------------------------
DROP TABLE IF EXISTS `l_comment`;
CREATE TABLE `l_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `trip_id` int NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL,
  `up` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

-- ----------------------------
-- Records of l_comment
-- ----------------------------
INSERT INTO `l_comment` VALUES ('1', '3', '8', '2022-01-27 00:00:00', '111', '1');
INSERT INTO `l_comment` VALUES ('2', '3', '8', '2022-01-27 00:00:00', '太美了！值得去！', '0');
INSERT INTO `l_comment` VALUES ('3', '3', '8', '2022-01-27 00:00:00', '太美了！值得去！', '3');
INSERT INTO `l_comment` VALUES ('4', '3', '8', '2022-01-27 22:36:36', '2222', '1');
INSERT INTO `l_comment` VALUES ('5', '4', '8', '2022-01-27 22:39:07', '我也想去！', '0');
INSERT INTO `l_comment` VALUES ('6', '4', '8', '2022-01-27 22:40:07', '一起!', '0');
INSERT INTO `l_comment` VALUES ('7', '4', '1', '2022-01-27 23:34:02', '一直很想去迪士尼！', '0');
INSERT INTO `l_comment` VALUES ('8', '3', '13', '2022-02-12 17:25:45', '欢迎大家来到济南大明湖！', '3');
INSERT INTO `l_comment` VALUES ('9', '3', '13', '2022-02-13 19:06:48', '111', '1');
INSERT INTO `l_comment` VALUES ('10', '3', '13', '2022-02-13 19:23:45', '1111', '2');

-- ----------------------------
-- Table structure for l_grade
-- ----------------------------
DROP TABLE IF EXISTS `l_grade`;
CREATE TABLE `l_grade` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `trip_id` int DEFAULT NULL,
  `score` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

-- ----------------------------
-- Records of l_grade
-- ----------------------------
INSERT INTO `l_grade` VALUES ('10', '3', '8', '2');
INSERT INTO `l_grade` VALUES ('11', '3', '8', '4');
INSERT INTO `l_grade` VALUES ('12', '3', '2', '5');
INSERT INTO `l_grade` VALUES ('13', '4', '1', '9');
INSERT INTO `l_grade` VALUES ('14', '3', '13', '9');
INSERT INTO `l_grade` VALUES ('15', '3', '13', '5');
INSERT INTO `l_grade` VALUES ('16', '3', '13', '7');
INSERT INTO `l_grade` VALUES ('17', '3', '11', '5');

-- ----------------------------
-- Table structure for l_level
-- ----------------------------
DROP TABLE IF EXISTS `l_level`;
CREATE TABLE `l_level` (
  `id` int NOT NULL AUTO_INCREMENT,
  `level_name` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

-- ----------------------------
-- Records of l_level
-- ----------------------------
INSERT INTO `l_level` VALUES ('1', 'AAAAA');
INSERT INTO `l_level` VALUES ('2', 'AAAA');
INSERT INTO `l_level` VALUES ('3', 'AAA');
INSERT INTO `l_level` VALUES ('4', 'AA');
INSERT INTO `l_level` VALUES ('5', '其他');

-- ----------------------------
-- Table structure for l_orders
-- ----------------------------
DROP TABLE IF EXISTS `l_orders`;
CREATE TABLE `l_orders` (
  `oid` varchar(255) COLLATE utf8_croatian_ci NOT NULL,
  `user_name` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL,
  `trip_name` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL,
  `total_price` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int DEFAULT '0',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

-- ----------------------------
-- Records of l_orders
-- ----------------------------
INSERT INTO `l_orders` VALUES ('0590957cd92f42528385af5895c1db6e', 'rain2', '17661290990', '日月潭', '台湾南投县鱼池乡水社村中山路599号', '300', '2022-02-22 20:32:37', '1');
INSERT INTO `l_orders` VALUES ('1c1b181a1ace476cb3acf039eb2b1ac3', 'rain2', '17661290990', '黄果树瀑布', '贵州省安顺市镇宁布依族苗族自治县黄果树镇', '210', '2022-02-23 14:20:33', '1');
INSERT INTO `l_orders` VALUES ('26412e40cb334ebf8a970709cab10985', 'rain2', '17661290990', '迪士尼', '上海浦东', '200', '2022-02-22 20:49:28', '1');
INSERT INTO `l_orders` VALUES ('300408c067344d7ca5683e81f70d9f58', 'rain2', '17661290990', '黄果树瀑布', '贵州省安顺市镇宁布依族苗族自治县黄果树镇', '210', '2022-02-23 14:29:29', '1');
INSERT INTO `l_orders` VALUES ('39976b3c5f0a43229cbaed7af4294039', 'rain2', '17661290990', '泰山', '山东泰安', '120', '2022-02-23 15:00:32', '1');
INSERT INTO `l_orders` VALUES ('3f324dfb74ad49448f3c91818debde45', 'rain2', '17661290990', '泰山', '山东泰安', '120', '2022-02-23 14:52:52', '1');
INSERT INTO `l_orders` VALUES ('4a84dff3c0e24f0e83fada634a0bcee4', 'rain2', '17661290990', '日月潭', '台湾南投县鱼池乡水社村中山路599号', '300', '2022-02-22 20:41:19', '1');
INSERT INTO `l_orders` VALUES ('4c572d9019ae406c9a49edc27f225b50', 'rain2', '17661290990', '泰山', '山东泰安', '120', '2022-02-23 14:51:11', '1');
INSERT INTO `l_orders` VALUES ('531681c50fe74b8ba60ba245028e5a91', 'rain2', '17661290990', '兵马俑', '陕西西安', '50', '2022-02-23 15:02:48', '1');
INSERT INTO `l_orders` VALUES ('c3bf1b46bffb47de9e9a563253e52e05', 'rain2', '17661290990', '日月潭', '台湾南投县鱼池乡水社村中山路599号', '300', '2022-02-22 20:38:16', '1');
INSERT INTO `l_orders` VALUES ('e5f2949071ac4baca4ecea4e350a7f34', 'rain2', '17661290990', '泰山', '山东泰安', '120', '2022-02-23 14:54:25', '1');
INSERT INTO `l_orders` VALUES ('f17a70096d5342e1a862db84aa3355bb', 'rain2', '17661290990', '日月潭', '台湾南投县鱼池乡水社村中山路599号', '300', '2022-02-22 20:48:44', '1');
INSERT INTO `l_orders` VALUES ('f8e26df6989a44808ba865a03e96b7df', 'rain2', '17661290990', '迪士尼', '上海浦东', '200', '2022-02-23 14:40:39', '1');

-- ----------------------------
-- Table structure for l_riji
-- ----------------------------
DROP TABLE IF EXISTS `l_riji`;
CREATE TABLE `l_riji` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL,
  `feel_score` int DEFAULT NULL,
  `content` varchar(2550) CHARACTER SET utf8 COLLATE utf8_croatian_ci DEFAULT NULL,
  `pic` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `url` varchar(1000) COLLATE utf8_croatian_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

-- ----------------------------
-- Records of l_riji
-- ----------------------------
INSERT INTO `l_riji` VALUES ('2', '3', '22', '3', '', '', '2022-02-04', null);
INSERT INTO `l_riji` VALUES ('5', '3', '233', '3', '111', null, '2022-02-05', '111');
INSERT INTO `l_riji` VALUES ('9', '3', '2', '3', ' 我爱你        ', 'http://localhost:9001/boot/pics/d3573262d9a04965834318260cb86ae0.jpg', '2022-02-05', null);
INSERT INTO `l_riji` VALUES ('10', '3', '412', '8', ' 我来了啊啊啊             ', 'http://localhost:9001/boot/pics/c1bc02b746bd4738b2d85755e247bcd8.jpg', '2022-02-05', '<p>我来了啊啊啊<br></p><p><img src=\"http://localhost:9001/boot/pics/c1bc02b746bd4738b2d85755e247bcd8.jpg\" style=\"width: 580.667px;\"></p><p><br></p><p><br></p><p></p>');
INSERT INTO `l_riji` VALUES ('19', '3', '222', '8', ' 111111 ', null, '2022-02-08', '<p>111111</p>');
INSERT INTO `l_riji` VALUES ('20', '3', '4444', '5', ' 哇哇哇哇方法 ', null, '2022-02-08', '<p>哇哇哇哇方法</p>');
INSERT INTO `l_riji` VALUES ('21', '3', '666', '5', '66', null, '2022-02-08', '66');
INSERT INTO `l_riji` VALUES ('22', '3', '2211', '5', ' 2e2e2 ', null, '2022-02-08', '<p>2e2e2</p>');
INSERT INTO `l_riji` VALUES ('23', '3', '11', '7', ' 111dsad ', null, '2022-02-08', '<p>111dsad</p>');
INSERT INTO `l_riji` VALUES ('26', '3', '我今天去看海了！', '8', ' 欢迎大家来山东     ', 'http://localhost:9001/boot/pics/0ef0de9c44874e6ab729671bd2c0ef19.jpg', '2022-02-13', '<p>欢迎大家来山东</p><p><img style=\"width: 25%;\" src=\"http://localhost:9001/boot/pics/0ef0de9c44874e6ab729671bd2c0ef19.jpg\"><br></p>');
INSERT INTO `l_riji` VALUES ('27', '3', '我今天去看海了！', '10', ' 欢迎大家来山东！     ', 'http://localhost:9001/boot/pics/de6d9989a5784e4ba229de0986908d5f.jpg', '2022-02-13', '<p>欢迎大家来山东！</p><p><img src=\"http://localhost:9001/boot/pics/de6d9989a5784e4ba229de0986908d5f.jpg\" style=\"width: 50%;\"><br></p>');

-- ----------------------------
-- Table structure for l_shoucang
-- ----------------------------
DROP TABLE IF EXISTS `l_shoucang`;
CREATE TABLE `l_shoucang` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `trip_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

-- ----------------------------
-- Records of l_shoucang
-- ----------------------------
INSERT INTO `l_shoucang` VALUES ('1', '3', '1');
INSERT INTO `l_shoucang` VALUES ('5', '3', '6');
INSERT INTO `l_shoucang` VALUES ('8', '3', '13');
INSERT INTO `l_shoucang` VALUES ('9', '3', '10');

-- ----------------------------
-- Table structure for l_trip
-- ----------------------------
DROP TABLE IF EXISTS `l_trip`;
CREATE TABLE `l_trip` (
  `id` int NOT NULL AUTO_INCREMENT,
  `trip_name` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL,
  `level_id` int DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_croatian_ci DEFAULT NULL,
  `price` int DEFAULT NULL,
  `picImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci DEFAULT NULL,
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `level` (`level_id`),
  CONSTRAINT `level` FOREIGN KEY (`level_id`) REFERENCES `l_level` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

-- ----------------------------
-- Records of l_trip
-- ----------------------------
INSERT INTO `l_trip` VALUES ('1', '迪士尼', '上海浦东', '2', '123456', '200', '/pics/ac92c676-04fd-4b93-8da7-6d2b4302c8ee.jpg', '11111');
INSERT INTO `l_trip` VALUES ('4', '迪士尼', '上海浦东', '1', '123456', '200', '/pics/14da378bd22e407f987de502cf6274ff.jpeg', '4444');
INSERT INTO `l_trip` VALUES ('6', '泰山', '山东泰安', '2', '111111', '120', '/pics/968ec0a2eef142b49e1919c91140bfd3.jpeg', '5555');
INSERT INTO `l_trip` VALUES ('7', '兵马俑', '陕西西安', '2', '111222', '50', '/pics/1584670838685.jpg', '66666');
INSERT INTO `l_trip` VALUES ('8', '长江三峡', '	湖北省宜昌市夷陵区', '2', '222222', '0', '/pics/0cf4ca372e7676aa93835fbb.jpg_710x360_0a128460.jpg', '7777');
INSERT INTO `l_trip` VALUES ('9', '日月潭', '台湾南投县鱼池乡水社村中山路599号', '2', '111111', '300', '/pics/3bd18b7a555246e8ac34cc7119b232fe.jpg', '88888欢迎大家到来');
INSERT INTO `l_trip` VALUES ('10', '故宫', '北京市东城区景山前街4号', '1', '111111', '10', '/pics/af0f306c2a0040fdaf04137728640bf1.jpeg', null);
INSERT INTO `l_trip` VALUES ('11', '黄果树瀑布', '贵州省安顺市镇宁布依族苗族自治县黄果树镇', '1', '888888', '210', '/pics/dfa9324bd680494b93891687d7915920.jpeg', null);
INSERT INTO `l_trip` VALUES ('12', '泰安方特欢乐世界', '山东省泰安市泰山区明堂路北首', '2', '400-166-0006', '240', '/pics/f04795b757304c168797d8da405ba1cf.jpg', '泰山方特欢乐世界，位于泰安市泰山区东部新区明堂路北段，属大型高科技科幻主题公园，由恐龙危机、飞越极限、神秘河谷、聊斋、维苏威火山、海螺湾、未来警察、生命之光等17个主题项目区组成。24');
INSERT INTO `l_trip` VALUES ('13', '大明湖', '山东省济南市历下区大明湖路271号', '1', '0531-86088900', '0', '/pics/15e4741d975848de84ab6bd3153cf8ae.jpg', '大明湖位于山东省济南市市中心偏东北处、旧城区北部，[1]天然湖泊，是国家AAAAA级旅游景区——天下第一泉风景区的核心组成部分之一，也是泉城济南重要的风景名胜、开放窗口和闻名中外的旅游胜地，素有“泉城明珠”的美誉，与趵突泉、千佛山并称为济南三大名胜。');
INSERT INTO `l_trip` VALUES ('14', '青岛栈桥', '11', '3', '111', '0', '/pics/fe076c5824da462a847927003efba74d.jpg', '111');

-- ----------------------------
-- Table structure for l_user
-- ----------------------------
DROP TABLE IF EXISTS `l_user`;
CREATE TABLE `l_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of l_user
-- ----------------------------
INSERT INTO `l_user` VALUES ('3', 'rain2', '6d574072bd835e2d4f4a43863ffb4325', '/pics/6979e4b86d8142ad93a7293b68f97dff.jpg', '96e79218965eb72c92a549dd5a330112', '17661290990');
INSERT INTO `l_user` VALUES ('5', 'rain5', '97047ea6e11168533c454803e4f0306a', '/pics/1e14f66137e34c458d59c99b15b24442.jpeg', '96e79218965eb72c92a549dd5a330112', '17661290990');
