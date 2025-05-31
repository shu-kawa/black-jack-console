package net.hollage.card;

/** カードクラス. */
public class Card {
    /** スート. */
    private final Suit suit;
    /** 数値 */
    private final int num;

    /**
     * コンストラクタ.
     *
     * @param suit スート
     * @param num  数値
     */
    public Card(Suit suit, int num) {
        this.suit = suit;
        this.num = num;
    }

    /**
     * トランプのカードを文字列で返す.
     *
     * @return 文字列化したトランプの絵柄
     */
    public String getString() {
        return this.suit.getSymbol() + numString();
    }

    /**
     * トランプがエースかどうかを判定する.
     *
     * @return true: エースの場合
     */
    public boolean isAce() {
        return this.num == 1;
    }

    /**
     * トランプの数値をそのまま返す.
     *
     * @return トランプの数値
     */
    public int getNum() {
        return this.num;
    }

    /**
     * トランプの得点を返す.<br>
     * 絵柄の場合は10点を返す.<br>
     * Aの場合は別途計算が必要となる.
     *
     * @return トランプの点数
     */
    public int getScore() {
        return Math.min(10, this.num);
    }

    /**
     * トランプの数値を文字列に変換する.
     *
     * @return 文字列変換した数値
     */
    private String numString() {
        return switch (this.num) {
            case 1 -> "A";
            case 2, 3, 4, 5, 6, 7, 8, 9, 10 -> String.format("%d", this.num);
            case 11 -> "J";
            case 12 -> "Q";
            case 13 -> "K";
            default -> throw new IllegalArgumentException("Invalid card number: " + this.num);
        };
    }
}
