package net.hollage;

import net.hollage.deck.Deck;
import net.hollage.player.Dealer;
import net.hollage.player.Participant;
import net.hollage.player.Player;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BlackJack {

    public static void main(String[] args) {
        BlackJack blackJack = new BlackJack();
        blackJack.run();
    }

    public void run() {
        // デッキとプレイヤーの初期化
        Deck deck = Deck.getInstance().init();
        Participant dealer = new Dealer();
        Participant player = new Player();

        // ベット（省略）

        // 配札（ディーラー）
        dealer.addCard(deck.deal());
        dealer.addCard(deck.deal());
        System.out.println("dealer: " + dealer.showPartOfCards());

        // 配札（プレイヤー）
        player.addCard(deck.deal());
        player.addCard(deck.deal());
        System.out.println("player: " + player.showAllCards());
        try (
                Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            while (player.canHit()) {
                System.out.println("do you want to hit or stand?");
                System.out.println("your score: " + player.getHandScore());
                System.out.print("1: Hit 2: Stand >> ");
                int input = scanner.nextInt();
                if (1 == input) {
                    player.addCard(deck.deal());
                    System.out.println("player: " + player.showAllCards());
                } else if (2 == input) {
                    break;
                } else {
                    System.out.println("your input is illegal!!");
                }
            }
        }

        // ディーラーのターン
        while (!dealer.canStand() && !dealer.isBust()) {
            System.out.println("dealer: " + dealer.showAllCards());
            System.out.println("once more deal...");
            dealer.addCard(deck.deal());
        }
        System.out.println("/_/_/_/_/_/_/_RESULT/_/_/_/_/_/_/_");
        System.out.println("player[" + player.getHandScore() + "]: " + player.showAllCards());
        System.out.println("dealer[" + dealer.getHandScore() + "]: " + dealer.showAllCards());
        System.out.println("/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_");

        // 勝敗判定
        if (dealer.getHandScore() < player.getHandScore()) {
            System.out.println("player win!!");
        } else if (dealer.getHandScore() > player.getHandScore()) {
            System.out.println("dealer win...");
        } else {
            System.out.println("result is draw");
        }
    }
}
