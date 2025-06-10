package net.hollage.player;

import net.hollage.card.Card;
import net.hollage.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantTest {

    private TestParticipant participant;

    @BeforeEach
    void setUp() {
        participant = new TestParticipant();
    }

    @DisplayName("指定したカードが追加されること")
    @Test
    void addCard_ShouldAddCardToHand() {
        Card card = new Card(Suit.HEARTS, 1);

        assertDoesNotThrow(() -> participant.addCard(card));

        assertEquals("[H]A", participant.showAllCards());
    }

    @DisplayName("手札がない時、Emptyが返ること")
    @Test
    void showAllCards_WhenEmpty_ShouldReturnEmptyString() {
        assertEquals("Empty", participant.showAllCards());
    }

    @DisplayName("手札がある時、全ての手札が開示されること")
    @Test
    void showAllCards_WithMultipleCards_ShouldReturnFormattedString() {
        participant.addCard(new Card(Suit.HEARTS, 1));
        participant.addCard(new Card(Suit.SPADES, 13));

        assertEquals("[H]A, [S]K", participant.showAllCards());
    }

    @DisplayName("基底クラスではshowPartOfCardsとshowAllCardsは同じ結果が得られること")
    @Test
    void showPartOfCards_ShouldReturnSameAsShowAllCards() {
        participant.addCard(new Card(Suit.HEARTS, 1));

        assertEquals(participant.showAllCards(), participant.showPartOfCards());
    }

    @DisplayName("手札の合計点が21点未満の時、trueが返ること")
    @Test
    void canHit_WhenUnder21_ShouldReturnTrue() {
        participant.addCard(new Card(Suit.HEARTS, 10));

        assertTrue(participant.canHit());
    }

    @DisplayName("手札の合計点が21点の時、falseが返ること")
    @Test
    void canHit_WhenAt21_ShouldReturnFalse() {
        participant.addCard(new Card(Suit.HEARTS, 1));
        participant.addCard(new Card(Suit.SPADES, 13));

        assertFalse(participant.canHit());
    }

    @DisplayName("手札の合計点が22点以上の時、falseが返ること")
    @Test
    void canHit_WhenOver1_ShouldReturnFalse() {
        participant.addCard(new Card(Suit.HEARTS, 5));
        participant.addCard(new Card(Suit.SPADES, 7));
        participant.addCard(new Card(Suit.DIAMONDS, 13));

        assertFalse(participant.canHit());
    }

    @DisplayName("手札の合計点が返ること")
    @Test
    void getHandScore_ShouldReturnCorrectScore() {
        participant.addCard(new Card(Suit.HEARTS, 13));
        participant.addCard(new Card(Suit.SPADES, 5));

        assertEquals(15, participant.getHandScore());
    }

    @DisplayName("手札の合計点が21点を超えるの時、trueが返ること")
    @Test
    void isBust_WhenOver21_ShouldReturnTrue() {
        participant.addCard(new Card(Suit.HEARTS, 13));
        participant.addCard(new Card(Suit.SPADES, 12));
        participant.addCard(new Card(Suit.DIAMONDS, 11));

        assertTrue(participant.isBust());
    }

    @DisplayName("手札の合計点が21点以下の時、trueが返ること")
    @Test
    void isBust_WhenUnder21_ShouldReturnFalse() {
        participant.addCard(new Card(Suit.HEARTS, 13));
        participant.addCard(new Card(Suit.SPADES, 12));
        participant.addCard(new Card(Suit.DIAMONDS, 1));

        assertFalse(participant.isBust());
    }

    private static class TestParticipant extends Participant {
        @Override
        public boolean canStand() {
            return true;
        }
    }
}