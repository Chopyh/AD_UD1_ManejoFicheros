package jv.chopy.crud.data;

import jv.chopy.crud.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class SecuentialTextTest {

    private SecuentialText secuentialText;
    private Set<Player> players;

    @BeforeEach
    void setUp() {
        secuentialText = new SecuentialText();
        players = new TreeSet<>();
        players.add(new Player(1, "Player1", 100, 50));
        players.add(new Player(2, "Player2", 200, 150));
    }

    @Test
    void serializeAndDeserializePlayers() throws IOException {
        secuentialText.serialize(players);
        Set<Player> deserializedPlayers = secuentialText.deserialize();
        assertEquals(players, deserializedPlayers);
    }

    @Test
    void serializeNullPlayersThrowsException() {
        assertThrows(NullPointerException.class, () -> secuentialText.serialize(null));
    }

    @Test
    void deserializeFromEmptyFile() throws IOException {
        Set<Player> deserializedPlayers = secuentialText.deserialize();
        assertTrue(deserializedPlayers.isEmpty());
    }
}