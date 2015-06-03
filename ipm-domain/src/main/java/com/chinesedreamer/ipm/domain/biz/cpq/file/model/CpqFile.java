package com.chinesedreamer.ipm.domain.biz.cpq.file.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.ipm.domain.base.model.IpmLogicDeleteEntity;
import com.chinesedreamer.ipm.domain.biz.cpq.file.constant.CpqFileClothingType;
import com.chinesedreamer.ipm.domain.biz.cpq.file.constant.CpqFileType;
import com.chinesedreamer.ipm.domain.biz.cpq.printorder.model.CpqOrder;
import com.chinesedreamer.ipm.domain.supp.attachment.model.Attachment;

@Entity
@Table(name = "ipm_biz_cpq_file")
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
	
	@Column(name = "attach_ref_id")
	private Long attachRefId;//附件id
	
	@Column(name = "upload_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date uploadDate;
	
	@Column(name = "clothing_type")
	@Enumerated(EnumType.ORDINAL)
	private CpqFileClothingType clothingType;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attach_ref_id", referencedColumnName = "id", insertable = false, updatable =false)
	private Attachment attachment;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "pdf")
	private List<CpqOrder> orders;
}
