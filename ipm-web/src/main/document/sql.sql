CREATE TABLE `ipm`.`ipm_biz_cpq_manufacotry_order_item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order_no` VARCHAR(45) NULL COMMENT '订单号',
  `style_no` VARCHAR(45) NULL COMMENT '款号',
  `from` VARCHAR(45) NULL,
  `to` VARCHAR(45) NULL,
  `color` VARCHAR(45) NULL,
  `size_s` INT NULL,
  `size_m` INT NULL,
  `size_l` INT NULL,
  `size_xl` INT NULL,
  `size_xxl` INT NULL,
  `size_p` INT NULL,
  `size_1` INT NULL,
  `size_2` INT NULL,
  `size_3` INT NULL,
  `size_4` INT NULL,
  `size_6` INT NULL,
  `size_8` INT NULL,
  `size_10` INT NULL,
  `size_12` INT NULL,
  `size_14` INT NULL,
  `size_16` INT NULL,
  `box_qty` INT NULL,
  `pcs_per_box` INT NULL,
  `remark` INT NULL,
  `country` VARCHAR(45) NULL,
  `deleted` TINYINT(1) NULL DEFAULT 0,
  `version` BIGINT NULL DEFAULT 0,
  PRIMARY KEY (`id`));

  ALTER TABLE `ipm`.`ipm_biz_cpq_manufacotry_order_item` 
RENAME TO  `ipm`.`ipm_biz_cpq_manufactory_order_item` ;

ALTER TABLE `ipm`.`ipm_biz_cpq_manufactory_order_item` 
ADD COLUMN `gross_weight_per_box` FLOAT NULL AFTER `deleted`,
ADD COLUMN `net_weight_per_box` FLOAT NULL AFTER `gross_weight_per_box`;

ALTER TABLE `ipm`.`ipm_biz_cpq_manufactory_order_item` 
ADD COLUMN `volume_per_box` FLOAT NULL AFTER `net_weight_per_box`;

ALTER TABLE `ipm`.`ipm_biz_cpq_file` 
ADD COLUMN `owner` VARCHAR(45) NULL AFTER `clothing_type`;
