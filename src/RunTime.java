import GameLoop.OpeningLoop;
import MapFiles.Landmass;
import MapFiles.MainMap;
import java.io.IOException;

public class RunTime {

  public static void main(String[] args) throws IOException {

    //Create random map
    Landmass playMap[][] = MainMap.createMap();

    // Launch opening game play loop
    OpeningLoop.launch(playMap);


   }

 }






