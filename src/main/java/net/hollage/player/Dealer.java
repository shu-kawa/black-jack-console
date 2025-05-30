package net.hollage.player;

import net.hollage.deck.Hand;

/** ディーラークラス. */
public class Dealer extends Participant {

    @Override
    public String showPartOfCards() {
        if (cards.isEmpty()) {
            return "Empty";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cards.get(0).getString());
        for (int i = 1; i < cards.size(); i++) {
            sb.append(", ???");
        }
        return sb.toString();
    }

    /**
     * スタンド可能（17点以上）かどうか.<br>
     * ソフト17（例: A + 6）の場合もスタンド可能扱い.
     *
     * @return true: スタンド可能
     */
    @Override
    public boolean canStand() {
        Hand hand = new Hand(cards);
        return 17 <= hand.getHandScore();
    }
}
