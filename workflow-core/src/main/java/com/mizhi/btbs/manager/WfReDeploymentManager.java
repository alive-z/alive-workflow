package com.mizhi.btbs.manager;

import com.mizhi.btbs.data.mapper.WfReDeploymentMapper;
import com.mizhi.btbs.domain.model.WfReDeployment;
import com.mizhi.btbs.domain.query.WfReDeploymentQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class WfReDeploymentManager {
    private static final Logger log = LoggerFactory.getLogger(WfReDeploymentManager.class);

    @Autowired
    private WfReDeploymentMapper wfReDeploymentMapper;

    public WfReDeployment singleResult(String groupId, String wfType) {
        WfReDeploymentQuery wfReDeploymentQuery = new WfReDeploymentQuery();
        wfReDeploymentQuery.setGroupId(groupId);
        wfReDeploymentQuery.setWfType(wfType);

        return singleResult(wfReDeploymentQuery);
    }

    public WfReDeployment singleResult(WfReDeploymentQuery wfReDeploymentQuery) {
        List<WfReDeployment> wfReDeployments = wfReDeploymentMapper.queryList(wfReDeploymentQuery);
        if (CollectionUtils.isEmpty(wfReDeployments)) {
            return null;
        }
        return wfReDeployments.get(0);
    }


    public void insert(WfReDeployment wfReDeployment) {
    }
}