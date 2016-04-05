package com.chinesedreamer.ipm.domain.system.company.info.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.chinesedreamer.ipm.domain.base.model.IpmVersionEntity;
import com.chinesedreamer.ipm.domain.system.company.model.Company;

/**
 * Description: 
 * @author Paris
 * @date Mar 26, 20155:22:54 PM
 * @version beta
 */
@Entity
@Table(name = "ipm_sys_company_info")
public class CompanyInfo extends IpmVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8140118347792089825L;

	/**
	 * 客户外键
	 */
	@Column(name = "company_id")
	private Long companyId;
	
	/**
	 * 规模
	 */
	@Column(name = "scale")
	private Long scale;
	
	/**
	 * 行业
	 */
	@Column
	private Long industry;
	
	/**
	 * 联系人邮箱
	 */
	@Column(name = "contact_email")
	private String contactEmail;
	
	/**
	 * 联系人电话
	 */
	@Column(name = "contact_phone")
	private String contactPhone;
	
	/** 外键 **/
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_code", referencedColumnName = "code", insertable = false, updatable = false)
	private Company company;

	public Long getCompanyId() {
		return companyId;
	}

	public Long getScale() {
		return scale;
	}

	public Long getIndustry() {
		return industry;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public void setScale(Long scale) {
		this.scale = scale;
	}

	public void setIndustry(Long industry) {
		this.industry = industry;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	
}
