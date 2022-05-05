import GameLoop.OpeningLoop;
import MapFiles.Landmass;
import MapFiles.MainMap;
import Player.Player;
import org.apache.derby.jdbc.EmbeddedDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RunTime {

  public static void main(String[] args) throws IOException, SQLException {
    //Create random map
    Landmass playMap[][] = MainMap.createMap();


    // Assign the player the starting ship, a Sloop using a builder method
     Player.getInstance().assignShip("sloop", "The Jolly Rodger");

    // Launch opening game play loop
    OpeningLoop.launch(playMap);


   }

 }






