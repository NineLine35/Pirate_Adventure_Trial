package Inventory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import GameLoop.OpeningLoop;
import Player.Player;
import org.apache.derby.jdbc.EmbeddedDataSource;

/**
 * class for the database
 */
//10.1 - Implement database with basic CRUD operations\
//NOTE:  We did not do Update and Delete since that was already built out in another way for Items and due to time, we just
//focused on creating and reading a database to loop up items for Player and Trade
public class ItemDatabase extends OpeningLoop {
    //database name
    private static String databaseName = "island_items";

    //the url for the database
    private static String databaseUrl = "jdbc:derby:" + databaseName + ";create=true";

    //connect
    //10.2 open database
    private static Connection connection;

    //boolean for if the database populated
    private static boolean databasePopulated;

    //getter for the database url
    public static String getDatabaseUrl() {
        return databaseUrl;
    }

    //getter for the databasePopulated
    public static boolean databasePopulated() {
        return databasePopulated;
    }

    //constructor for the database
    public ItemDatabase() {
        InitializeDatabase();
    }

    /**
     * initialize the database
     */
    private static void InitializeDatabase() {
        //try for the database connection and initialization
        try {

            // Initialize Connection
            connection = DriverManager.getConnection(databaseUrl);

            // Query database to check for existing tables
            DatabaseMetaData databaseMeta = connection.getMetaData();

            ResultSet tablesFound = databaseMeta.getTables(null, null, "%", new String[]{"TABLE"});

            // Create Statement Object on Connection
            Statement stmt = connection.createStatement();

            //drop tables if they were already created
            //6.1 - use of try catch blocks
            //try to drop bridge island_items table
            try {
                stmt.executeUpdate("DROP TABLE island_items");
            } catch (Exception ex) {
                System.out.println("cannot drop island_items table");
            }

            //try to drop bridge island_item_trade tables
            try {
                stmt.executeUpdate("DROP TABLE island_item_trade");
            } catch (Exception ex) {
                System.out.println("cannot drop items table");
            }

            //try to drop player items tables
            try {
                stmt.executeUpdate("DROP TABLE items");
            } catch (Exception ex) {
                System.out.println("cannot drop items table");
            }

            //try to drop player items_trade tables
            try {
                stmt.executeUpdate("DROP TABLE items_trade");
            } catch (Exception ex) {
                System.out.println("cannot drop trade table");
            }

            //try to drop island tables
            try {
                stmt.executeUpdate("DROP TABLE islands");
            } catch (Exception ex) {
                System.out.println("cannot drop islands table");
            }

            // Close Statement
            stmt.close();

            // Create Initial Database Tables
            createInitialTables(connection);

        }
        //catch for SQL and then catch for Exception
        //6.2 multi catch
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex.getStackTrace());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex.getStackTrace());
        }
    }

    /**
     * close the connection to the database
     *
     * @throws SQLException
     */
    public static void closeDatabaseConnection() throws SQLException {
        connection.close();
    }

    /**
     * create the tables
     *
     * @param conn
     */
    private static void createInitialTables(Connection conn) {

        try (Statement stmt = conn.createStatement()) {

            //create the island table
            stmt.executeUpdate("CREATE TABLE islands ("
                    + "island_id INTEGER PRIMARY KEY, "
                    + "x INTEGER, "
                    + "y INTEGER) ");

            // Create Player Item Table
            stmt.executeUpdate("CREATE TABLE items ("
                    + "item_id INTEGER PRIMARY KEY, "
                    + "item_name VARCHAR(255), "
                    + "item_description VARCHAR(255), "
                    + "item_type VARCHAR(255), "
                    + "item_price INTEGER) ");

            // Create Trade Item Table
            stmt.executeUpdate("CREATE TABLE items_trade ("
                    + "item_id INTEGER PRIMARY KEY, "
                    + "item_name VARCHAR(255), "
                    + "item_description VARCHAR(255), "
                    + "item_type VARCHAR(255),"
                    + "item_price INTEGER) ");

            // Create Island_Item Table
            stmt.executeUpdate("CREATE TABLE island_items ("
                    + "id INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "island_id_fk integer REFERENCES islands (island_id), "
                    + "item_id_fK integer REFERENCES items (item_id)) ");

            // Create Island_Item_Trade Table
            stmt.executeUpdate("CREATE TABLE island_item_trade ("
                    + "id INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "island_id_fk integer REFERENCES islands (island_id), "
                    + "item_id_fK integer REFERENCES items (item_id)) ");
        }
        //6.2 multi catch for SQLException and Exception
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex.getStackTrace());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex.getStackTrace());
        }
    }

    /**
     * Add values to the tables
     */
    public void populateNewItemIslandData() {
        try (Statement stmt = connection.createStatement()) {
            //add to player items
            stmt.executeUpdate("INSERT INTO items VALUES (1, 'Plank And Nails', 'could Be Used To Repair Your Ships Hull', 'repair', 100)");
            stmt.executeUpdate("INSERT INTO items VALUES (2, 'Patching Kit', 'could Be Used To Repair Your Ships Sail', 'repair', 100)");
            stmt.executeUpdate("INSERT INTO items VALUES (3, 'Ruby', 'shiny Red Gem', 'treasure', 200)");
            stmt.executeUpdate("INSERT INTO items VALUES (4, 'Sapphire', 'shiny Blue Gem', 'treasure', 250)");
            stmt.executeUpdate("INSERT INTO items VALUES (5, 'Diamond', 'extremely Shinny Gem', 'treasure', 500)");
            stmt.executeUpdate("INSERT INTO items VALUES (7, 'Rum', 'perfect Drink For Every Sailor', 'treasure', 25)");

            //add to trade items
            stmt.executeUpdate("INSERT INTO items_trade VALUES (1, 'Plank And Nails', 'could Be Used To Repair a Pirate Ships Hull', 'repair', 100)");
            stmt.executeUpdate("INSERT INTO items_trade VALUES (2, 'Patching Kit', 'could Be Used To Repair a Pirate Ships Sail', 'repair', 100)");
            stmt.executeUpdate("INSERT INTO items_trade VALUES (3, 'Ruby', 'shiny Red Gem', 'treasure', 200)");
            stmt.executeUpdate("INSERT INTO items_trade VALUES (4, 'Sapphire', 'shiny Blue Gem', 'treasure', 250)");
            stmt.executeUpdate("INSERT INTO items_trade VALUES (5, 'Diamond', 'extremely Shinny Gem', 'treasure', 500)");
            stmt.executeUpdate("INSERT INTO items_trade VALUES (7, 'Rum', 'perfect Drink For Every Pirate', 'treasure', 25)");

            //add to islands
            stmt.executeUpdate("INSERT INTO islands VALUES (1,0,0)");
            stmt.executeUpdate("INSERT INTO islands VALUES (2,1,0)");
            stmt.executeUpdate("INSERT INTO islands VALUES (3,2,0)");
            stmt.executeUpdate("INSERT INTO islands VALUES (4,3,0)");
            stmt.executeUpdate("INSERT INTO islands VALUES (5,4,0)");
            stmt.executeUpdate("INSERT INTO islands VALUES (6,5,0)");
            stmt.executeUpdate("INSERT INTO islands VALUES (7,0,1)");
            stmt.executeUpdate("INSERT INTO islands VALUES (8,1,1)");
            stmt.executeUpdate("INSERT INTO islands VALUES (9,2,1)");
            stmt.executeUpdate("INSERT INTO islands VALUES (10,3,1)");
            stmt.executeUpdate("INSERT INTO islands VALUES (11,4,1)");
            stmt.executeUpdate("INSERT INTO islands VALUES (12,5,1)");
            stmt.executeUpdate("INSERT INTO islands VALUES (13,0,2)");
            stmt.executeUpdate("INSERT INTO islands VALUES (14,1,2)");
            stmt.executeUpdate("INSERT INTO islands VALUES (15,2,2)");
            stmt.executeUpdate("INSERT INTO islands VALUES (16,3,2)");
            stmt.executeUpdate("INSERT INTO islands VALUES (17,4,2)");
            stmt.executeUpdate("INSERT INTO islands VALUES (18,5,2)");
            stmt.executeUpdate("INSERT INTO islands VALUES (19,0,3)");
            stmt.executeUpdate("INSERT INTO islands VALUES (20,1,3)");
            stmt.executeUpdate("INSERT INTO islands VALUES (21,2,3)");
            stmt.executeUpdate("INSERT INTO islands VALUES (22,3,3)");
            stmt.executeUpdate("INSERT INTO islands VALUES (23,4,3)");
            stmt.executeUpdate("INSERT INTO islands VALUES (24,5,3)");
            stmt.executeUpdate("INSERT INTO islands VALUES (25,0,4)");
            stmt.executeUpdate("INSERT INTO islands VALUES (26,1,4)");
            stmt.executeUpdate("INSERT INTO islands VALUES (27,2,4)");
            stmt.executeUpdate("INSERT INTO islands VALUES (28,3,4)");
            stmt.executeUpdate("INSERT INTO islands VALUES (29,4,4)");
            stmt.executeUpdate("INSERT INTO islands VALUES (30,5,4)");
            stmt.executeUpdate("INSERT INTO islands VALUES (31,0,5)");
            stmt.executeUpdate("INSERT INTO islands VALUES (32,1,5)");
            stmt.executeUpdate("INSERT INTO islands VALUES (33,2,5)");
            stmt.executeUpdate("INSERT INTO islands VALUES (34,3,5)");
            stmt.executeUpdate("INSERT INTO islands VALUES (35,4,5)");
            stmt.executeUpdate("INSERT INTO islands VALUES (36,5,5)");

            //add player items to island
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (1,1)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (1,5)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (1,4)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (2,1)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (2,3)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (3,1)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (3,5)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (2,5)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (4,2)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (4,4)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (5,3)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (5,4)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (6,5)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (6,4)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (6,7)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (7,2)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (7,3)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (8,1)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (8,3)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (8,5)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (9,3)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (9,4)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (10,7)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (11,5)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (12,5)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (12,1)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (13,2)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (14,1)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (14,5)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (15,1)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (15,2)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (15,7)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (16,1)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (16,2)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (17,2)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (17,5)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (18,1)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (19,1)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (19,7)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (20,2)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (20,3)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (20,4)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (21,1)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (22,2)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (22,3)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (23,2)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (23,3)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (23,4)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (24,5)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (24,4)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (25,2)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (26,1)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (27,2)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (27,3)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (28,1)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (28,4)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (28,5)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (28,7)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (29,1)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (29,3)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (30,1)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (30,2)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (30,3)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (30,4)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (30,5)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (30,7)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (31,1)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (31,5)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (32,1)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (32,2)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (32,3)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (33,2)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (33,4)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (34,1)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (34,7)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (35,2)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (35,3)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (36,1)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (36,2)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (36,3)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (36,4)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (36,5)");
            stmt.executeUpdate("INSERT INTO island_items (island_id_fk, item_id_fk) "
                    + "VALUES (36,7)");


            //add to trader items to island
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (1,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (1,3)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (1,7)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (2,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (2,5)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (3,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (3,7)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (2,7)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (4,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (4,3)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (5,4)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (5,5)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (6,7)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (6,5)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (6,5)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (7,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (7,4)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (8,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (8,4)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (8,7)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (9,4)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (9,5)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (10,3)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (11,7)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (12,7)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (12,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (13,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (14,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (14,7)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (15,1)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (15,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (15,3)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (16,1)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (16,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (17,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (17,7)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (18,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (19,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (19,3)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (20,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (20,4)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (20,5)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (21,3)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (22,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (22,4)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (23,4)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (23,3)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (23,5)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (24,7)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (24,5)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (25,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (26,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (27,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (27,4)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (28,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (28,5)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (28,7)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (28,3)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (29,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (29,4)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (30,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (30,4)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (30,3)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (30,7)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (31,5)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (32,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (32,4)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (33,3)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (33,4)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (34,5)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (34,3)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (35,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (35,4)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (36,2)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (36,7)");
            stmt.executeUpdate("INSERT INTO island_item_trade (island_id_fk, item_id_fk) "
                    + "VALUES (36,5)");

            // Set flag used for quit functionality
            databasePopulated = true;

        }
        //6.2 - multi catch SQLException and Exception
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex.getStackTrace());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex.getStackTrace());
        }
    }

    /**
     * Retrieves the island it is on and the items on that island and then adds items to tradeinventory
     *
     * @param x
     * @param y
     * @return
     * @throws SQLException
     */
    // TODO remove this if we want to
  /*  public static void retrieveIslandItemData(int x, int y) throws SQLException {
        //connect
        Statement stmt = connection.createStatement();

        //query to get items
        ResultSet islandItemPlace = stmt.executeQuery("select i.item_id, i.item_name, i.item_description, i.item_type, "
                + " i.item_price"
                + "	from island_items as ii inner join items as i on ii.item_id_fk = i.item_id inner join islands "
                + " as isl on isl.island_id = ii.island_id_fk"
                + " where ii.island_id_fK =  isl.island_id" + " AND isl.x =  " + x + " AND isl.y =  " + y + " ");

        //loop to go through the results
        while (islandItemPlace.next()) {
            //get item name
            String itemName = islandItemPlace.getString("item_name");
            //System.out.println("item " + itemName);

            //get item description
            String itemDescription = islandItemPlace.getString("item_description");
            //System.out.println("itemDescription " + itemDescription);

            //get item type
            String itemType = islandItemPlace.getString("item_type");
            //System.out.println("itemType " + itemType);

            //get item price
            int itemPrice = islandItemPlace.getInt("item_price");
            //System.out.println("itemPrice " + Integer.toString(itemPrice));

            //get the item id
            int itemId = islandItemPlace.getInt("item_id");

            //create a link word for a sentence
            String linkWord = "";

            if (itemId == 1 || itemId == 2) {
                linkWord = " ";
            } else if (itemId == 5) {
                linkWord = " is an ";
            } else {
                linkWord = " is a ";
            }
            //print the item for the user to see
            System.out.println(itemName + linkWord + itemDescription + " and has been added to your trade inventory."
                    + " The value is " + itemPrice + " coins.");

            //write the item to a new Item
            Item writeItem = new Item(itemName, itemDescription, itemType, itemPrice);

            Player.getInstance().AddItem(writeItem, 1);
        }

        //query to get items
        ResultSet islandItemPlaceTrade = stmt.executeQuery("select i.item_id, i.item_name, i.item_description, i.item_type"
                + "	from island_item_trade as ii inner join items_trade as i on ii.item_id_fk = i.item_id inner join islands "
                + " as isl on isl.island_id = ii.island_id_fk"
                + " where ii.island_id_fK =  isl.island_id" + " AND isl.x =  " + x + " AND isl.y =  " + y + " ");

        //loop to go through the results
        System.out.println();
        while (islandItemPlaceTrade.next()) {
            //get item name
            String itemName = islandItemPlaceTrade.getString("item_name");
            //System.out.println("item " + itemName);

            //get item description
            String itemDescription = islandItemPlaceTrade.getString("item_description");
            //System.out.println("itemDescription " + itemDescription);

            //get item type
            String itemType = islandItemPlaceTrade.getString("item_type");
            //System.out.println("itemType " + itemType);

            //get a random price to trade
            int randomPrice = getRandom(25, 800);

            //get the item id
            int itemId = islandItemPlaceTrade.getInt("item_id");

            //create a link word for a sentence
            String linkWord = "";

            if (itemId == 1 || itemId == 2) {
                linkWord = " ";
            } else if (itemId == 5) {
                linkWord = " is an ";
            } else {
                linkWord = " is a ";
            }
            //print the item for the user to see
            System.out.println(itemName + linkWord + itemDescription + " and has been added to the island trade inventory."
                    + " The value is " + randomPrice + " coins.");

            //write the item to a new Item
            Item writeItem = new Item(itemName, itemDescription, itemType, randomPrice);

            //add to trader inventory
            TraderInventory traderInventory = new TraderInventory();
            traderInventory.addItem(writeItem, 1);
        }
    }
*/

    /**
     * Method to retrieve TraderItems
     * @param x
     * @param y
     * @return items
     * @throws SQLException
     */
    public static List<Item> retrieveTraderItems(int x, int y) throws SQLException{
        //new list for items
        //3.2 ArrayList
        List<Item> items = new ArrayList<>();

        //connect
        Statement stmt = connection.createStatement();

        //query to get items
        ResultSet islandItemPlaceTrade = stmt.executeQuery("select i.item_id, i.item_name, i.item_description, i.item_type, i.item_price"
                + "	from island_item_trade as ii inner join items_trade as i on ii.item_id_fk = i.item_id inner join islands "
                + " as isl on isl.island_id = ii.island_id_fk"
                + " where ii.island_id_fK =  isl.island_id" + " AND isl.x =  " + x + " AND isl.y =  " + y + " ");

        //loop to go through the results
        while (islandItemPlaceTrade.next()) {
            //get item name
            String itemName = islandItemPlaceTrade.getString("item_name");
            //System.out.println("item " + itemName);

            //get item description
            String itemDescription = islandItemPlaceTrade.getString("item_description");
            //System.out.println("itemDescription " + itemDescription);

            //get item type
            String itemType = islandItemPlaceTrade.getString("item_type");
            //System.out.println("itemType " + itemType);

            //get item price
            int itemPrice = islandItemPlaceTrade.getInt("item_price");
            //System.out.println("itemPrice " + Integer.toString(itemPrice));

            //get the item id
            int itemId = islandItemPlaceTrade.getInt("item_id");

            //add the item
            items.add(new Item(itemName, itemDescription, itemType, itemPrice));
        }
        //return the items
        return items;
    }

    /**
     * retrieve the Island Items
     * @param x
     * @param y
     * @return items
     * @throws SQLException
     */
    public static List<Item> retrieveIslandItems(int x, int y) throws SQLException{
        //3.2 ArrayList
        List<Item> items = new ArrayList<>();

        //connect
        Statement stmt = connection.createStatement();

        //query to get items
        ResultSet islandItemPlace = stmt.executeQuery("select i.item_id, i.item_name, i.item_description, i.item_type, "
                + " i.item_price"
                + "	from island_items as ii inner join items as i on ii.item_id_fk = i.item_id inner join islands "
                + " as isl on isl.island_id = ii.island_id_fk"
                + " where ii.island_id_fK =  isl.island_id" + " AND isl.x =  " + x + " AND isl.y =  " + y + " ");

        //loop to go through the results
        while (islandItemPlace.next()) {
            //get item name
            String itemName = islandItemPlace.getString("item_name");
            //System.out.println("item " + itemName);

            //get item description
            String itemDescription = islandItemPlace.getString("item_description");
            //System.out.println("itemDescription " + itemDescription);

            //get item type
            String itemType = islandItemPlace.getString("item_type");
            //System.out.println("itemType " + itemType);

            //get item price
            int itemPrice = islandItemPlace.getInt("item_price");
            //System.out.println("itemPrice " + Integer.toString(itemPrice));

            //add the item
            items.add(new Item(itemName, itemDescription, itemType, itemPrice));
        }
        //return the items
        return items;
    }
}


