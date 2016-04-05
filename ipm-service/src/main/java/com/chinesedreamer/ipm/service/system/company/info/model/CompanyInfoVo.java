package com.chinesedreamer.ipm.service.system.company.info.model;

/**
 * Description:
 * 
 * @author Paris
 * @date Mar 27, 201511:36:49 AM
 * @version beta
 */
public class CompanyInfoVo {
	private Long id;
	private Long companyId;
	private Long scale;
	private String scaleDescription;
	private Long industry;
	private String industryDescription;
	private String email;
	private String phone;
	public Long getId() {
		return id;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public Long getScale() {
		return scale;
	}
	public String getScaleDescription() {
		return scaleDescription;
	}
	public Long getIndustry() {
		return industry;
	}
	public String getIndustryDescription() {
		return industryDescription;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public void setScale(Long scale) {
		this.scale = scale;
	}
	public void setScaleDescription(String scaleDescription) {
		this.scaleDescription = scaleDescription;
	}
	public void setIndustry(Long industry) {
		this.industry = industry;
	}
	public void setIndustryDescription(String industryDescription) {
		this.industryDescription = industryDescription;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
