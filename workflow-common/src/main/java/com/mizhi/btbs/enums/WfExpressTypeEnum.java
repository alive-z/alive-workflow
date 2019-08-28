package com.mizhi.btbs.enums;

/**
 * @author chaobo
 * @since 2019/5/14
 */
public enum WfExpressTypeEnum {

    USER("1"),
    ROLE("2"),
    ;

    private String value;

    WfExpressTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

