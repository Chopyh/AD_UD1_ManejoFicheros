package jv.chopy.crud.data;

import jv.chopy.crud.model.Player;
import jv.chopy.crud.utils.PropertiesLoader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.TreeSet;

/**
 * Serialize and deserialize Player object to binary file using RandomAccessFile.
 *
 * @author Javier Vázquez
 * @version 1.0
 */
public class BinaryRandom {

    private static final String FILE_PATH = PropertiesLoader.getProperty("files.binary.random");

    /**
     * Generates a file with random Player objects.
     *
     * @param players the set of Players
     * @throws IOException if an I/O error occurs
     */
    public static void generateRandomFile(TreeSet<Player> players) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "rw")) {
            if(players.isEmpty()) {
                raf.setLength(0);
            }
            for (Player player : players) {
                raf.writeInt(player.getId());
                StringBuffer sb = new StringBuffer(player.getNick_name());
                sb.setLength(16);
                raf.writeChars(sb.toString());
                raf.writeInt(player.getExperience());
                raf.writeInt(player.getCoins());
            }
        }
    }

    /**
     * Reads Player objects from the generated file.
     *
     * @return a tree of Player objects read from the file
     * @throws IOException if an I/O error occurs
     */
    public static TreeSet<Player> readRandomFile() throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "r")) {
            int count = (int) (raf.length() / (Integer.BYTES * 3 + 16 * 2));
            TreeSet<Player> players = new TreeSet<>();
            for (int i = 0; i < count; i++) {
                int id = raf.readInt();
                char[] nick_nameChars = new char[16];
                for (int j = 0; j < 16; j++) {
                    nick_nameChars[j] = raf.readChar();
                }
                String nick_name = new String(nick_nameChars).trim();
                int experience = raf.readInt();
                int coins = raf.readInt();
                players.add(new Player(id, nick_name, experience, coins));
            }
            return players;
        }
    }
}