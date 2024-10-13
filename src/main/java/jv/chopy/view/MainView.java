package jv.chopy.view;

import jv.chopy.crud.controller.Controller;
import jv.chopy.crud.model.Player;
import jv.chopy.crud.utils.PropertiesLoader;

import java.util.Scanner;

/**
 * MainView class that implements the I_Vista interface for Player objects.
 * This class handles the user interface for managing players.
 * @see I_Vista
 * @see Player
 * @see Controller
 *
 * @version 1.0
 * @since 2021-10-06
 * @author Javier Vázquez
 */
public class MainView implements I_Vista<Player> {
    private Controller controller;
    private Player player;
    private final Scanner scanner;

    /**
     * Constructor for MainView.
     * Initializes the scanner, sets the controller, and displays the main menu.
     *
     * @param controller the controller to be used by this view
     */
    public MainView(Controller controller) {
        scanner = new Scanner(System.in);
        setController(controller);
        controller.setView(this);

        player = controller.getFirstPlayer();

        if (PropertiesLoader.getProperty("app.export").equals("none")) {
            showError("Export type not found.\nPlease select an export type.");
            exportConfig();
        }

        mostrar();
    }

    /**
     * Gets the current player object.
     *
     * @return the current player object
     */
    @Override
    public Player getObject() {
        return player;
    }

    /**
     * Sets the current player object.
     *
     * @param obj the player object to be set
     */
    @Override
    public void setObject(Player obj) {
        this.player = obj;
    }

    /**
     * Displays the main menu and handles user input.
     */
    @Override
    public void mostrar() {
        int option;
        do {
            System.out.printf(
                    """
                    ====================================
                    ===        PLAYER MANAGER        ===
                    ====================================
                    Current player: %s
                        1. CREATE NEW PLAYER
                        2. DELETE PLAYER
                        3. UPDATE PLAYER
                        4. SEARCH BY ID
                        5. SHOW ALL PLAYERS
                        6. CONFIGURATION
                        7. EXIT
                    """, player != null ? player.getNick_name() : "None");
            try {
                option = scanner.nextInt();

                if (option < 1 || option > 7) showError("Invalid option. Please try again.");

                switch (option) {
                    case 1 -> createPlayer();
                    case 2 -> deletePlayer();
                    case 3 -> updatePlayer();
                    case 4 -> searchPlayer();
                    case 5 -> showAllPlayers();
                    case 6 -> exportConfig();
                    case 7 -> {
                        System.out.println("Goodbye!");
                        controller.exportData();
                    }
                }
            } catch (Exception e) {
                option = 0;
                showError("Invalid input. Please try again.");
                scanner.nextLine(); // Clear the scanner buffer
            }

        } while (option != 7);
    }

    /**
     * Prompts the user to enter a player ID and searches for the player.
     * Displays the player information if found, otherwise shows an error message.
     */
    private void searchPlayer() {
        int id;
        System.out.println("[*] Enter the player's id:");
        try {
            id = scanner.nextInt();
        } catch (Exception e) {
            showError("Invalid id. Please try again.");
            return;
        }

        player = controller.searchPlayer(id);
        if (player != null) showPlayerInfo();
        else showError("Player not found.");
    }

    /**
     * Displays the update player menu and handles user input for updating player fields.
     */
    private void updatePlayer() {
        int option;
        do {
            System.out.printf("=== UPDATE PLAYER MENU ===" +
                            "%15s|%15d%n%15s|%15s%n%15s|%15s%n%15s|%15s%n|%15s",
                    "1. ID", player.getId(), "2. NICKNAME", player.getNick_name(), "3. EXPERIENCE", player.getExperience(), "4. COINS", player.getCoins(), "5. EXIT");

            System.out.print("Enter the field to update:");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    System.out.println("Enter the new id:");
                    player.setId(scanner.nextInt());
                }
                case 2 -> {
                    System.out.println("Enter the new nickname:");
                    player.setNick_name(scanner.next());
                }
                case 3 -> {
                    System.out.println("Enter the new experience:");
                    player.setExperience(scanner.nextInt());
                }
                case 4 -> {
                    System.out.println("Enter the new coins:");
                    player.setCoins(scanner.nextInt());
                }
                case 5 -> {
                    if (controller.updatePlayer(player)) System.out.println("Player updated successfully!");
                    else System.out.println("Error updating player.");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 5);

    }

