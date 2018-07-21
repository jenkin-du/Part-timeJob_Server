/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : parttimejob

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2018-07-21 14:42:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_address`
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address` (
  `address_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `province` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `district` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `detail_address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `longitude` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `latitude` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_address
-- ----------------------------
INSERT INTO `t_address` VALUES ('A1', '', '北京市', '东城区', null, '116.416357', '39.928353');
INSERT INTO `t_address` VALUES ('A10', '江苏省', '南京市', '栖霞区', '学则路苏果超市', '106.632146', '30.453748');
INSERT INTO `t_address` VALUES ('A11', '江苏省', '南京市', '栖霞区', '大成门店二楼', '118.922363', '32.101235');
INSERT INTO `t_address` VALUES ('A12', '广东省', '广州市', '番禺区', '番禺大学城广州中医药大学国际楼F201室', '113.40604', '23.057286');
INSERT INTO `t_address` VALUES ('A13', '江苏省', '南京市', '江宁区', '长兴大街298号盛江花苑', '106.622576', '30.450715');
INSERT INTO `t_address` VALUES ('A14', '广东省', '广州市', '增城区', null, '113.810627', '23.261465');
INSERT INTO `t_address` VALUES ('A15', '江苏省', '南京市', '栖霞区', '大成名店一楼', '118.922363', '32.101235');
INSERT INTO `t_address` VALUES ('A16', '广东省', '广州市', '海珠区', '光大花园小区', '113.256148', '23.086169');
INSERT INTO `t_address` VALUES ('A17', '江苏省', '南京市', '鼓楼区', '山西路39号凤凰京华大酒店旁', '106.646823', '30.463514');
INSERT INTO `t_address` VALUES ('A18', '江苏省', '南京市', '秦淮区', '三山街升州路', '118.78302', '32.023529');
INSERT INTO `t_address` VALUES ('A19', '江苏省', '南京市', '玄武区', '樱铁村43幢1单元302', '118.828764', '32.086831');
INSERT INTO `t_address` VALUES ('A2', '江苏省', '南京市', '栖霞区', '营苑北路2号学大教育', '118.832505', '32.104931');
INSERT INTO `t_address` VALUES ('A20', '江苏省', '南京市', '鼓楼区', '定淮门大街8号', '118.74063', '32.065549');
INSERT INTO `t_address` VALUES ('A21', '江苏省', '南京市', '鼓楼区', '定淮门大街8号', '118.74063', '32.065549');
INSERT INTO `t_address` VALUES ('A24', '四川省', '成都市', '武侯区', '九方购物中心', '104.065242', '30.589629');
INSERT INTO `t_address` VALUES ('A25', '四川省', '成都市', '青羊区', '四川成都青羊新劳动人民文化宫恒温游泳馆旁', '104.008536', '30.670738');
INSERT INTO `t_address` VALUES ('A3', '江苏省', '南京市', '江宁区', '胜太东路8号同曦鸣城商务楼A9栋503室', '118.823754', '31.944667');
INSERT INTO `t_address` VALUES ('A4', '江苏省', '南京市', '鼓楼区', '下关南通路99号世茂外滩新城售楼处', '118.734211', '32.077412');
INSERT INTO `t_address` VALUES ('A5', '山东省', '青岛市', '城阳区', '东流亭工业园11号 青岛知行学校', '120.411872', '36.276615');
INSERT INTO `t_address` VALUES ('A6', '江苏省', '南京市', '建邺区', '奥体中泰国际广场2幢1单元1203室', '118.727206', '32.000769');
INSERT INTO `t_address` VALUES ('A7', '江苏省', '南京市', '浦口区', '浦口桥北大桥北路48号弘阳广场', '118.725954', '32.137827');
INSERT INTO `t_address` VALUES ('A8', '江苏省', '南京市', '雨花台区', '西善桥93号友谊公寓3栋二单元', '118.721676', '31.963709');
INSERT INTO `t_address` VALUES ('A9', '江苏省', '南京市', '玄武区', '新街口德基二期8楼西提', '118.788055', '32.0415');

-- ----------------------------
-- Table structure for `t_chat_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_chat_record`;
CREATE TABLE `t_chat_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `my_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `friend_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `message` text COLLATE utf8_unicode_ci,
  `time` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_chat_record
-- ----------------------------
INSERT INTO `t_chat_record` VALUES ('2', 'C5', 'P6', '你好', '2017-02-23 16:39');
INSERT INTO `t_chat_record` VALUES ('3', 'C5', 'P9', '哦', '2017-03-21 10:01');
INSERT INTO `t_chat_record` VALUES ('4', 'C5', 'P9', '那你', '2017-03-21 10:04');

-- ----------------------------
-- Table structure for `t_company`
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `company_id` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `company_description` text COLLATE utf8_unicode_ci,
  `address` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `legal_person` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `satisfaction` float DEFAULT NULL,
  `latitude` float(11,6) DEFAULT NULL,
  `longtitude` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_company
-- ----------------------------
INSERT INTO `t_company` VALUES ('C1', '南京宁俪佳园酒店管理有限公司', '休闲娱乐行业', '南京秦淮夫子庙南京秦淮区三山街升州路', '17327087993', '慕容经理', '111111', '4', '32.025669', '118.777437');
INSERT INTO `t_company` VALUES ('C10', '南京南天苏财教育科技有限公司', '中小学个性化辅导及全科综合辅导的一体化全方位的文化教育企业。’)', '南京江宁江宁区胜太东路8号同曦鸣城商务楼A9栋503室', '15850517536', '王老师', '123abcm', '3.5', '31.943529', '118.8226');
INSERT INTO `t_company` VALUES ('C11', '金氏集团', '服务行业', '南京建邺奥体中泰国际广场2幢1单元1203室', '883193411', '金总', '123abcn', '4', '32.000771', '118.727206');
INSERT INTO `t_company` VALUES ('C12', '世茂集团', '以房地产开发为主的国际化大型企业集群', '南京下关南通路99号世茂外滩新城售楼处', '15850718292', '李先生', '123abco', '2', '32.083122', '118.738715');
INSERT INTO `t_company` VALUES ('C13', '南京格兰布企业管理有限公司', '', ' 南京秦淮区建邺路80号8408室', '13022592677', '肖经理', '123abcp', '1', '32.030510', '118.78185');
INSERT INTO `t_company` VALUES ('C14', '南京佳得利艺术品有限公司', null, '南京江宁湖山路天景山公寓春秀苑30栋2单元一楼', '15051877502', '李老师', '123abcq', '1', '31.954849', '118.876314');
INSERT INTO `t_company` VALUES ('C15', '南京万鼎电子有限公司', null, '南京玄武四川中路与西环路交口221号', '13856783678', '王经理', '123abcr', '4.4', '32.071602', '118.816452');
INSERT INTO `t_company` VALUES ('C16', '宏翔酒店', null, '南京鼓楼山西路39号凤凰京华大酒店旁', '17327715029', '高经理', '123abcr', '3', '32.066887', '118.771634');
INSERT INTO `t_company` VALUES ('C17', '老友大酒店', null, '南京东流亭工业园11号 青岛知行学校', '18863982059', '连老师', '123abcs', '4.6', '32.077110', '118.96547');
INSERT INTO `t_company` VALUES ('C18', '苏果超市学则路店', null, '南京栖霞区学则路苏果超市', '13851833260', '林小姐', '123abct', '3', '32.094181', '118.915958');
INSERT INTO `t_company` VALUES ('C19', '学哥鸡排', null, '南京栖霞区大成门店二楼', '15977732292', '王先生', '123abcu', '2.1', '32.101234', '118.922363');
INSERT INTO `t_company` VALUES ('C2', ' 南京苏仕祥商贸有限公司', ' 从事礼品、玩具挂件摆件、装饰画、工艺品制作', ' 南京江宁南京市江宁区长兴大街298号盛江花苑', '15261494173', '黄小姐', '123abce', '3.3', '31.855923', '118.603596');
INSERT INTO `t_company` VALUES ('C20', '口味村', null, '南京玄武新街口德基二期8楼西提', '15850714192', '丁先生', '123abcv', '4.8', '32.044071', '118.784862');
INSERT INTO `t_company` VALUES ('C21', '心语装饰', null, '南京雨花台西善桥93号友谊公寓3栋二单元', '13905172067', '胡老师', '123abcw', '3.6', '31.963709', '118.721676');
INSERT INTO `t_company` VALUES ('C22', '游乐园', null, '南京浦口桥北大桥北路48号弘阳广场', '13852287011', '厉先生', '123abcx', '2.5', '32.137829', '118.725954');
INSERT INTO `t_company` VALUES ('C3', '陈佳花泥画', '', '南京玄武樱铁村43幢1单元302', '13770827479', '陈老师', '123abcf', '5', '32.086830', '118.828764');
INSERT INTO `t_company` VALUES ('C34', '杜氏集团', '杜氏集团，代码培训，堪比传智播客', '南京邮电大学', '18251957379', '杜健森', '123456', '5', '32.113682', '118.930828');
INSERT INTO `t_company` VALUES ('C4', '维爵阳明大酒店有限公司', '诚邀富有星级酒店工作经验的有志之士', '南京市鼓楼区定淮门大街8号', '13801594228', '夏经理', '123abcg', '2.2', '32.065548', '118.74063');
INSERT INTO `t_company` VALUES ('C5', '深圳恒富隆财富管理有限公司', '', '南京鼓楼中央门鼓楼区黑龙江路35号', '15850788815', '张经理', '123456', '3.3', '32.088200', '118.771466');
INSERT INTO `t_company` VALUES ('C6', '南京柏维迩电气设备有限公司', '', '南京江宁将军大道开发区133号', '13814522337', '夏助理', '123abci', '3', '31.902086', '118.793488');
INSERT INTO `t_company` VALUES ('C7', '亚东体育俱乐部', '', '南京市栖霞区仙林仙隐北路19号亚东俱乐部', '13844449987', '李先生', '123abcj', '3.5', '32.091301', '118.911403');
INSERT INTO `t_company` VALUES ('C8', '南京卡巴文化传媒有限公司', '', '南京栖霞万谷慧生活广场', '15889203437', '王小姐', '123abcd', '5', '32.102135', '118.813639');
INSERT INTO `t_company` VALUES ('C9', '聚智堂名师教育', '', '南京栖霞营苑北路2号学大教育', '13899834347', '张小姐', '123abcl', '1', '32.104931', '118.832505');

-- ----------------------------
-- Table structure for `t_friend`
-- ----------------------------
DROP TABLE IF EXISTS `t_friend`;
CREATE TABLE `t_friend` (
  `my_id` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `friend_id` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`my_id`,`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_friend
-- ----------------------------
INSERT INTO `t_friend` VALUES ('P20', 'P1');

-- ----------------------------
-- Table structure for `t_friend_request`
-- ----------------------------
DROP TABLE IF EXISTS `t_friend_request`;
CREATE TABLE `t_friend_request` (
  `friend_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `my_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `my_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `friend_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `request_reason` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_friend_request
-- ----------------------------

-- ----------------------------
-- Table structure for `t_image`
-- ----------------------------
DROP TABLE IF EXISTS `t_image`;
CREATE TABLE `t_image` (
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `code` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_image
-- ----------------------------
INSERT INTO `t_image` VALUES ('154597993031495079544091.jpg', '/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEB\nAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/2wBDAQEBAQEBAQEBAQEBAQEBAQEBAQEB\nAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/wAARCABAAEADASIA\nAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA\nAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3\nODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm\np6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA\nAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx\nBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK\nU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3\nuLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDyPUvB\nUKSS5QkevH95x0x04A59+OGz95fsV+E9M1DSNc0y7TD2l15sJkh6faywPBznqeCf4u+0s3Gah4Cs\nCsn+swP4fXl/4s5759egxzmvVf2eltPBniTWAskiLeWqy4kB/wCftu5J+vJz1AySSPyXJsfR/tDD\n+10u0ra/3tNFdtNJ9d7Xd7n7HmmX1nlOKVFctrNO3ZzXw20b0fbe7Td2/wDbN+E+nzfAT4g31qN4\n0f8As3VIY4oeR9j1e098kHbk+gK4ztfP4yaHo6bkOz5ehPQdX7c59+e68HnP76/FTxba+KPhv8Vv\nC1zbRiXU/C/iGwtMjIFzeaTf/ZOCCf8Aj+bgdsnnHX8TNCsvkiIjz/sZ9Nw6e4P556kk14vipTpU\n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\n7OaVld9+9209Vqz/2Q==\n');
INSERT INTO `t_image` VALUES ('157998293031520419559500.jpg', '/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEB\nAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/2wBDAQEBAQEBAQEBAQEBAQEBAQEBAQEB\nAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/wAARCABAAEADASIA\nAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA\nAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3\nODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm\np6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA\nAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx\nBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK\nU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3\nuLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDkLlPO\ntmmsXbULMl8iSOJ/OiERIihmHuP+Xk9J+PswyW5yTTVjMkMUrCFfK3w3ME32dP3TCHrb/ueMdvUc\n4Jn1rCYpKsumXCwNPLD58dwc2U0cM9zj9wP+POc+bj7RakkeaMn+9pwXlvqQ+yalYta30LCZU/cp\nJwXPnQn/AErzoO4t83FpjHJAIHzvJSxMHOL7K+3WXTdXera1dt2lzHBSvhIuEn+5VtW1bq+rtrzX\na81tu+Qewu7L5SjSrG0XkyM48tP391iKGbJuIckN/wAfOecZyc7mRXLx+fFcfMm4AsygyJ8z46/6\n6A4JPfoDnLZ6O5sryCQLGFlg84JI8i+XGYhO+Iv+PjMGSNx5+yYxzxk4UVgjTTSFmXzTJ5katviW\nTNzCIsA+fDmfH/HtnAxyfmaoeHUE+RJ6btb3cktLJfZ1V9rWd7t9dGq6kGpXe1/O0ppNb2upbXfW\n92225bRj9omtTDdW77X+zsn7vmdxxyevH+k+oPPGaVLK4CEWTqjeXFutZPP8xI8vB+5nP/XHnnuB\nnk7nRW0trM4WaWITY+Vm3xvzcwHzuT/nPeBi2il1C0Z8yJldsRwtx9mkky3/AC3PbA9ftfJH23g1\nxuMW3KpJ2dtVbZcyW7t5+V5LVpt9tG7pNwet0tdvik+zfW/TVrTeRnIkcc0itG0ckcb+ZaSH/lp8\n4H78YH+vUkfXqcWeeq0kXDRyjb9oi2DKbcyeXMbvqM5m5gXvk5GeAd1ZLSHUpzG8SyoFjj+U7ZR+\n7bmGcZuIc/8APv2Gf+Pok1qwWE0GTHExVMbP3cKS/up3gBmyf3P698nIJp06E1KSdlaybv5yt67f\nZ68t3q79dGrGMJX1vZ3W28k7ptW892nZPozi7Ro2ZXJkZkXe88bmSSH/AEi5/wCWWP30B4Jx9DnJ\nrdN6rRxW8v2e+tZW2faIbcyRjBuuckm4gxg/bLj/AI9SSRk/aP3LH0+x1NJb+wlUXHmK5jX/AEeS\nKTMsJm8gk/8APBbfB9Ri9OCTQs47nT7x5xE/LBCNhEcsnI/0yA237kNtI+0HOPO/5ds5nuhVlRi6\nKV72s3e68203un/ivbW8Wn5Hso1o15yfsKrW99W05aLfXazvotG3LlRq2Nt9hime0una2lmhdpB+\n/wBnlG6g8qczH/UZ+o6DBAJGpHp8V55uBHA6qqQbSTbzxzG6Hmw/88eRn/l5tMy23PBzUtvsmpkS\nw3EljfqOFVojvEs7zTygfZ8z8EDFt05P2MkZOtp0ksCziWKO3MIiclf9Lt5ohFdkSzW8Nv8AufX7\nRbW3e15zkn16bpy5ua725baLfd+q2e26d201wyo1U31XR3XeS2ctNlffS13fWWJPZXVoZnMEdzax\nLvkjXypfMj8px/qQeOOf9GNyeQPsZ5szWsosQTBAyGXD+XMmI2yWI7nOeRm1ORkZ5IrtrVPtM7pH\ntUyfNHbyN/o8snnv+9gmiyDkedm5tuMSrj7TfC9zJJBCjSkwhJjHbIgZiftEgMnkjk/v84ixn7Pd\njzh/oV1fk5zq4KPsueFrdJJNW95rVa6O3qnbZt36sJipRlKE9VpdJbWc7u19GtL9GrK6djmbPSzC\n0Yt9vmMJP3cxi/5bG6x5MxHfH/Hvc+5zdHNdnp7q8Dwy8Mg2LM8XlXMWSR0/5bdeep6ZNtjM2FZW\n7LNlYZMHDiRcyWT25LdDu/czmAZA5/13tXe6Lp8S4kSNlgePe/neTHEczPN53nQn7RZ4nEuec9Ob\nnJva5cPRnGo720stPV9Hr1fTS9/M9CVSEoSV229lZ95NPR26Lrtd6tNPyH7JFMsd7p91GsgxtuIh\nv8/JceVNBn/X8w+oJPW55pEuZJriS3mRUuU/dorSwxSPzcgzWc2cjkZNt+ZOQD1suhGylYacqwtw\njQyLv6T3X/LDF0Jj5+Lj7Oe+BknJGE9vBcyst1buhkkCAYziLz2xLCTxPmaU/wDHt/peSOci8rDk\nlTpv2b0dtXutZJp2fdKz9U/eak+ChWjUi5Sulpfleq1lfp2t6OUU5JpX5DUIY2umZJPLdJgkm2Xy\nI5PJmuDB58BJ8if/AFx+0Dv9pySASOm0LVnjQR36tIieW51GMHyxJm5x50BOIgfNPOc/MDm5yTVC\nWJY2d7lHu7aTHk3kbwx3KxQzOD582f8AS4B5H/Hvc5zxyalt7a68qaOGNbq2DQ4Ma7N8ZL4/130h\n6XJ4Y88muahW9nJ+x20XL0u79NNW1fTZpJ3aueo6fNScKjtr/vdn/NNNWtd6rmWrv73M1dp9hHbw\n3e2e0LRJujuZSkcMlvPy2JvIz/r+IMXFr2P+n8Grct3NYI6XZW5hmWNI5dpnxi2uZpvOyf8AUeR5\n2f8Ap0YehzxkYuLfzZrGVVZfK8yzmQ+RNzc/uoTn/Qp/lFx9otucfZuTn7JXaaJq66g8UEkLwSzS\nQ77RojJG2Gugf+XcZyIR9rueSLstZA3R+2sfXo4tVYypJvRK115zWmr899bW1VoteZLAyoe/71lf\no+8l1je2/XS6u27X6HRbOScm6tpvtUVxb/uVheGSR8zw48mf7SYJYPIhGLf/AI+8Mf8ATT9m+xn0\nnw5pFlLPDaS3kej36Ytnh8yIWQ8neBaRedp/2efIlz9n+zG7BlPBz83KaLp2POuLCdYPNYXMkMym\nTTppIY7ryPIgJzZf6j/j503/AET/AI+c2bXtyMeueFpdN1KC4s/EumfYbgSWCJLdKfs8nnMYYZdM\n1KG5/wBRczf8S/Fx9nu7vzxZX1nmc+f62BpU5Tld3enZ6Xkr3tpZ620vrq2lbJSlCDbs37qduvvT\njfVdeVO1vK7d2/n5ZAo81nXIxNA24yb8Fux/6bEfTA4Iqs2lRTwSQSRbvMwGUqf3+GfE3XME5Hkn\nqegBN0QSNW507z45JYxtkRpHVZpN+y4864/1xzmHnH+k5z+7t/8ARAAxnzIrye2kkhmt/PtYm/iX\ny5EjhLgc/aLnHHnY+zXPWJvsRJ+22deVDl5Ht0v/AOBTt19Lebdm3zXyw8Vyya3uo/jK34PT1d7u\nxxV5oskEoiEnmKZHygBktxme5nEvknJOSD3+1jda5vSB9kmdaLJpj3U9tFGrxtDNPCxl+zXPznyO\nPs9sbKfyAcj7Lbf64cgi+z200cdxGVifzkVv3csbRCRP37YlwM5znjPTnk5JqvJDDMksVxtmhEyb\nVmb/AEd5JIT5E3J+0QznH2fr6ZYk7jyfVaTdacXdrR2t/eunJ6u2l3e9rat8zPSpV6ij7NO62s07\n3TSto79tP8Kd2kzKjt7XVJXYy/Zbu2k2SKySxSJ973Pn254wc9SOchs11zbq6XVvtuFaJLeVWPEU\nJufJu/PmuT5M5xCfs/Xp9hObdrM39R0i5tYTNZjfbqsj2zpDM8lrJm5hh6XPOSIcZ/4+fN62xzfM\nmnXgvGltdZghhkQr+8WQ+W/7t/Jmmz/qQfKOP+XT7XFdZNz9nrncasZS9o7uy/OSTvbayf37Jts7\nsPTjOhKdN7NaWeutS9+t3su1kru7a6jw1dXVoEt43VUWLckUj+bb337+E5z9nFxBONnkWnW0Hmj7\nbk5vY/adK1SwaN4LyNbULcSQKkk1r5Yj85oTLCc+fDAR5B/0nNoDLb83RntPP8e03SZ7eZrtV+0R\nyKvnSRMY5ZCA8PndT50/+pIuT3i/043R+x11ujand6fbG3muWngDW0zhZpo4zIWuYZpbOc3JueJ5\nZoP+fu1EvOTcG8l9TA4udL2/Oldaav8AvS5Xq+j0tvqle7bOZ4WM25Rvay2V1u+t9n59Wve0sf/Z\n');
INSERT INTO `t_image` VALUES ('158098595041520427795502.jpg', '/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEB\nAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/2wBDAQEBAQEBAQEBAQEBAQEBAQEBAQEB\nAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/wAARCABAAEADASIA\nAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA\nAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3\nODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm\np6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA\nAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx\nBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK\nU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3\nuLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDzew1x\nPMKSOyybhvEsv+sAmbMXP/bD654PFbVhPDbzPJav+4lYPc2u7935krXA+1w94eR1/kD+58+spHuz\nG8yqmySJ93/LTrd/89j6AkdeoySRXSaZMLG2+zwDzkRwIJh51xt/dxkxY+z58gT/APgL59tzdYBn\n/hOCU43a1d76yW0pJbybXmr72TvdW/docqhaon7L+VXto5LbmaWnLdJ3tpdas9g0DULnTZop4Atx\np5V/NgyfMSSaaSCH1/uzY+zZP75h1GT9EaBNbXdhLDDJ51rcCVJFkM3z4naGbqx8mfgf6MD/AMfZ\nGSSc187eFL2MwRnejQ+ZGjBs44jce/m++c/asrkk5J9H0r7ZZzST6M8sxupI5rm0jkhEfmTG63Xf\nkzEweQQM3dvpv2a7usG9H2q/BvInSqXlJNfC189ai2b6Pf79GYVIJqvKDf2d2/hUql+rs2vi6bba\nnrP9iBkS1vo5LzTPOk+w3YcXH9lZjE3+mzTc+R58E2bi6zafN/pxP2fNbeleEdStt9ldot9pUaWq\nafq0MWyNY5p7u0h0/U/OuLr9/wDvYT9o/wBG4Fr9iOc2cPffCGCDxxYoY4oZrl45EltTAZLKb9zk\nxQzE2/2yAwQ5Fzbm3+1eUehuL0t9O+H/AIJW+lWl6v8AZzat4c1GSWO/8P3zm6kh828gghu9N8/U\nP9CgtYBL9r062+0/6IT9jszru61vPcwmAqYiHPG/Tsklera7vu0r79UtWmeTUxkKKlBt2b1st3dr\n1t7uvTdaptnxpeeA7jRIbi7gjme2WP7NNbrHiSGSFmhPkwQ5M/3f+Xr/AJdM/Ybz/RiTyl6YobW5\nSdI0jZrZ1keGb7TbETnzvJJ+zXHkfZ/PP2fvdm3/AND+3hvP+tfEHhW/8H2kF9bWmoah4eWb+ydQ\n0TVrYyeJvDF7M0dvBd3nlfabeXSseTcXmbm5vLWz1fTLzRLz+w7a9tIvmDx1pqokN7pcnn2K2qzQ\neWY7eWe3vLSTqfs5P2j7cBcfZ7r/AI+vKFl/o99b/wBs1eIw86aa6xvbRu6vJaaX0srp3663jMeE\nqxqLEbu2mz+K77u/LFwjbfRpaq5+XGj6nZ3UaCGTduWF0k2/vH/1nv8A6+2P9eT0rrrG6jjLRtt/\nfOEVVWYeZJuf/a/c52xe4wO558M8MzOlkpQOpFxau0sIiPe5/dedP/o/kcnHoPs2b3ndXrmk3W8t\nIyqm1tm7fn95CbmHzcg4g+1f8fHPYgG8yuT4E7wTm/sra26vLbXe8e/fWyu/oaPJJTg22rRaa3vH\n2vfum3rpur3Sb73SZ5rGR5XuN9s0cnmRFJXjk8okWfIPA+wwTWHe7uvKtuft9yxm988IanKICLaW\nMJ5MdyqyfP8A2fHKbrzppvJ+zDnJ/wBHP/PYf8/Oa8A0e6QBIpH27G/c/wC3HmH37jnqeR1BFese\nFNetXlvYTBauskMj+ddJdyb4vKaDM3F1+/8A3Pkf8u/2q0tLX7cTfQf2xSp1FUu01ZWtv3s73/4G\njTs00ypUlGPNB3itHtdatJ7Ld82m+7u1c+7vhp4cuLw6NqGlyWsOsRahCkcdtIbSN7e7N1Ne/vpr\ni60+yJPm/wDHzbfZPtc1r9u+0i4s7y0/SH4MeJh4os7u1votDh1OS8vLIWFvfXccl1Hp1rol7ef2\nnZ6xpFtcWd9/xNobi80b7V9rtbuI2R+03+n6yT+ZHwI8b2sN1/wjtxfyNdW0f2y2tpjNaSanHMYr\nu8mhhhurq4mg86CbT7TH2j7X5YzZnFpeV91W1kdTsl1TSdUutM1yxhihjvLOWWBLzE0Et7p+pz2d\nz9vxdXumaNqH2m2uf7W0DVtJ0680S9tiLXz/ALLJqip0/aXt7sUnZvW8u3kl+Kd7q/xmY0pVJShu\n0421Vvd50933106yb3Z6H8arCC1sbzxRoa3UeuS6Lb6fLaxz6j/ZV/p32y5hgtLPTD/xKf7ct4Jp\nYLPUbk232XydM0YnNyb2L8oPirb2ianrP2DU1s7i1e5S4spLiK/8+9+Y/voIdS+z2fT/AEy3tbq6\n+y+VptnZXgsbeztJ/sfXPjtqWoQTeENeuL461oOnvpszyLGJJ7ezntLOx1DzdNgt7eysbkXlr/x8\n22bv7ZqRstHtjp959k+APjDeWN3dSzRRWsOrTNYzTX1rJLHbahc2cV3ZTefDZ3NtBqQ/cZs7j/Sf\nstp9msgcQfZJlmFalO/I9klfXXSrbrrfR66pNppJXdZVRq4dV4VJXXTor3mlpd7726Pm0buz8q9E\n8o285gfhWjfYoOEkzdfvv33/AF7/AJkcgghu40rFogaPcFkYTLHJH+8/10M00sM32k+dnEv2O2us\n2gx/x+c2dnDxegQkQznG4OvzHGMYnIHGT1PBP04IB8/oLdgYOQoMZkCmZvL8zm4HTyceR9nH2c/9\ndrkdcE/Jf3b99Pnrpfvv57u59XSSv7lRLrdtWfxbNK/nvbVattneWdzNIkYnYrJC3lq0fnf8fGbm\naH/l39M+2Sf9NzbjPYaJqqWMuHkaOUSIjMTN+5kETD/UQH/XjyVP2frycng58oiuXs2lZrlobdpJ\nbaYwXV2lvJ5M5FlF1/fQefb5/wBJ/wCPW7/4/QQD5W1FcrZiFWhuozqCyXNvGv2yWNI7KeKz86E3\nlwPO/fLNbn7Nc3P2W7judGvrw/ZjeTlOml7Zryb7aXXf+rq6bjzPSNWKjyVE2nbVbqzmk9W15Kzs\nlzXb1PsLw9q975UOqaXd2+j6hHZ3GnzX+pNd3HneTE/9mRebDmws/s04srcXF0dNtboxabZ317de\nTZ6PN9geA/2m9M1SwvtON9ptrqckENydNVpp4pLe7L3lkYbjyLaD7d5BiN5p119mF1dm4FleXJ+y\n3t5+WOieM5LaJIPt0ipHD9mkXcfL8uY/66Yz8/aLYwfaLPNz/ovlW2c8ed1t54qS9trjzpv7RvLj\nfbFLU/Y4po/KazzqU/8ApX22C6sYp4LO5N3b3ml+LJtE8ZA22u6datXoYStOPMoa9V0aacl36303\nd29dGzzauEjUTSsvtb6aSeqd7x+0tOjaSVm39r/FLxkPFcF9JBfXFtrcNvbahoNxHcTfbIv7M0/U\nobLyZorn7RNY2s015i2t7q5Bs7v7Ebw2NxrN5P8AHmteKDJIlle3KzSrp8KQXMfyRXn2OOUmWz8k\n2uR5NvD9rH2a2vLryrr7FaW32ck8y3xHvblL2xvXaxj/ANG+yx3UMX2iO2Et1BZmaeY21xNMYMW9\n5qJ+zWl1eQ3H/HsRaWlcfqevS3bzys8zbWfzJZpj5koy8MF3DeE23n5/4+PtI74zk21m0+irO1ZS\n0cbWa2VuddZWu0/0bcmgw9F0r2a6PS7btzLW60vZPTrpbrLxPw5OZ3iiTa0ckcaO/mQ/883m/wCW\nGfJHTnB6ryCMnUWMG5vVJXY0UThVB/d/urkf8sj+5PEft+/HIB+3HnNEfyiIZm3G2aLDqpfz4/Pu\nvJm/4+fQD8xkkkibq7020kwmWRY2220nk7WMn2fyo8yzwhiB++AxcH3GTk55PYNtqOr6WTfWXT/g\n9F2ZNOvON18V7eunN6326q+j11YqXYKJwqoyybbgLDHcReQbj91D5w4gE8/r9kOTz3qxDrM5uEUR\nT5tVuYVs5pJriODzpzKYoIZrf9/BbT9Ps1z/AKJ/x5fY9Sv7h7yLFVfIllgHlgTLuik/55vm6HPP\nOeOvP7o85BM1bLPHLFP+8jj8pG81iN/NyITPz+P5A9CZ9KVLf9fmrvz8ul1rdXOk6UzyRMzIJBNb\ns6Xn+h3UccwhLfvofOJIsbnz4MW1z/y9db0/8ekV2DWnERVlZpE3jzPtUv8ApUeZALTqfO/69/s2\ncEehJ50Xc0ay/um+z/Z5P3KmWSRxCWmM0000/wC5nEEk3+kW32cYNzn7M1wPLy5r2W3uXka83Lc4\ngggZZvMtrgTagP3Pnkmb9xD9n+zG5/5Z3XNubXM9vDyW6f3x8/N9vz10d5jJSva6t0dr9VfST7d7\n6rqmzt2uonhLNLJ55giQWyyRRxdW8mU/Mf3/AO+yP+Pb/XC9OcEnOtdXLJPbPDG1vDNst5JIbuPz\nPJuLmHyp5p7nMx8+Ca5s7j/l1tDa/bSc3t7Nmxyu8YeLcAWD7oxFHsly2O/EHXHXPmnv8xrxKjwq\ngHkx7onk/fRR75M3MPqc/ZvKi+yfUZJx++IR3Ufnf1kr/f8A+262TZR//9k=\n');
INSERT INTO `t_image` VALUES ('182519573791520443274363.jpg', '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\n');

-- ----------------------------
-- Table structure for `t_information`
-- ----------------------------
DROP TABLE IF EXISTS `t_information`;
CREATE TABLE `t_information` (
  `information_id` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `salary` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `start_work_time` date DEFAULT NULL,
  `work_days` int(11) DEFAULT NULL,
  `work_time` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `recruit_number` int(255) DEFAULT NULL,
  `contact_name` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contact_phone` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `work_detail` text COLLATE utf8_unicode_ci,
  `gender_request` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `height_request` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `experience_request` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`information_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_information
-- ----------------------------
INSERT INTO `t_information` VALUES ('I1', '高薪手机赚钱试玩app兼职', '50元/小时', '其他', '2016-07-02', '30', '不限', '999', '手机魔道', '18734670975', '手机上试玩App超级简单，就是下载几个手机应用（京东、携程、淘宝）进行试玩。目的是提高App在应用商店的排名。放心好了，所有试玩应用都是官方程序，安全无毒！你想想看连美团、京东都需要刷榜，你还有什么好担心的<br/>　　只需要利用自己的空闲时间在手机上领取任务并完成即可，在家可以做，不限时间地点，不会耽误学习和工作。<br/>【申请要求】<br/>　　拥有iPhone手机或者安卓手机，有WiFi即可，地址不限制。苹果手机一小时可以赚45元，安卓一小时25元。<br/>【具体内容】<br/>①、请添加官方认证微信：【appmalls】（或搜索关注：App超市）<br/>②、关注后仔细阅读新手必读，按照步骤[注册→安装助手→下载App→提现]操作即可。不明白的可联系客服。<br/>③、试玩成功后会有信息提示，薪酬到账后即可删除下载的软件，不必担心内存不足的问题。<br/>【薪酬说明】<br/>①、苹果用户每个软件的试玩薪酬为2-4元，安卓用户每个软件的试玩薪酬为1-3元，试玩成功后，平台系统实时发放，一分钟就可得2-4元哦！<br/>②、薪酬统一通过支付宝或微信钱包发放；<br/>③、郑重承诺：全程不收取任何押金会费。<br/>【加入方式】<br/>微信右上角“十”→添加朋友→搜索“appmalls”关注我们的公众号【App超市】即报名成功<br/>点击菜单栏进入试玩列表，从平台1做起，20多个平台都要做（有邀请码的一定要填邀请码）收入才能更高！', '不限', '无', '无', 'A1', '机赚钱试玩');
INSERT INTO `t_information` VALUES ('I10', '辅导学生', '3000元/月', '家教', '2016-08-02', '30', '周末全天', '10', '张小姐', '13899834347', '授业解惑，辅导学生，使其成绩得到提升并达到预期的学习目标', '不限', '无', '无', 'A2', '辅导学生');
INSERT INTO `t_information` VALUES ('I11', '辅导学生', '70元/天', '家教', '2016-06-30', '60', '16:00-21:00', '6', '王老师', '15850517336', '负责学生作业的辅导检查，给学生上小班课和一对一课’, 有一定教学经验，能严格的管理好学生纪律，严抓学习，对学生认真负责，有明显的教学效果。', '不限', '无', '无', 'A3', '辅导学生');
INSERT INTO `t_information` VALUES ('I12', '通过电话，向客户介绍我们楼盘', '100元/天', '其他', '2016-06-22', '16', '10:00—17:00', '10', '李先生', '15850718292', '通过电话，向客户介绍我们楼盘的基本信息，寻找意向客户', '不限', '无', '无', 'A4', '电话联络');
INSERT INTO `t_information` VALUES ('I13', '兼职服务员', '190元/天', '服务', '2016-07-06', '50', '9:00—18:00', '30', '连老师', '18863982059', '前台接待、翻译、餐厅服务、客人向导、清扫工作等', '不限', '男1.65m女1.58m', '无', 'A5', '服务员');
INSERT INTO `t_information` VALUES ('I14', '通过电话，负责搜集新客户的资料', '4000元/月', '其他', '2016-06-29', '50', '9:00—17:00', '20', '金总', '883193411', '通过电话，’负责搜集新客户的资料并进行沟通，开发新客户', '不限', '无', '无', 'A6', '电话联络');
INSERT INTO `t_information` VALUES ('I15', '游乐场现场辅助', '2000元/月', '其他', '2016-07-07', '39', '9:30-18:00', '10', '厉先生', '13852287011', '游乐场现场辅助性，如果壳里辅导员，检票等', '不限', '无', '无', 'A7', '游乐场辅助');
INSERT INTO `t_information` VALUES ('I16', '手工制作工艺品', '100元/天', '其他', '2016-07-13', '60', '10:00—17:00', '50', '胡老师', '13905172067', '长期外发有货，带回家制作', '不限', '无', '有经验者优先', 'A8', '工艺品');
INSERT INTO `t_information` VALUES ('I17', '兼职服务员', '16元/小时', '服务', '2016-07-07', '50', '17:30-21:30', '10', '丁先生', '15850714192', '1、男孩子主要负责传菜\r\n2、女孩子主要负责现场中服务客人\r\n1、男孩子主要负责传菜\r\n2、女孩子主要负责现场中服务客人\r\n男孩子主要负责传菜\r\n2、女孩子主要负责现场中服务客人。\r\n', '不限', '男1.65m女1.58m', '无', 'A9', '服务员');
INSERT INTO `t_information` VALUES ('I18', '临时促销员', '15元/小时', '促销', '2016-06-29', '45', '周末8小时', '20', ' 林小姐', '13851833260', '善于沟通，能适应超市叫卖试吃', '不限', '无', '无', 'A10', '促销');
INSERT INTO `t_information` VALUES ('I19', '奶茶制作', '70元/天', '其他', '2016-08-17', '30', '13:00—18:00', '2', '王先生', '15977732292', '主要工作为鸡排，奶茶制作', '不限', '无', '无', 'A11', '奶茶制作');
INSERT INTO `t_information` VALUES ('I2', '高薪聘请招生代表+包住宿', '6000元/月\r', '实习', '2016-07-11', '60', '09:00-12:00,14:00-18', '100', null, null, '负责打电话与学生、家长联系沟通，介绍学校的基本情况、专业信息、办学优势，推荐学员报读我中心合作院校。<br/>工作时间：6天7小时工作制，暑假兼职时间1个半月。<br/>工资薪酬：固定底薪+高提成+团队奖金+包住宿（学生普遍假期收入在4000-8000元，多劳多得，上不封顶）。<br/>福利待遇：提供免费标准学生宿舍住宿，提供企业实习证明，表现优异者可在我司及我司合作企业内实习或就业。<br/>基本工资要求：<br/>1、高三或以上在校大学生，性别不限。<br/>2、形象气质好、心理素质好，有较强团队精神。<br/>3、较强的表达及协调能力，有上进心。<br/>4、在校学生干部或有过兼职经验优先。<br/> <br/>面试地址：广州市番禺区大学城广州中医药大学国际楼F201室。<br/>坐车指南：坐地铁4号线至大学城南站，B出口出来转86路公交，在“广药路”站下车。<br/>面试时间：星期一至星期日09:00-12:00,14:00-18：00。', '不限', '无', '有经验者优先', 'A12', '实习');
INSERT INTO `t_information` VALUES ('I20', ' 组装粘贴卡通电零件', '120元/天', '其他', '2016-07-26', '30', '10.00-16.00', '50', '黄小姐', '15261494173', '没经验的要现场学会培训熟练才可入用', '不限', '无', '有经验者优先', 'A13', '工艺品制作');
INSERT INTO `t_information` VALUES ('I21', '代码培训', '15元/小时', '其他', '2017-01-16', '5', '22:17', '2', 'null', '18251957379', '无', '男', '无', '有经验者优先', 'A21', '代码培训');
INSERT INTO `t_information` VALUES ('I3', '临时促销员', '100元/天\r', '促销', '2016-09-30', '1', '09:00-18:00', '30', null, null, '1.负责所辖门店个人销售任务的完成；<br/>2.沟通表达能力强，善于交际，热爱销售；<br/>3..有快速消费品行业或日化行业经验优先考虑。<br/>工作地点：增城区域<br/>工作时间：超市排班<br/>薪资：高额提成，上不封顶，广州五险', '不限', '无', '无', 'A14', '促销员');
INSERT INTO `t_information` VALUES ('I4', '百乐诚招语/数/英一对一兼职老师', '50元/小时', '家教', '2016-07-18', '120', '报道后通知', '5', '张老师', null, '百乐诚招一对一兼职老师<br/>科目：语/数/英<br/>任教年级：小学/初中/高中<br/>要求：学历全日制本科以上，在校学生也可，非学渣，擅表达。<br/>薪酬：课时费由面试情况、学历、经验构成，另有交通补贴18元/天，全勤奖100/月。按有两个学生工作时长3小时计算平均可达120~165元以上一天，学生越多工资越高。<br/>申请面试：在兼职猫里写清楚可任教科目以及可任教年级（重要！），个人信息与学科相关的奖项，有经验者优先。筛选后会电话联系参加面试。<br/>上课时间：寒暑假周一至周五上课，开学期间逢周六或周日上课<br/>上课及面试地址：荔湾区德星路1号荔湾广场三号楼荔泰阁2406百乐教育', '不限', '无', '有经验者优先', 'A15', '辅导');
INSERT INTO `t_information` VALUES ('I5', '派单留电话', '600元/月\r', '派单', '2016-07-18', '6', '10:30-17:00', '4', null, null, '1、小区内派单，邀约家长来体验课程，同时留下家长联系方式<br/>2、如觉得有困难，请不要报名<br/>3、每天拿到6~7个家长，一个星期必须拿到50个家长电话（且必须有效），则拿到600元，如数量不够50，则当星期获得400元！(无责任底薪)<br/>4、如一个星期拿到60个电话，增加100元，70个电话再加100，如此类推，但必须真实有效！<br/>5、有挑战性格热烈欢迎<br/>6、如有迟到者，或工作消极懒散则立即取消工作', '不限', '无', '有经验者优先', 'A16', '派单');
INSERT INTO `t_information` VALUES ('I6', '兼职服务员', '180元/天', '服务', '2016-07-12', '30', '16:00-22:00', '15', '高经理', '17327715029', '单点单、点送小吃物品、打扫分配区域卫生以及大厅杂务工作', '不限', '无', '无', 'A17', '服务员');
INSERT INTO `t_information` VALUES ('I7', '兼职服务员', ' 180元/天', '服务', '2017-04-25', '60', '16:00-22:00', '10', '慕容经理', '17327087993', '休闲娱乐行业', '不限', '无', '无', 'A18', '休闲');
INSERT INTO `t_information` VALUES ('I8', '工艺品制作', '100元/天', '其他', '2016-07-10', '50', '10.00-16.00', '80', '陈老师', '13770827479', '手工制作花泥画、无底色画、无框画、成品画等', '不限', '无', '无', 'A19', '工艺品制作');
INSERT INTO `t_information` VALUES ('I9', '兼职服务员', ' 180元/天', '服务', '2017-01-09', '60', '16:00-22:00', '10', '夏经理', '13801594228', '端茶递水,打扫卫生,传递物品,点单买单,输送酒水,为客人引路等', '不限', '男1.65m女1.58m', '无', 'A20', '端茶');

-- ----------------------------
-- Table structure for `t_pluralist`
-- ----------------------------
DROP TABLE IF EXISTS `t_pluralist`;
CREATE TABLE `t_pluralist` (
  `pluralist_id` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `salary` float DEFAULT NULL,
  `gender` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL,
  `age` int(5) DEFAULT NULL,
  `height` int(10) DEFAULT NULL,
  `education_background` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `head_img_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `school` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `feature` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `experience` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`pluralist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_pluralist
-- ----------------------------
INSERT INTO `t_pluralist` VALUES ('P1', '张三', '15799829303', '456abc', '45', '男', '20', '175', '大四', '157998293031520419559500.jpg', '123456789@163.com', '南京大学', '吃货，吃货，吃货', '吃货经理');
INSERT INTO `t_pluralist` VALUES ('P10', '孙方', '15809859504', '456abl', null, '男', '21', '2', '大二', '158098595041520427795502.jpg', null, '南京邮电大学', null, null);
INSERT INTO `t_pluralist` VALUES ('P11', '杨阳', '15459799303', '456abm', null, '男', '20', '2', '大一', '154597993031495079544091.jpg', null, null, null, null);
INSERT INTO `t_pluralist` VALUES ('P12', '丁洋', '17098295316', '456abn', null, '女', '19', '2', '研一', null, null, null, null, null);
INSERT INTO `t_pluralist` VALUES ('P13', '孙轩', '15994529397', '456abo', null, '男', '18', '2', '博士', null, null, null, null, null);
INSERT INTO `t_pluralist` VALUES ('P14', '武杨', '15309829303', '456abp', null, '男', '17', '2', '大三', null, null, null, null, null);
INSERT INTO `t_pluralist` VALUES ('P15', '赵毅倩', '15908496359', '456abq', null, '女', '19', '2', '研三', null, null, null, null, null);
INSERT INTO `t_pluralist` VALUES ('P19', '张峰', '12345678901', '123456789', '50', null, null, null, null, null, null, null, null, null);
INSERT INTO `t_pluralist` VALUES ('P2', '王娟', '15358897393', '456abd', null, '女', '20', '2', '大一', null, null, null, null, null);
INSERT INTO `t_pluralist` VALUES ('P20', '刘峰', '18251957379', '123456789', null, '男', '20', '175', '大三', '182519573791520443274363.jpg', '1763917131@qq.com', '南京邮电大学', '吃货，吃货', '吃货的经历');
INSERT INTO `t_pluralist` VALUES ('P21', '杜健森', '13699650497', '123456789', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_pluralist` VALUES ('P3', '李娜', '15835984503', '456abe', '40.5', '女', '21', '2', '专一', null, null, null, null, null);
INSERT INTO `t_pluralist` VALUES ('P4', '孙方', '15997305058', '456abf', null, '男', '19', '2', '大一', null, null, '西安电子科技大学', null, null);
INSERT INTO `t_pluralist` VALUES ('P5', '张强义', '18799924607', '456abg', null, '男', '18', '2', '大二', null, null, null, null, null);
INSERT INTO `t_pluralist` VALUES ('P6', '蔡晓', '15989805898', '456abh', null, '女', '22', '2', '大三', null, null, null, null, null);
INSERT INTO `t_pluralist` VALUES ('P7', '陈曦', '15889040832', '456abi', null, '女', '21', '2', '大四', null, null, null, null, null);
INSERT INTO `t_pluralist` VALUES ('P8', '周雨', '15897505048', '456abj', null, '男', '19', '2', '大一', null, null, null, null, null);
INSERT INTO `t_pluralist` VALUES ('P9', '孙方', '15908293778', '456abk', null, '男', '20', '2', '研一', null, null, '北京理工大学', null, null);

-- ----------------------------
-- Table structure for `t_recruit`
-- ----------------------------
DROP TABLE IF EXISTS `t_recruit`;
CREATE TABLE `t_recruit` (
  `pluralist_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `information_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `apply_reason` text COLLATE utf8_unicode_ci,
  `recruit_status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `collection_status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`pluralist_id`,`information_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_recruit
-- ----------------------------
INSERT INTO `t_recruit` VALUES ('P1', 'I1', null, 'ENROLLED', '');
INSERT INTO `t_recruit` VALUES ('P1', 'I15', '你好', 'ENROLLED', null);
INSERT INTO `t_recruit` VALUES ('P1', 'I17', 'OK们咯glad哦', 'ENROLLED', null);
INSERT INTO `t_recruit` VALUES ('P1', 'I2', null, 'ENROLLED', null);
INSERT INTO `t_recruit` VALUES ('P1', 'I4', 'bao', 'ENROLLED', null);
INSERT INTO `t_recruit` VALUES ('P1', 'I8', '你好', 'ENROLLED', null);
INSERT INTO `t_recruit` VALUES ('P11', 'I13', null, 'ENROLLED', null);
INSERT INTO `t_recruit` VALUES ('P11', 'I21', '摸摸头', 'ENROLLED', null);
INSERT INTO `t_recruit` VALUES ('P12', 'I13', null, 'FINISHED', null);
INSERT INTO `t_recruit` VALUES ('P13', 'I1', null, 'FINISHED', null);
INSERT INTO `t_recruit` VALUES ('P19', 'I12', '攻击力就了', 'ENROLLED', null);
INSERT INTO `t_recruit` VALUES ('P19', 'I14', '考虑考虑', 'CANCELLED', 'COLLECTED');
INSERT INTO `t_recruit` VALUES ('P19', 'I19', '', 'ENROLLED', null);
INSERT INTO `t_recruit` VALUES ('P19', 'I4', '', 'FINISHED', '');
INSERT INTO `t_recruit` VALUES ('P19', 'I7', null, 'FINISHED', '');
INSERT INTO `t_recruit` VALUES ('P2', 'I9', '我要做兼职', 'ENROLLED', null);
INSERT INTO `t_recruit` VALUES ('P20', 'I19', '', 'ENROLLED', '');
INSERT INTO `t_recruit` VALUES ('P20', 'I4', '你好，我要参加兼职活动', 'ENROLLED', null);
INSERT INTO `t_recruit` VALUES ('P20', 'I7', '', 'ENROLLED', null);
INSERT INTO `t_recruit` VALUES ('P21', 'I11', '啦咔咔', 'ENROLLED', null);
INSERT INTO `t_recruit` VALUES ('P3', 'I1', null, 'FINISHED', null);
INSERT INTO `t_recruit` VALUES ('P3', 'I9', '', 'ENROLLED', 'COLLECTED');
INSERT INTO `t_recruit` VALUES ('P4', 'I5', null, 'UNEMPLOYED', null);
INSERT INTO `t_recruit` VALUES ('P5', 'I10', null, 'UNEMPLOYED', null);
INSERT INTO `t_recruit` VALUES ('P5', 'I4', null, 'EMPLOYED', null);
INSERT INTO `t_recruit` VALUES ('P6', 'I1', null, 'EMPLOYED', null);
INSERT INTO `t_recruit` VALUES ('P6', 'I11', null, 'UNEMPLOYED', null);
INSERT INTO `t_recruit` VALUES ('P7', 'I12', null, 'EMPLOYED', null);
INSERT INTO `t_recruit` VALUES ('P7', 'I8', null, 'WORKED', null);
INSERT INTO `t_recruit` VALUES ('P8', 'I6', null, 'EMPLOYED', null);
INSERT INTO `t_recruit` VALUES ('P8', 'I7', null, 'FINISHED', null);
INSERT INTO `t_recruit` VALUES ('P9', 'I1', null, 'WORKED', null);
INSERT INTO `t_recruit` VALUES ('P9', 'I9', null, 'WORKED', null);

-- ----------------------------
-- Table structure for `t_release`
-- ----------------------------
DROP TABLE IF EXISTS `t_release`;
CREATE TABLE `t_release` (
  `company_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `information_id` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `release_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`information_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_release
-- ----------------------------
INSERT INTO `t_release` VALUES ('C9', 'I10', '2016-07-26 15:34:56');
INSERT INTO `t_release` VALUES ('C10', 'I11', '2016-06-15 07:56:55');
INSERT INTO `t_release` VALUES ('C12', 'I12', '2016-06-16 13:45:59');
INSERT INTO `t_release` VALUES ('C17', 'I13', '2016-06-26 03:00:00');
INSERT INTO `t_release` VALUES ('C11', 'I14', '2016-06-16 04:24:13');
INSERT INTO `t_release` VALUES ('C22', 'I15', '2016-06-28 04:56:23');
INSERT INTO `t_release` VALUES ('C21', 'I16', '2016-07-08 17:43:45');
INSERT INTO `t_release` VALUES ('C20', 'I17', '2016-06-21 15:55:00');
INSERT INTO `t_release` VALUES ('C18', 'I18', '2016-06-23 12:00:44');
INSERT INTO `t_release` VALUES ('C19', 'I19', '2016-07-23 09:34:56');
INSERT INTO `t_release` VALUES ('C6', 'I2', '2016-07-13 20:15:27');
INSERT INTO `t_release` VALUES ('C2', 'I20', '2016-07-15 12:56:00');
INSERT INTO `t_release` VALUES ('C5', 'I21', '2016-07-14 18:14:06');
INSERT INTO `t_release` VALUES ('C7', 'I3', '2016-07-12 12:17:04');
INSERT INTO `t_release` VALUES ('C8', 'I4', '2016-07-19 23:17:48');
INSERT INTO `t_release` VALUES ('C13', 'I5', '2016-07-12 18:18:16');
INSERT INTO `t_release` VALUES ('C16', 'I6', '2016-07-06 17:28:39');
INSERT INTO `t_release` VALUES ('C1', 'I7', '2016-06-01 13:45:29');
INSERT INTO `t_release` VALUES ('C3', 'I8', '2016-07-01 08:06:00');
INSERT INTO `t_release` VALUES ('C4', 'I9', '2016-06-01 19:56:28');
