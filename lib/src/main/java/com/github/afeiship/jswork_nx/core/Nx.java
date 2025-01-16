package com.github.afeiship.jswork_nx.core;

public class Nx {
    public static void log(String message) {
        String prefix = "🍏 [jsw-nx-log]: ";
        message = prefix + message;
        System.out.println(message);
    }

    public static void error(String message) {
        String prefix = "❌ [jsw-nx-error]: ";
        message = prefix + message;
        System.err.println(message);
    }

    public static void warn(String message) {
        String prefix = "⚠️ [jsw-nx-warn]: ";
        message = prefix + message;
        System.err.println(message);
    }

    public static void info(String message) {
        String prefix = "🌈 [jsw-nx-info]: ";
        message = prefix + message;
        System.out.println(message);
    }

    // println use string format
    public static void println(String format, Object... args) {
        String message = String.format(format, args);
        System.out.println(message);
    }

    // sprintf use string format
    public static String sprintf(String format, Object... args) {
        return String.format(format, args);
    }
}
