package com.chinesedreamer.ipm.service.supp.dictionary.model;

/**
 * Description: 
 * @author Paris
 * @date Mar 27, 201512:34:43 PM
 * @version beta
 */
public class DictionaryVo {
	private Long id;
	private String type;
	private String typeDescription;
	private String code;
	private String name;
	private String status;
	private String statusDescription;
	public Long getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public String getTypeDescription() {
		return typeDescription;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public String getStatus() {
		return status;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	
	
}
