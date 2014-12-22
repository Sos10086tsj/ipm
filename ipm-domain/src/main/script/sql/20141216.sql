CREATE SCHEMA `ipm` ;

DROP TABLE IF EXISTS `ipm`.`sys_user`;
CREATE TABLE `ipm`.`sys_user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `salt` VARCHAR(45) NULL,
  `password` VARCHAR(64) NULL,
  `cust_code` VARCHAR(45) NULL,
  `create_date` TIMESTAMP NULL,
  `create_user` BIGINT NULL,
  `last_update_date` TIMESTAMP NULL,
  `last_update_user` BIGINT NULL,
  `version` BIGINT NULL DEFAULT 0,
  PRIMARY KEY (`id`, `username`));
ALTER TABLE `ipm`.`sys_user` 
ADD COLUMN `status` VARCHAR(45) NULL AFTER `password`;

DROP TABLE IF EXISTS `ipm`.`sys_user_profile`;
CREATE TABLE `ipm`.`sys_user_profile` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NULL,
  `first_name` VARCHAR(45) CHARACTER SET 'utf8' NULL,
  `last_name` VARCHAR(45) CHARACTER SET 'utf8' NULL,
  `email` VARCHAR(45) NULL,
  `create_date` TIMESTAMP NULL,
  `create_user` BIGINT NULL,
  `last_update_date` TIMESTAMP NULL,
  `last_update_user` BIGINT NULL,
  `version` BIGINT NULL DEFAULT 0,
  PRIMARY KEY (`id`));

DROP TABLE IF EXISTS `ipm`.`sys_customer`;
CREATE TABLE `ipm`.`sys_customer` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `cust_code` VARCHAR(45) NOT NULL,
  `name` VARCHAR(200) CHARACTER SET 'utf8' NULL,
  `type` VARCHAR(45) NULL,
  `valid_from` TIMESTAMP NULL,
  `valid_to` TIMESTAMP NULL,
  `status` VARCHAR(45) NULL,
  `create_date` TIMESTAMP NULL,
  `create_user` BIGINT NULL,
  `last_update_date` TIMESTAMP NULL,
  `last_update_user` BIGINT NULL,
  `version` BIGINT NULL DEFAULT 0,
  PRIMARY KEY (`id`, `cust_code`));

INSERT INTO `ipm`.`sys_customer` (`cust_code`, `name`, `type`, `valid_from`, `status`, `create_date`) 
SELECT 'SYS_IPM', 'IPM', 'VIP', '20141216', 'ACTIVE', '20141216' FROM DUAL 
WHERE NOT EXISTS (SELECT 1 FROM `ipm`.`sys_customer` WHERE `cust_code` = 'SYS_IPM');

INSERT INTO `ipm`.`sys_user` (`username`, `salt`, `password`, `cust_code`, `create_date`) 
SELECT 'SYS_IPM_001', 'aaaaaaaaaa', '232550ca5cc77905c4de1caee9442088', 'SYS_IPM', '20141216' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM `ipm`.`sys_user` WHERE `username` = 'SYS_IPM_001');

INSERT INTO `ipm`.`sys_user_profile` (`user_id`, `first_name`, `last_name`, `email`, `create_date`) 
SELECT '1', 'Test', 'Ipm', 'ipm@ipm.com', '20141216' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM `ipm`.`sys_user_profile` WHERE `user_id` = 1);
