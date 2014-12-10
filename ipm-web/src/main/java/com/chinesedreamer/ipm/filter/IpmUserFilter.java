package com.chinesedreamer.ipm.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;

import org.apache.shiro.web.filter.authc.UserFilter;

public class IpmUserFilter extends UserFilter{
	private @Getter @Setter String defaultSuccessUrl;


    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        String isAjax =httpRequest.getHeader("x-requested-with");
        String uri = httpRequest.getRequestURI();
        if(!uri.equals(this.getDefaultSuccessUrl()) && !uri.startsWith(this.getDefaultSuccessUrl()) && !uri.equals("/")&&isAjax==null){
            httpRequest.getSession().setAttribute("defaultPage", uri);
            httpResponse.sendRedirect(httpResponse.encodeRedirectURL(this.getDefaultSuccessUrl()));
        }else{
            super.onAccessDenied(request,response);
        }
        return false;
    }
}
