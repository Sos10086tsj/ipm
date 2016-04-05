package com.chinesedreamer.ipm.domain.system.config.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.chinesedreamer.ipm.domain.base.model.IpmEntity;

/**
 * Description: 
 * @author Paris
 * @date Jun 3, 20159:34:17 AM
 * @version beta
 */
@Entity
@Table(name = "ipm_sys_config")
public class IpmConfig extends IpmEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3888447407066119918L;

	@Column
	private String property;//配置主键，唯一
	
	@Column(name = "property_value")
	private String propertyValue;

	public String getProperty() {
		return property;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	
	
}
