package main.utils;

public class Message {
    private final static String RED = "\u001b[31m";
    private final static String GREEN = "\u001b[32m";
    private final static String YELLOW = "\u001b[33m";
    private final static String RESET = "\u001b[0m";

    public static void println(String msg, MsgColor color) {
        switch (color) {
            case RED -> System.out.println(RED + msg + RESET);
            case GREEN -> System.out.println(GREEN + msg + RESET);
            case YELLOW -> System.out.println(YELLOW + msg + RESET);
            default -> System.out.println(msg);
        }
    }
}
