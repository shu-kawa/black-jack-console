package net.hollage.rule;

import net.hollage.player.Participant;
import net.hollage.printer.PrinterService;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/** デフォルトのブラックジャックルール. */
public class DefaultBlackJackRule extends BlackJackRule {

    public DefaultBlackJackRule(Participant dealer, Participant player, PrinterService printer) {
        super(dealer, player, printer);
    }

    @Override
    public void init() {
        // 配札（ディーラー）
        dealer.addCard(deck.deal());
        dealer.addCard(deck.deal());
        printer.print("dealer: " + dealer.showPartOfCards());

        // 配札（プレイヤー）
        player.addCard(deck.deal());
        player.addCard(deck.deal());
        printer.print("player: " + player.showAllCards());
    }

    @Override
    public void playPlayer() {
        try (
                Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            while (player.canHit()) {
                printer.print("do you want to hit or stand?");
                printer.print("your score: " + player.getHandScore());
                printer.print("1: Hit 2: Stand >> ");
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

    @Override
    public void playDealer() {
        while (!dealer.canStand() && !dealer.isBust()) {
            printer.print("dealer: " + dealer.showAllCards());
            printer.print("once more deal...");
            dealer.addCard(deck.deal());
        }
    }

    @Override
    public void checkResult() {
        if (this.isLose()) {
            printer.print("dealer win...");
        } else if (this.isDraw()) {
            printer.print("result is draw");
        } else {
            printer.print("player win!!!");
        }
    }

    @Override
    public void displayResult() {
        printer.print("/_/_/_/_/_/_/_RESULT/_/_/_/_/_/_/_");
        printer.print("player[" + player.getHandScore() + "]: " + player.showAllCards());
        printer.print("dealer[" + dealer.getHandScore() + "]: " + dealer.showAllCards());
        printer.print("/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_");
    }

    /**
     * プレイヤーのバーストか、ディーラーがバーストせずに得点が下回っていれば負け.
     *
     * @return true: プレイヤーの負け
     */
    private boolean isLose() {
        return player.isBust() || (!dealer.isBust() && (player.getHandScore() < dealer.getHandScore()));
    }

    /**
     * 同点であれば引き分け.
     *
     * @return true: 引き分け
     */
    private boolean isDraw() {
        return player.getHandScore() == dealer.getHandScore();
    }
}
