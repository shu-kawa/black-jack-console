package net.hollage.card;

import java.util.List;

/** 手札の役を判定するクラス. */
public class Hand {

    /** ブラックジャックの場合のスコア. */
    private static final int BLACK_JACK_SCORE = 22;
    /** ブラックジャックでない場合の最高スコア. */
    private static final int NORMAL_HAND_MAX_SCORE = 21;
    /** バーストの場合のスコア. */
    private static final int BUST_SCORE = 0;

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
     * 手札の強さを取得する.
     *
     * @return ブラックジャック: 22点、バースト: 0点、その他: 合計点
     */
    public int getHandScore() {
        if (isBlackJack()) {
            return BLACK_JACK_SCORE;
        }
        return calcScore();
    }

    /**
     * ブラックジャックかどうかを判定する.
     *
     * @return true: ブラックジャック, false: ブラックジャックではない
     */
    protected boolean isBlackJack() {
        // A + 10, J, Q, Kのいずれかの2枚で構成されているかを判定する.
        if (cards.size() != 2) {
            return false;
        }
        boolean hasAce = cards.stream().anyMatch(Card::isAce);
        boolean hasTen = cards.stream().anyMatch(card -> card.getNum() >= 10);
        return (hasAce && hasTen);
    }

    /**
     * 手札の合計点を計算する.
     *
     * @return 合計点
     */
    protected int calcScore() {
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
        while (NORMAL_HAND_MAX_SCORE < score && 0 < aceNum) {
            aceNum -= 1;
            score -= 10;
        }
        if (score <= NORMAL_HAND_MAX_SCORE) {
            return score;
        }
        return BUST_SCORE;
    }

    /**
     * バーストかどうかを判定する.
     *
     * @return true: バースト, false: バーストではない
     */
    public boolean isBust() {
        return calcScore() == BUST_SCORE;
    }
}
