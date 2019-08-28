package com.mizhi.btbs.support;

import com.mizhi.btbs.api.WorkFlowApi;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ServiceClientTestBeansConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceClientTestCaseSupport {

}
