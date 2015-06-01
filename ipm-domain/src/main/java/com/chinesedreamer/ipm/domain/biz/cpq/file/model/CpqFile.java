package com.chinesedreamer.ipm.domain.biz.cpq.file.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.ipm.domain.base.model.IpmLogicDeleteEntity;
import com.chinesedreamer.ipm.domain.biz.cpq.file.constant.CpqFileType;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.model.CpqOrder;

@Entity
@Table(name = "ipm_biz_cpq_files")
public @Getter @Setter class CpqFile extends IpmLogicDeleteEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5931017022779188881L;
	
	@Column(name = "type")
	@Enumerated(EnumType.ORDINAL)
	private CpqFileType type;//类型

	@Column(name = "file_name")
	private String fileName;//文件名
	
	@Column(name = "file_path")
	private String filePath;//文件路径
	
	@Column(name = "file_size")
	private Long fileSize;//文件大小
	
	@Column(name = "upload_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date uploadDate;//上传时间
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "pdf")
	private List<CpqOrder> orders;
}
