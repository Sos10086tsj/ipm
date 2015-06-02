--
CREATE DATABASE `ipm` /*!40100 DEFAULT CHARACTER SET latin1 */;
--附件
CREATE TABLE `ipm_sys_supp_attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(200) DEFAULT NULL,
  `file_path` varchar(200) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `upload_date` timestamp NULL DEFAULT NULL,
  `upload_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--file对象
CREATE TABLE `ipm_biz_cpq_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `attach_ref_id` bigint(20) DEFAULT NULL,
  `file_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `version` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `ipm_biz_cpq_print_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(45) DEFAULT NULL,
  `style_no` varchar(45) DEFAULT NULL,
  `maker` varchar(2000) DEFAULT NULL,
  `customer` varchar(2000) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `quality` varchar(45) DEFAULT NULL,
  `filling` varchar(45) DEFAULT NULL,
  `price` varchar(45) DEFAULT NULL,
  `delivery` varchar(45) DEFAULT NULL,
  `weight` varchar(45) DEFAULT NULL,
  `washing` varchar(45) DEFAULT NULL,
  `packing` varchar(45) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  `brand_name` varchar(45) DEFAULT NULL,
  `shipping_mode` varchar(45) DEFAULT NULL,
  `terms_payment` varchar(45) DEFAULT NULL,
  `version` bigint(20) DEFAULT '0',
  `pdf_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2961 DEFAULT CHARSET=latin1;

CREATE TABLE `ipm_biz_cpq_print_order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  `size_s` int(11) DEFAULT NULL,
  `size_m` int(11) DEFAULT NULL,
  `size_l` int(11) DEFAULT NULL,
  `size_xl` int(11) DEFAULT NULL,
  `size_xxl` int(11) DEFAULT NULL,
  `version` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4846 DEFAULT CHARSET=latin1;

CREATE TABLE `ipm_biz_cpq_data_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `property` varchar(45) DEFAULT NULL,
  `value` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
commit;

INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'11','11-ginger');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'17','17-prune');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'21','21-retro orange');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'24','24-dijon');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'36','36-mod blue');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'42','42-soda lite blue');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'44','44-burgundy');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'45','45-boradeaux');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'48','48-denim blue');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'50','50-blue');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'51','51-indigo');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'52','52-genuine indigo');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'57','57-navy');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'58','58-night');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'61','61-jade');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'69','69-cranberry');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'72','72-bottle green');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'77','77-steel');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'78','78-olive');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'80','80-mint');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'90','90-black');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'95','95-antra');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'98','98-salte grey');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'120','120-bone melange');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'160','160-faded plum melange');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'450','450-bordeaux melange');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'690','960-blue moon melange');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'720','720-bottle green melange');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'900','900-black melange');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'960','960-charcoal melange');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'970','970-grey melange');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'00','00-whithe');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'02','02-kit');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'03','03-offwhite');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'050','050-ecru melange');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'A','A-dessin A');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'B','B-dessin B');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'C','C-dessin C');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'D','D-dessin D');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'E','E-dessin E');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'F','F-dessin F');
INSERT INTO `ipm_biz_cpq_data_dictionary` (`type`,`property`,`value`) VALUES (1,'G','G-dessin G');
commit;