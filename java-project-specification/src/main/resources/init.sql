CREATE TABLE `test`
(
    `id`   int NOT NULL AUTO_INCREMENT,
    `info` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into test (info)
values ('plugin');
insert into test (info)
values ('hs');
insert into test (info)
values ('trx');

CREATE TABLE `am_product`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(256) NOT NULL DEFAULT '' COMMENT '产品名称',
    `is_engine`   tinyint unsigned NOT NULL DEFAULT '1' COMMENT '1 需要选择引擎数量 2 不需要',
    `status`      tinyint unsigned NOT NULL DEFAULT '1' COMMENT '1 正常 2 删除',
    `description` text         NOT NULL COMMENT '描述',
    `created_at`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='产品类型';

CREATE TABLE `am_supplier`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '设备ID',
    `name`        varchar(100) NOT NULL DEFAULT '' COMMENT '供应商名称',
    `description` text         NOT NULL COMMENT '描述',
    `status`      tinyint unsigned NOT NULL DEFAULT '1' COMMENT '1 正常 2 删除',
    `created_at`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='供应商信息表'