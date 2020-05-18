/*
Navicat MySQL Data Transfer

Source Server         : 192.168.111.19
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : mysql

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2020-01-14 10:32:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wcheck
-- ----------------------------
DROP TABLE IF EXISTS `wcheck`;
CREATE TABLE `wcheck` (
  `time` datetime DEFAULT NULL,
  `usercode` varchar(20) DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `stime` datetime DEFAULT NULL,
  `etime` datetime DEFAULT NULL,
  `outdesc` varchar(500) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of wcheck
-- ----------------------------
INSERT INTO `wcheck` VALUES ('2020-01-14 10:19:16', '1111111', '张三', '2020-01-14 10:19:43', '2020-01-14 10:19:46', '侧额是');


--视图
select `wcheck`.`time` AS `日期时间`,`wcheck`.`usercode` AS `员工编号`,`wcheck`.`username` AS `员工姓名`,`wcheck`.`stime` AS `上班时间`,`wcheck`.`etime` AS `下班时间`,`wcheck`.`outdesc` AS `出勤说明` from `wcheck`


--加字段"考勤状态"
alter table wcheck ADD kqzt varchar(500) null
