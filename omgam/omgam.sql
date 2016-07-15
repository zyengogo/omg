/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_root
Source Server Version : 50530
Source Host           : 127.0.0.1:3306
Source Database       : omgam

Target Server Type    : MYSQL
Target Server Version : 50530
File Encoding         : 65001

Date: 2016-06-27 16:17:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `app_code`
-- ----------------------------
DROP TABLE IF EXISTS `app_code`;
CREATE TABLE `app_code` (
  `CODE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE_TYPE` varchar(16) DEFAULT NULL,
  `CODE_NAME` varchar(64) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `LAST_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`CODE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='�ֵ��_APP_CODE';

-- ----------------------------
-- Records of app_code
-- ----------------------------
INSERT INTO `app_code` VALUES ('2', 'sex', '性别', '17', '2015-05-01 19:07:45', '2015-05-01 21:06:43');

-- ----------------------------
-- Table structure for `app_code_det`
-- ----------------------------
DROP TABLE IF EXISTS `app_code_det`;
CREATE TABLE `app_code_det` (
  `CODE_DET_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE_NUM` char(2) DEFAULT NULL,
  `CODE_NAME` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`CODE_DET_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�ֵ������_APP_CODE_DET';

-- ----------------------------
-- Records of app_code_det
-- ----------------------------

-- ----------------------------
-- Table structure for `app_cost`
-- ----------------------------
DROP TABLE IF EXISTS `app_cost`;
CREATE TABLE `app_cost` (
  `COST_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USET_ID` int(11) DEFAULT NULL,
  `COST_TYPE` int(11) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`COST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�û����ѱ�_APP_COST<�ݷ�>';

-- ----------------------------
-- Records of app_cost
-- ----------------------------

-- ----------------------------
-- Table structure for `app_cost_type`
-- ----------------------------
DROP TABLE IF EXISTS `app_cost_type`;
CREATE TABLE `app_cost_type` (
  `COST_TYPE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COST_TYPE_NAME` varchar(128) DEFAULT NULL,
  `COST_TYPE_CONT` varchar(256) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`COST_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�û��������ͱ�_APP_COST_TYPE<�ݷ�>';

-- ----------------------------
-- Records of app_cost_type
-- ----------------------------

-- ----------------------------
-- Table structure for `app_favor`
-- ----------------------------
DROP TABLE IF EXISTS `app_favor`;
CREATE TABLE `app_favor` (
  `FAVOR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USET_ID` int(11) DEFAULT NULL,
  `USER_NAME` varchar(16) DEFAULT NULL,
  `FAVOR_USER_ID` int(11) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`FAVOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='���к��м��_APP_FAVOR';

-- ----------------------------
-- Records of app_favor
-- ----------------------------

-- ----------------------------
-- Table structure for `app_letter`
-- ----------------------------
DROP TABLE IF EXISTS `app_letter`;
CREATE TABLE `app_letter` (
  `LETTER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USET_ID` int(11) DEFAULT NULL,
  `LETTER_USER_ID` int(11) DEFAULT NULL,
  `CONTENT` varchar(256) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`LETTER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='д�ű�_APP_LETTER';

-- ----------------------------
-- Records of app_letter
-- ----------------------------

-- ----------------------------
-- Table structure for `app_love`
-- ----------------------------
DROP TABLE IF EXISTS `app_love`;
CREATE TABLE `app_love` (
  `LOVE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `LOVE_CONTECT` varchar(200) DEFAULT NULL,
  `ADD_NUM` varchar(200) DEFAULT NULL,
  `ADD_NAME` varchar(200) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`LOVE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='����ǽ_APP_LOVE';

-- ----------------------------
-- Records of app_love
-- ----------------------------

-- ----------------------------
-- Table structure for `app_love_data`
-- ----------------------------
DROP TABLE IF EXISTS `app_love_data`;
CREATE TABLE `app_love_data` (
  `LOVE_DATA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `LOVE_USER_ID` int(11) DEFAULT NULL,
  `LOVE_DATA_CONTECT` varchar(200) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `LOVE_USER_NAME` varchar(200) DEFAULT NULL,
  `LOVE_USER_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`LOVE_DATA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='����ǽ�ظ���_APP_LOVE_DATA';

-- ----------------------------
-- Records of app_love_data
-- ----------------------------

-- ----------------------------
-- Table structure for `app_report`
-- ----------------------------
DROP TABLE IF EXISTS `app_report`;
CREATE TABLE `app_report` (
  `REPORT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `REPORT_USER_ID` int(11) DEFAULT NULL,
  `CHECK_REPORT_ID` int(11) DEFAULT NULL,
  `REPORT_TYPE` int(11) DEFAULT NULL,
  `REPORT_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`REPORT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='APP_REPORT';

-- ----------------------------
-- Records of app_report
-- ----------------------------

-- ----------------------------
-- Table structure for `app_user`
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TELEPHONE` varchar(11) DEFAULT NULL,
  `PASS_WORD` varchar(50) DEFAULT NULL,
  `NICK_NAME` varchar(16) DEFAULT NULL,
  `SEX` char(2) DEFAULT '1',
  `AGE` int(11) DEFAULT NULL,
  `STATUS` char(2) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `LAST_DATE` datetime DEFAULT NULL,
  `GRADE` varchar(50) DEFAULT NULL,
  `SPEIIS` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='�û���_APP_USER';

-- ----------------------------
-- Records of app_user
-- ----------------------------
INSERT INTO `app_user` VALUES ('1', '1592871818', '58141f6cb6ffbda5a85766042ff6110a', '测试人员18', '1', '-72', '1', '2015-05-16 09:55:13', '2015-05-16 09:55:13', 'grade18', 'Speiis18');
INSERT INTO `app_user` VALUES ('2', '1592871819', '58141f6cb6ffbda5a85766042ff6110a', '????19', '1', '-71', '1', '2015-05-16 09:55:14', '2015-05-16 09:55:14', 'grade19', 'Speiis19');

-- ----------------------------
-- Table structure for `app_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `app_user_info`;
CREATE TABLE `app_user_info` (
  `USER_INFO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(16) DEFAULT NULL,
  `IDCARD` varchar(18) DEFAULT NULL,
  `EMAIL` varchar(34) DEFAULT NULL,
  `DOMICILE` varchar(128) DEFAULT NULL,
  `RECRUITMENT` varchar(32) DEFAULT NULL,
  `PROPERTY` varchar(128) DEFAULT NULL,
  `INTERESTS` varchar(128) DEFAULT NULL,
  `MONOLOGUE` varchar(200) DEFAULT NULL,
  `BIRTHDAY` datetime DEFAULT NULL,
  `STAR_ID` int(11) DEFAULT NULL,
  `RISE` int(11) DEFAULT NULL,
  `WEIGHT` int(11) DEFAULT NULL,
  `BLOOD_ID` int(11) DEFAULT NULL,
  `EDUCATION_ID` int(11) DEFAULT NULL,
  `OCCUPATION_ID` int(11) DEFAULT NULL,
  `INCOME_ID` int(11) DEFAULT NULL,
  `CHARM_ID` int(11) DEFAULT NULL,
  `MARRIAGE_ID` int(11) DEFAULT NULL,
  `HOUSING_ID` int(11) DEFAULT NULL,
  `DISTANCE_ID` int(11) DEFAULT NULL,
  `OPPOSITE_SEX_ID` int(11) DEFAULT NULL,
  `INTIMACY_ID` int(11) DEFAULT NULL,
  `PARENTS_ID` int(11) DEFAULT NULL,
  `CHILD_ID` int(11) DEFAULT NULL,
  `TA_LOCATION` varchar(32) DEFAULT NULL,
  `TA_AGE` int(11) DEFAULT NULL,
  `TA_RISE` int(11) DEFAULT NULL,
  `TA_EDUCATION_ID` int(11) DEFAULT NULL,
  `TA_INCOME_ID` int(11) DEFAULT NULL,
  `STATUS` char(2) DEFAULT NULL,
  `GOLD` decimal(18,0) DEFAULT NULL,
  PRIMARY KEY (`USER_INFO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�û��������_APP_USER_INFO';

-- ----------------------------
-- Records of app_user_info
-- ----------------------------

-- ----------------------------
-- Table structure for `ofextcomponentconf`
-- ----------------------------
DROP TABLE IF EXISTS `ofextcomponentconf`;
CREATE TABLE `ofextcomponentconf` (
  `subdomain` varchar(255) NOT NULL,
  `wildcard` tinyint(4) NOT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `permission` varchar(10) NOT NULL,
  PRIMARY KEY (`subdomain`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofextcomponentconf
-- ----------------------------

-- ----------------------------
-- Table structure for `ofgroup`
-- ----------------------------
DROP TABLE IF EXISTS `ofgroup`;
CREATE TABLE `ofgroup` (
  `groupName` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`groupName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofgroup
-- ----------------------------

-- ----------------------------
-- Table structure for `ofgroupprop`
-- ----------------------------
DROP TABLE IF EXISTS `ofgroupprop`;
CREATE TABLE `ofgroupprop` (
  `groupName` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `propValue` text NOT NULL,
  PRIMARY KEY (`groupName`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofgroupprop
-- ----------------------------

-- ----------------------------
-- Table structure for `ofgroupuser`
-- ----------------------------
DROP TABLE IF EXISTS `ofgroupuser`;
CREATE TABLE `ofgroupuser` (
  `groupName` varchar(50) NOT NULL,
  `username` varchar(100) NOT NULL,
  `administrator` tinyint(4) NOT NULL,
  PRIMARY KEY (`groupName`,`username`,`administrator`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofgroupuser
-- ----------------------------

-- ----------------------------
-- Table structure for `ofid`
-- ----------------------------
DROP TABLE IF EXISTS `ofid`;
CREATE TABLE `ofid` (
  `idType` int(11) NOT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`idType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofid
-- ----------------------------
INSERT INTO `ofid` VALUES ('18', '6');
INSERT INTO `ofid` VALUES ('19', '21');
INSERT INTO `ofid` VALUES ('23', '6');
INSERT INTO `ofid` VALUES ('25', '10');
INSERT INTO `ofid` VALUES ('26', '2');

-- ----------------------------
-- Table structure for `ofmucaffiliation`
-- ----------------------------
DROP TABLE IF EXISTS `ofmucaffiliation`;
CREATE TABLE `ofmucaffiliation` (
  `roomID` bigint(20) NOT NULL,
  `jid` text NOT NULL,
  `affiliation` tinyint(4) NOT NULL,
  PRIMARY KEY (`roomID`,`jid`(70))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofmucaffiliation
-- ----------------------------
INSERT INTO `ofmucaffiliation` VALUES ('1', 'yangf@wbsp6yfd1ia4jdl', '10');

-- ----------------------------
-- Table structure for `ofmucconversationlog`
-- ----------------------------
DROP TABLE IF EXISTS `ofmucconversationlog`;
CREATE TABLE `ofmucconversationlog` (
  `roomID` bigint(20) NOT NULL,
  `sender` text NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `logTime` char(15) NOT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `body` text,
  KEY `ofMucConversationLog_time_idx` (`logTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofmucconversationlog
-- ----------------------------

-- ----------------------------
-- Table structure for `ofmucmember`
-- ----------------------------
DROP TABLE IF EXISTS `ofmucmember`;
CREATE TABLE `ofmucmember` (
  `roomID` bigint(20) NOT NULL,
  `jid` text NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `firstName` varchar(100) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `faqentry` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`roomID`,`jid`(70))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofmucmember
-- ----------------------------

-- ----------------------------
-- Table structure for `ofmucroom`
-- ----------------------------
DROP TABLE IF EXISTS `ofmucroom`;
CREATE TABLE `ofmucroom` (
  `serviceID` bigint(20) NOT NULL,
  `roomID` bigint(20) NOT NULL,
  `creationDate` char(15) NOT NULL,
  `modificationDate` char(15) NOT NULL,
  `name` varchar(50) NOT NULL,
  `naturalName` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `lockedDate` char(15) NOT NULL,
  `emptyDate` char(15) DEFAULT NULL,
  `canChangeSubject` tinyint(4) NOT NULL,
  `maxUsers` int(11) NOT NULL,
  `publicRoom` tinyint(4) NOT NULL,
  `moderated` tinyint(4) NOT NULL,
  `membersOnly` tinyint(4) NOT NULL,
  `canInvite` tinyint(4) NOT NULL,
  `roomPassword` varchar(50) DEFAULT NULL,
  `canDiscoverJID` tinyint(4) NOT NULL,
  `logEnabled` tinyint(4) NOT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `rolesToBroadcast` tinyint(4) NOT NULL,
  `useReservedNick` tinyint(4) NOT NULL,
  `canChangeNick` tinyint(4) NOT NULL,
  `canRegister` tinyint(4) NOT NULL,
  PRIMARY KEY (`serviceID`,`name`),
  KEY `ofMucRoom_roomid_idx` (`roomID`),
  KEY `ofMucRoom_serviceid_idx` (`serviceID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofmucroom
-- ----------------------------
INSERT INTO `ofmucroom` VALUES ('1', '1', '001444375901496', '001444375901531', '111', '???', 'ss', '000000000000000', '001444375901496', '0', '30', '1', '0', '0', '0', null, '1', '0', 'sss', '7', '0', '1', '1');

-- ----------------------------
-- Table structure for `ofmucroomprop`
-- ----------------------------
DROP TABLE IF EXISTS `ofmucroomprop`;
CREATE TABLE `ofmucroomprop` (
  `roomID` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `propValue` text NOT NULL,
  PRIMARY KEY (`roomID`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofmucroomprop
-- ----------------------------

-- ----------------------------
-- Table structure for `ofmucservice`
-- ----------------------------
DROP TABLE IF EXISTS `ofmucservice`;
CREATE TABLE `ofmucservice` (
  `serviceID` bigint(20) NOT NULL,
  `subdomain` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isHidden` tinyint(4) NOT NULL,
  PRIMARY KEY (`subdomain`),
  KEY `ofMucService_serviceid_idx` (`serviceID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofmucservice
-- ----------------------------
INSERT INTO `ofmucservice` VALUES ('1', 'conference', null, '0');

-- ----------------------------
-- Table structure for `ofmucserviceprop`
-- ----------------------------
DROP TABLE IF EXISTS `ofmucserviceprop`;
CREATE TABLE `ofmucserviceprop` (
  `serviceID` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `propValue` text NOT NULL,
  PRIMARY KEY (`serviceID`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofmucserviceprop
-- ----------------------------

-- ----------------------------
-- Table structure for `ofoffline`
-- ----------------------------
DROP TABLE IF EXISTS `ofoffline`;
CREATE TABLE `ofoffline` (
  `username` varchar(64) NOT NULL,
  `messageID` bigint(20) NOT NULL,
  `creationDate` char(15) NOT NULL,
  `messageSize` int(11) NOT NULL,
  `stanza` text NOT NULL,
  PRIMARY KEY (`username`,`messageID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofoffline
-- ----------------------------

-- ----------------------------
-- Table structure for `ofpresence`
-- ----------------------------
DROP TABLE IF EXISTS `ofpresence`;
CREATE TABLE `ofpresence` (
  `username` varchar(64) NOT NULL,
  `offlinePresence` text,
  `offlineDate` char(15) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofpresence
-- ----------------------------
INSERT INTO `ofpresence` VALUES ('yangf', null, '001444382049314');

-- ----------------------------
-- Table structure for `ofprivacylist`
-- ----------------------------
DROP TABLE IF EXISTS `ofprivacylist`;
CREATE TABLE `ofprivacylist` (
  `username` varchar(64) NOT NULL,
  `name` varchar(100) NOT NULL,
  `isDefault` tinyint(4) NOT NULL,
  `list` text NOT NULL,
  PRIMARY KEY (`username`,`name`),
  KEY `ofPrivacyList_default_idx` (`username`,`isDefault`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofprivacylist
-- ----------------------------
INSERT INTO `ofprivacylist` VALUES ('lisi', 'invisible', '0', '<list xmlns=\"jabber:iq:privacy\" name=\"invisible\"><item action=\"deny\" order=\"1\"><presence-out/></item></list>');
INSERT INTO `ofprivacylist` VALUES ('yangf', 'invisible', '0', '<list xmlns=\"jabber:iq:privacy\" name=\"invisible\"><item action=\"deny\" order=\"1\"><presence-out/></item></list>');

-- ----------------------------
-- Table structure for `ofprivate`
-- ----------------------------
DROP TABLE IF EXISTS `ofprivate`;
CREATE TABLE `ofprivate` (
  `username` varchar(64) NOT NULL,
  `name` varchar(100) NOT NULL,
  `namespace` varchar(200) NOT NULL,
  `privateData` text NOT NULL,
  PRIMARY KEY (`username`,`name`,`namespace`(100))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofprivate
-- ----------------------------

-- ----------------------------
-- Table structure for `ofproperty`
-- ----------------------------
DROP TABLE IF EXISTS `ofproperty`;
CREATE TABLE `ofproperty` (
  `name` varchar(100) NOT NULL,
  `propValue` text NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofproperty
-- ----------------------------
INSERT INTO `ofproperty` VALUES ('admin.authorizedJIDs', 'admin@wbsp6yfd1ia4jdl,yangf@wbsp6yfd1ia4jdl,lisi@wbsp6yfd1ia4jdl,zhangsan@wbsp6yfd1ia4jdl');
INSERT INTO `ofproperty` VALUES ('adminConsole.port', '9090');
INSERT INTO `ofproperty` VALUES ('adminConsole.securePort', '9091');
INSERT INTO `ofproperty` VALUES ('connectionProvider.className', 'org.jivesoftware.database.DefaultConnectionProvider');
INSERT INTO `ofproperty` VALUES ('database.defaultProvider.connectionTimeout', '1.0');
INSERT INTO `ofproperty` VALUES ('database.defaultProvider.driver', 'com.mysql.jdbc.Driver');
INSERT INTO `ofproperty` VALUES ('database.defaultProvider.maxConnections', '25');
INSERT INTO `ofproperty` VALUES ('database.defaultProvider.minConnections', '5');
INSERT INTO `ofproperty` VALUES ('database.defaultProvider.password', '2bb341bb7551547023b3b656acf1761a');
INSERT INTO `ofproperty` VALUES ('database.defaultProvider.serverURL', 'jdbc:mysql://127.0.0.1:3306/omgam?rewriteBatchedStatements=true');
INSERT INTO `ofproperty` VALUES ('database.defaultProvider.testAfterUse', 'false');
INSERT INTO `ofproperty` VALUES ('database.defaultProvider.testBeforeUse', 'false');
INSERT INTO `ofproperty` VALUES ('database.defaultProvider.testSQL', 'select 1');
INSERT INTO `ofproperty` VALUES ('database.defaultProvider.username', '83bb592508a67f2ae93602b9eaa0b6ea3100fbe691d0a918');
INSERT INTO `ofproperty` VALUES ('locale', 'zh_CN');
INSERT INTO `ofproperty` VALUES ('passwordKey', 'E46d6fJQ5dgr7XJ');
INSERT INTO `ofproperty` VALUES ('plugin.presence.public', 'true');
INSERT INTO `ofproperty` VALUES ('plugin.presence.unavailable.status', 'Unavailable');
INSERT INTO `ofproperty` VALUES ('provider.admin.className', 'org.jivesoftware.openfire.admin.DefaultAdminProvider');
INSERT INTO `ofproperty` VALUES ('provider.auth.className', 'org.jivesoftware.openfire.auth.DefaultAuthProvider');
INSERT INTO `ofproperty` VALUES ('provider.group.className', 'org.jivesoftware.openfire.group.DefaultGroupProvider');
INSERT INTO `ofproperty` VALUES ('provider.lockout.className', 'org.jivesoftware.openfire.lockout.DefaultLockOutProvider');
INSERT INTO `ofproperty` VALUES ('provider.securityAudit.className', 'org.jivesoftware.openfire.security.DefaultSecurityAuditProvider');
INSERT INTO `ofproperty` VALUES ('provider.user.className', 'org.jivesoftware.openfire.user.DefaultUserProvider');
INSERT INTO `ofproperty` VALUES ('provider.vcard.className', 'org.jivesoftware.openfire.vcard.DefaultVCardProvider');
INSERT INTO `ofproperty` VALUES ('setup', 'true');
INSERT INTO `ofproperty` VALUES ('update.lastCheck', '1444375810011');
INSERT INTO `ofproperty` VALUES ('xmpp.auth.anonymous', 'true');
INSERT INTO `ofproperty` VALUES ('xmpp.domain', 'wbsp6yfd1ia4jdl');
INSERT INTO `ofproperty` VALUES ('xmpp.session.conflict-limit', '0');
INSERT INTO `ofproperty` VALUES ('xmpp.socket.ssl.active', 'true');

-- ----------------------------
-- Table structure for `ofpubsubaffiliation`
-- ----------------------------
DROP TABLE IF EXISTS `ofpubsubaffiliation`;
CREATE TABLE `ofpubsubaffiliation` (
  `serviceID` varchar(100) NOT NULL,
  `nodeID` varchar(100) NOT NULL,
  `jid` varchar(255) NOT NULL,
  `affiliation` varchar(10) NOT NULL,
  PRIMARY KEY (`serviceID`,`nodeID`,`jid`(70))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofpubsubaffiliation
-- ----------------------------
INSERT INTO `ofpubsubaffiliation` VALUES ('pubsub', '', 'wbsp6yfd1ia4jdl', 'owner');

-- ----------------------------
-- Table structure for `ofpubsubdefaultconf`
-- ----------------------------
DROP TABLE IF EXISTS `ofpubsubdefaultconf`;
CREATE TABLE `ofpubsubdefaultconf` (
  `serviceID` varchar(100) NOT NULL,
  `leaf` tinyint(4) NOT NULL,
  `deliverPayloads` tinyint(4) NOT NULL,
  `maxPayloadSize` int(11) NOT NULL,
  `persistItems` tinyint(4) NOT NULL,
  `maxItems` int(11) NOT NULL,
  `notifyConfigChanges` tinyint(4) NOT NULL,
  `notifyDelete` tinyint(4) NOT NULL,
  `notifyRetract` tinyint(4) NOT NULL,
  `presenceBased` tinyint(4) NOT NULL,
  `sendItemSubscribe` tinyint(4) NOT NULL,
  `publisherModel` varchar(15) NOT NULL,
  `subscriptionEnabled` tinyint(4) NOT NULL,
  `accessModel` varchar(10) NOT NULL,
  `language` varchar(255) DEFAULT NULL,
  `replyPolicy` varchar(15) DEFAULT NULL,
  `associationPolicy` varchar(15) NOT NULL,
  `maxLeafNodes` int(11) NOT NULL,
  PRIMARY KEY (`serviceID`,`leaf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofpubsubdefaultconf
-- ----------------------------
INSERT INTO `ofpubsubdefaultconf` VALUES ('pubsub', '0', '0', '0', '0', '0', '1', '1', '1', '0', '0', 'publishers', '1', 'open', 'English', null, 'all', '-1');
INSERT INTO `ofpubsubdefaultconf` VALUES ('pubsub', '1', '1', '5120', '0', '-1', '1', '1', '1', '0', '1', 'publishers', '1', 'open', 'English', null, 'all', '-1');

-- ----------------------------
-- Table structure for `ofpubsubitem`
-- ----------------------------
DROP TABLE IF EXISTS `ofpubsubitem`;
CREATE TABLE `ofpubsubitem` (
  `serviceID` varchar(100) NOT NULL,
  `nodeID` varchar(100) NOT NULL,
  `id` varchar(100) NOT NULL,
  `jid` varchar(255) NOT NULL,
  `creationDate` char(15) NOT NULL,
  `payload` mediumtext,
  PRIMARY KEY (`serviceID`,`nodeID`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofpubsubitem
-- ----------------------------

-- ----------------------------
-- Table structure for `ofpubsubnode`
-- ----------------------------
DROP TABLE IF EXISTS `ofpubsubnode`;
CREATE TABLE `ofpubsubnode` (
  `serviceID` varchar(100) NOT NULL,
  `nodeID` varchar(100) NOT NULL,
  `leaf` tinyint(4) NOT NULL,
  `creationDate` char(15) NOT NULL,
  `modificationDate` char(15) NOT NULL,
  `parent` varchar(100) DEFAULT NULL,
  `deliverPayloads` tinyint(4) NOT NULL,
  `maxPayloadSize` int(11) DEFAULT NULL,
  `persistItems` tinyint(4) DEFAULT NULL,
  `maxItems` int(11) DEFAULT NULL,
  `notifyConfigChanges` tinyint(4) NOT NULL,
  `notifyDelete` tinyint(4) NOT NULL,
  `notifyRetract` tinyint(4) NOT NULL,
  `presenceBased` tinyint(4) NOT NULL,
  `sendItemSubscribe` tinyint(4) NOT NULL,
  `publisherModel` varchar(15) NOT NULL,
  `subscriptionEnabled` tinyint(4) NOT NULL,
  `configSubscription` tinyint(4) NOT NULL,
  `accessModel` varchar(10) NOT NULL,
  `payloadType` varchar(100) DEFAULT NULL,
  `bodyXSLT` varchar(100) DEFAULT NULL,
  `dataformXSLT` varchar(100) DEFAULT NULL,
  `creator` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `replyPolicy` varchar(15) DEFAULT NULL,
  `associationPolicy` varchar(15) DEFAULT NULL,
  `maxLeafNodes` int(11) DEFAULT NULL,
  PRIMARY KEY (`serviceID`,`nodeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofpubsubnode
-- ----------------------------
INSERT INTO `ofpubsubnode` VALUES ('pubsub', '', '0', '001443077175956', '001443077175956', null, '0', '0', '0', '0', '1', '1', '1', '0', '0', 'publishers', '1', '0', 'open', '', '', '', 'wbsp6yfd1ia4jdl', '', 'English', '', null, 'all', '-1');

-- ----------------------------
-- Table structure for `ofpubsubnodegroups`
-- ----------------------------
DROP TABLE IF EXISTS `ofpubsubnodegroups`;
CREATE TABLE `ofpubsubnodegroups` (
  `serviceID` varchar(100) NOT NULL,
  `nodeID` varchar(100) NOT NULL,
  `rosterGroup` varchar(100) NOT NULL,
  KEY `ofPubsubNodeGroups_idx` (`serviceID`,`nodeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofpubsubnodegroups
-- ----------------------------

-- ----------------------------
-- Table structure for `ofpubsubnodejids`
-- ----------------------------
DROP TABLE IF EXISTS `ofpubsubnodejids`;
CREATE TABLE `ofpubsubnodejids` (
  `serviceID` varchar(100) NOT NULL,
  `nodeID` varchar(100) NOT NULL,
  `jid` varchar(255) NOT NULL,
  `associationType` varchar(20) NOT NULL,
  PRIMARY KEY (`serviceID`,`nodeID`,`jid`(70))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofpubsubnodejids
-- ----------------------------

-- ----------------------------
-- Table structure for `ofpubsubsubscription`
-- ----------------------------
DROP TABLE IF EXISTS `ofpubsubsubscription`;
CREATE TABLE `ofpubsubsubscription` (
  `serviceID` varchar(100) NOT NULL,
  `nodeID` varchar(100) NOT NULL,
  `id` varchar(100) NOT NULL,
  `jid` varchar(255) NOT NULL,
  `owner` varchar(255) NOT NULL,
  `state` varchar(15) NOT NULL,
  `deliver` tinyint(4) NOT NULL,
  `digest` tinyint(4) NOT NULL,
  `digest_frequency` int(11) NOT NULL,
  `expire` char(15) DEFAULT NULL,
  `includeBody` tinyint(4) NOT NULL,
  `showValues` varchar(30) DEFAULT NULL,
  `subscriptionType` varchar(10) NOT NULL,
  `subscriptionDepth` tinyint(4) NOT NULL,
  `keyword` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`serviceID`,`nodeID`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofpubsubsubscription
-- ----------------------------

-- ----------------------------
-- Table structure for `ofremoteserverconf`
-- ----------------------------
DROP TABLE IF EXISTS `ofremoteserverconf`;
CREATE TABLE `ofremoteserverconf` (
  `xmppDomain` varchar(255) NOT NULL,
  `remotePort` int(11) DEFAULT NULL,
  `permission` varchar(10) NOT NULL,
  PRIMARY KEY (`xmppDomain`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofremoteserverconf
-- ----------------------------

-- ----------------------------
-- Table structure for `ofroster`
-- ----------------------------
DROP TABLE IF EXISTS `ofroster`;
CREATE TABLE `ofroster` (
  `rosterID` bigint(20) NOT NULL,
  `username` varchar(64) NOT NULL,
  `jid` varchar(1024) NOT NULL,
  `sub` tinyint(4) NOT NULL,
  `ask` tinyint(4) NOT NULL,
  `recv` tinyint(4) NOT NULL,
  `nick` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rosterID`),
  KEY `ofRoster_unameid_idx` (`username`),
  KEY `ofRoster_jid_idx` (`jid`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofroster
-- ----------------------------
INSERT INTO `ofroster` VALUES ('1', 'lisi', 'yangf@wbsp6yfd1ia4jdl', '3', '-1', '-1', 'yangf');
INSERT INTO `ofroster` VALUES ('2', 'yangf', 'lisi@wbsp6yfd1ia4jdl', '3', '-1', '-1', 'lisi');

-- ----------------------------
-- Table structure for `ofrostergroups`
-- ----------------------------
DROP TABLE IF EXISTS `ofrostergroups`;
CREATE TABLE `ofrostergroups` (
  `rosterID` bigint(20) NOT NULL,
  `rank` tinyint(4) NOT NULL,
  `groupName` varchar(255) NOT NULL,
  PRIMARY KEY (`rosterID`,`rank`),
  KEY `ofRosterGroup_rosterid_idx` (`rosterID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofrostergroups
-- ----------------------------
INSERT INTO `ofrostergroups` VALUES ('1', '0', 'Friends');
INSERT INTO `ofrostergroups` VALUES ('2', '0', 'Friends');

-- ----------------------------
-- Table structure for `ofsaslauthorized`
-- ----------------------------
DROP TABLE IF EXISTS `ofsaslauthorized`;
CREATE TABLE `ofsaslauthorized` (
  `username` varchar(64) NOT NULL,
  `principal` text NOT NULL,
  PRIMARY KEY (`username`,`principal`(200))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofsaslauthorized
-- ----------------------------

-- ----------------------------
-- Table structure for `ofsecurityauditlog`
-- ----------------------------
DROP TABLE IF EXISTS `ofsecurityauditlog`;
CREATE TABLE `ofsecurityauditlog` (
  `msgID` bigint(20) NOT NULL,
  `username` varchar(64) NOT NULL,
  `entryStamp` bigint(20) NOT NULL,
  `summary` varchar(255) NOT NULL,
  `node` varchar(255) NOT NULL,
  `details` text,
  PRIMARY KEY (`msgID`),
  KEY `ofSecurityAuditLog_tstamp_idx` (`entryStamp`),
  KEY `ofSecurityAuditLog_uname_idx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofsecurityauditlog
-- ----------------------------
INSERT INTO `ofsecurityauditlog` VALUES ('1', 'admin', '1443077345934', 'created new user yangf', 'WBSP6YFD1IA4JDL', 'name = yangfei, email = null, admin = true');
INSERT INTO `ofsecurityauditlog` VALUES ('2', 'yangf', '1444375901557', 'created new MUC room 111', 'WBSP6YFD1IA4JDL', 'subject = sss\nroomdesc = ss\nroomname = ???\nmaxusers = 30');
INSERT INTO `ofsecurityauditlog` VALUES ('3', 'yangf', '1444380723387', 'created new user lisi', 'WBSP6YFD1IA4JDL', 'name = null, email = null, admin = true');
INSERT INTO `ofsecurityauditlog` VALUES ('4', 'yangf', '1444381127876', 'created new user zhangsan', 'WBSP6YFD1IA4JDL', 'name = null, email = null, admin = true');
INSERT INTO `ofsecurityauditlog` VALUES ('5', 'yangf', '1444381308942', 'edited user lisi', 'WBSP6YFD1IA4JDL', 'set name = null, email = null, admin = true');
INSERT INTO `ofsecurityauditlog` VALUES ('6', 'yangf', '1444381342052', 'set password for user yangf', 'WBSP6YFD1IA4JDL', null);
INSERT INTO `ofsecurityauditlog` VALUES ('7', 'yangf', '1444466689760', 'set password for user admin', 'WBSP6YFD1IA4JDL', null);
INSERT INTO `ofsecurityauditlog` VALUES ('8', 'admin', '1444467890575', 'enabled db profiling', 'WBSP6YFD1IA4JDL', null);
INSERT INTO `ofsecurityauditlog` VALUES ('9', 'admin', '1444467901669', 'disabled db profiling', 'WBSP6YFD1IA4JDL', null);

-- ----------------------------
-- Table structure for `ofuser`
-- ----------------------------
DROP TABLE IF EXISTS `ofuser`;
CREATE TABLE `ofuser` (
  `username` varchar(64) NOT NULL,
  `plainPassword` varchar(32) DEFAULT NULL,
  `encryptedPassword` varchar(255) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `creationDate` char(15) NOT NULL,
  `modificationDate` char(15) NOT NULL,
  PRIMARY KEY (`username`),
  KEY `ofUser_cDate_idx` (`creationDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofuser
-- ----------------------------
INSERT INTO `ofuser` VALUES ('admin', null, 'c387a82e65ea369ea6ed4616f850239e06b1a51edb0c5cba', 'Administrator', 'admin@example.com', '001443076166284', '0');
INSERT INTO `ofuser` VALUES ('lisi', null, '424fc737501fb656761d24c21c5f7c04560c16d960917984', null, null, '001444380723103', '001444380723103');
INSERT INTO `ofuser` VALUES ('yangf', null, '8048d5ca0cb0d6601b318037b05836772ea831b331e7a382', 'yangfei', null, '001443077344250', '001443077344250');
INSERT INTO `ofuser` VALUES ('zhangsan', null, 'eefe087669363fbcbbc0d958c62b4887a06c251752de4439', null, null, '001444381127859', '001444381127859');

-- ----------------------------
-- Table structure for `ofuserflag`
-- ----------------------------
DROP TABLE IF EXISTS `ofuserflag`;
CREATE TABLE `ofuserflag` (
  `username` varchar(64) NOT NULL,
  `name` varchar(100) NOT NULL,
  `startTime` char(15) DEFAULT NULL,
  `endTime` char(15) DEFAULT NULL,
  PRIMARY KEY (`username`,`name`),
  KEY `ofUserFlag_sTime_idx` (`startTime`),
  KEY `ofUserFlag_eTime_idx` (`endTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofuserflag
-- ----------------------------

-- ----------------------------
-- Table structure for `ofuserprop`
-- ----------------------------
DROP TABLE IF EXISTS `ofuserprop`;
CREATE TABLE `ofuserprop` (
  `username` varchar(64) NOT NULL,
  `name` varchar(100) NOT NULL,
  `propValue` text NOT NULL,
  PRIMARY KEY (`username`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofuserprop
-- ----------------------------

-- ----------------------------
-- Table structure for `ofvcard`
-- ----------------------------
DROP TABLE IF EXISTS `ofvcard`;
CREATE TABLE `ofvcard` (
  `username` varchar(64) NOT NULL,
  `vcard` mediumtext NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofvcard
-- ----------------------------

-- ----------------------------
-- Table structure for `ofversion`
-- ----------------------------
DROP TABLE IF EXISTS `ofversion`;
CREATE TABLE `ofversion` (
  `name` varchar(50) NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ofversion
-- ----------------------------
INSERT INTO `ofversion` VALUES ('openfire', '21');

-- ----------------------------
-- Table structure for `organizeinfo`
-- ----------------------------
DROP TABLE IF EXISTS `organizeinfo`;
CREATE TABLE `organizeinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orgName` varchar(200) DEFAULT NULL,
  `orgAdress` varchar(200) DEFAULT NULL,
  `postCode` varchar(30) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `webSiteUrl` varchar(200) DEFAULT NULL,
  `orgFunction` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organizeinfo
-- ----------------------------
INSERT INTO `organizeinfo` VALUES ('7', '重庆市农业信息中心', '北部新区黄山大道东段186号', '401121', '89133602', 'cqnx@cqagri.gov.cn', 'http://www.cqagri.gov.cn', '农业和农村经济技术信息服务采集；信息加工处理，信息分析、信息发布；农业信息系统建设；农业信息资料管理 ；信息查询与咨询服务，信息业务培训；信息技术的推广和农业信息资源的开发及利用。');
INSERT INTO `organizeinfo` VALUES ('8', '重庆市农业广播电视学校', '北部新区黄山大道东段186号 ', '401121', '（023）89133658', '', 'http://www.cqngx.com', ' 成人农业技术学科中专学历教育、职业培训；指导、组织农民开展群众性体育活动，承办农民体育竞赛、农民体育交流，培养农民体育骨干；农民科学技术教育培训和农民实用技术培训及科技咨询。');
INSERT INTO `organizeinfo` VALUES ('9', '重庆市农业行政执法总队', '北部新区黄山大道东段186号', '401121', '（023）89133800', '', 'http://www.cqagri.gov.cn/nyfz', '（一）依法集中行使法律法规赋予农业行政主管部门的行政处罚权和行政检查权，受理种子、农药、兽药、饲料与饲料添加剂、肥料、种畜禽、农产品质量安全、农业转基因生物安全、植物新品种保护等方面违法行为的投诉、举报，并承担上述领域的执法检查和重大区域违法行为的立案调查工作；\r\n    （二）受托承担本系统农业行政综合执法工作的组织、指导、协调；\r\n    （三）受托承担农业综合执法机构的考核及其人员的培训等工作；\r\n    （四）授权具体承担农资打假和农药管理及执法工作；\r\n    （五）具体组织实施由法律赋予农业行政主管部门职权范围内的专项整治行动；\r\n    （六）授权安排和实施农业投入品执法监督抽查计划；\r\n    （七）授权具体实施对被许可人从事行政许可活动的执法检查；\r\n    （八）承办局委托的其他工作。');
INSERT INTO `organizeinfo` VALUES ('10', '重庆市农业技术推广总站', '北部新区黄山大道东段186号', '401121', '89133753', '', 'http://www.cqates.com', '承担种植业技术推广与技术服务，种植业新技术、新品种的引进与开发创新、试验示范，种植业推广服务体系建设，土壤肥料和农田用水监测，基本农田保护与耕地地力建设，肥料质 量与残留监测、产品鉴定与登记管理具体工作。');
INSERT INTO `organizeinfo` VALUES ('11', '重庆市农业环境监测站', '北部新区黄山大道东段186号', '401121', '（023）89133533', '', 'http://www.cqagri.gov.cn/cqhjz', '为保证农业环境质量，提供监测检验技术支持，主要职责任务是：农业环境保护管理与监测、农业污染事故调查鉴定；种植业农产品质量安全监督和检验测试。');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `realname` varchar(100) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `depart` varchar(255) DEFAULT NULL,
  `isManager` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('12', 'yulin', '6361aec1c44f152a228eff61e43d630a', '喻林', null, null, null, null, '1');
INSERT INTO `sys_user` VALUES ('17', 'admin', '58141f6cb6ffbda5a85766042ff6110a', '管理员', null, null, null, null, '1');
