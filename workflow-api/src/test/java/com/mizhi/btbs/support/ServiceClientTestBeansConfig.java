package com.mizhi.btbs.support;

import com.mizhi.btbs.api.WorkFlowApi;
import com.mizhi.btbs.config.TokenClientHttpRequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "com.mizhi.btbs.api"
})
@EnableDiscoveryClient
public class ServiceClientTestBeansConfig{

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceClientTestBeansConfig.class);


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(ClientHttpRequestInterceptor tokenClientHttpRequestInterceptor) {

        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(tokenClientHttpRequestInterceptor);

        restTemplate.setInterceptors(interceptors);

        LOGGER.info(">>>> RestTemplate inited");

        return restTemplate;
    }

    @Bean
    public TokenClientHttpRequestInterceptor tokenClientHttpRequestInterceptor() {
        TokenClientHttpRequestInterceptor tokenClientHttpRequestInterceptor = new TokenClientHttpRequestInterceptor();
        return tokenClientHttpRequestInterceptor;
    }

    @Bean
    public WorkFlowApi workFlowApi(RestTemplate restTemplate) {
        WorkFlowApi workFlowApi = new WorkFlowApi(restTemplate);
        return workFlowApi;
    }


}


