package com.B3.struct.analyzer.utils;

public class Convert {

    public static Long[] convertLongArrayToLongObjectArray(long[] array) {
        Long[] result = new Long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i]; // Autoboxing
        }
        return result;
    }
}
