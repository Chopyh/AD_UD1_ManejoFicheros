package jv.chopy.crud.model;

import java.io.Serializable;

/**
 * The Player class represents a player in the game.
 * It implements the Serializable interface to allow player objects to be serialized.
 *
 * @author Javier Vázquez
 * @version 1.0
 */
public class Player implements Serializable, Comparable<Player> {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nick_name;
    private int experience;
    private int coins;

    /**
     * Constructs a new Player with the specified id, nick_name, experience, and coins.
     *
     * @param id the player's id
     * @param nick_name the player's nickname
     * @param experience the player's experience points
     * @param coins the player's coin count
     */
    public Player(int id, String nick_name, int experience, int coins) {
        this.id = id;
        this.nick_name = nick_name;
        this.experience = experience;
        this.coins = coins;
    }

    /**
     * Constructs a new Player with default values.
     * The default values are: id = 0, nick_name = "", experience = 0, coins = 0.
     */
    public Player() {
        this(0, "", 0, 0);
    }

    /**
     * Returns the player's id.
     *
     * @return the player's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the player's id.
     *
     * @param id the player's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the player's nickname.
     *
     * @return the player's nickname
     */
    public String getNick_name() {
        return nick_name;
    }

    /**
     * Sets the player's nickname.
     *
     * @param nick_name the player's nickname
     */
    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    /**
     * Returns the player's experience points.
     *
     * @return the player's experience points
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Sets the player's experience points.
     *
     * @param experience the player's experience points
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Returns the player's coin count.
     *
     * @return the player's coin count
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Sets the player's coin count.
     *
     * @param coins the player's coin count
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }

    /**
     * Returns a string representation of the player.
     *
     * @return a string representation of the player
     */
    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", nick_name='" + nick_name + '\'' +
                ", experience=" + experience +
                ", coins=" + coins +
                '}';
    }

    @Override
    public int compareTo(Player o) {
        if (this.id < o.id) {
            return -1;
        } else if (this.id > o.id) {
            return 1;
        }
        return 0;
    }
}