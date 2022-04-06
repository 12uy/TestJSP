package com.dvduy.filter;

import com.dvduy.model.UserModel;
import com.dvduy.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();
        if(requestURI.startsWith("/admin")){
            UserModel user = (UserModel) SessionUtil.getInstance().getValue(httpRequest, "USERMODEL");
            if(user != null){
                if (user.getRole().getName().equals("ADMIN")){
                    chain.doFilter(request, response);
                } else{
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/login?action=login&message=not_permission&alert=danger");
                }
            }else{
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login?action=login&message=not_login&alert=danger");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
