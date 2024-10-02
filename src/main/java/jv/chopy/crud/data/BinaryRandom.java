package jv.chopy.crud.data;

import jv.chopy.crud.model.Player;
import jv.chopy.crud.utils.PropertiesLoader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

/**
 * Serialize and deserialize Player object to binary file using RandomAccessFile.
 *
 * TODO: DO THIS CLASS
 */
public class BinaryRandom {

    private static final String FILE_PATH = PropertiesLoader.getProperty("files.binary.random");

    /**
     * Generates a file with random Player objects.
     *
     * @param count the number of random Player objects to generate
     * @throws IOException if an I/O error occurs
     */
    public void generateRandomFile(int count) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "rw")) {
            Random random = new Random();
            for (int i = 0; i < count; i++) {
                Player player = new Player(random.nextInt(), "Player" + i, random.nextInt(1000), random.nextInt(500));
                raf.writeInt(player.getId());
                raf.writeUTF(player.getNick_name());
                raf.writeInt(player.getExperience());
                raf.writeInt(player.getCoins());
            }
        }
    }

    /**
     * Reads Player objects from the generated file.
     *
     * @return an array of Player objects read from the file
     * @throws IOException if an I/O error occurs
     */
    public Player[] readRandomFile() throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "r")) {
            int count = (int) (raf.length() / (Integer.BYTES * 3 + 2)); // Approximate size of each Player object
            Player[] players = new Player[count];
            for (int i = 0; i < count; i++) {
                int id = raf.readInt();
                String nick_name = raf.readUTF();
                int experience = raf.readInt();
                int coins = raf.readInt();
                players[i] = new Player(id, nick_name, experience, coins);
            }
            return players;
        }
    }
}