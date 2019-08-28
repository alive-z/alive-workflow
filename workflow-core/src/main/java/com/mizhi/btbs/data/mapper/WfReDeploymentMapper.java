package com.mizhi.btbs.data.mapper;

import com.mizhi.btbs.domain.model.WfReDeployment;
import com.mizhi.btbs.domain.query.WfReDeploymentQuery;

import java.util.List;

public interface WfReDeploymentMapper {
    WfReDeployment load(String id);

    void insert(WfReDeployment wfReDeployment);

    void update(WfReDeployment wfReDeployment);

    void delete(String id);

    List<WfReDeployment> queryList(WfReDeploymentQuery wfReDeploymentQuery);

    int queryCount(WfReDeploymentQuery wfReDeploymentQuery);
}