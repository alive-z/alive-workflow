package com.mizhi.btbs.domain.dto;

import java.util.List;

public class WfReDeploymentDTO {

    private String id;
    private String wfType;
    private String groupId;
    private Integer stepNum;
    private List<WfRePecdefDTO> procdefs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWfType() {
        return wfType;
    }

    public void setWfType(String wfType) {
        this.wfType = wfType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getStepNum() {
        return stepNum;
    }

    public void setStepNum(Integer stepNum) {
        this.stepNum = stepNum;
    }

    public List<WfRePecdefDTO> getProcdefs() {
        return procdefs;
    }

    public void setProcdefs(List<WfRePecdefDTO> procdefs) {
        this.procdefs = procdefs;
    }
}
