package com.mizhi.btbs.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
    private static final Logger log = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void test() {
        log.warn(">>>>>>>>>>> ");
    }
}
