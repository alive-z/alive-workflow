package com.mizhi.btbs.core.impl;

import com.mizhi.btbs.domain.model.WfReDeployment;
import com.mizhi.btbs.domain.model.WfReProcdef;
import com.mizhi.btbs.domain.model.WfRuTask;
import com.mizhi.btbs.domain.query.WfReProcdefQuery;
import com.mizhi.btbs.manager.WfReDeploymentManager;
import com.mizhi.btbs.manager.WfReProcdefManager;
import com.mizhi.btbs.manager.WfRuTaskManager;
import com.mizhi.btbs.util.IDUtil;
import com.mizhi.btbs.util.StepUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkFlowRollBackImpl {
    private static Logger log = LoggerFactory.getLogger(WorkFlowRollBackImpl.class);

    @Autowired
    private WfReDeploymentManager wfReDeploymentManager;
    @Autowired
    private WfReProcdefManager wfReProcdefManager;
    @Autowired
    private WfRuTaskManager wfRuTaskManager;

    @Transactional
    public void rollbackToHead(String groupId, String currentGroupId,String wfType, String bizId) {
        WfReDeployment wfReDeployment = wfReDeploymentManager.singleResult(groupId, wfType);
        if (wfReDeployment == null) {
            log.info("WfReDeployment not exist , groupId: {} , wfType:{} , bizId: {}", groupId, wfType, bizId);
            return;
        }

        WfReProcdefQuery wfReProcdefQuery = WfReProcdefQuery.create()
                .setGroupId(currentGroupId)
                .setReDeployId(wfReDeployment.getId())
                .setStepCurrent(StepUtil.HEAD);

        WfReProcdef wfReProcdef = wfReProcdefManager.singleResult(wfReProcdefQuery);
        if (wfReProcdef == null) {
            log.info("WfReProcdef head not exist , groupId: {} , wfType:{} , bizId: {}", groupId, wfType, bizId);
            return;
        }

        wfRuTaskManager.delete(wfType, bizId);

        WfRuTask wfRuTask = new WfRuTask(IDUtil.generate());
        wfRuTask.setCurrentStep(StepUtil.HEAD);
        wfRuTask.setGroupId(wfReProcdef.getGroupId());
        wfRuTask.setBizId(wfReProcdef.getGroupId());
        wfRuTask.setWfType(wfType);
        wfRuTask.setBizId(bizId);
        wfRuTask.setReDeployId(wfReDeployment.getId());
        wfRuTask.setReProcdefId(wfReProcdef.getId());

        wfRuTaskManager.insert(wfRuTask);
    }

    public void rollbackToPre() {

    }

    public void rollbackByWf() {

    }
}
