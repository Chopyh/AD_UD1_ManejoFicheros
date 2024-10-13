package jv.chopy.crud.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jv.chopy.crud.model.Player;
import jv.chopy.crud.utils.PropertiesLoader;

import java.lang.reflect.Type;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeSet;
import java.util.Set;

public class JSON {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private String jsonFilePath = PropertiesLoader.getProperty("files.json");

    /**
     * Serializes the specified object into its equivalent JSON representation.
     *
     * @param object the object to be serialized
     * @return a JSON string representation of the object
     */
    public String serializeToString(Object object) {
        return gson.toJson(object);
    }

    public Set<Player> deserializeToSet() throws IOException {
        Type type = new TypeToken<TreeSet<Player>>() {}.getType();
        String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        return gson.fromJson(jsonContent, type);
    }

    /**
     * Exports the specified treeSet to a JSON file.
     *
     * @param treeSet the treeSet to be exported
     * @throws IOException if an I/O error occurs
     */
    public void exportToJsonFile(TreeSet<Player> treeSet) throws IOException {
        String json = serializeToString(treeSet);
        try (FileWriter writer = new FileWriter(jsonFilePath)) {
            writer.write(json);
        }
    }
}