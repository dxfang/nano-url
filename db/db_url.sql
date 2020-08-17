CREATE DATABASE `nanourl_service`;

USE `nanourl_service`;

/*Table structure for table `tb_url` */

DROP TABLE IF EXISTS `tb_url`;

CREATE TABLE `tb_url` (
  `id` bigint PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `long_url` varchar(128) NOT NULL COMMENT 'Original URL',
  `clicks` int(10) NOT NULL COMMENT 'How many times has been clicked',
  `expired` int(10) NOT NULL COMMENT 'Link is expired or not',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'Created at',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Updated at'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='URL Table';
