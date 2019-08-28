package com.mizhi.btbs.api;

import com.mizhi.btbs.cache.WfCurrentInfoCache;
import com.mizhi.btbs.constant.Constant;
import com.mizhi.btbs.domain.dto.WfCurrentInfo;
import com.mizhi.btbs.response.ApiResponse;
import com.mizhi.btbs.response.BooleanApiResponse;
import com.mizhi.btbs.response.WfCurrentInfoApiResponse;
import com.mizhi.btbs.response.WfCurrentInfoListApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chaobo
 * @since 2019/5/15
 */
public class WorkFlowApi {
    private static final Logger log = LoggerFactory.getLogger(WorkFlowApi.class);


    private RestTemplate restTemplate;
    private WfCurrentInfoCache cache;
    private String urlrefix;

    public WorkFlowApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.init();
    }

    public WorkFlowApi(RestTemplate restTemplate, WfCurrentInfoCache cache) {
        this.restTemplate = restTemplate;
        this.cache = cache;
        this.init();
    }

    public void init() {
        urlrefix = "http://" + Constant.WORKFLOW_SERVICE_NAME + "/wf-api";
        log.info(">>>>>>>>> workflow api inited >>>>>>>>");
    }

    /**
     * 开始工作流
     * @param groupId 双方的组织ID 中间用分隔符连接('_')
     * @param wfType 业务类型(例子如:contract)
     * @param bizId 业务对象的ID
     */
    public void start(String groupId, String wfType, Number bizId) {

        Assert.isTrue(validate(groupId, wfType, bizId), "wf start params is error");

        String url = urlrefix + "/start?groupId={groupId}&wfType={wfType}&bizId={bizId}";

        Map<String, Object> params = defaultParams(groupId, wfType, bizId);
        params.put("groupId", groupId);
        params.put("wfType", wfType);
        params.put("bizId", bizId);

        ApiResponse response = restTemplate.postForObject(url, null, ApiResponse.class, params);
        if (!response.isSuccess()) {
            log.error("wf start workflow task fail:" + response);
        }
    }

    /**
     * 执行下一步任务
     *  @param groupId 双方的组织ID 中间用分隔符连接('_')
     * @param currentGroupId 当前组织的ID
     * @param bizId 业务对象的ID
     * @param wfType 业务类型(例子如:contract)
     */
    public Boolean next(String groupId,
                             String currentGroupId,
                             Number bizId,
                             String wfType) {
        return transform(groupId, currentGroupId, bizId, wfType, false);
    }

    /**
     * 回滚到第一步
     * @param groupId
     * @param currentGroupId
     * @param bizId
     * @param wfType
     */
    public void rollback(String groupId,
                         String currentGroupId,
                         Number bizId,
                         String wfType) {
        transform(groupId, currentGroupId, bizId, wfType, true);
    }

    /**
     * 执行下一步/回滚到第一步 任务
     *  @param groupId 双方的组织ID 中间用分隔符连接('_')
     * @param currentGroupId 当前组织的ID
     * @param bizId 业务对象的ID
     * @param wfType 业务类型(例子如:contract)
     * @param rollBack true 回滚到第一步 false 下一步
     */
    public Boolean transform(String groupId,
                             String currentGroupId,
                             Number bizId,
                             String wfType,
                             boolean rollBack) {

        String url = urlrefix + "/transform?groupId={groupId}&currentGroupId={currentGroupId}&bizId={bizId}&wfType={wfType}&rollBack={rollBack}";

        Map<String, Object> params = defaultParams(groupId, wfType, bizId);
        params.put("currentGroupId", currentGroupId);
        params.put("rollBack", rollBack);

        BooleanApiResponse response = restTemplate.postForObject(url, params, BooleanApiResponse.class);
        if (!response.isSuccess()) {
            log.error(rollBack  ?  "wf rollback" : "wf next" + "workflow task fail:" + response);
            return false;
        }
        return response.getData();
    }

    /**
     *
     * @param wfType 业务类型(例子如:contract)
     * @param bizId 业务对象的ID
     */
    public WfCurrentInfo current(String wfType, Number bizId) {

        if (StringUtils.isBlank(wfType)) {
            log.warn("wf get current wfType is empty");
            return null;
        }

        if (bizId == null) {
            log.warn("wf get current bizId is empty");
            return null;
        }

        WfCurrentInfo wfCurrentInfo = getCurrentCache(wfType, bizId);
        if (wfCurrentInfo != null) {
            return wfCurrentInfo;
        }

        String url = urlrefix + "/current?wfType={wfType}&bizId={bizId}";

        Map<String, Object> params = defaultParams(null, wfType, bizId);

        WfCurrentInfoApiResponse response = restTemplate.getForObject(url, WfCurrentInfoApiResponse.class, params);
        if (!response.isSuccess()) {
            log.error("wf get current fail:" + response);
            return null;
        }

        wfCurrentInfo = response.getData();

        putCache(wfType, bizId, wfCurrentInfo);

        return wfCurrentInfo;
    }

    public List<WfCurrentInfo> currentList(String wfType, Collection<Number> bizIds) {
        return currentMaps(wfType, bizIds).values().stream().collect(Collectors.toList());
    }

    public Map<Number, WfCurrentInfo> currentMaps(String wfType, Collection<Number> bizIds) {
        if (CollectionUtils.isEmpty(bizIds)) {
            log.warn("wf get current bizIds is empty");
            return new HashMap<>(0);
        }

        if (StringUtils.isBlank(wfType)) {
            log.warn("wf get current wfType is empty");
            return new HashMap<>(0);
        }

        Collection<Number> noCacheIds = new ArrayList<>(bizIds);

        Map<Number, WfCurrentInfo> maps = getCurrentCache(wfType, noCacheIds);

        if (CollectionUtils.isEmpty(noCacheIds)) {
            return maps;
        }

        String url = urlrefix + "/current-list?wfType={wfType}&bizIds[]={bizIds}";
        Map<String, Object> params = new HashMap<>();
        params.put("wfType", wfType);
        params.put("bizIds", bizIds.toArray(new Number[bizIds.size()]));

        WfCurrentInfoListApiResponse response = restTemplate.getForObject(url, WfCurrentInfoListApiResponse.class, params);
        if (!response.isSuccess()) {
            log.error("wf get current fail:" + response);
            return new HashMap<>(0);
        }

        List<WfCurrentInfo> wfCurrentInfoList = response.getData();
        if (CollectionUtils.isEmpty(wfCurrentInfoList)) {
            return maps;
        }

        wfCurrentInfoList.forEach(wfCurrentInfo->{

            putCache(wfType, Long.valueOf(wfCurrentInfo.getBizId()), wfCurrentInfo);

            maps.put(Long.valueOf(wfCurrentInfo.getBizId()), wfCurrentInfo);
        });

        return maps;
    }

    private void putCache(String wfType, Number bizId, WfCurrentInfo wfCurrentInfo) {
        if (!hasCache()) {
            return;
        }

        if (wfCurrentInfo == null) {
            return;
        }

        cache.put(wfType + Constant.GRUOP_SPLIT + bizId, wfCurrentInfo);
    }

    private Map<String, Object> defaultParams(String groupId, String wfType, Number bizId) {

        Map<String, Object> params = new HashMap<>();
        params.put("groupId", groupId);
        params.put("wfType", wfType);
        params.put("bizId", bizId);

        return params;
    }

    private WfCurrentInfo getCurrentCache(String wfType, Number bizId) {
        if (!hasCache()) {
            return null;
        }

        return cache.get(wfType + Constant.GRUOP_SPLIT + bizId);
    }

    private Map<Number, WfCurrentInfo> getCurrentCache(String wfType, Collection<Number> noCacheIds) {
        Map<Number, WfCurrentInfo> maps = new HashMap<>();

        if (!hasCache()) {
            return maps;
        }

        noCacheIds.forEach(bizId->{

            WfCurrentInfo currentCache = getCurrentCache(wfType, bizId);
            if (currentCache!= null) {
                noCacheIds.remove(bizId);
                maps.put(bizId, currentCache);
            }
        });

        noCacheIds.removeAll(maps.keySet());
        return maps;
    }

    private boolean validate(String groupId, String wfType, Number bizId) {
        if (log.isDebugEnabled()) {
            log.debug("wf api params: 【groupId:{}】,【wfType:{}】,【bizId:{}】", groupId, wfType, bizId);
        }

        if (StringUtils.isEmpty(groupId) || StringUtils.isEmpty(wfType) || bizId == null) {
            return false;
        }
        return true;
    }

    private boolean hasCache() {
        return cache != null;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setCache(WfCurrentInfoCache cache) {
        this.cache = cache;
    }
}
