package jv.chopy.crud.dao;

import jv.chopy.crud.data.*;
import jv.chopy.crud.model.Player;
import jv.chopy.crud.utils.PropertiesLoader;

import java.util.Collection;
import java.util.TreeSet;
import java.util.Optional;

/**
 * The PlayerDAO class provides CRUD operations for Player objects.
 * It implements the I_DAO interface.
 *
 * @author Javier Vázquez
 * @version 1.0
 */
public class PlayerDAO implements I_DAO<Player> {

    private TreeSet<Player> players;

    /**
     * Constructs a new PlayerDAO with an empty set of players.
     */
    public PlayerDAO() {

        String exportType = PropertiesLoader.getProperty("app.export");

        try {
            switch (exportType) {
                case "text.secuential":
                    players = new SecuentialText().deserialize();
                    break;
                case "binary.secuential":
                    players = (TreeSet<Player>) new BinarySecuential().deserialize();
                    break;
                case "binary.object":
                    players = (TreeSet<Player>) BinaryObject.deserialize();
                    break;
                case "binary.random":
                    players = BinaryRandom.readRandomFile();
                    break;
                case "json":
                    players = (TreeSet<Player>) new JSON().deserializeToSet();
                    break;
                default:
                    players = new TreeSet<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new player and adds it to the set of players.
     *
     * @param player the player to be added
     */
    @Override
    public void create(Player player) {
        players.add(player);
    }

    /**
     * Reads a player by their id.
     *
     * @param id the id of the player to be read
     * @return the player with the specified id, or null if no such player exists
     */
    @Override
    public Player read(int id) {
        Optional<Player> player = players.stream().filter(p -> p.getId() == id).findFirst();
        return player.orElse(null);
    }

    /**
     * Updates an existing player.
     * The player with the same id is removed and the new player is added.
     *
     * @param player the player to be updated
     */
    @Override
    public void update(Player player) {
        if (players.contains(player)) {
            players.remove(player);
            players.add(player);
        }
    }

    /**
     * Deletes a player by their id.
     *
     * @param id the id of the player to be deleted
     */
    @Override
    public void delete(int id) {
        players.removeIf(player -> player.getId() == id);
    }

    /**
     * Gets all players.
     *
     * @return a collection of all players
     */
    public Collection<Player> getPlayers() {
        return players;
    }

    /**
     * Exports the players data to the specified format.
     */
    public void export() {
        String exportType = PropertiesLoader.getProperty("app.export");

        try {
            switch (exportType) {
                case "text.secuential":
                    SecuentialText.serialize(players);
                    break;
                case "binary.secuential":
                    BinarySecuential.serialize(players);
                    break;
                case "binary.object":
                    BinaryObject.serialize(players);
                    break;
                case "binary.random":
                    BinaryRandom.generateRandomFile(players);
                    break;
                case "json":
                    new JSON().exportToJsonFile(players);
                    break;
                default:
                    System.out.println("Invalid export type.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}