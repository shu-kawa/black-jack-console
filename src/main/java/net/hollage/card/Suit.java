package net.hollage.card;

/** トランプの記号列挙型クラス. */
public enum Suit {
    //    HEARTS("♥"),
    //    SPADES("♠"),
    //    DIAMONDS("♦"),
    //    CLUBS("♣");
    HEARTS("[H]"),
    SPADES("[S]"),
    DIAMONDS("[D]"),
    CLUBS("[C]");

    /** スートの記号. */
    private final String symbol;

    /**
     * コンストラクタ.
     *
     * @param symbol スートの記号
     */
    Suit(String symbol) {
        this.symbol = symbol;
    }

    /**
     * スートの記号を取得する.
     *
     * @return スートの記号
     */
    String getSymbol() {
        return symbol;
    }
}
