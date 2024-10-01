package jv.chopy.crud.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void playerConstructorWithParameters() {
        Player player = new Player(1, "PlayerOne", 100, 50);
        assertEquals(1, player.getId());
        assertEquals("PlayerOne", player.getNick_name());
        assertEquals(100, player.getExperience());
        assertEquals(50, player.getCoins());
    }

    @Test
    void playerConstructorWithDefaultValues() {
        Player player = new Player();
        assertEquals(0, player.getId());
        assertEquals("", player.getNick_name());
        assertEquals(0, player.getExperience());
        assertEquals(0, player.getCoins());
    }

    @Test
    void setIdUpdatesId() {
        Player player = new Player();
        player.setId(2);
        assertEquals(2, player.getId());
    }

    @Test
    void setNickNameUpdatesNickName() {
        Player player = new Player();
        player.setNick_name("NewNick");
        assertEquals("NewNick", player.getNick_name());
    }

    @Test
    void setExperienceUpdatesExperience() {
        Player player = new Player();
        player.setExperience(200);
        assertEquals(200, player.getExperience());
    }

    @Test
    void setCoinsUpdatesCoins() {
        Player player = new Player();
        player.setCoins(100);
        assertEquals(100, player.getCoins());
    }

    @Test
    void playerWithNegativeExperience() {
        Player player = new Player(1, "PlayerOne", -10, 50);
        assertEquals(-10, player.getExperience());
    }

    @Test
    void playerWithNegativeCoins() {
        Player player = new Player(1, "PlayerOne", 100, -20);
        assertEquals(-20, player.getCoins());
    }
}