package com.ysla.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

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

    @Override
    protected boolean pathsMatch(String path, ServletRequest request) {
        String requestURI = getPathWithinApplication(request);
        System.out.println("Attempting to match pattern '"+path+"' with current requestURI '"+requestURI+"'...");

        // path: url==method eg: http://api/menu==GET   需要解析出path中的url和httpMethod
        String[] strings = path.split("--");
        if (strings.length <= 1) {
            // 分割出来只有URL
            return pathsMatch(strings[0], requestURI);
        } else {
            // 分割出url+httpMethod,判断httpMethod和request请求的method是否一致,不一致直接false
            String httpMethod = WebUtils.toHttp(request).getMethod().toUpperCase();
            return httpMethod.equals(strings[1].toUpperCase()) && pathsMatch(strings[0], requestURI);
        }
    }
}
