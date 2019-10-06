package com.list.demo.filter;

import com.alibaba.fastjson.JSON;
import com.list.demo.entity.vo.MonitorData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author cughu
 * @Description
 * @Date 20:20 2019/8/3
 **/
//@Component
//@WebFilter(urlPatterns = "/robot")
public class LogFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        throw new UnsupportedOperationException();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        RequestWrapper requestWrapper = new RequestWrapper(httpServletRequest);
        ResponseWrapper responseWrapper = new ResponseWrapper(httpServletResponse);

        filterChain.doFilter(requestWrapper, responseWrapper);

        MonitorData monitorData = new MonitorData();
        String params = null;
        String val = null;

        String meth = httpServletRequest.getMethod();

        //delete 和put 请求暂时未处理
        if ("POST".equals(meth)) {
            params = requestWrapper.getBody();
        } else {
            params = JSON.toJSONString(httpServletRequest.getParameterMap());
        }
        logger.info(params);

        monitorData.setReqUrl(httpServletRequest.getRequestURL().toString());
        monitorData.setInterType("");

        byte[] bytes = responseWrapper.getBytes();
        val = new String(bytes, "UTF-8");

        logger.info(val);

        //将数据 再写到 response 中
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();

    }

    @Override
    public void destroy() {
//        throw new UnsupportedOperationException();
    }
}
