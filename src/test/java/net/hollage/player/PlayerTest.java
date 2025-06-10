package net.hollage.player;

import net.hollage.card.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    @Test
    public void testCanStand_ShouldReturnTrue() {
        Player player = new Player();
        player.addCard(new Card(net.hollage.card.Suit.SPADES, 1));

        boolean actual = assertDoesNotThrow(player::canStand);

        assertTrue(actual);
    }
}