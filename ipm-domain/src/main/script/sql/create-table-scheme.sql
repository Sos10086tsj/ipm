--ipm_sys_company
DROP TABLE IF EXISTS ipm_sys_company;

CREATE TABLE `ipm_sys_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `vip` int(11) DEFAULT NULL,
  `register_date` timestamp NULL DEFAULT NULL,
  `version` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`,`code`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE ipm_sys_company ADD INDEX ipm_sys_company_seq (id)  ;
ALTER TABLE ipm_sys_company ADD INDEX ipm_sys_company_code_index (code)  ;

--ipm_sys_company_info
CREATE TABLE `ipm_sys_company_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_code` varchar(45) DEFAULT NULL,
  `scal` int(11) DEFAULT NULL,
  `industry` int(11) DEFAULT NULL,
  `contact_email` varchar(45) DEFAULT NULL,
  `contact_phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `company_code_UNIQUE` (`company_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `ipm`.`ipm_sys_company_info` 
CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,
ADD COLUMN `version` BIGINT NULL DEFAULT 0 AFTER `contact_phone`;

ALTER TABLE `ipm`.`ipm_sys_company_info` 
ADD CONSTRAINT `IPM_SYS_COMP_INFO_FK`
  FOREIGN KEY (`company_code`)
  REFERENCES `ipm`.`ipm_sys_company` (`code`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
ALTER TABLE ipm_sys_company_info ADD INDEX ipm_sys_company_info_company_code_index (company_code)  ;


--晚上
CREATE TABLE `ipm`.`ipm_sys_supp_dictionary` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `type` INT NULL,
  `code` VARCHAR(45) NULL,
  `name` VARCHAR(45) CHARACTER SET 'utf8' NULL,
  `status` INT NULL,
  `version` BIGINT NULL DEFAULT 0,
  PRIMARY KEY (`id`));

  ALTER TABLE ipm_sys_supp_dictionary ADD INDEX ipm_sys_supp_dictionary_code_index (code)  ;
  
--20150327
  ALTER TABLE `ipm`.`ipm_sys_company_info` 
DROP INDEX `ipm_sys_company_info_company_code_index` ;
ALTER TABLE `ipm`.`ipm_sys_company_info` 
DROP FOREIGN KEY `IPM_SYS_COMP_INFO_FK`;
ALTER TABLE `ipm`.`ipm_sys_company_info` 
DROP INDEX `company_code_UNIQUE` ;
ALTER TABLE `ipm`.`ipm_sys_company_info` 
CHANGE COLUMN `company_code` `company_id` BIGINT NULL DEFAULT NULL ;

ALTER TABLE `ipm`.`ipm_sys_company_info` 
ADD INDEX `ipm_sys_company_info_comp_id_index` (`company_id` ASC);
ALTER TABLE `ipm`.`ipm_sys_company_info` 
ADD CONSTRAINT `ipm_sys_company_info_comp_fk`
  FOREIGN KEY (`company_id`)
  REFERENCES `ipm`.`ipm_sys_company` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  CREATE TABLE `ipm`.`ipm_sys_user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `salt` VARCHAR(45) NULL,
  `password` VARCHAR(100) NULL,
  `name` VARCHAR(45) CHARACTER SET 'utf8' NULL,
  `status` INT NULL,
  `register_date` TIMESTAMP NULL,
  `version` BIGINT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC));
ALTER TABLE `ipm`.`ipm_sys_user` 
ADD INDEX `ipm_sys_user_username_index` (`username` ASC);

ALTER TABLE `ipm`.`ipm_sys_user` 
ADD COLUMN `pinyin` VARCHAR(45) NULL AFTER `register_date`,
ADD COLUMN `pinyin_first_letter` VARCHAR(45) NULL AFTER `pinyin`;
ALTER TABLE `ipm`.`ipm_sys_user` 
ADD COLUMN `company_id` BIGINT NULL AFTER `name`;
