package net.hollage.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** トランプのデッキ. */
public final class Deck {

    /** シングルトンインスタンス. */
    private static final Deck instance = new Deck();
    /** 記号毎の枚数. */
    private static final int KIND_OF_SUIT = 13;

    private final List<Card> cards = new ArrayList<>();

    /** コンストラクタ. */
    private Deck() {
        init();
    }

    /**
     * デッキインスタンスを取得する.
     *
     * @return デッキインスタンス
     */
    public static Deck getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Deck instance is null.");
        }
        return instance;
    }

    /** デッキを初期化する. */
    private void init() {
        for (int i = 1; i <= KIND_OF_SUIT; i++) {
            cards.add(new Card(Suit.HEARTS, i));
            cards.add(new Card(Suit.SPADES, i));
            cards.add(new Card(Suit.DIAMONDS, i));
            cards.add(new Card(Suit.CLUBS, i));
        }
        this.shuffle();
    }

    /** デッキをシャッフルする. */
    private void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * カードを1枚引く.
     *
     * @return 引いたカード
     */
    public Card deal() {
        if (cards.isEmpty()) {
            this.init();
        }
        return cards.removeFirst();
    }
}
