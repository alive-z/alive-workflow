package com.mizhi.btbs.util;

public class StepUtil {
    public static int HEAD = 1;

    public static int getNext(int current){
        return current + 1;
    }

    public static int getPre(int current){
        return current - 1;
    }
}
