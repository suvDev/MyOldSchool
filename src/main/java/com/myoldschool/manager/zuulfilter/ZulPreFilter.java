package com.myoldschool.manager.zuulfilter;

import com.myoldschool.manager.AppConstant;
import com.netflix.zuul.FilterFactory;
import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.FilterType;

import javax.servlet.http.HttpServletRequest;

public class ZulPreFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(ZulPreFilter.class);

    @Override
    public String filterType() {
        return AppConstant.PRE_FILTER;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("Pre filter working!!");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        return null;
    }
}
