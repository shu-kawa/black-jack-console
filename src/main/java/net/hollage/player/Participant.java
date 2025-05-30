package net.hollage.player;

import net.hollage.deck.Card;
import net.hollage.deck.Hand;

import java.util.ArrayList;
import java.util.List;

/** ゲームの参加者の抽象クラス. */
public abstract class Participant {

    /** カードリスト */
    protected final List<Card> cards;

    /** コンストラクタ */
    public Participant() {
        this.cards = new ArrayList<>();
    }

    /**
     * カードを追加する.
     *
     * @param card 追加するカード
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * 手札の枚数を返す.
     *
     * @return 所持枚数
     */
    public int size() {
        return cards.size();
    }

    /**
     * 手札をすべて開示する.
     *
     * @return 全ての手札の文字列
     */
    public String showAllCards() {
        if (cards.isEmpty()) {
            return "Empty";
        }
        return String.join(", ", cards.stream().map(Card::getString).toArray(String[]::new));
    }

    /**
     * 手札の一部を開示する.
     *
     * @return 一部の手札の文字列
     */
    public String showPartOfCards() {
        return this.showAllCards();
    }

    /**
     * ヒット可能（21点未満）かどうか.
     *
     * @return true: ヒット可能
     */
    public boolean canHit() {
        Hand hand = new Hand(cards);
        return !hand.isBust() && hand.getHandScore() < 21;
    }

    /**
     * 手札の合計点を返す.
     *
     * @return 合計点
     */
    public int getHandScore() {
        Hand hand = new Hand(cards);
        return hand.getHandScore();
    }

    /**
     * バーストかどうかを判定する.
     *
     * @return true: バースト, false: バーストではない
     */
    public boolean isBust() {
        Hand hand = new Hand(cards);
        return hand.isBust();
    }

    /**
     * スタンド可能かどうか.
     *
     * @return true: スタンド可能
     */
    abstract public boolean canStand();
}
