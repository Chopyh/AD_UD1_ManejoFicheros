package jv.chopy.crud.data;

import jv.chopy.crud.model.Player;
import jv.chopy.crud.utils.PropertiesLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class JSONTest {

    private JSON json;
    private TreeSet<Player> players;

    @BeforeEach
    void setUp() {
        json = new JSON();
        players = new TreeSet<>();
        players.add(new Player(1, "PlayerOne", 100, 50));
        players.add(new Player(2, "PlayerTwo", 200, 100));
    }

    @Test
    void serializeToStringObjectToJsonString() {
        String jsonString = json.serializeToString(players);
        assertTrue(jsonString.contains("\"id\": 1"));
        assertTrue(jsonString.contains("\"nick_name\": \"PlayerOne\""));
        assertTrue(jsonString.contains("\"experience\": 100"));
        assertTrue(jsonString.contains("\"coins\": 50"));
        assertTrue(jsonString.contains("\"id\": 2"));
        assertTrue(jsonString.contains("\"nick_name\": \"PlayerTwo\""));
        assertTrue(jsonString.contains("\"experience\": 200"));
        assertTrue(jsonString.contains("\"coins\": 100"));
    }

    @Test
    void deserializeJsonStringToObject() throws IOException {
        json.exportToJsonFile(players);
        TreeSet<Player> deserializedPlayers = (TreeSet<Player>) json.deserializeToSet();
        assertEquals(2, deserializedPlayers.size());
        Player player = deserializedPlayers.first();
        assertEquals(1, player.getId());
        assertEquals("PlayerOne", player.getNick_name());
        assertEquals(100, player.getExperience());
        assertEquals(50, player.getCoins());
        player = deserializedPlayers.last();
        assertEquals(2, player.getId());
        assertEquals("PlayerTwo", player.getNick_name());
        assertEquals(200, player.getExperience());
        assertEquals(100, player.getCoins());
    }

    @Test
    void exportHashSetToJsonFile() throws IOException {
        json.exportToJsonFile(players);
        String jsonFilePath = PropertiesLoader.getProperty("files.json");
        try (FileReader reader = new FileReader(jsonFilePath)) {
            char[] buffer = new char[1024];
            int length = reader.read(buffer);
            String jsonString = new String(buffer, 0, length);
            assertTrue(jsonString.contains("\"id\": 1"));
            assertTrue(jsonString.contains("\"nick_name\": \"PlayerOne\""));
            assertTrue(jsonString.contains("\"experience\": 100"));
            assertTrue(jsonString.contains("\"coins\": 50"));
            assertTrue(jsonString.contains("\"id\": 2"));
            assertTrue(jsonString.contains("\"nick_name\": \"PlayerTwo\""));
            assertTrue(jsonString.contains("\"experience\": 200"));
            assertTrue(jsonString.contains("\"coins\": 100"));
        }
    }

    @Test
    void exportEmptyHashSetToJsonFile() throws IOException {
        TreeSet<Player> emptyPlayers = new TreeSet<>();
        json.exportToJsonFile(emptyPlayers);
        String jsonFilePath = PropertiesLoader.getProperty("files.json");
        try (FileReader reader = new FileReader(jsonFilePath)) {
            char[] buffer = new char[1024];
            int length = reader.read(buffer);
            String jsonString = new String(buffer, 0, length);
            assertEquals("[]", jsonString.trim());
        }
    }
}