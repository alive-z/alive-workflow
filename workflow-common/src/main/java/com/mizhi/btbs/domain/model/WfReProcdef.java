package com.mizhi.btbs.domain.model;

import java.io.Serializable;

/**
 * 实体类
 */
public class WfReProcdef implements Serializable {
    private static final long serialVersionUID = 15570784676622L;


    private String id;//
    private String groupId;//所属id
    private String wfType;//流程类型
    private String reDeployId;//主表id
    private Integer stepCurrent;//当前步骤
    private Integer expressType;//表达式类型
    private String expressKeys;//表达式键
    private Integer operateType;//操作类型
    private Integer operatorType;//操作方类型


    public WfReProcdef() {
    }

    /**
     * @param id --
     */
    public WfReProcdef(String id) {
        this.id = id;
    }

    /***/
    public String getId() {
        return id;
    }

    /***/
    public WfReProcdef setId(String id) {
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
    public WfReProcdef setGroupId(String groupId) {
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
    public WfReProcdef setWfType(String wfType) {
        this.wfType = wfType;
        return this;
    }

    /**
     * 主表id
     */
    public String getReDeployId() {
        return reDeployId;
    }

    /**
     * 主表id
     */
    public WfReProcdef setReDeployId(String reDeployId) {
        this.reDeployId = reDeployId;
        return this;
    }

    /**
     * 当前步骤
     */
    public Integer getStepCurrent() {
        return stepCurrent;
    }

    /**
     * 当前步骤
     */
    public void setStepCurrent(Integer stepCurrent) {
        this.stepCurrent = stepCurrent;
    }

    /**
     * 表达式类型
     */
    public Integer getExpressType() {
        return expressType;
    }

    /**
     * 表达式类型
     */
    public void setExpressType(Integer expressType) {
        this.expressType = expressType;
    }

    /**
     * 表达式键
     */
    public String getExpressKeys() {
        return expressKeys;
    }

    /**
     * 表达式键
     */
    public void setExpressKeys(String expressKeys) {
        this.expressKeys = expressKeys;
    }

    /**
     * 操作类型
     */
    public Integer getOperateType() {
        return operateType;
    }

    /**
     * 操作类型
     */
    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    /**
     * 操作方类型
     */
    public Integer getOperatorType() {
        return operatorType;
    }

    /**
     * 操作方类型
     */
    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    @Override
    public String toString() {
        return "WfReProcdef [ id=" + id + ", groupId=" + groupId + ", wfType=" + wfType + ", reDeployId=" + reDeployId + ", stepCurrent=" + stepCurrent + ", expressType=" + expressType + ", expressKeys=" + expressKeys + ", operateType=" + operateType + "]";
    }
}
