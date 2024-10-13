package jv.chopy.crud.data;

import jv.chopy.crud.model.Player;
import jv.chopy.crud.utils.PropertiesLoader;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Serialize and deserialize Player objects to a binary file sequentially.
 */
public class BinarySecuential {

    private static final String FILE_PATH = PropertiesLoader.getProperty("files.binary.secuential");

    /**
     * Serializes a list of Player objects to a binary file sequentially.
     *
     * @param players the set of Player objects to be serialized
     * @throws IOException if an I/O error occurs
     */
    public static void serialize(Set<Player> players) throws IOException {
        assert FILE_PATH != null;

        if (players == null) {
            throw new NullPointerException("The list of players to be serialized cannot be null");
        }

        try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            for (Player player : players) {
                out.writeObject(player);
            }
        }
    }

    /**
     * Deserializes a list of Player objects from a binary file sequentially.
     *
     * @return the set of deserialized Player objects
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class of a deserialized object cannot be found
     */
    public static TreeSet<Player> deserialize() throws IOException, ClassNotFoundException {
        assert FILE_PATH != null;

        TreeSet<Player> players = new TreeSet<>();
        try (FileInputStream fileIn = new FileInputStream(FILE_PATH);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            while (fileIn.available() > 0) {
                players.add((Player) in.readObject());
            }
        }
        return players;
    }
}