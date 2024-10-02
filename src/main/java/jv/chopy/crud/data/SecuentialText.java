package jv.chopy.crud.data;

import jv.chopy.crud.model.Player;
import jv.chopy.crud.utils.PropertiesLoader;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Serialize and deserialize Player objects to a text file sequentially.
 */
public class SecuentialText {

    private static final String FILE_PATH = PropertiesLoader.getProperty("files.text.secuential");

    /**
     * Serializes a list of Player objects to a text file sequentially.
     *
     * @param players the list of Player objects to be serialized
     * @throws IOException if an I/O error occurs
     */
    public void serialize(Set<Player> players) throws IOException {
        assert FILE_PATH != null;

        if (players == null) {
            throw new NullPointerException("The Set of players to be serialized cannot be null");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Player player : players) {
                writer.write(player.getId() + "," + player.getNick_name() + "," + player.getExperience() + "," + player.getCoins());
                writer.newLine();
            }
        }
    }

    /**
     * Deserializes a Set of Player objects from a text file sequentially.
     *
     * @return the Set of deserialized Player objects
     * @throws IOException if an I/O error occurs
     */
    public Set<Player> deserialize() throws IOException {
        assert FILE_PATH != null;

        Set<Player> players = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String nick_name = parts[1];
                int experience = Integer.parseInt(parts[2]);
                int coins = Integer.parseInt(parts[3]);
                players.add(new Player(id, nick_name, experience, coins));
            }
        }
        return players;
    }
}