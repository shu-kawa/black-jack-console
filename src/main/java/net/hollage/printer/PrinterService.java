package net.hollage.printer;

/** 文字列を出力するためのインターフェース. */
public interface PrinterService {

    /**
     * 文字列を出力する.
     *
     * @param message 出力したいメッセージ
     */
    void print(String message);

    /**
     * 文字列を出力する.
     *
     * @param message 出力したいメッセージ
     * @param args    出力するメッセージに埋め込む引数
     */
    void print(String message, Object... args);
}
