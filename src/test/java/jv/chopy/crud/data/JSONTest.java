package jv.chopy.crud.data;

import jv.chopy.crud.model.Player;
import jv.chopy.crud.utils.PropertiesLoader;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;

class JSONTest {

    private static final String jsonFilePath = PropertiesLoader.getProperty("files.json");

    @Test
    void serializeObjectToJsonString() {
        Player player = new Player(1, "PlayerOne", 100, 50);
        String json = JSON.serialize(player);
        assertTrue(json.contains("\"id\": 1"));
        assertTrue(json.contains("\"nick_name\": \"PlayerOne\""));
        assertTrue(json.contains("\"experience\": 100"));
        assertTrue(json.contains("\"coins\": 50"));
    }

    @Test
    void deserializeJsonStringToObject() {
        String json = "{\"id\":1,\"nick_name\":\"PlayerOne\",\"experience\":100,\"coins\":50}";
        Player player = JSON.deserialize(json, Player.class);
        assertEquals(1, player.getId());
        assertEquals("PlayerOne", player.getNick_name());
        assertEquals(100, player.getExperience());
        assertEquals(50, player.getCoins());
    }

    @Test
    void exportHashSetToJsonFile() throws IOException {
        assert jsonFilePath != null;

        HashSet<Player> players = new HashSet<>();
        JSON jsonUtil = new JSON();
        File file = new File(jsonFilePath);

        players.add(new Player(1, "PlayerOne", 100, 50));
        players.add(new Player(2, "PlayerTwo", 200, 100));

        jsonUtil.exportToJsonFile(players);
        assertTrue(file.exists());
        try (FileReader reader = new FileReader(file)) {
            char[] buffer = new char[1024];
            int length = reader.read(buffer);
            String json = new String(buffer, 0, length);
            assertTrue(json.contains("\"id\": 1"));
            assertTrue(json.contains("\"nick_name\": \"PlayerOne\""));
            assertTrue(json.contains("\"experience\": 100"));
            assertTrue(json.contains("\"coins\": 50"));
            assertTrue(json.contains("\"id\": 2"));
            assertTrue(json.contains("\"nick_name\": \"PlayerTwo\""));
            assertTrue(json.contains("\"experience\": 200"));
            assertTrue(json.contains("\"coins\": 100"));
        }
    }

    @Test
    void exportEmptyHashSetToJsonFile() throws IOException {
        assert jsonFilePath != null;

        HashSet<Player> players = new HashSet<>();
        JSON jsonUtil = new JSON();
        jsonUtil.exportToJsonFile(players);
        File file = new File(jsonFilePath);
        assertTrue(file.exists());
        try (FileReader reader = new FileReader(file)) {
            char[] buffer = new char[1024];
            int length = reader.read(buffer);
            String json = new String(buffer, 0, length);
            assertEquals("[]", json.trim());
        }
    }

    @Test
    void deserializeInvalidJsonStringThrowsException() {
        String invalidJson = "{\"id\":1,\"nick_name\":\"PlayerOne\",\"experience\":100,\"coins\":50";
        assertThrows(com.google.gson.JsonSyntaxException.class, () -> JSON.deserialize(invalidJson, Player.class));
    }

    @Test
    void deserializeJsonToHashSet(){
        String json = "[{\"id\": 1,\"nick_name\": \"PlayerOne\",\"experience\": 100,\"coins\": 50},{\"id\": 2,\"nick_name\": \"PlayerTwo\",\"experience\": 200,\"coins\": 100}]";
        HashSet<Player> players = (HashSet<Player>) JSON.deserializeToSet(json, Player.class);
        assertEquals(2, players.size());
        assertTrue(players.stream().anyMatch(p -> p.getId() == 1));
        assertTrue(players.stream().anyMatch(p -> p.getId() == 2));
    }
}