package com.example.demo.util;

import java.util.UUID;

public class UUIDUtil {
    public static String uuid() {
        //原生的UUID有横杠，我们把横杠去掉
        return UUID.randomUUID().toString().replace("-", "");
    }
}
