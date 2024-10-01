package jv.chopy.crud.dao;

import jv.chopy.crud.model.Player;

import java.util.HashSet;
import java.util.Optional;

/**
 * The PlayerDAO class provides CRUD operations for Player objects.
 * It implements the I_DAO interface.
 *
 * @author Javier Vázquez
 * @version 1.0
 */
public class PlayerDAO implements I_DAO<Player> {

    private HashSet<Player> players;

    /**
     * Constructs a new PlayerDAO with an empty set of players.
     */
    public PlayerDAO() {
        players = new HashSet<>();
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
        delete(player.getId());
        create(player);
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
}