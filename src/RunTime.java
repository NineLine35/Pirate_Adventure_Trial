import GameLoop.OpeningLoop;
import MapFiles.Landmass;
import MapFiles.MainMap;
import Player.Player;

import java.io.IOException;

public class RunTime {

  public static void main(String[] args) throws IOException {

    //Create random map
    Landmass playMap[][] = MainMap.createMap();

    // Assign the player the starting ship, a Sloop using a builder method
     Player.getInstance().assignShip("sloop", "The Jolly Rodger");

    // Launch opening game play loop
    OpeningLoop.launch(playMap);


   }

 }






