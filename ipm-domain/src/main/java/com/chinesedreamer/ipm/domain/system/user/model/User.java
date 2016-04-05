package com.chinesedreamer.ipm.domain.system.user.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.chinesedreamer.ipm.domain.base.model.IpmVersionEntity;
import com.chinesedreamer.ipm.domain.system.company.model.Company;

/**
 * Description: 
 * @author Paris
 * @date Mar 27, 201512:59:40 PM
 * @version beta
 */
@Entity
@Table(name = "ipm_sys_user")
public class User extends IpmVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3247381729984860506L;

	@Column
	private String username;
	
	@Column
	private String salt;
	
	@Column
	private String password;
	
	@Column
	private String name;
	
	@Column(name = "pinyin")
	private String pinYin;
	
	@Column(name = "pinyin_first_letter")
	private String pinYinFirstLetter;
	
	@Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	private UserStatus status = UserStatus.ACTIVE;
	
	@Column(name = "register_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date registerDate = new Date();
	
	@Column(name = "company_id")
	private Long companyId;
	
	//ORM
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Company company;

	public String getUsername() {
		return username;
	}

	public String getSalt() {
		return salt;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getPinYin() {
		return pinYin;
	}

	public String getPinYinFirstLetter() {
		return pinYinFirstLetter;
	}

	public UserStatus getStatus() {
		return status;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public Company getCompany() {
		return company;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}

	public void setPinYinFirstLetter(String pinYinFirstLetter) {
		this.pinYinFirstLetter = pinYinFirstLetter;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	
}
