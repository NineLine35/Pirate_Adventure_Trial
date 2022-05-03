package Inventory;

import java.sql.*;

import org.apache.derby.jdbc.EmbeddedDataSource;

public class ItemDatabase {
    public static void main(String[] args) throws Exception {
        try {
            EmbeddedDataSource ds = new EmbeddedDataSource();
            ds.setDatabaseName("island_items");

            Connection conn = ds.getConnection();

            Statement stmt = conn.createStatement();

            //stmt.execute("DROP TABLE items");
            //stmt.execute("DROP TABLE islands");
            //stmt.execute("DROP TABLE island_items");

            stmt.executeUpdate("CREATE TABLE islands ("
                    + "island_id INTEGER PRIMARY KEY, "
                    + "x INTEGER, "
                    + "y INTEGER)");

            stmt.executeUpdate("CREATE TABLE items ("
                    + "item_id INTEGER PRIMARY KEY, "
                    + "item_name VARCHAR(255), "
                    + "item_description VARCHAR(255), "
                    + "item_type VARCHAR(255), "
                    + "item_price INTEGER)");

            stmt.executeUpdate("CREATE TABLE island_items ("
                    + "island_id_fk integer FOREIGN KEY REFERENCES island (island_id), "
                    + "item_id_fK integer FOREIGN KEY REFERENCES item (item_id))");

            stmt.executeUpdate("INSERT INTO items VALUES (1, 'Plank And Nails', 'Could Be Used To Repair Your Ships Hull', 'repair', 100)");
            stmt.executeUpdate("INSERT INTO items VALUES (2, 'Patching Kit', 'Could Be Used To Repair Your Ships Sail', 'repair', 100)");
            stmt.executeUpdate("INSERT INTO items VALUES (3, 'Ruby', 'Shiny Red Gem', 'treasure', 200)");
            stmt.executeUpdate("INSERT INTO items VALUES (4, 'Sapphire', 'Shiny Blue Gem', 'treasure', 250)");
            stmt.executeUpdate("INSERT INTO items VALUES (5, 'Diamond', 'Extremely Shinny Gem', 'treasure', 500)");
            stmt.executeUpdate("INSERT INTO items VALUES (7, 'Rum', 'Perfect Drink For Every Sailor', 'repair', 25)");
        }catch(SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }finally {
        }
    }
}
