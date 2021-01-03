package com.myzulproxy.zuulfilter;

import com.myzulproxy.AppConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class ZulPostFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(ZulPostFilter.class);

    @Override
    public String filterType() {
        return AppConstant.POST_FILTER;
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
//        RequestContext ctx = RequestContext.getCurrentContext();
//        InputStream responseDataStream = ctx.getResponseDataStream();
//        try {
//            String responseAsString = StreamUtils.copyToString(responseDataStream, Charset.forName("UTF-8"));
//            log.info(String.format("Response before: %s", responseAsString));
            // Do want you want with your String response
//            responseAsString = "response updated";
            // Replace the response with the modified object
//            ctx.setResponseBody(responseAsString);
//            log.info(String.format("Response after: %s", responseAsString));
//        } catch (IOException e) {
//            log.warn("Error reading body", e);
//        }
        return null;
    }
}
