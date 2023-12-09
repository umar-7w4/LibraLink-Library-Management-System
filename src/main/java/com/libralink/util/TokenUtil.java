package com.libralink.util;

import java.util.UUID;

public class TokenUtil {

    public static String generateNewToken() {
        return UUID.randomUUID().toString();
    }
}

