/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80021
Source Host           : localhost:3306
Source Database       : lvzz

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2022-02-12 17:42:55
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
INSERT INTO `l_admin` VALUES ('1', 'admin', '/pics/43717e513c7b41fcb6bd909c1f2d1252.jpg', '/pics/95948b2fa3e04bb9b37bbdc1b146544b.jpeg');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

-- ----------------------------
-- Records of l_grade
-- ----------------------------
INSERT INTO `l_grade` VALUES ('10', '3', '8', '2');
INSERT INTO `l_grade` VALUES ('11', '3', '8', '4');
INSERT INTO `l_grade` VALUES ('12', '3', '2', '5');
INSERT INTO `l_grade` VALUES ('13', '4', '1', '9');
INSERT INTO `l_grade` VALUES ('14', '3', '13', '9');
INSERT INTO `l_grade` VALUES ('15', '3', '13', '5');

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

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
INSERT INTO `l_riji` VALUES ('24', '3', 'waini', '10', '    ', 'http://localhost:9001/boot/pics/ea683611448844ef926bfac6f4c554ac.jpg', '2022-02-08', '<p><img src=\"http://localhost:9001/boot/pics/ea683611448844ef926bfac6f4c554ac.jpg\" style=\"width: 698px;\"><br></p>');

-- ----------------------------
-- Table structure for l_shoucang
-- ----------------------------
DROP TABLE IF EXISTS `l_shoucang`;
CREATE TABLE `l_shoucang` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `trip_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

-- ----------------------------
-- Records of l_shoucang
-- ----------------------------
INSERT INTO `l_shoucang` VALUES ('1', '3', '1');
INSERT INTO `l_shoucang` VALUES ('5', '3', '6');
INSERT INTO `l_shoucang` VALUES ('7', '3', '13');

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

-- ----------------------------
-- Records of l_trip
-- ----------------------------
INSERT INTO `l_trip` VALUES ('1', '迪士尼', '上海浦东', '2', '123456', '200', '/pics/ac92c676-04fd-4b93-8da7-6d2b4302c8ee.jpg', '11111');
INSERT INTO `l_trip` VALUES ('2', '迪士尼', '上海浦东', '3', '123456', '200', '/pics/14da378bd22e407f987de502cf6274ff.jpeg', '2222');
INSERT INTO `l_trip` VALUES ('4', '迪士尼', '上海浦东', '1', '123456', '200', '/pics/14da378bd22e407f987de502cf6274ff.jpeg', '4444');
INSERT INTO `l_trip` VALUES ('6', '泰山', '山东泰安', '2', '111111', '120', '/pics/968ec0a2eef142b49e1919c91140bfd3.jpeg', '5555');
INSERT INTO `l_trip` VALUES ('7', '兵马俑', '陕西西安', '2', '111222', '50', '/pics/1584670838685.jpg', '66666');
INSERT INTO `l_trip` VALUES ('8', '长江三峡', '	湖北省宜昌市夷陵区', '2', '222222', '0', '/pics/0cf4ca372e7676aa93835fbb.jpg_710x360_0a128460.jpg', '7777');
INSERT INTO `l_trip` VALUES ('9', '日月潭', '台湾南投县鱼池乡水社村中山路599号', '2', '111111', '300', '/pics/3bd18b7a555246e8ac34cc7119b232fe.jpg', '88888欢迎大家到来');
INSERT INTO `l_trip` VALUES ('10', '故宫', '北京市东城区景山前街4号', '1', '111111', '10', '/pics/af0f306c2a0040fdaf04137728640bf1.jpeg', null);
INSERT INTO `l_trip` VALUES ('11', '黄果树瀑布', '贵州省安顺市镇宁布依族苗族自治县黄果树镇', '1', '888888', '210', '/pics/dfa9324bd680494b93891687d7915920.jpeg', null);
INSERT INTO `l_trip` VALUES ('12', '泰安方特欢乐世界', '山东省泰安市泰山区明堂路北首', '2', '400-166-0006', '240', '/pics/f04795b757304c168797d8da405ba1cf.jpg', '泰山方特欢乐世界，位于泰安市泰山区东部新区明堂路北段，属大型高科技科幻主题公园，由恐龙危机、飞越极限、神秘河谷、聊斋、维苏威火山、海螺湾、未来警察、生命之光等17个主题项目区组成。24');
INSERT INTO `l_trip` VALUES ('13', '大明湖', '山东省济南市历下区大明湖路271号', '1', '0531-86088900', '0', '/pics/15e4741d975848de84ab6bd3153cf8ae.jpg', '大明湖位于山东省济南市市中心偏东北处、旧城区北部，[1]天然湖泊，是国家AAAAA级旅游景区——天下第一泉风景区的核心组成部分之一，也是泉城济南重要的风景名胜、开放窗口和闻名中外的旅游胜地，素有“泉城明珠”的美誉，与趵突泉、千佛山并称为济南三大名胜。');

-- ----------------------------
-- Table structure for l_user
-- ----------------------------
DROP TABLE IF EXISTS `l_user`;
CREATE TABLE `l_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
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
INSERT INTO `l_user` VALUES ('4', 'rain4', '08ac3ff8150a21fbb4960fc905121086', '/pics/04e2a776121a477fb512a728a68b9181.jpg', '96e79218965eb72c92a549dd5a330112', '111111');
INSERT INTO `l_user` VALUES ('5', 'rain5', '97047ea6e11168533c454803e4f0306a', '/pics/1e14f66137e34c458d59c99b15b24442.jpeg', '96e79218965eb72c92a549dd5a330112', '17661290990');
