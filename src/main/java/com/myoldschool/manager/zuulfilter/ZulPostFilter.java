package com.myoldschool.manager.zuulfilter;

import com.myoldschool.manager.AppConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

public class ZulPostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return AppConstant.POST_FILTER;
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
        return null;
    }
}
