CREATE DATABASE IF NOT EXISTS mock
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;

use mock;

CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `login_name` varchar(255) DEFAULT NULL COMMENT '登陆名',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `user_type` int(2) DEFAULT '0' COMMENT '普通用户：0、管理员：1',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `sex` int(255) DEFAULT NULL COMMENT '男：0、女：1',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `status` int(255) DEFAULT '0' COMMENT '用户状态：正常：0(默认)、停用：1',
  `del_flag` int(255) DEFAULT '0' COMMENT '删除：正常：0(默认)、停用：1',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '登陆日期',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新用户',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` longtext COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户表';


INSERT INTO  `sys_user`( `login_name`, `user_name`, `user_type`, `email`, `phone`, `sex`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ( 'admin', 'admin', 1, NULL, NULL, 1, '4c590c75bd7d9836e1c960ae0ed2fe7c', 0, 0, '192.168.101.13', '2020-12-18 18:31:10', '0', '2020-10-22 15:11:05', '0', '2020-12-18 18:31:08', NULL);

CREATE TABLE `sys_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '模块名称',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '备注',
  `create_by` int(255) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新用户',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='模块表';

INSERT INTO  `sys_category`( `name`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ( '默认模块', '默认模块', NULL, '2020-12-16 10:07:25', 4, '2020-12-16 15:45:27');


CREATE TABLE `mock_url` (
  `url_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `category_id` bigint(20) NOT NULL COMMENT '模块ID',
  `url` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT 'URL路径',
  `name` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT 'URL名称',
  `description` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '描述',
  `del_flag` bigint(20) DEFAULT '0' COMMENT '删除：正常：0(默认)、停用：1',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '备注',
  PRIMARY KEY (`url_id`) USING BTREE,
  KEY `url_id` (`url_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='mock表';


CREATE TABLE `mock_response` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `url_id` bigint(20) NOT NULL COMMENT 'url_id',
  `name` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '响应名称',
  `request_method` varchar(255) DEFAULT NULL COMMENT 'GET/POST/PUT/DEL',
  `param_type` int(2) DEFAULT '0' COMMENT '0:None(默认)、1:x-www-form-urlencoded、2:json、3:raw',
  `request_param` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '请求参数',
  `request_header` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '请求头',
  `delay` int(20) DEFAULT '0' COMMENT '延迟时间(秒)',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '响应内容',
  `status_code` bigint(20) DEFAULT NULL COMMENT '状态码',
  `response_header` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '响应头',
  `description` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '描述',
  `del_flag` bigint(20) DEFAULT '0' COMMENT '删除：正常：0(默认)、停用：1',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING BTREE COMMENT '普通索引',
  KEY `url_id` (`url_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='mock响应表';

CREATE TABLE `mock_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `request_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求IP',
  `category_id` int(11) DEFAULT NULL COMMENT '模块ID',
  `hit_url` varchar(255) DEFAULT NULL COMMENT '命中URL',
  `request_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求URL',
  `request_method` varchar(255) DEFAULT NULL COMMENT '请求方法',
  `request_detail` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '请求详情',
  `request_header` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '请求头',
  `response_header` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '响应头',
  `response_detail` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '响应内容',
  `del_flag` int(2) DEFAULT '0' COMMENT '是否删除(默认：0、1：删除)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `request_url` (`request_url`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='mock记录表';

CREATE TABLE `dict_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `associateId` int(255) DEFAULT NULL COMMENT '关联ID',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '值',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='基础数据字典';

INSERT INTO  `dict_data`(`id`, `associateId`, `value`, `remark`) VALUES (1, 1, 'GET', NULL);
INSERT INTO  `dict_data`(`id`, `associateId`, `value`, `remark`) VALUES (2, 1, 'POST', NULL);
INSERT INTO  `dict_data`(`id`, `associateId`, `value`, `remark`) VALUES (3, 1, 'PUT', NULL);
INSERT INTO  `dict_data`(`id`, `associateId`, `value`, `remark`) VALUES (4, 1, 'DEL', NULL);


