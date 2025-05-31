package net.hollage.rule;

import net.hollage.card.Deck;
import net.hollage.player.Participant;
import net.hollage.printer.PrinterService;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/** ブラックジャックルール. */
public class BlackJackRule {

    /** ディーラー. */
    private final Participant dealer;
    /** プレイヤー. */
    private final Participant player;
    /** 出力サービス. */
    private final PrinterService printer;
    /** デッキ. */
    private final Deck deck;

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
    protected void init() {
        // 配札（ディーラー）
        dealer.addCard(deck.deal());
        dealer.addCard(deck.deal());
        printer.print("dealer: " + dealer.showPartOfCards());

        // 配札（プレイヤー）
        player.addCard(deck.deal());
        player.addCard(deck.deal());
        printer.print("player: " + player.showAllCards());
    }

    /** プレイヤーのターン. */
    protected void playPlayer() {
        try (
                Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            while (player.canHit()) {
                printer.print("do you want to hit or stand?");
                printer.print("your score: " + player.getHandScore());
                System.out.print("1: Hit 2: Stand >> ");
                int input = scanner.nextInt();
                if (1 == input) {
                    player.addCard(deck.deal());
                    printer.print("player: " + player.showAllCards());
                } else if (2 == input) {
                    break;
                } else {
                    printer.print("your input is illegal!!");
                }
            }
        }
    }

    /** ディーラーのターン. */
    protected void playDealer() {
        while (!dealer.canStand() && !dealer.isBust()) {
            printer.print("dealer: " + dealer.showAllCards());
            printer.print("once more deal...");
            dealer.addCard(deck.deal());
        }
    }

    /** 結果をチェックする. */
    protected void checkResult() {
        printer.print("/_/_/_/_/_/_/_RESULT/_/_/_/_/_/_/_");
        printer.print("player[" + player.getHandScore() + "]: " + player.showAllCards());
        printer.print("dealer[" + dealer.getHandScore() + "]: " + dealer.showAllCards());
        printer.print("/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_");
    }

    /** 結果を表示する. */
    protected void displayResult() {
        if (dealer.getHandScore() < player.getHandScore()) {
            printer.print("player win!!");
        } else if (dealer.getHandScore() > player.getHandScore()) {
            printer.print("dealer win...");
        } else {
            printer.print("result is draw");
        }
    }

}
