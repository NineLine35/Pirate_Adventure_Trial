package Inventory;

import java.sql.*;

import org.apache.derby.jdbc.EmbeddedDataSource;

public class ItemDatabase {
    private static String databaseName = "island_items";
    private static String databaseUrl = "jdbc:derby:" + databaseName + ";create=true";

    private static Connection connection;
    private static boolean databasePopulated;

    public static String getDatabaseUrl() {
        return databaseUrl;
    }

    public static boolean databasePopulated() { return databasePopulated; }

    public ItemDatabase() {

        //System.out.println("Initializing Database...");

        InitializeDatabase();

        //System.out.println("Database Initialized.");
    }

    private static void InitializeDatabase() {

        try {

            // Initialize Connection
            connection = DriverManager.getConnection(databaseUrl);

            // Query database to check for existing tables
            DatabaseMetaData databaseMeta = connection.getMetaData();

            ResultSet tablesFound = databaseMeta.getTables(null, null, "%", new String[] {"TABLE"});

            // Create Statement Object on Connection
            Statement stmt = connection.createStatement();

            try {
                stmt.executeUpdate("DROP TABLE island_items");
            } catch (Exception ex) {
                System.out.println("cannot drop island_items table");
                // for logging logger.info
            }

            try {
                stmt.executeUpdate("DROP TABLE items");
            } catch (Exception ex) {
                System.out.println("cannot drop items table");
                // for logging logger.info
            }

            try {
                stmt.executeUpdate("DROP TABLE islands");
            } catch (Exception ex) {
                System.out.println("cannot drop islands table");
                // for logging logger.info
            }
/*            while(tablesFound.next()) {
                //  If the table exists, drop it
                if(tablesFound.getString("TABLE_NAME").equals("island_items")){
                    stmt.executeUpdate("DROP TABLE " + tablesFound.getString("TABLE_NAME"));
                    // Commit (save) changes
                    connection.commit();
                }
            }

            tablesFound = databaseMeta.getTables(null, null, "%", new String[] {"TABLE"});
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

    public static void closeDatabaseConnection() throws SQLException {
        connection.close();
    }

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
                    + "id INTEGER PRIMARY KEY, "
                    + "island_id_fk integer REFERENCES islands (island_id), "
                    + "item_id_fK integer REFERENCES items (item_id)) ");
        }catch (SQLException e) {
            System.err.println(e.toString());
        }catch(Exception ex) {
            System.err.println(ex.toString());
        }
    }

    public void populateNewItemIslandData() {
        try(Statement stmt = connection.createStatement()) {

            stmt.executeUpdate("INSERT INTO items VALUES (1, 'Plank And Nails', 'Could Be Used To Repair Your Ships Hull', 'repair', 100)");
            stmt.executeUpdate("INSERT INTO items VALUES (2, 'Patching Kit', 'Could Be Used To Repair Your Ships Sail', 'repair', 100)");
            stmt.executeUpdate("INSERT INTO items VALUES (3, 'Ruby', 'Shiny Red Gem', 'treasure', 200)");
            stmt.executeUpdate("INSERT INTO items VALUES (4, 'Sapphire', 'Shiny Blue Gem', 'treasure', 250)");
            stmt.executeUpdate("INSERT INTO items VALUES (5, 'Diamond', 'Extremely Shinny Gem', 'treasure', 500)");
            stmt.executeUpdate("INSERT INTO items VALUES (7, 'Rum', 'Perfect Drink For Every Sailor', 'repair', 25)");

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

            stmt.executeUpdate("INSERT INTO island_items VALUES (1,1,1)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (2,1,5)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (3,1,4)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (4,2,1)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (5,2,3)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (6,3,1)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (7,3,5)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (8,2,5)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (9,4,2)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (10,4,4)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (11,5,3)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (12,5,4)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (13,6,5)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (14,6,4)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (15,6,7)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (16,7,2)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (17,7,3)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (18,8,1)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (19,8,3)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (20,8,5)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (21,9,3)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (22,9,4)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (23,10,7)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (24,11,5)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (25,12,5)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (26,12,1)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (27,13,2)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (28,14,1)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (29,14,5)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (30,15,7)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (31,16,1)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (32,16,2)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (33,17,2)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (34,17,5)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (35,18,1)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (36,19,1)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (37,19,7)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (38,20,2)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (39,20,3)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (40,20,4)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (41,21,1)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (42,22,2)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (43,22,3)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (44,23,2)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (45,23,3)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (46,23,4)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (47,24,5)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (48,24,4)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (49,25,2)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (50,26,1)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (51,27,2)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (52,27,3)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (53,28,1)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (54,28,4)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (55,28,5)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (56,28,7)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (57,29,1)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (58,29,3)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (59,30,1)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (60,30,2)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (61,30,3)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (62,30,4)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (63,30,5)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (64,30,7)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (65,31,1)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (66,31,5)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (67,32,1)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (68,32,2)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (69,32,3)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (70,33,2)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (71,33,4)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (72,36,1)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (73,36,2)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (74,36,3)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (75,36,4)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (77,36,5)");
            stmt.executeUpdate("INSERT INTO island_items VALUES (78,36,7)");


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
        ResultSet islandItemPlace = stmt.executeQuery("select i.item_name, i.item_description, i.item_type, "
                + " i.item_price"
                + "	from island_items as ii inner join items as i on ii.item_id_fk = i.item_id inner join islands "
                + " as isl on isl.island_id = ii.island_id_fk"
                + " where ii.island_id_fK =  isl.island_id" +" AND isl.x =  "+ x +" AND isl.y =  "+ y +" ");

        //loop to go through the results
        while(islandItemPlace.next()) {
            //get item name
            String itemName = islandItemPlace.getString("item_name");
            System.out.println("item " + itemName);

            //get item description
            String itemDescription = islandItemPlace.getString("item_description");
            System.out.println("itemDescription " + itemDescription);

            //get item type
            String itemType = islandItemPlace.getString("item_type");
            System.out.println("itemType " + itemType);

            //get item price
            int itemPrice = islandItemPlace.getInt("item_price");
            System.out.println("itemPrice " + Integer.toString(itemPrice));

            //print the item for the user to see
            System.out.println(itemName + " has been added to your trade inventory.");
            System.out.println("item " + itemName);

            //write the item to a new Item
            Item writeItem = new Item(itemName,itemDescription,itemType,itemPrice);

            //add to trader inventory
            TraderInventory traderInventory = new TraderInventory();
            traderInventory.addItem(writeItem,1);
        }
    }
}


