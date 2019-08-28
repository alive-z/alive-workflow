package com.mizhi.btbs.controller;

import com.mizhi.btbs.core.impl.WorkFlowRollBackImpl;
import com.mizhi.btbs.core.impl.WorkFlowTransformImpl;
import com.mizhi.btbs.domain.dto.WfCurrentInfo;
import com.mizhi.btbs.domain.model.WfReProcdef;
import com.mizhi.btbs.domain.model.WfRuTask;
import com.mizhi.btbs.domain.query.WfReProcdefQuery;
import com.mizhi.btbs.domain.query.WfRuTaskQuery;
import com.mizhi.btbs.manager.WfReProcdefManager;
import com.mizhi.btbs.manager.WfRuTaskManager;
import com.mizhi.btbs.response.ApiResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chaobo
 * @since 2019/5/14
 */
@RestController
@RequestMapping("wf-api")
public class WorkFlowApiController {
    private static Logger log = LoggerFactory.getLogger(WorkFlowApiController.class);

    @Autowired
    private WorkFlowRollBackImpl workFlowRollBackImpl;
    @Autowired
    private WorkFlowTransformImpl workFlowTransformImpl;
    @Autowired
    private WfRuTaskManager wfRuTaskManager;
    @Autowired
    private WfReProcdefManager wfReProcdefManager;

    @PostMapping("start")
    public ApiResponse start(@RequestParam("groupId") String groupId,
                             @RequestParam("wfType") String wfType,
                             @RequestParam("bizId") String bizId) {

        workFlowTransformImpl.start(groupId, wfType, bizId);
        return ApiResponse.create();
    }

    @PostMapping("transform")
    public ApiResponse<Boolean> transform(@RequestParam("groupId") String groupId,
                                 @RequestParam("currentGroupId") String currentGroupId,
                                 @RequestParam("bizId") String bizId,
                                 @RequestParam("wfType") String wfType,
                                 @RequestParam(value = "rollBack", defaultValue = "false", required = false) Boolean rollBack) {
        boolean isEnd = false;
        if (!rollBack) {
            isEnd = workFlowTransformImpl.transform(groupId, currentGroupId, wfType, bizId);
        } else {
            workFlowRollBackImpl.rollbackToHead(groupId, currentGroupId, wfType, bizId);
        }
        return ApiResponse.create(isEnd);
    }

    @GetMapping("current")
    public ApiResponse current(@RequestParam("bizId") String bizId,
                               @RequestParam("wfType") String wfType) {
        if (StringUtils.isBlank(bizId)) {
            log.warn("bizId is empty");
            return ApiResponse.create();
        }

        if (StringUtils.isBlank(wfType)) {
            log.warn("wfType is empty");
            return ApiResponse.create();
        }

        WfRuTaskQuery wfRuTaskQuery = new WfRuTaskQuery();
        wfRuTaskQuery.setBizId(bizId);
        wfRuTaskQuery.setWfType(wfType);

        WfRuTask wfRuTask = wfRuTaskManager.singleResult(wfRuTaskQuery);
        if (wfRuTask == null) {
            log.warn("wfRuTask is not exist, bizId:{}, wfType:{}", bizId, wfType);
            return ApiResponse.create();
        }

        WfCurrentInfo wfCurrentInfo = new WfCurrentInfo();
        wfCurrentInfo.setBizId(bizId);

        WfReProcdef wfReProcdef = wfReProcdefManager.load(wfRuTask.getReProcdefId());
        if (wfReProcdef == null) {
            log.warn("wfReProcdef is not exist, wfRuTaskId:{}, reProcdefId:{}", wfRuTask.getId(), wfRuTask.getReProcdefId());
            return ApiResponse.create(wfCurrentInfo);
        }
        wfCurrentInfo.setOperateType(String.valueOf(wfReProcdef.getOperateType()));
        wfCurrentInfo.setOperatorType(String.valueOf(wfReProcdef.getOperatorType()));

        wfCurrentInfo.setExpressKeys(wfReProcdef.getExpressKeys());
        wfCurrentInfo.setExpressType(String.valueOf(wfReProcdef.getExpressType()));

        return ApiResponse.create(wfCurrentInfo);
    }

    @GetMapping("current-list")
    public ApiResponse currentList(@RequestParam("bizIds[]") Set<String> bizIds,
                               @RequestParam("wfType") String wfType) {
        if (CollectionUtils.isEmpty(bizIds)) {
            log.warn("bizId is empty");
            return ApiResponse.create();
        }

        if (StringUtils.isBlank(wfType)) {
            log.warn("wfType is empty");
            return ApiResponse.create();
        }

        WfRuTaskQuery wfRuTaskQuery = new WfRuTaskQuery();
        wfRuTaskQuery.setBizIds(bizIds);
        wfRuTaskQuery.setWfType(wfType);

        List<WfRuTask> wfRuTaskList = wfRuTaskManager.queryList(wfRuTaskQuery);
        if (CollectionUtils.isEmpty(wfRuTaskList)) {
            log.warn("wfRuTaskList is empty");
            return ApiResponse.create();
        }

        Map<String, WfReProcdef> wfReProcdefMap = null;
        Set<String> reProcdefIds = wfRuTaskList.stream().map(o -> o.getReProcdefId()).collect(Collectors.toSet());

        if (!CollectionUtils.isEmpty(reProcdefIds)) {
            WfReProcdefQuery wfReProcdefQuery = new WfReProcdefQuery();
            wfReProcdefQuery.setIds(reProcdefIds);

            List<WfReProcdef> wfReProcdefList = wfReProcdefManager.queryList(wfReProcdefQuery);
            if (!CollectionUtils.isEmpty(wfReProcdefList)) {
                wfReProcdefMap = wfReProcdefList.stream().collect(Collectors.toMap(o -> o.getId(), o -> o));
            }
        }

        List<WfCurrentInfo> wfCurrentInfoList = new ArrayList<>();

        for (WfRuTask task : wfRuTaskList) {
            WfCurrentInfo wfCurrentInfo = new WfCurrentInfo();
            wfCurrentInfoList.add(wfCurrentInfo);

            wfCurrentInfo.setBizId(task.getBizId());
            if (CollectionUtils.isEmpty(wfReProcdefMap)) {
                continue;
            }

            WfReProcdef wfReProcdef = wfReProcdefMap.get(task.getReProcdefId());
            if (wfReProcdef == null) {
                log.warn("wfReProcdef is empty,reProcdefId :{}", task.getReProcdefId());
                continue;
            }

            wfCurrentInfo.setOperateType(String.valueOf(wfReProcdef.getOperateType()));
            wfCurrentInfo.setOperatorType(String.valueOf(wfReProcdef.getOperatorType()));

            wfCurrentInfo.setExpressKeys(wfReProcdef.getExpressKeys());
            wfCurrentInfo.setExpressType(String.valueOf(wfReProcdef.getExpressType()));
        }

        return ApiResponse.create(wfCurrentInfoList);
    }
}
