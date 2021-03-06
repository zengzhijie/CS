USE test;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS  `ret_adres`;
CREATE TABLE `ret_adres` (
  `id` char(32) NOT NULL COMMENT 'ID序列号',
  `sto_id` char(32) NOT NULL COMMENT '店铺ID序列号',
  `consignee` varchar(20) NOT NULL COMMENT '收货人',
  `contact` varchar(100) NOT NULL COMMENT '联系方式',
  `ship_adres` varchar(255) NOT NULL COMMENT '发货地址',
  `adres` varchar(500) NOT NULL COMMENT '详细地址',
  `postcode` varchar(32) NOT NULL COMMENT '邮编',
  `is_def` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是默认地址（1-是、0-不是）',
  `crer_id` char(32) NOT NULL COMMENT '创建者用户ID号',
  `cre_tim` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updr_id` char(32) DEFAULT NULL COMMENT '更新者用户ID号',
  `upd_tim` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='售后地址';

DROP TABLE IF EXISTS  `aci_ent_cert`;
CREATE TABLE `aci_ent_cert` (
  `id` char(32) NOT NULL COMMENT 'ID序列号',
  `ent_id` char(32) NOT NULL COMMENT '店铺ID序列号',
  `image` varchar(255) NOT NULL COMMENT '图片',
  `name` varchar(50) NOT NULL COMMENT '许可证类型名称',
  `number` varchar(60) NOT NULL COMMENT '证件编号',
  `address` varchar(500) NOT NULL COMMENT '地址',
  `val_typ` char(32) DEFAULT NULL COMMENT '经营时间（LONG_TERM-长期、REGULARLY-定期）',
  `validity` varchar(50) DEFAULT NULL COMMENT '有效期',
  `crer_id` char(32) NOT NULL COMMENT '创建者用户ID号',
  `crer_idt_id` char(32) DEFAULT NULL COMMENT '创建者身份ID号',
  `cre_tim` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updr_id` char(32) DEFAULT NULL COMMENT '更新者用户ID号',
  `updr_idt_id` char(32) DEFAULT NULL COMMENT '更新者身份ID号',
  `upd_tim` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='证件信息表';

DROP TABLE IF EXISTS  `aci_ent_lic`;
CREATE TABLE `aci_ent_lic` (
  `id` char(32) NOT NULL COMMENT 'ID序列号',
  `ent_id` char(32) NOT NULL COMMENT '店铺ID序列号',
  `name` varchar(60) NOT NULL COMMENT '名称',
  `image` varchar(255) NOT NULL COMMENT '图片',
  `bus_sco` varchar(1000) DEFAULT NULL COMMENT '经营范围',
  `leg_reps` varchar(50) NOT NULL COMMENT '法定代表人名称',
  `val_typ` char(32) DEFAULT NULL COMMENT '经营时间（LONG_TERM-长期、REGULARLY-定期）',
  `validity` varchar(50) DEFAULT NULL COMMENT '有效期',
  `address` varchar(500) NOT NULL COMMENT '地址',
  `bus_reg_num` varchar(255) NOT NULL COMMENT '营业执照注册号',
  `crer_id` char(32) NOT NULL COMMENT '创建者用户ID号',
  `crer_idt_id` char(32) DEFAULT NULL COMMENT '创建者身份ID号',
  `cre_tim` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updr_id` char(32) DEFAULT NULL COMMENT '更新者用户ID号',
  `updr_idt_id` char(32) DEFAULT NULL COMMENT '更新者身份ID号',
  `upd_tim` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='营业执照信息表';

DROP TABLE IF EXISTS  `aci_carsl`;
CREATE TABLE `aci_carsl` (
  `id` char(32) NOT NULL COMMENT 'ID序列号',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `type` char(25) NOT NULL COMMENT '类型',
  `redr_id` varchar(255) DEFAULT NULL COMMENT '跳转URL',
  `image` varchar(255) DEFAULT NULL COMMENT '跳转图片',
  `redr_info` char(32) NOT NULL COMMENT '跳转目标',
  `sequence` int DEFAULT 0 COMMENT '顺序',
  `display` tinyint(1) DEFAULT '0' COMMENT '是否上架',
  `sto_id` char(32) NOT NULL COMMENT '店铺ID',
  `cre_tim` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `crer_id` char(32) DEFAULT NULL COMMENT '创建者用户ID号',
  `updr_id` char(32) DEFAULT NULL COMMENT '更新者用户ID号',
  `upd_tim` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='轮播图表';

DROP TABLE IF EXISTS  `aci_enterprise`;
CREATE TABLE `aci_enterprise` (
  `id` char(32) NOT NULL COMMENT 'ID序列号',
  `app_id` char(32) NOT NULL COMMENT '应用id',
  `app_ctg` char(32) NOT NULL COMMENT '应用分类',
  `name` char(50) NOT NULL COMMENT '企业全称',
  `app_nam` char(32) NOT NULL COMMENT '小程序名称',
  `logo` varchar(255) DEFAULT NULL COMMENT '企业logo',
  `bus_lic` varchar(255) DEFAULT NULL COMMENT '营业执照URL',
  `cert` varchar(255) DEFAULT NULL COMMENT '特许证件',
  `cert_type` varchar(255) DEFAULT NULL COMMENT '特许证件类型',
  `intro` varchar(255) DEFAULT NULL COMMENT '介绍',
  `coordinate` varchar(64) DEFAULT NULL COMMENT '位置坐标',
  `province` char(32) DEFAULT NULL COMMENT '省',
  `city` char(32) DEFAULT NULL COMMENT '市',
  `area` char(32) DEFAULT NULL COMMENT '区',
  `detail` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '座机',
  `mobile` varchar(20) DEFAULT NULL COMMENT '移动电话',
  `bot_img` varchar(255) DEFAULT NULL COMMENT '启动图像',
  `bot_tim` int(11) DEFAULT NULL COMMENT '启动时间',
  `posters` varchar(1300) DEFAULT NULL COMMENT '海报列表',
  `telephone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `url` varchar(255) DEFAULT NULL COMMENT '官方网站',
  `cre_tim` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `crer_id` char(32) DEFAULT NULL COMMENT '创建者用户ID号',
  `updr_id` char(32) DEFAULT NULL COMMENT '更新者用户ID号',
  `upd_tim` datetime DEFAULT NULL COMMENT '更新时间',
  `mer_cate` mediumtext COMMENT '商品类目列表',
  `mem_por` tinyint(1) DEFAULT '0' COMMENT '是否在商品详情显示会员注册通道\n',
  `mem_dis` tinyint(1) DEFAULT '0' COMMENT '非会员是否显示会员权益',
  `gro_rule` char(32) DEFAULT NULL COMMENT '会员成长值规则JSON',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_ent_name` (`name`),
  UNIQUE KEY `idx_ent_app_id` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业信息表';

SET FOREIGN_KEY_CHECKS = 1;
