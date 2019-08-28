package com.mizhi.btbs.domain.dto;

import java.io.Serializable;

public class WfRePecdefDTO implements Serializable {

    private String id;
    private String currentGroupId;
    private Integer optType;
    private Integer step;
    private Integer expressType;
    private String expressKeys;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrentGroupId() {
        return currentGroupId;
    }

    public void setCurrentGroupId(String currentGroupId) {
        this.currentGroupId = currentGroupId;
    }

    public Integer getOptType() {
        return optType;
    }

    public void setOptType(Integer optType) {
        this.optType = optType;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getExpressType() {
        return expressType;
    }

    public void setExpressType(Integer expressType) {
        this.expressType = expressType;
    }

    public String getExpressKeys() {
        return expressKeys;
    }

    public void setExpressKeys(String expressKeys) {
        this.expressKeys = expressKeys;
    }
}
