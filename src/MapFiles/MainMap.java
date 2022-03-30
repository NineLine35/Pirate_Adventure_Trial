package MapFiles;

import java.util.Random;

public class MainMap {

    //Main map fields
    static Landmass[][] treasureMap = new Landmass[4][4];
    int islandCount =0;


     public static Landmass[][] createMap(){

         Random rand = new Random();

        //Create the random map
         for(int x =0; x<4;x++)
        {
            for(int y =0; y<4;y++)
            {
                int seed = rand.nextInt(100);

                if (seed <=70){
                    treasureMap[x][y] = new OpenWater(4);
                }
                else
                {
                    treasureMap[x][y] = new Island(4);
                }
            }
        }

        validateMap(treasureMap);

        return treasureMap;

    }


    // Private method that checks the map after creation to ensure that islands are present. No less than 4, no more than 5
    private static void validateMap(Landmass[][] testMap)
    {
        int islandCount =0;

        //Iterate over new 2D array and count the number of islands.  If less than 4 rerun the random map generation
        for(Landmass x[]: testMap){
            for(Landmass y: x){
                if(y instanceof Island){
                    islandCount++;
                }
            }
        }

        if(islandCount < 4 || islandCount >5){
            createMap();
        }
    }


    //Class to hold coordinates of player
    public static class Coordinates{
         int row;
         int column;

        public Coordinates(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }


    //Method to find the "movePlayer" coordinates to movePlayer the player to a new location on the map.
    public static Coordinates movePlayer(int x, int y, Direction direction){

         Coordinates returnCoord = new Coordinates(x,y);

         switch (direction){
             case NORTH:
                 returnCoord.row = x-1;
                 returnCoord.column = y;
                 break;
         }

         return returnCoord;
    }


    public static Landmass mapCheck(Coordinates currentLocation, Landmass[][] map){
         Landmass returnLandmass;

         returnLandmass = map[currentLocation.row][currentLocation.column];

         System.out.println(returnLandmass.toString());  //Used to debug

        return returnLandmass;
    }


}
