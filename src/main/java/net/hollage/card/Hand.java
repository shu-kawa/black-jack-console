package net.hollage.card;

import java.util.List;

/** 手札の役を判定するクラス. */
public class Hand {

    /** バースト上限値. */
    private static final int MAX_SCORE = 21;

    /** 手札リスト. */
    private final List<Card> cards;

    /**
     * コンストラクタ.
     *
     * @param cards 手札リスト
     */
    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * 手札の合計点を計算する.
     *
     * @return 合計点
     */
    public int calcScore() {
        int score = 0;
        if (cards.isEmpty()) {
            return score;
        }
        // Aを11点として加算
        for (Card card : cards) {
            if (card.isAce()) {
                score += 11;
            } else {
                score += card.getScore();
            }
        }
        // Aの枚数をカウントして、バースト値を下回るまで減算
        long aceNum = cards.stream().filter(Card::isAce).count();
        while (MAX_SCORE < score && 0 < aceNum) {
            aceNum -= 1;
            score -= 10;
        }
        return score;
    }

    /**
     * バーストかどうかを判定する.
     *
     * @return true: バースト, false: バーストではない
     */
    public boolean isBust() {
        return MAX_SCORE < this.calcScore();
    }
}
