package com.pika.electricrat.web.filter;


import com.pika.electricrat.fileinclude.FileIncludeServlet;
import com.pika.electricrat.unsafeupload.bo.Impl.FileServerImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter(urlPatterns = {"/*"})
public class ForbiddenAccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestPath = URLDecoder.decode(
                httpRequest.getRequestURI().substring(
                        httpRequest.getContextPath().length()).replaceAll("/{2,}", "/"), StandardCharsets.UTF_8);
        System.out.println(requestPath);
        if (requestPath.startsWith(FileIncludeServlet.FORBIDDEN_PATH)
                || requestPath.startsWith(FileServerImpl.TEMPLATES_DIRECTORY)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else if (requestPath.contains(FileIncludeServlet.FORBIDDEN_PATH)
                || requestPath.contains(FileServerImpl.TEMPLATES_DIRECTORY)){
            if (requestPath.contains("../")
                    || requestPath.contains("./")
                    || requestPath.contains("..\\")
                    || requestPath.contains(".\\")
                    || requestPath.contains(";")){
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
