package com.mizhi.btbs.domain.query;


import java.io.Serializable;
import java.util.Collection;

public class WfRuTaskQuery implements Serializable {
    private static final long serialVersionUID = 15571263316551L;

    private String groupId;//
    private String reDeployId;//
    private String reProcdefId;//
    private Integer currentStep;//
    private String bizId;//
    private Collection<String> bizIds;//
    private String wfType;//
    private String status;//

    public static WfRuTaskQuery create() {
        return new WfRuTaskQuery();
    }


    public String getGroupId() {
        return groupId;
    }

    public WfRuTaskQuery setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getReDeployId() {
        return reDeployId;
    }

    public WfRuTaskQuery setReDeployId(String reDeployId) {
        this.reDeployId = reDeployId;
        return this;
    }

    public String getReProcdefId() {
        return reProcdefId;
    }

    public WfRuTaskQuery setReProcdefId(String reProcdefId) {
        this.reProcdefId = reProcdefId;
        return this;
    }

    public Integer getCurrentStep() {
        return currentStep;
    }

    public WfRuTaskQuery setCurrentStep(Integer currentStep) {
        this.currentStep = currentStep;
        return this;
    }

    public String getBizId() {
        return bizId;
    }

    public WfRuTaskQuery setBizId(String bizId) {
        this.bizId = bizId;
        return this;
    }

    public String getWfType() {
        return wfType;
    }

    public WfRuTaskQuery setWfType(String wfType) {
        this.wfType = wfType;
        return this;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStatus() {
        return status;
    }

    public WfRuTaskQuery setStatus(String status) {
        this.status = status;
        return this;
    }

    public Collection<String> getBizIds() {
        return bizIds;
    }

    public void setBizIds(Collection<String> bizIds) {
        this.bizIds = bizIds;
    }
}
