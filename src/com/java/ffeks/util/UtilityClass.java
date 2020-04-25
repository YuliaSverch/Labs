package com.java.ffeks.util;

import com.java.ffeks.exception.EmptyRowException;

public final class UtilityClass {

    public static void checkIpExistence(String ip) {
        if (ip.isEmpty()) {
            throw new EmptyRowException();
        }
    }
}