    /**
     * Prompts the user to enter player details and creates a new player.
     * Validates the input and handles exceptions.
     */
    private void createPlayer() {
        player = new Player();
        try {
            do {
                System.out.println("[*] Enter the player's id:");
                player.setId(scanner.nextInt());
            } while (controller.repeatedID(player.getId()));
            System.out.println("[*] Enter the player's nickname:");
            player.setNick_name(scanner.next());
            System.out.println("[*] Enter the player's experience:");
            player.setExperience(scanner.nextInt());
            System.out.println("[*] Enter the player's coins:");
            player.setCoins(scanner.nextInt());
            controller.createPlayer(player);
        } catch (Exception e) {
            showError("Invalid input. Please try again.");
            scanner.nextLine(); // Clear the scanner buffer
        }
    }

    /**
     * Displays the export configuration menu and handles user input for selecting export type.
     */
    private void exportConfig() {
        int option;
        do {
            System.out.println("""
                    === EXPORT CONFIGURATION ===
                    Available options:
                        1. Secuential text
                        2. Secuential binary
                        3. Binary object
                        4. Random binary
                        5. JSON""");
            option = scanner.nextInt();
        } while (option < 1 || option > 5);

        PropertiesLoader.setProperty("app.export", switch (option) {
            case 1 -> "text.secuential";
            case 2 -> "binary.secuential";
            case 3 -> "binary.object";
            case 4 -> "binary.random";
            case 5 -> "json";
            default -> "none";
        });
    }

    /**
     * Displays the delete player menu and handles user input for deleting a player.
     */
    private void deletePlayer() {
        int option;
        do {
            try {
                System.out.printf("""
                    === DELETE PLAYER MENU ===
                    1. Delete by id
                    2. Delete current player: %s
                    3. Exit
                    """, player.getNick_name().isEmpty() ? "None" : player.getNick_name());
                option = scanner.nextInt();
                if (option < 1 || option > 3) showError("Invalid option. Please try again.");
            } catch (Exception e) {
                showError("Invalid option. Please try again.");
                scanner.nextLine();
                option = 0;
            }

        } while (option < 1 || option > 3);

        switch (option) {
            case 1 -> {
                System.out.println("[*] Enter the player's id:");
                controller.deletePlayer(scanner.nextInt());
                player = controller.getFirstPlayer();
            }
            case 2 -> {
                controller.deletePlayer(player.getId());
                player = controller.getFirstPlayer();
            }
        }
    }

    /**
     * Displays the current player's information.
     */
    private void showPlayerInfo() {
        Player.playerHeader();
        System.out.printf("%-15d|%-15s|%-15d|%-15d%n", player.getId(), player.getNick_name(), player.getExperience(), player.getCoins());
    }

    /**
     * Displays all players' information.
     */
    private void showAllPlayers() {
        Player.playerHeader();
        for (Player p : controller.getAllPlayers()) {
            System.out.printf("%15d|%15s|%15d|%15d%n", p.getId(), p.getNick_name(), p.getExperience(), p.getCoins());
        }
    }

    /**
     * Sets the controller for this view.
     *
     * @param controller the controller to be set
     */
    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Displays a message to the user.
     *
     * @param message the message to be displayed
     */
    @Override
    public void showMessage(String message) {
        System.out.println("[v/]" + message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to be displayed
     */
    @Override
    public void showError(String message) {
        System.out.println("[X] " + message);
    }
}