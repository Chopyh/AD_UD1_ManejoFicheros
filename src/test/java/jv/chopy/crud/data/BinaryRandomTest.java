package jv.chopy.crud.data;

import jv.chopy.crud.model.Player;
import jv.chopy.crud.utils.PropertiesLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class BinaryRandomTest {

    private TreeSet<Player> players;

    @BeforeEach
    void setUp() {
        players = new TreeSet<>();
        players.add(new Player(1, "Player1", 100, 50));
        players.add(new Player(2, "Player2", 200, 150));
    }

    @Test
    void generateAndReadRandomFile() throws IOException {
        BinaryRandom.generateRandomFile(players);
        TreeSet<Player> readPlayers = BinaryRandom.readRandomFile();
        assertEquals(players, readPlayers);
    }

    @Test
    void generateRandomFileWithEmptySet() throws IOException {
        TreeSet<Player> emptyPlayers = new TreeSet<>();
        BinaryRandom.generateRandomFile(emptyPlayers);
        TreeSet<Player> readPlayers = BinaryRandom.readRandomFile();
        assertTrue(readPlayers.isEmpty());
    }
}