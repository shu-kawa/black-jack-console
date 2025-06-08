package net.hollage.rule;

import net.hollage.card.Deck;
import net.hollage.player.Participant;
import net.hollage.printer.PrinterService;

abstract public class BlackJackRule {

    /** ディーラー. */
    protected final Participant dealer;
    /** プレイヤー. */
    protected final Participant player;
    /** 出力サービス. */
    protected final PrinterService printer;
    /** デッキ. */
    protected final Deck deck;

    /**
     * コンストラクタ.
     *
     * @param dealer  ディーラー
     * @param player  プレイヤー
     * @param printer 出力サービス
     */
    public BlackJackRule(Participant dealer, Participant player, PrinterService printer) {
        this.dealer = dealer;
        this.player = player;
        this.printer = printer;
        this.deck = Deck.getInstance();
    }

    /** ゲームプレイ. */
    public void play() {
        init();
        playPlayer();
        playDealer();
        checkResult();
        displayResult();
    }

    /** 初期化. */
    abstract void init();

    /** プレイヤーのターン. */
    abstract void playPlayer();

    /** ディーラーのターン. */
    abstract void playDealer();

    /** 結果をチェックする. */
    abstract void checkResult();

    /** 結果を表示する. */
    abstract void displayResult();
}
