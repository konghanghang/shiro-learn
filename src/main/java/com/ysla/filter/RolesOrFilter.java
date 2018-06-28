package com.ysla.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 如果是授权相关继承org.apache.shiro.web.filter.authz.AuthorizationFilter
 * 如果和认证相关继承org.apache.shiro.web.filter.authc.AuthenticatingFilter
 */
public class RolesOrFilter extends AuthorizationFilter {
    /**
     *
     * @param request
     * @param response
     * @param mappedValue   roles["admin","admin1"] 值为中括号里边的值
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request,response);
        String[] roles = (String[]) mappedValue;
        if (roles == null || roles.length == 0){
            return true;
        }
        for (String role : roles) {
            if (subject.hasRole(role)){
                return true;
            }
        }
        return false;
    }
}
