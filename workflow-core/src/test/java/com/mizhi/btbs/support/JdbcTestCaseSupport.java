package com.mizhi.btbs.support;


import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JdbcTestBeansConfig.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class JdbcTestCaseSupport {
}
