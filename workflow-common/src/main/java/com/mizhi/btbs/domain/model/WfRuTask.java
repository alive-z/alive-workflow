package com.mizhi.btbs.domain.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 */
public class WfRuTask implements Serializable {
    private static final long serialVersionUID = 15571263316552L;


    private String id;//
    private String groupId;//
    private String reDeployId;//
    private String reProcdefId;//
    private Integer currentStep;//
    private String bizId;//
    private String wfType;//
    private String status;//
    private Integer firstStepReProcdefId;//
    private Integer preStepReProcdefId;//
    private Integer currentStepReProcdefId;//


    public WfRuTask() {
    }

    /**
     * @param id --
     */
    public WfRuTask(String id) {
        this.id = id;
    }

    /***/
    public String getId() {
        return id;
    }

    /***/
    public void setId(String id) {
        this.id = id;
    }

    /***/
    public String getGroupId() {
        return groupId;
    }

    /***/
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /***/
    public String getReDeployId() {
        return reDeployId;
    }

    /***/
    public void setReDeployId(String reDeployId) {
        this.reDeployId = reDeployId;
    }

    /***/
    public String getReProcdefId() {
        return reProcdefId;
    }

    /***/
    public void setReProcdefId(String reProcdefId) {
        this.reProcdefId = reProcdefId;
    }

    /***/
    public Integer getCurrentStep() {
        return currentStep;
    }

    /***/
    public void setCurrentStep(Integer currentStep) {
        this.currentStep = currentStep;
    }

    /***/
    public String getBizId() {
        return bizId;
    }

    /***/
    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    /***/
    public String getWfType() {
        return wfType;
    }

    /***/
    public void setWfType(String wfType) {
        this.wfType = wfType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /***/
    public Integer getFirstStepReProcdefId() {
        return firstStepReProcdefId;
    }

    /***/
    public void setFirstStepReProcdefId(Integer firstStepReProcdefId) {
        this.firstStepReProcdefId = firstStepReProcdefId;
    }

    /***/
    public Integer getPreStepReProcdefId() {
        return preStepReProcdefId;
    }

    /***/
    public void setPreStepReProcdefId(Integer preStepReProcdefId) {
        this.preStepReProcdefId = preStepReProcdefId;
    }

    /***/
    public Integer getCurrentStepReProcdefId() {
        return currentStepReProcdefId;
    }

    /***/
    public void setCurrentStepReProcdefId(Integer currentStepReProcdefId) {
        this.currentStepReProcdefId = currentStepReProcdefId;
    }

    @Override
    public String toString() {
        return "WfRuTask [ id=" + id + ", groupId=" + groupId + ", reDeployId=" + reDeployId + ", reProcdefId=" + reProcdefId + ", currentStep=" + currentStep + ", bizId=" + bizId + ", wfType=" + wfType + ", firstStepReProcdefId=" + firstStepReProcdefId + ", preStepReProcdefId=" + preStepReProcdefId + ", currentStepReProcdefId=" + currentStepReProcdefId + "]";
    }
}
