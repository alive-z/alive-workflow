package com.mizhi.btbs.domain.model;

import java.io.Serializable;

public class WfReDeployment implements Serializable {
    private static final long serialVersionUID = 15563184279492L;

    private String id;//
    private String groupId;//所属id
    private String wfType;//流程类型
    private Integer stepNum;//总步骤数目

    public WfReDeployment() {
    }

    public WfReDeployment(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    /***/
    public WfReDeployment setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 所属id
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 所属id
     */
    public WfReDeployment setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 流程类型
     */
    public String getWfType() {
        return wfType;
    }

    /**
     * 流程类型
     */
    public void setWfType(String wfType) {
        this.wfType = wfType;
    }

    /**
     * 总步骤数目
     */
    public Integer getStepNum() {
        return stepNum;
    }

    /**
     * 总步骤数目
     */
    public void setStepNum(Integer stepNum) {
        this.stepNum = stepNum;
    }

    @Override
    public String toString() {
        return "WfReDeployment [ id=" + id + ", groupId=" + groupId + ", wfType=" + wfType + ", stepNum=" + stepNum + "]";
    }
}
