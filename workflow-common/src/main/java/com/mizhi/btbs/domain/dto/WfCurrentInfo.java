package com.mizhi.btbs.domain.dto;

import com.mizhi.btbs.constant.Constant;
import com.mizhi.btbs.enums.WfExpressTypeEnum;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author mouhaining
 * @since 2019-05-21 10:01
 */
public class WfCurrentInfo implements Serializable {

    private String bizId;
    private String operateType;
    private String operatorType;
    private String expressType;
    private String expressKeys;

    public boolean contains(WfExpressTypeEnum expressTypeEnum, Set<Long> expressKeys) {
        if(expressTypeEnum == null){
            return false;
        }
        if (expressTypeEnum.getValue().equals(expressType)) {
            return containsUser(expressKeys);
        } else if (expressTypeEnum.getValue().equals(expressType)) {
            return containsRole(expressKeys);
        }
        return false;
    }

    public boolean contains(String expressType, Long expressKey) {
        Set<Long> keys = new HashSet<>(Arrays.asList(expressKey));
        return contains(expressType, keys);
    }

    public boolean contains(String expressType, Set<Long> expressKeys) {
        if (WfExpressTypeEnum.USER.getValue().equals(expressType)) {
            return containsUser(expressKeys);
        } else if (WfExpressTypeEnum.ROLE.getValue().equals(expressType)) {
            return containsRole(expressKeys);
        }
        return false;
    }

    public boolean containUser(Long expressKey) {
        if(expressKey == null){
            return false;
        }
        Set<Long> userIds = new HashSet<>();
        userIds.add(expressKey);
        return containsUser(userIds);
    }

    public boolean containsUser(Set<Long> userIds) {
        if(WfExpressTypeEnum.USER.getValue().equals(expressType)){
            return false;
        }
        return containsExpressKeys(userIds);
    }

    public boolean containsRole(Set<Long> roleIds) {
        if(WfExpressTypeEnum.ROLE.getValue().equals(expressType)){
            return false;
        }
        return containsExpressKeys(roleIds);
    }

    private boolean containsExpressKeys(Set<Long> expressKeys){
        if(expressKeys == null || expressKeys.size() == 0){
            return false;
        }
        if(this.expressKeys == null || this.expressKeys.trim().equals("")){
            return false;
        }

        for (String key : this.expressKeys.split(Constant.SPLIT)) {
            if (key == null || key.trim().equals("")) {
                continue;
            }
            if (expressKeys.contains(Long.valueOf(key))) {
                return true;
            }
        }
        return false;
    }

//    public String getOptTypeText() {
//        if (operateType == null || operateType.trim().equals("")) {
//            return "";
//        }
//        WfOperateTypeEnum optTypeEnum = WfOperateTypeEnum.get(operateType);
//        return optTypeEnum == null ? "" : optTypeEnum.getText();
//    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getExpressType() {
        return expressType;
    }

    public void setExpressType(String expressType) {
        this.expressType = expressType;
    }

    public String getExpressKeys() {
        return expressKeys;
    }

    public void setExpressKeys(String expressKeys) {
        this.expressKeys = expressKeys;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    @Override
    public String toString() {
        return "WfCurrentInfo{" +
                "bizId='" + bizId + '\'' +
                ", operateType='" + operateType + '\'' +
                ", expressType='" + expressType + '\'' +
                ", expressKeys='" + expressKeys + '\'' +
                '}';
    }
}
