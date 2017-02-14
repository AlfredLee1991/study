/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2016-09-01 16:40:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `phone` varchar(200) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'fname33', 'lname1', '(000)000-0000', 'name1@gmail.com');
INSERT INTO `users` VALUES ('2', 'fname2', 'lname2', '(000)000-0000', 'name2@gmail.com');
INSERT INTO `users` VALUES ('3', 'fname3', 'lname3', '(000)000-0000', 'name3@gmail.com');
INSERT INTO `users` VALUES ('4', 'fname4', 'lname4', '(000)000-0000', 'name4@gmail.com');
INSERT INTO `users` VALUES ('5', 'fname5', 'lname5', '(000)000-0000', 'name5@gmail.com');
INSERT INTO `users` VALUES ('6', 'fname6', 'lname6', '(000)000-0000', 'name6@gmail.com');
INSERT INTO `users` VALUES ('7', 'fname7', 'lname7', '(000)000-0000', 'name7@gmail.com');
INSERT INTO `users` VALUES ('8', 'fname8', 'lname8', '(000)000-0000', 'name8@gmail.com');
INSERT INTO `users` VALUES ('9', 'fname9', 'lname9', '(000)000-0000', 'name9@gmail.com');
INSERT INTO `users` VALUES ('10', 'fname10', 'lname44', '(000)000-0000', 'name10@gmail.com');
INSERT INTO `users` VALUES ('11', 'fname11', 'lname11', '(000)000-0000', 'name11@gmail.com');
INSERT INTO `users` VALUES ('12', 'fname12', 'lname12', '(000)000-0000', 'name12@gmail.com');

-- ----------------------------
-- Table structure for `visitor`
-- ----------------------------
DROP TABLE IF EXISTS `visitor`;
CREATE TABLE `visitor` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(1000) CHARACTER SET utf8mb4 NOT NULL,
  `Email` varchar(1000) NOT NULL,
  `Status` int(11) NOT NULL DEFAULT '1',
  `CreateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of visitor
-- ----------------------------
INSERT INTO `visitor` VALUES ('1', '李旭', '123@qq.com', '1', '2016-08-30 13:22:29');
INSERT INTO `visitor` VALUES ('2', '李小二', 'qaz@126.com', '2', '2016-08-30 13:24:02');
