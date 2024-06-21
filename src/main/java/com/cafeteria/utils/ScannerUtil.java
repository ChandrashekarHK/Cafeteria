package com.cafeteria.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScannerUtil {
    private static BufferedReader reader;

    public static synchronized BufferedReader getReader() {
        if (reader == null) {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }
        return reader;
    }

    public static synchronized int scanInt(String message) throws IOException {
        System.out.print(message);
        return Integer.parseInt(getReader().readLine());
    }

    public static synchronized String scanString(String message) throws IOException {
        System.out.print(message);
        return getReader().readLine();
    }

    public static synchronized String[] scanStringArray(String message) throws IOException {
        System.out.print(message);
        return getReader().readLine().split(",");
    }
}
