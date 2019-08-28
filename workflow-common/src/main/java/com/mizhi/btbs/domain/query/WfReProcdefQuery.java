package com.mizhi.btbs.domain.query;


import java.io.Serializable;
import java.util.Collection;

public class WfReProcdefQuery implements Serializable {
    private static final long serialVersionUID = 15570784676621L;

    private String id;//主键Id
    private Collection<String> ids;//主键Ids
    private String groupId;//所属id
    private String wfType;//流程类型
    private String reDeployId;//主表id
    private Integer stepCurrent;//当前步骤
    private Integer expressType;//表达式类型

    public static WfReProcdefQuery create() {
        return new WfReProcdefQuery();
    }


    public String getGroupId() {
        return groupId;
    }

    public WfReProcdefQuery setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getWfType() {
        return wfType;
    }

    public WfReProcdefQuery setWfType(String wfType) {
        this.wfType = wfType;
        return this;
    }

    public String getReDeployId() {
        return reDeployId;
    }

    public WfReProcdefQuery setReDeployId(String reDeployId) {
        this.reDeployId = reDeployId;
        return this;
    }

    public Integer getStepCurrent() {
        return stepCurrent;
    }

    public WfReProcdefQuery setStepCurrent(Integer stepCurrent) {
        this.stepCurrent = stepCurrent;
        return this;
    }

    public Integer getExpressType() {
        return expressType;
    }

    public WfReProcdefQuery setExpressType(Integer expressType) {
        this.expressType = expressType;
        return this;
    }

    public Collection<String> getIds() {
        return ids;
    }

    public void setIds(Collection<String> ids) {
        this.ids = ids;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
