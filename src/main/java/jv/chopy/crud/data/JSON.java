package jv.chopy.crud.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jv.chopy.crud.utils.PropertiesLoader;

import java.lang.reflect.Type;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JSON {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private String jsonFilePath = PropertiesLoader.getProperty("files.json");

    /**
     * Serializes the specified object into its equivalent JSON representation.
     *
     * @param object the object to be serialized
     * @return a JSON string representation of the object
     */
    public static String serialize(Object object) {
        return gson.toJson(object);
    }

    /**
     * Deserializes the specified JSON string into an object of the specified class.
     *
     * @param json the JSON string to be deserialized
     * @param type the class of T
     * @param <T> the type of the desired object
     * @return an object of type T from the JSON string
     */
    public static <T> T deserialize(String json, Type type) {
        return gson.fromJson(json, type);
    }

    /**
     * Deserializes the specified JSON string into an object of the specified class.
     * @param json the JSON string to be deserialized
     * @param clazz the class of T
     * @return an object of type T from the JSON string
     * @param <T>  the type of the desired object
     */

    public static <T> Set<T> deserializeToSet(String json, Class<T> clazz) {
        Type type = TypeToken.getParameterized(Set.class, clazz).getType();
        return gson.fromJson(json, type);
    }

    /**
     * Exports the specified HashSet to a JSON file.
     *
     * @param hashSet the HashSet to be exported
     * @throws IOException if an I/O error occurs
     */
    public void exportToJsonFile(HashSet<?> hashSet) throws IOException {
        String json = serialize(hashSet);
        try (FileWriter writer = new FileWriter(jsonFilePath)) {
            writer.write(json);
        }
    }
}