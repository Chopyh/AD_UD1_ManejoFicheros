package jv.chopy.crud.data;

import jv.chopy.crud.model.Player;
import jv.chopy.crud.utils.PropertiesLoader;

import java.io.*;
import java.util.Collection;
import java.util.TreeSet;

public class BinaryObject {

    private static final String filePath = PropertiesLoader.getProperty("files.binary.object");

    /**
     * Serializes the specified object to a binary file.
     *
     * @param object the object to be serialized
     * @throws IOException if an I/O error occurs
     */
    public static void serialize(TreeSet<Player> object) throws IOException {
        assert filePath != null;

        if (object == null) {
            throw new NullPointerException("The object to be serialized cannot be null");
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(object);
        }
    }

    /**
     * Deserializes an object from a binary file.
     *
     * @param <T> the type of the desired object
     * @return the deserialized object
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class of the deserialized object cannot be found
     */
    public static Collection<Player> deserialize() throws IOException, ClassNotFoundException {
        assert filePath != null;
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (TreeSet<Player>) in.readObject();
        }
    }
}