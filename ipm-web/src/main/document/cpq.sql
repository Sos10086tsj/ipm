CREATE TABLE `ipm`.`ipm_biz_cpq_print_order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order_no` VARCHAR(45) NULL,
  `style_no` VARCHAR(45) NULL,
  `maker` VARCHAR(2000) NULL,
  `customer` VARCHAR(2000) NULL,
  `description` VARCHAR(45) NULL,
  `quality` VARCHAR(45) NULL,
  `filling` VARCHAR(45) NULL,
  `price` VARCHAR(45) NULL,
  `delivery` VARCHAR(45) NULL,
  `weight` VARCHAR(45) NULL,
  `washing` VARCHAR(45) NULL,
  `packing` VARCHAR(45) NULL,
  `remarks` VARCHAR(45) NULL,
  `brand_name` VARCHAR(45) NULL,
  `shipping_mode` VARCHAR(45) NULL,
  `terms_payment` VARCHAR(45) NULL,
  `optlock` BIGINT NULL DEFAULT 0,
  PRIMARY KEY (`id`));

  ALTER TABLE `ipm`.`ipm_biz_cpq_print_order` 
CHANGE COLUMN `optlock` `version` BIGINT(20) NULL DEFAULT '0' ;


CREATE TABLE `ipm`.`ipm_biz_cpq_print_order_item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order_no` VARCHAR(45) NULL,
  `style_no` VARCHAR(45) NULL,
  `size_s` VARCHAR(45) NULL,
  `size_m` VARCHAR(45) NULL,
  `size_l` VARCHAR(45) NULL,
  `size_xl` VARCHAR(45) NULL,
  `size_xxl` VARCHAR(45) NULL,
  `version` VARCHAR(45) NULL DEFAULT 0,
  PRIMARY KEY (`id`));

  ALTER TABLE `ipm`.`ipm_biz_cpq_print_order_item` 
DROP COLUMN `style_no`,
CHANGE COLUMN `order_no` `order_id` BIGINT NULL DEFAULT NULL ;

CREATE TABLE `ipm`.`ipm_biz_cpq_data_dictionary` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `type` INT NULL,
  `key` VARCHAR(45) NULL,
  `value` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

  ALTER TABLE `ipm`.`ipm_biz_cpq_print_order_item` 
ADD COLUMN `color` VARCHAR(45) NULL AFTER `order_id`;

ALTER TABLE `ipm`.`ipm_biz_cpq_print_order_item` 
CHANGE COLUMN `size_s` `size_s` INT NULL DEFAULT NULL ,
CHANGE COLUMN `size_m` `size_m` INT NULL DEFAULT NULL ,
CHANGE COLUMN `size_l` `size_l` INT NULL DEFAULT NULL ,
CHANGE COLUMN `size_xl` `size_xl` INT NULL DEFAULT NULL ,
CHANGE COLUMN `size_xxl` `size_xxl` INT NULL DEFAULT NULL ,
CHANGE COLUMN `version` `version` BIGINT NULL DEFAULT '0' ;


ALTER TABLE `ipm`.`ipm_biz_cpq_data_dictionary` 
CHANGE COLUMN `key` `property` VARCHAR(45) NULL DEFAULT NULL ;

CREATE TABLE `ipm_biz_cpq_pdf` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(200) DEFAULT NULL,
  `file_path` varchar(200) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `upload_date` timestamp NULL DEFAULT NULL,
  `deleted` tinyint(2) DEFAULT '0',
  `version` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ipm`.`ipm_common_attachment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `biz_type` INT NULL,
  `biz_reference` VARCHAR(45) NULL,
  `file_name` VARCHAR(200) NULL,
  `file_path` VARCHAR(200) NULL,
  `file_size` BIGINT NULL,
  `upload_date` TIMESTAMP NULL,
  `upload_user` BIGINT NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `ipm`.`ipm_common_attachment` 
RENAME TO  `ipm`.`ipm_sys_supp_attachment` ;
