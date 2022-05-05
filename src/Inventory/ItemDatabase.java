package Inventory;

import java.sql.*;

import org.apache.derby.jdbc.EmbeddedDataSource;

/**
 * class for the database
 */
public class ItemDatabase {
    //database name
    private static String databaseName = "island_items";

    //the url for the database
    private static String databaseUrl = "jdbc:derby:" + databaseName + ";create=true";

    //connect
    private static Connection connection;

    //boolean for if the database populated
    private static boolean databasePopulated;

    //getter for the database url
    public static String getDatabaseUrl() {
        return databaseUrl;
    }

    //getter for the databasePopulated
    public static boolean databasePopulated() { return databasePopulated; }

    /**
     * constructor for the database
     */
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

            ResultSet tablesFound = databaseMeta.getTables(null, null, "%", new String[] {"TABLE"});

            // Create Statement Object on Connection
            Statement stmt = connection.createStatement();

            //try to drop bridge tables
            try {
                stmt.executeUpdate("DROP TABLE island_items");
            } catch (Exception ex) {
                System.out.println("cannot drop island_items table");
                // for logging logger.info
            }

            //try to drop items tables
            try {
                stmt.executeUpdate("DROP TABLE items");
            } catch (Exception ex) {
                System.out.println("cannot drop items table");
                // for logging logger.info
            }

            //try to drop island tables
            try {
                stmt.executeUpdate("DROP TABLE islands");
            } catch (Exception ex) {
                System.out.println("cannot drop islands table");
                // for logging logger.info
            }


            /*tablesFound = databaseMeta.getTables(null, null, "%", new String[] {"TABLE"});
            // Iterate through tables, dropping them if they exist
            while(tablesFound.next()) {
                //  If the table exists, drop it
                stmt.executeUpdate("DROP TABLE " + tablesFound.getString("TABLE_NAME"));

                // Commit (save) changes
                connection.commit();
            }*/

            // Close Statement
            stmt.close();

            // Create Initial Database Tables
            createInitialTables(connection);

        }catch(SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex.getStackTrace());
        }catch(Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex.getStackTrace());
        }

    }

    /**
     * close the connection to the database
     * @throws SQLException
     */
    public static void closeDatabaseConnection() throws SQLException {
        connection.close();
    }

    /**
     * create the tables
     * @param conn
     */
    private static void createInitialTables(Connection conn) {

        try(Statement stmt = conn.createStatement()) {
            // Create Island Table
            stmt.executeUpdate("CREATE TABLE islands ("
                    + "island_id INTEGER PRIMARY KEY, "
                    + "x INTEGER, "
                    + "y INTEGER) ");

            // Create Item Table
            stmt.executeUpdate("CREATE TABLE items ("
                    + "item_id INTEGER PRIMARY KEY, "
                    + "item_name VARCHAR(255), "
                    + "item_description VARCHAR(255), "
                    + "item_type VARCHAR(255), "
                    + "item_price INTEGER)" );

            // Create Island_Item Table
            stmt.executeUpdate("CREATE TABLE island_items ("
                    + "id INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "island_id_fk integer REFERENCES islands (island_id), "
                    + "item_id_fK integer REFERENCES items (item_id)) ");
        }catch (SQLException e) {
            System.err.println(e.toString());
        }catch(Exception ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     * Add values to the tables
     */
    public void populateNewItemIslandData() {
        try(Statement stmt = connection.createStatement()) {
            //add to items
            stmt.executeUpdate("INSERT INTO items VALUES (1, 'Plank And Nails', 'could Be Used To Repair Your Ships Hull', 'repair', 100)");
            stmt.executeUpdate("INSERT INTO items VALUES (2, 'Patching Kit', 'could Be Used To Repair Your Ships Sail', 'repair', 100)");
            stmt.executeUpdate("INSERT INTO items VALUES (3, 'Ruby', 'shiny Red Gem', 'treasure', 200)");
            stmt.executeUpdate("INSERT INTO items VALUES (4, 'Sapphire', 'shiny Blue Gem', 'treasure', 250)");
            stmt.executeUpdate("INSERT INTO items VALUES (5, 'Diamond', 'extremely Shinny Gem', 'treasure', 500)");
            stmt.executeUpdate("INSERT INTO items VALUES (7, 'Rum', 'perfect Drink For Every Sailor', 'repair', 25)");

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

            //add to item island
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

            // Set flag used for quit functionality
            databasePopulated = true;

        }catch (SQLException e) {
            System.err.println(e.toString());
        }catch(Exception ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     * Retrieves the island it is on and the items on that island and then adds items to tradeinventory
     * @param x
     * @param y
     * @return
     * @throws SQLException
     */
    public static void retrieveIslandItemData(int x, int y) throws SQLException{
        //connect
        Statement stmt = connection.createStatement();

        //query to get items
        ResultSet islandItemPlace = stmt.executeQuery("select i.item_id, i.item_name, i.item_description, i.item_type, "
                + " i.item_price"
                + "	from island_items as ii inner join items as i on ii.item_id_fk = i.item_id inner join islands "
                + " as isl on isl.island_id = ii.island_id_fk"
                + " where ii.island_id_fK =  isl.island_id" +" AND isl.x =  "+ x +" AND isl.y =  "+ y +" ");

        //loop to go through the results
        while(islandItemPlace.next()) {
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

            if(itemId == 1 || itemId == 2){
                linkWord = " ";
            }
            else if(itemId == 5){
                linkWord = " is an ";
            }
            else
            {
                linkWord = " is a ";
            }
            //print the item for the user to see
            System.out.println(itemName + linkWord + itemDescription + " and has been added to your trade inventory."
                    + " The value is " + itemPrice + " coins.");

            //write the item to a new Item
            Item writeItem = new Item(itemName,itemDescription,itemType,itemPrice);

            //add to trader inventory
            TraderInventory traderInventory = new TraderInventory();
            traderInventory.addItem(writeItem,1);
        }
    }
}


