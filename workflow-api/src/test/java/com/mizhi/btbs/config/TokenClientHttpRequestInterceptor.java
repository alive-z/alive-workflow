package com.mizhi.btbs.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.io.IOException;

/**
 * Created on 2017/9/5.
 *
 * @author evan.shen
 */
public class TokenClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenClientHttpRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);

        LOGGER.info("=====>> HttpRequest[{}],uri:{}", request.getHeaders(),request.getURI());

        return execution.execute(requestWrapper, body);
    }
}
