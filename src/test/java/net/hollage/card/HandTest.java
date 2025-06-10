package net.hollage.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {

    @DisplayName("手札が空の時に合計点が0を返すこと")
    @Test
    void calcScore_emptyHand_returnsZero() {
        Hand hand = new Hand(List.of());

        int actual = hand.calcScore();

        assertEquals(0, actual);
    }

    @DisplayName("手札に1がない時に合計点が返ること")
    @ParameterizedTest
    @CsvSource({"2, 3, 5", "2, 8, 10", "9, 12, 19", "11, 13, 20"})
    void calcScore_handWithNoAces_returnsSumOfCardScores(int card1Num, int card2Num, int expected) {
        Card card1 = new Card(Suit.HEARTS, card1Num);
        Card card2 = new Card(Suit.DIAMONDS, card2Num);

        Hand hand = new Hand(List.of(card1, card2));
        int actual = hand.calcScore();

        assertEquals(expected, actual);
    }

    @DisplayName("手札に1がなくバーストするケース")
    @Test
    void calcScore_handWithNoAces_returnsBust() {
        Card card1 = new Card(Suit.HEARTS, 2);
        Card card2 = new Card(Suit.DIAMONDS, 10);
        Card card3 = new Card(Suit.DIAMONDS, 11);

        Hand hand = new Hand(List.of(card1, card2, card3));
        int actual = hand.calcScore();

        assertEquals(22, actual);
    }

    @DisplayName("Aが11点としてカウントされるケース")
    @ParameterizedTest
    @CsvSource({"1, 12", "4, 15", "13, 21"})
    void calcScore_handWithOneAceAndOtherCardsUnder21_returnsCorrectScore(int cardNum, int expected) {
        Card ace = new Card(Suit.SPADES, 1);
        Card card = new Card(Suit.HEARTS, cardNum);

        Hand hand = new Hand(List.of(ace, card));
        int actual = hand.calcScore();

        assertEquals(expected, actual);
    }

    @DisplayName("Aが2枚以上あり、1点と11点としてバーストしないケース")
    @ParameterizedTest
    @CsvSource({"2, 14", "9, 21", "7, 19"})
    void calcScore_handWithMultipleAcesAdjustingToStayUnder21_returnsCorrectScore(int cardNum, int expected) {
        Card ace1 = new Card(Suit.SPADES, 1);
        Card ace2 = new Card(Suit.CLUBS, 1);
        Card card = new Card(Suit.DIAMONDS, cardNum);

        Hand hand = new Hand(List.of(ace1, ace2, card));
        int actual = hand.calcScore();

        assertEquals(expected, actual);
    }

    @DisplayName("Aが2枚以上あり、両者1点としてバーストしないケース")
    @ParameterizedTest
    @CsvSource({"9, 10, 21", "3, 7, 12", "1, 9, 12"})
    void calcScore_handWithMultipleAcesAndScoreOver21_returnsAdjustedScore(int cardNum1, int cardNum2, int expected) {
        Card ace1 = new Card(Suit.HEARTS, 1);
        Card ace2 = new Card(Suit.DIAMONDS, 1);
        Card card1 = new Card(Suit.SPADES, cardNum1);
        Card card2 = new Card(Suit.CLUBS, cardNum2);

        Hand hand = new Hand(List.of(ace1, ace2, card1, card2));
        int actual = hand.calcScore();

        assertEquals(expected, actual);
    }

    @DisplayName("絵柄が10としてカウントされること")
    @ParameterizedTest
    @CsvSource({"10, 11", "12, 13"})
    void calcScore_handWithAllFaceCards_returnsSumCorrectly(int cardNum1, int cardNum2) {
        Card card1 = new Card(Suit.SPADES, cardNum1);
        Card card2 = new Card(Suit.HEARTS, cardNum2);

        Hand hand = new Hand(List.of(card1, card2));
        int actual = hand.calcScore();

        assertEquals(20, actual);
    }

    @DisplayName("バースト判定")
    @ParameterizedTest
    @CsvSource({"1, false", "2, true"})
    void isBust(int cardNum, boolean expected) {
        Card card1 = new Card(Suit.SPADES, 10);
        Card card2 = new Card(Suit.HEARTS, 13);
        Card card3 = new Card(Suit.HEARTS, cardNum);

        Hand hand = new Hand(List.of(card1, card2, card3));
        boolean actual = hand.isBust();

        assertEquals(expected, actual);
    }
}