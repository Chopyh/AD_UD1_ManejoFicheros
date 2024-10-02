package jv.chopy.crud.data;

import jv.chopy.crud.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class BinarySecuentialTest {

    private BinarySecuential binarySecuential;
    private Set<Player> players;

    @BeforeEach
    void setUp() {
        binarySecuential = new BinarySecuential();
        players = new TreeSet<>();
        players.add(new Player(1, "Player1", 100, 50));
        players.add(new Player(2, "Player2", 200, 150));
    }

    @Test
    void serializeAndDeserializePlayers() throws IOException, ClassNotFoundException {
        binarySecuential.serialize(players);
        Set<Player> deserializedPlayers = binarySecuential.deserialize();
        assertEquals(players, deserializedPlayers);
    }

    @Test
    void serializeNullPlayersThrowsException() {
        assertThrows(NullPointerException.class, () -> binarySecuential.serialize(null));
    }
}