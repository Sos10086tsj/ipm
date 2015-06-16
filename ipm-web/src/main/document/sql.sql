--默认 UTF -8 
ALTER TABLE `ipm`.`ipm_sys_supp_attachment` 
CHANGE COLUMN `file_name` `file_name` VARCHAR(200) CHARACTER SET 'utf8' NULL DEFAULT NULL ;
ALTER TABLE `ipm`.`ipm_sys_supp_attachment` 
CHANGE COLUMN `file_path` `file_path` VARCHAR(200) CHARACTER SET 'utf8' NULL DEFAULT NULL ;

ALTER TABLE `ipm`.`ipm_sys_config` 
CHANGE COLUMN `property_value` `property_value` VARCHAR(255) CHARACTER SET 'utf8' NULL DEFAULT NULL ;

INSERT INTO `ipm`.`ipm_sys_config` (`property`, `property_value`) VALUES ('CPQ_COLOR_HK', '11,17,18,30,31,36,37,38,39,40,41,42,43,44,45,46');
INSERT INTO `ipm`.`ipm_sys_config` (`property`, `property_value`) VALUES ('CPQ_COLOR_NETHERLANDS', '01,04,05,12,13,15,16,21,22,23,24');

ALTER TABLE `ipm`.`ipm_biz_cpq_manufactory_order_item` 
ADD COLUMN `order_no_type` VARCHAR(45) NULL AFTER `order_no`;

UPDATE `ipm`.`ipm_sys_config` SET `property`='CPQ_ORDER_TYPE_HK' WHERE `id`='5';
UPDATE `ipm`.`ipm_sys_config` SET `property`='CPQ_ORDER_TYPE_NETHERLANDS' WHERE `id`='6';

ALTER TABLE `ipm`.`ipm_biz_cpq_print_order` 
ADD COLUMN `order_no_type` VARCHAR(45) NULL AFTER `order_no`;


ALTER TABLE `ipm`.`ipm_biz_cpq_data_dictionary` 
CHANGE COLUMN `property` `property` VARCHAR(45) CHARACTER SET 'utf8' NULL DEFAULT NULL ;

