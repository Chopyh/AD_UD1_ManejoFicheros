package jv.chopy.crud.data;

import jv.chopy.crud.model.Player;
import jv.chopy.crud.utils.PropertiesLoader;

import java.io.*;

public class BinaryObject {

    private static final String filePath = PropertiesLoader.getProperty("files.binary.object");

    /**
     * Serializes the specified object to a binary file.
     *
     * @param object the object to be serialized
     * @throws IOException if an I/O error occurs
     */
    public static void serialize(Object object) throws IOException {
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
    public static <T> T deserialize() throws IOException, ClassNotFoundException {
        assert filePath != null;
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (T) in.readObject();
        }
    }
}