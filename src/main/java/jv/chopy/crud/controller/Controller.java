package jv.chopy.crud.controller;

import jv.chopy.crud.dao.PlayerDAO;
import jv.chopy.crud.model.Player;
import jv.chopy.view.I_Vista;

import java.util.Collection;

/**
 * Controller class that handles the business logic for Player operations.
 *
 * @version 1.0
 * @author Javier Vázquez
 */
public class Controller {
    private final PlayerDAO playerDAO;
    I_Vista<Player> view;

    /**
     * Constructor for Controller.
     * Initializes the PlayerDAO.
     */
    public Controller() {
        playerDAO = new PlayerDAO();
    }

    /**
     * Creates a new player.
     *
     * @param aux the player to be created
     */
    public void createPlayer(Player aux) {
        playerDAO.create(aux);
    }

    /**
     * Deletes a player by ID.
     *
     * @param id the ID of the player to be deleted
     */
    public void deletePlayer(int id) {
        playerDAO.delete(id);
    }

    /**
     * Checks if a player ID is already in use.
     *
     * @param id the ID to be checked
     * @return true if the ID is already in use, false otherwise
     */
    public boolean repeatedID(int id){
        if(playerDAO.read(id) == null){
            return false;
        }

        view.showError("The ID " + id + " is already in use.");
        return true;
    }

    /**
     * Updates an existing player.
     *
     * @param player the player to be updated
     * @return true if the update was successful, false otherwise
     */
    public boolean updatePlayer(Player player) {
        try {
            playerDAO.update(player);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Sets the view for this controller.
     *
     * @param view the view to be set
     */
    public void setView(I_Vista<Player> view) {
        this.view = view;
    }

    /**
     * Searches for a player by ID.
     *
     * @param id the ID of the player to be searched
     * @return the player if found, null otherwise
     */
    public Player searchPlayer(int id) {
        return playerDAO.read(id);
    }

    /**
     * Gets all players.
     *
     * @return a collection of all players
     */
    public Collection<Player> getAllPlayers() {
        return playerDAO.getPlayers();
    }

    /**
     * Exports player data.
     */
    public void exportData() {
        playerDAO.export();
    }

    /**
     * Gets the first player in the collection.
     *
     * @return the first player, or a new Player object if the collection is empty
     */
    public Player getFirstPlayer() {
        try {
            return playerDAO.getPlayers().iterator().next();
        } catch (Exception e) {
            return new Player();
        }
    }
}