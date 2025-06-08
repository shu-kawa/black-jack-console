package net.hollage.app;

import net.hollage.player.Dealer;
import net.hollage.player.Participant;
import net.hollage.player.Player;
import net.hollage.printer.ConsolePrinter;
import net.hollage.printer.PrinterService;
import net.hollage.rule.BlackJackRule;
import net.hollage.rule.DefaultBlackJackRule;

/** エントリーポイント. */
public class BlackJack {

    /** エントリーポイント. */
    public static void main(String[] args) {
        Participant dealer = new Dealer();
        Participant player = new Player();
        PrinterService printer = new ConsolePrinter();
        BlackJackRule blackJackRule = new DefaultBlackJackRule(dealer, player, printer);
        blackJackRule.play();
    }
}
