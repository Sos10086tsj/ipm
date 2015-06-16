--默认 UTF -8 
ALTER TABLE `ipm`.`ipm_sys_supp_attachment` 
CHANGE COLUMN `file_name` `file_name` VARCHAR(200) CHARACTER SET 'utf8' NULL DEFAULT NULL ;
ALTER TABLE `ipm`.`ipm_sys_supp_attachment` 
CHANGE COLUMN `file_path` `file_path` VARCHAR(200) CHARACTER SET 'utf8' NULL DEFAULT NULL ;

ALTER TABLE `ipm`.`ipm_sys_config` 
CHANGE COLUMN `property_value` `property_value` VARCHAR(255) CHARACTER SET 'utf8' NULL DEFAULT NULL ;

INSERT INTO `ipm`.`ipm_sys_config` (`property`, `property_value`) VALUES ('CPQ_ORDER_TYPE_HK', '11,17,18,30,31,36,37,38,39,40,41,42,43,44,45,46');
INSERT INTO `ipm`.`ipm_sys_config` (`property`, `property_value`) VALUES ('CPQ_ORDER_TYPE_NETHERLANDS', '01,04,05,12,13,15,16,21,22,23,24');

ALTER TABLE `ipm`.`ipm_biz_cpq_manufactory_order_item` 
ADD COLUMN `order_no_type` VARCHAR(45) NULL AFTER `order_no`;

UPDATE `ipm`.`ipm_sys_config` SET `property`='CPQ_ORDER_TYPE_HK' WHERE `id`='5';
UPDATE `ipm`.`ipm_sys_config` SET `property`='CPQ_ORDER_TYPE_NETHERLANDS' WHERE `id`='6';

ALTER TABLE `ipm`.`ipm_biz_cpq_print_order` 
ADD COLUMN `order_no_type` VARCHAR(45) NULL AFTER `order_no`;


ALTER TABLE `ipm`.`ipm_biz_cpq_data_dictionary` 
CHANGE COLUMN `property` `property` VARCHAR(45) CHARACTER SET 'utf8' NULL DEFAULT NULL ;

ALTER TABLE `ipm`.`ipm_biz_cpq_manufactory_order_item` 
CHANGE COLUMN `color` `color` VARCHAR(45) CHARACTER SET 'utf8' NULL DEFAULT NULL ;


insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'30','AUSTRALIA' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '30');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'31','CA_RETAIL' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '31');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'32','CA_WHS' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '32');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'33','HONGKANG' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '33');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'34','EMIRATES' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '34');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'35','INDIA WEB' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '35');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'36','INDIA WHS' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '36');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'37','INDONESIA' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '37');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'38','JAPAN' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '38');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'39','MEXICO' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '39');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'40','NWZ' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '40');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'41','PANAMA' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '41');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'42','PHILIPINNES' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '42');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'43','S_AFRICA' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '43');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'44','S_ARABIA' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '44');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'45','SINGAPORE' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '45');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'46','THAILAND' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '46');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'11','USA' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '11');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'17','USRET' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '17');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'18','USWEB' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '18');
insert into ipm.ipm_biz_cpq_data_dictionary(type,property,value) select 4,'19','USNOS' from dual where not exists (select 1 from ipm.ipm_biz_cpq_data_dictionary where type = 4 and property = '19');
