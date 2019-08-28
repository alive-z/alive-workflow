package com.mizhi.btbs;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author chaobo
 * @since 2019/5/15
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.mizhi.btbs"})
@ComponentScan(basePackages = {
         "com.mizhi.btbs.core",
         "com.mizhi.btbs.service",
         "com.mizhi.btbs.manager",
         "com.mizhi.btbs.config",
         "com.mizhi.btbs.controller",
})
public class WorkFlowServiceApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(WorkFlowServiceApplication.class);
        builder.web(true);
        ApplicationContext ctx = builder.run(args);
        DispatcherServlet dispatcherServlet = ctx.getBean(DispatcherServlet.class);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }
}
