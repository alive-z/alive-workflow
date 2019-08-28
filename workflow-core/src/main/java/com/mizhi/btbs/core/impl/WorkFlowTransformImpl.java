package com.mizhi.btbs.core.impl;

import com.mizhi.btbs.domain.model.WfReDeployment;
import com.mizhi.btbs.domain.model.WfReProcdef;
import com.mizhi.btbs.domain.model.WfRuTask;
import com.mizhi.btbs.domain.query.WfReProcdefQuery;
import com.mizhi.btbs.domain.query.WfRuTaskQuery;
import com.mizhi.btbs.enums.WfRuTaskStatusEnum;
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
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class WorkFlowTransformImpl {
    private static Logger log = LoggerFactory.getLogger(WorkFlowRollBackImpl.class);

    @Autowired
    private WfReDeploymentManager wfReDeploymentManager;
    @Autowired
    private WfRuTaskManager wfRuTaskManager;
    @Autowired
    private WfReProcdefManager wfReProcdefManager;

    @Transactional
    public void start(String groupId, String wfType, String bizId) {
        WfReDeployment wfReDeployment = wfReDeploymentManager.singleResult(groupId, wfType);
        if (wfReDeployment == null) {
            log.info("WfReDeployment not exist , groupId: {} , wfType:{} , bizId: {}", groupId, wfType, bizId);
            return;
        }

        WfReProcdefQuery wfReProcdefQuery = WfReProcdefQuery.create()
                .setReDeployId(wfReDeployment.getId())
                .setStepCurrent(StepUtil.HEAD);

        WfReProcdef wfReProcdef = wfReProcdefManager.singleResult(wfReProcdefQuery);
        if (wfReProcdef == null) {
            log.info("WfReProcdef head not exist , groupId: {} , wfType:{} , bizId: {}", groupId, wfType, bizId);
            return;
        }

        WfRuTask wfRuTask = new WfRuTask(IDUtil.generate());
        wfRuTask.setCurrentStep(StepUtil.HEAD);
        wfRuTask.setGroupId(wfReProcdef.getGroupId());
        wfRuTask.setWfType(wfType);
        wfRuTask.setBizId(bizId);
        wfRuTask.setReDeployId(wfReDeployment.getId());
        wfRuTask.setReProcdefId(wfReProcdef.getId());

        wfRuTaskManager.insert(wfRuTask);

    }

    @Transactional
    public boolean transform(String groupId, String currentGroupId, String wfType, String bizId) {

        WfReDeployment wfReDeployment = wfReDeploymentManager.singleResult(groupId, wfType);
        if (wfReDeployment == null) {
            log.info("WfReDeployment not exist , groupId: {} , wfType:{} , bizId: {}", groupId, wfType, bizId);
            return false;
        }

        WfRuTaskQuery wfRuTaskQuery = new WfRuTaskQuery();
        wfRuTaskQuery.setReDeployId(wfReDeployment.getId()).setWfType(wfType).setBizId(bizId);
        List<WfRuTask> wfRuTasks = wfRuTaskManager.queryList(wfRuTaskQuery);
        if (CollectionUtils.isEmpty(wfRuTasks)) {
            log.info("WfRuTask is empty , groupId: {} , wfType:{} , bizId: {}", groupId, wfType, bizId);
            return false;
        }

        boolean isEnd = false;
        long count = wfRuTasks.stream().filter(o -> !WfRuTaskStatusEnum.END.name().equals(o.getStatus())).count();
        if (count > 1) {
            currentTaskTransform(currentGroupId, wfType, bizId);
        } else {
            isEnd = transform(wfReDeployment.getId(), wfType, bizId);
        }
        return isEnd;
    }

    private void currentTaskTransform(String currentGroupId, String wfType, String bizId) {

        WfRuTaskQuery wfRuTaskQuery = new WfRuTaskQuery();
        wfRuTaskQuery.setWfType(wfType).setBizId(bizId).setGroupId(currentGroupId).setStatus(WfRuTaskStatusEnum.END.name());
        WfRuTask wfRuTask = wfRuTaskManager.singleResult(wfRuTaskQuery);
        if (wfRuTask == null) {
            log.info("wfRuTask is not exist , groupId: {} , wfType:{} , bizId: {}", currentGroupId, wfType, bizId);
            return;
        }

        WfRuTask newWfRuTask = new WfRuTask(wfRuTask.getId());
        newWfRuTask.setStatus(WfRuTaskStatusEnum.END.name());
        wfRuTaskManager.update(newWfRuTask);
    }

    private boolean transform(String reDeployId, String wfType, String bizId){

        WfRuTaskQuery wfRuTaskQuery = new WfRuTaskQuery();
        wfRuTaskQuery.setWfType(wfType).setBizId(bizId).setReDeployId(reDeployId).setStatus(WfRuTaskStatusEnum.END.name());
        List<WfRuTask> wfRuTasks = wfRuTaskManager.queryList(wfRuTaskQuery);
        if(CollectionUtils.isEmpty(wfRuTasks)){
            log.info("wfRuTask is empty ,wfType:{} , bizId: {}", wfType, bizId);
            return false;
        }

        wfRuTaskManager.delete(wfType, bizId);

        int nextStep = StepUtil.getNext(wfRuTasks.get(0).getCurrentStep());
        WfReProcdefQuery wfReProcdefQuery = WfReProcdefQuery.create()
                .setReDeployId(reDeployId)
                .setWfType(wfType)
                .setStepCurrent(StepUtil.getNext(wfRuTasks.get(0).getCurrentStep()));

        List<WfReProcdef> wfReProcdefs = wfReProcdefManager.queryList(wfReProcdefQuery);

        if(CollectionUtils.isEmpty(wfReProcdefs)){
            log.info("wfRuTask is empty ,wfType:{} , bizId: {}", wfType, bizId);
            return true;
        }

        for (WfReProcdef wfReProcdef : wfReProcdefs) {
            WfRuTask wfRuTask = new WfRuTask(IDUtil.generate());
            wfRuTask.setCurrentStep(nextStep);
            wfRuTask.setGroupId(wfReProcdef.getGroupId());
            wfRuTask.setBizId(wfReProcdef.getGroupId());
            wfRuTask.setWfType(wfType);
            wfRuTask.setBizId(bizId);
            wfRuTask.setReDeployId(reDeployId);
            wfRuTask.setReProcdefId(wfReProcdef.getId());

            wfRuTaskManager.insert(wfRuTask);
        }
        return false;
    }

}
