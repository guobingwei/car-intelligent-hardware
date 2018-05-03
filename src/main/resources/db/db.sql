CREATE TABLE if not exists `car_intelligent_hardware_upload` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT primary key COMMENT '自增id',
  `temperatures` DOUBLE(8, 4) NOT NULL COMMENT '温度',
  `back_distance` DOUBLE(8, 4) NOT NULL COMMENT '倒车距离',
  `mtime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `ctime` datetime NOT NULL COMMENT '创建时间'
)engine=innodb default charset=utf8mb4 COMMENT = '汽车智能硬件数据上报表'