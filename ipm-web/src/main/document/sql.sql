/*系统配置表*/
CREATE TABLE `ipm`.`ipm_sys_config` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `property` VARCHAR(45) NULL,
  `property_value` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
ALTER TABLE `ipm`.`ipm_sys_config` 
CHANGE COLUMN `property_value` `property_value` VARCHAR(45) CHARACTER SET 'utf8' NULL DEFAULT NULL ;

insert into ipm.ipm_sys_config(property,property_value) 
SELECT 'CPQ_EXCEL_JIANAN','出荷兰,香港' from dual
where not exists (select 1 from ipm.ipm_sys_config where property = 'CPQ_EXCEL_JIANAN');
insert into ipm.ipm_sys_config(property,property_value) 
SELECT 'CPQ_EXCEL_JIANAN_SPECIAL','01' from dual
where not exists (select 1 from ipm.ipm_sys_config where property = 'CPQ_EXCEL_JIANAN_SPECIAL');
ALTER TABLE `ipm`.`ipm_sys_config` 
ADD UNIQUE INDEX `property_UNIQUE` (`property` ASC);
ALTER TABLE `ipm`.`ipm_sys_config` 
ADD INDEX `ipm_sys_config_property_index` (`property` ASC);

/*时间字段*/
ALTER TABLE `ipm`.`ipm_biz_cpq_file` 
ADD COLUMN `upload_date` TIMESTAMP NULL AFTER `version`;

/*增加服装类型配置*/
ALTER TABLE `ipm`.`ipm_biz_cpq_data_dictionary` 
CHANGE COLUMN `value` `value` VARCHAR(45) CHARACTER SET 'utf8' NULL DEFAULT NULL ;

insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value)
select 2,'MALE','男装' from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 2 and t.property = 'MALE');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value)
select 2,'FEMALE','女装' from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 2 and t.property = 'FEMALE');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value)
select 2,'BOY','男装' from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 2 and t.property = 'BOY');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value)
select 2,'GIRL','男装' from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 2 and t.property = 'GIRL');

/*字典增加排序*/
ALTER TABLE `ipm`.`ipm_biz_cpq_data_dictionary` 
ADD COLUMN `seq` INT NULL AFTER `value`;

/*size配置*/
/*男装size*/
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'MALE','Size S',1 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'MALE' and t.value = 'Size S');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'MALE','Size M',2 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'MALE' and t.value = 'Size M');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'MALE','Size L',3 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'MALE' and t.value = 'Size L');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'MALE','Size XL',4 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'MALE' and t.value = 'Size XL');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'MALE','Size XXL',5 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'MALE' and t.value = 'Size XXL');
/*女装size*/
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'FEMALE','Size P',1 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'FEMALE' and t.value = 'Size P');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'FEMALE','Size 1',2 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'FEMALE' and t.value = 'Size 1');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'FEMALE','Size 2',3 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'FEMALE' and t.value = 'Size 2');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'FEMALE','Size 3',4 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'FEMALE' and t.value = 'Size 3');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'FEMALE','Size 4',5 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'FEMALE' and t.value = 'Size 4');
/*男童size*/
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'BOY','Size 4',1 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'BOY' and t.value = 'Size 4');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'BOY','Size 6',2 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'BOY' and t.value = 'Size 6');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'BOY','Size 8',3 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'BOY' and t.value = 'Size 8');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'BOY','Size 10',4 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'BOY' and t.value = 'Size 10');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'BOY','Size 12',5 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'BOY' and t.value = 'Size 12');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'BOY','Size 14',6 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'BOY' and t.value = 'Size 14');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'BOY','Size 16',7 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'BOY' and t.value = 'Size 16');
/*女童size*/
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'GIRL','Size 4',1 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'GIRL' and t.value = 'Size 4');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'GIRL','Size 6',2 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'GIRL' and t.value = 'Size 6');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'GIRL','Size 8',3 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'GIRL' and t.value = 'Size 8');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'GIRL','Size 10',4 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'GIRL' and t.value = 'Size 10');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'GIRL','Size 12',5 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'GIRL' and t.value = 'Size 12');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'GIRL','Size 14',6 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'GIRL' and t.value = 'Size 14');
insert into ipm.ipm_biz_cpq_data_dictionary(type, property, value, seq)
select 3,'GIRL','Size 16',7 from dual
where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary t where t.type = 3 and t.property = 'GIRL' and t.value = 'Size 16');

/*cpq 文件增加衣服类型 */
ALTER TABLE `ipm`.`ipm_biz_cpq_file` 
ADD COLUMN `clothing_type` INT NULL AFTER `upload_date`;

/*增加item size 类型*/
ALTER TABLE `ipm`.`ipm_biz_cpq_print_order_item` 
ADD COLUMN `size_p` INT NULL AFTER `size_xxl`,
ADD COLUMN `size_1` INT NULL AFTER `size_p`,
ADD COLUMN `size_2` INT NULL AFTER `size_1`,
ADD COLUMN `size_3` INT NULL AFTER `size_2`,
ADD COLUMN `size_4` INT NULL AFTER `size_3`,
ADD COLUMN `size_6` INT NULL AFTER `size_4`,
ADD COLUMN `size_8` INT NULL AFTER `size_6`,
ADD COLUMN `size_10` INT NULL AFTER `size_8`,
ADD COLUMN `size_12` INT NULL AFTER `size_10`,
ADD COLUMN `size_14` INT NULL AFTER `size_12`,
ADD COLUMN `size_16` INT NULL AFTER `size_14`;

UPDATE `ipm`.`ipm_biz_cpq_data_dictionary` SET `value`='男童' WHERE `id`='45';
UPDATE `ipm`.`ipm_biz_cpq_data_dictionary` SET `value`='女童' WHERE `id`='46';
