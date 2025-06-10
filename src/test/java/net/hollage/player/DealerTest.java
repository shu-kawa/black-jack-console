package net.hollage.player;

import net.hollage.card.Card;
import net.hollage.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DealerTest {

    @DisplayName("手札が空の時Emptyが返ること")
    @Test
    public void testShowPartOfCardsWhenEmpty() {
        Dealer dealer = new Dealer();

        String actual = assertDoesNotThrow(dealer::showPartOfCards);

        String expected = "Empty";
        assertEquals(expected, actual);
    }

    @DisplayName("手札が1枚の時、全て開示されること")
    @Test
    public void testShowPartOfCardsWithOneCard() {
        Dealer dealer = new Dealer();
        dealer.cards.add(new Card(Suit.SPADES, 1));

        String actual = dealer.showPartOfCards();

        String expected = "[S]A";
        assertEquals(expected, actual);
    }

    @DisplayName("手札が複数枚の時、1枚目以外の情報が伏せられること")
    @Test
    public void testShowPartOfCardsWithMultiCard() {
        Dealer dealer = new Dealer();
        dealer.cards.add(new Card(Suit.CLUBS, 3));
        dealer.cards.add(new Card(Suit.HEARTS, 11));
        dealer.cards.add(new Card(Suit.DIAMONDS, 13));

        String actual = dealer.showPartOfCards();

        String expected = "[C]3, ???, ???";
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"1, 6, true", "7, 10, true", "7, 13, true"
            , "1, 5, false", "6, 10, false", "6, 13, false"})
    public void testCanStand(int cardNum1, int cardNum2, boolean expected) {
        Dealer dealer = new Dealer();
        dealer.cards.add(new Card(Suit.SPADES, cardNum1));
        dealer.cards.add(new Card(Suit.SPADES, cardNum2));

        boolean actual = dealer.canStand();

        assertEquals(expected, actual);
    }
}