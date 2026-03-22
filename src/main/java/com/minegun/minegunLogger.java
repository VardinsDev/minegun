package com.minegun;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class minegunLogger {
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static final String RESET = "\u001B[0m";
    private static final String DARK_PURPLE = "\u001B[35m";
    private static final String PURPLE = "\u001B[95m";
    private static final String CYAN = "\u001B[96m";
    private static final String GRAY = "\u001B[90m";
    private static final String WHITE = "\u001B[97m";
    private static final String RED = "\u001B[91m";
    private static final String YELLOW = "\u001B[93m";
    private static final String GREEN = "\u001B[92m";

    private static String timestamp() {
        return GRAY + "[" + timeFormatter.format(LocalTime.now()) + "]" + RESET;
    }

    private static String prefix() {
        return DARK_PURPLE + "[" + PURPLE + "Minegun" + DARK_PURPLE + "]" + RESET;
    }

    public static void info(String message) {
        System.out.println(timestamp() + " " + prefix() + " " + CYAN + "[INFO]" + RESET + " " + WHITE + message + RESET);
    }

    public static void warn(String message) {
        System.out.println(timestamp() + " " + prefix() + " " + YELLOW + "[WARN]" + RESET + " " + WHITE + message + RESET);
    }

    public static void error(String message) {
        System.out.println(timestamp() + " " + prefix() + " " + RED + "[ERROR]" + RESET + " " + WHITE + message + RESET);
    }

    public static void success(String message) {
        System.out.println(timestamp() + " " + prefix() + " " + GREEN + "[OK]" + RESET + " " + WHITE + message + RESET);
    }

    public static void printBanner() {
        System.out.println();
        System.out.println(DARK_PURPLE + "  ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄  " + RESET);
        System.out.println(DARK_PURPLE + "  █                                       █  " + RESET);
        System.out.println(DARK_PURPLE + "  █  " + PURPLE + "  ██▄  ▄█ ▄█▄  ▄▄▄  ▄████ ██  ██▄  " + DARK_PURPLE + "█  " + RESET);
        System.out.println(DARK_PURPLE + "  █  " + PURPLE + "  █ ██▄█ █ █  █  █  █     █ █  █ █  " + DARK_PURPLE + "█  " + RESET);
        System.out.println(DARK_PURPLE + "  █  " + PURPLE + "  █ ▀█▀ █ █   █  █  █████ █ █  █ █  " + DARK_PURPLE + "█  " + RESET);
        System.out.println(DARK_PURPLE + "  █  " + PURPLE + "  █  █  █ █   █  █  █     ████  █ █  " + DARK_PURPLE + "█  " + RESET);
        System.out.println(DARK_PURPLE + "  █  " + PURPLE + "  █  █  █ ███  ▀▀▀  █     █  █ ███  " + DARK_PURPLE + "█  " + RESET);
        System.out.println(DARK_PURPLE + "  █                                       █  " + RESET);
        System.out.println(DARK_PURPLE + "  ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀  " + RESET);
        System.out.println();
    }
}
