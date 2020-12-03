package com.myoldschool.manager.zuulfilter;

import com.myoldschool.manager.AppConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

public class ZulErrorFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return AppConstant.ERROR_FILTER;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        return null;
    }
}
