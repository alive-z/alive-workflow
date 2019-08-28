package com.mizhi.btbs.api;

import com.mizhi.btbs.domain.dto.WfCurrentInfo;
import com.mizhi.btbs.support.ServiceClientTestCaseSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author chaobo
 * @since 2019/5/16
 */
public class WorkFlowApiTest extends ServiceClientTestCaseSupport {
    private static Logger LOGGER = LoggerFactory.getLogger(WorkFlowApiTest.class);

    @Autowired
    private WorkFlowApi workFlowApi;

    @Test
    public void testStart() {
        String groupId = "333,444";
        String wfType = "order";
        Long bizId = 777L;

        workFlowApi.start(groupId,wfType,bizId);
        LOGGER.info("==============testStart is Success!!!!");
    }

    @Test
    public void testNext() {
        String groupId = "333,444";
        String wfType = "order";
        Long bizId = 777L;
        String currentGroupId = "333";

        workFlowApi.next(groupId, currentGroupId, bizId, wfType);
        LOGGER.info("==============testNext is Success!!!!");
    }

    @Test
    public void testRollback() {
        String groupId = "333,444";
        String wfType = "order";
        Long bizId = 777L;
        String currentGroupId = "333";

        workFlowApi.rollback(groupId, currentGroupId, bizId, wfType);
        LOGGER.info("==============testRollback is Success!!!!");
    }

    @Test
    public void testGetCurrent() {
        String wfType = "order";
        Long bizId = 777L;
        WfCurrentInfo wfCurrentInfo = workFlowApi.current(wfType, bizId);
        LOGGER.info("==============testGetCurrent result = {}", wfCurrentInfo);
    }

    @Test
    public void testGetCurrentList(){
        String wfType = "order";

        Collection<Number> bizIdList = Arrays.asList(777L, 4444L);

        Map<Number, WfCurrentInfo> wfCurrentInfoMap = workFlowApi.currentMaps(wfType, bizIdList);
        LOGGER.info("==============testGetCurrentList result = {}", wfCurrentInfoMap);

    }

}
