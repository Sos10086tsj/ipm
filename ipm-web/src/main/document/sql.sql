--默认 UTF -8 
ALTER TABLE `ipm`.`ipm_sys_supp_attachment` 
CHANGE COLUMN `file_name` `file_name` VARCHAR(200) CHARACTER SET 'utf8' NULL DEFAULT NULL ;
ALTER TABLE `ipm`.`ipm_sys_supp_attachment` 
CHANGE COLUMN `file_path` `file_path` VARCHAR(200) CHARACTER SET 'utf8' NULL DEFAULT NULL ;

