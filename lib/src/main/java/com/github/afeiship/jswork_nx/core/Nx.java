package com.github.afeiship.jswork_nx.core;

public class Nx {
    public static void log(String message) {
        String prefix = "🌈 [jsw-nx-log]: ";
        message = prefix + message;
        System.out.println(message);
    }

    public static void error(String message) {
        String prefix = "🍎 [jsw-nx-error]: ";
        message = prefix + message;
        System.err.println(message);
    }
}
