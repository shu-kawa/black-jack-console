package net.hollage.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @DisplayName("数字に対応する文字列が返ること")
    @ParameterizedTest
    @CsvSource({"1, A", "2, 2", "3, 3", "4, 4", "5, 5",
            "6, 6", "7, 7", "8, 8", "9, 9", "10, 10",
            "11, J", "12, Q", "13, K"})
    void getString(int num, String expected) {
        Suit suit = Suit.CLUBS;
        Card sut = new Card(suit, num);

        String actual = assertDoesNotThrow(sut::getString);

        assertEquals("[C]" + expected, actual);
    }

    @DisplayName("存在しない数字の時、IllegalArgumentException例外が発生すること")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 14, 15})
    void getStringWithException(int num) {
        Suit suit = Suit.CLUBS;
        Card sut = new Card(suit, num);

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, sut::getString);

        String expected = String.format("Invalid card number: %d", num);
        assertEquals(expected, actual.getMessage());
    }

    @DisplayName("1以外のカードを引いた場合falseが返ること")
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14})
    void isAceWithoutAceCard(int num) {
        Suit suit = Suit.CLUBS;
        Card sut = new Card(suit, num);

        assertFalse(sut::isAce);
    }

    @DisplayName("1の場合trueが返ること")
    @Test
    void isAceWithAceCard() {
        Suit suit = Suit.CLUBS;
        Card sut = new Card(suit, 1);

        assertTrue(sut::isAce);
    }

    @DisplayName("絵柄の場合10を返すこと")
    @ParameterizedTest
    @ValueSource(ints = {10, 11, 12, 13})
    void getScore(int num) {
        Suit suit = Suit.CLUBS;
        Card sut = new Card(suit, num);

        int actual = assertDoesNotThrow(sut::getScore);

        assertEquals(10, actual);
    }
}