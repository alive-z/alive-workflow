package com.mizhi.btbs.domain.query;


import java.io.Serializable;

public class WfReDeploymentQuery implements Serializable {
    private static final long serialVersionUID = 15563184279491L;

    private String groupId;//所属id
    private String wfType;//流程类型

    public static WfReDeploymentQuery create() {
        return new WfReDeploymentQuery();
    }


    public String getGroupId() {
        return groupId;
    }

    public WfReDeploymentQuery setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getWfType() {
        return wfType;
    }

    public WfReDeploymentQuery setWfType(String wfType) {
        this.wfType = wfType;
        return this;
    }


}
