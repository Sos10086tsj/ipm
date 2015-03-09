package com.chinesedreamer.ipm.filter;

import lombok.Getter;
import lombok.Setter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class IpmFormAuthenticationFilter extends FormAuthenticationFilter{
	private @Getter @Setter String defaultSuccessUrl;
}
