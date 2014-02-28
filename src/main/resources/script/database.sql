/*
Navicat MySQL Data Transfer

Source Server         : local_db
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : ocean

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2014-01-28 14:00:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `business`
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `appkey` varchar(60) COLLATE utf8_bin NOT NULL,
  `uid` varchar(60) COLLATE utf8_bin NOT NULL,
  `taskurl` varchar(200) COLLATE utf8_bin NOT NULL,
  `status` tinyint(1) NOT NULL,
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of business
-- ----------------------------
INSERT INTO `business` VALUES ('1', 'f466a8b3851f2ca1', 'sn12345789', 'http://dpurl.cn/p/Xsege tSwc', '1', '2013-11-29 10:55:24', '2013-11-29 11:00:00');
INSERT INTO `business` VALUES ('2', 'f466a8b3851f2ca1', 'sn12345789', 'http://dpurl.cn/p/5Y96b6SEuO', '1', '2013-11-29 17:46:24', '2013-11-29 17:50:00');
INSERT INTO `business` VALUES ('3', 'f466a8b3851f2ca1', 'sn12345789', 'http://dpurl.cn/p/w5xjzK2q9r', '0', '2013-11-29 17:54:57', '2013-11-29 17:54:57');
INSERT INTO `business` VALUES ('4', 'f466a8b3851f2ca1', 'sn12345789', 'http://dpurl.cn/p/Zl6eiFDdXc', '0', '2013-11-29 17:56:04', '2013-11-29 17:56:04');
INSERT INTO `business` VALUES ('5', 'f466a8b3851f2ca1', 'sn12345789', 'http://dpurl.cn/p/ Z4dgPItmO', '0', '2013-11-29 17:56:23', '2013-11-29 17:56:23');
INSERT INTO `business` VALUES ('6', 'f466a8b3851f2ca1', 'sn12345789', 'http://dpurl.cn/p/RDVC7MChde', '0', '2013-11-29 17:57:17', '2013-11-29 17:57:17');
INSERT INTO `business` VALUES ('7', 'f466a8b3851f2ca1', 'sn12345789', 'http://dpurl.cn/p/w5xjzK2q9r', '0', '2013-11-29 17:57:40', '2013-11-29 17:57:40');
INSERT INTO `business` VALUES ('8', 'f466a8b3851f2ca1', 'sn12345789', 'http://dpurl.cn/p/RDVC7MChde', '0', '2013-11-29 17:58:48', '2013-11-29 17:58:48');
INSERT INTO `business` VALUES ('9', 'f466a8b3851f2ca1', 'sn12345789', 'http://dpurl.cn/p/euCvDkcySB', '0', '2013-12-02 13:48:11', '2013-12-02 13:48:11');
INSERT INTO `business` VALUES ('10', 'f466a8b3851f2ca1', 'sn12345789', 'http://dpurl.cn/p/euCvDkcySB', '0', '2013-12-02 14:19:47', '2013-12-02 14:19:47');
INSERT INTO `business` VALUES ('11', 'f466a8b3851f2ca1', 'sn12345789', 'http://dpurl.cn/p/U7aTE6c2Ih', '1', '2013-12-03 18:14:09', '2013-12-03 18:15:00');
INSERT INTO `business` VALUES ('12', 'f466a8b3851f2ca1', 'sn12345789', 'http://dpurl.cn/p/LHFJxqw5Ow', '0', '2014-01-27 17:45:30', '2014-01-27 17:45:30');
