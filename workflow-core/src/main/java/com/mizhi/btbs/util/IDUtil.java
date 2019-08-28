package com.mizhi.btbs.util;

import java.util.UUID;

public class IDUtil {

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
