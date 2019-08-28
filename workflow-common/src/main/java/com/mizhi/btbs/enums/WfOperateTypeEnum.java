package com.mizhi.btbs.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chaobo
 * @since 2019/5/14
 */
public enum WfOperateTypeEnum {
    TEMP_STORAGE("tempStorage", "暂存"),

    CONFIRM("confirm", "确认"),
    AUDIT("audit", "审批"),

    NORMAL("normal", "生效"),
    CANCEL("cancel", "撤销"),

    ;

    private static Map<String, WfOperateTypeEnum> pool = new HashMap<>();
    static {
        for (WfOperateTypeEnum o : WfOperateTypeEnum.values()) {
            pool.put(o.value, o);
        }
    }


    private String value;
    private String text;

    WfOperateTypeEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public static WfOperateTypeEnum get(String optType) {
        if (optType == null) {
            return null;
        }
        return pool.get(optType);
    }
}
