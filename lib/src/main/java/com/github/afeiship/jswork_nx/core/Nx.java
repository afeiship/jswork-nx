package com.github.afeiship.jswork_nx.core;

public class Nx {
    public static void log(String message) {
        String prefix = "[js-nx]: ";
        message = prefix + message;
        System.out.println(message);
    }
}
