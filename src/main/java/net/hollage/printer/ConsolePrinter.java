package net.hollage.printer;

/** 文字列をコンソールに出力するクラス. */
public class ConsolePrinter implements PrinterService {
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public void print(String message, Object... args) {
        System.out.printf((message) + "%n", args);
    }
}
