package MapFiles;

import Combat.ShipBattle;
import GameLoop.AtSeaLoop;
import GameLoop.IslandLoop;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

/**
 * Class MainMap - build the map for the game
 */
public class MainMap {

    //Main map fields
    static Landmass[][] treasureMap = new Landmass[6][6];
    //set islandCount to 0
    int islandCount =0;

     /**
     * creates the map
     * @return
     */
     public static Landmass[][] createMap(){

         //get a new random number
         Random rand = new Random();

        //Create the random map
         for(int x =0; x<6;x++)
        {
            for(int y =0; y<6;y++)
            {
                //get a random number from 1 to 100
                int seed = rand.nextInt(100);

                //if less than or equal to 70, we are in open water
                if (seed <=70){
                    treasureMap[x][y] = new OpenWater(4);
                }
                else
                {
                    //Create a new island landmass, and use the randomEnum method to randomly select an Island name
                    treasureMap[x][y] = new Island(4,Island.randomEnum());

                }
            }
        }

         //call to method to check the map
        validateMap(treasureMap);

         //return the map
        return treasureMap;

    }

    /**
     * Private method that checks the map after creation to ensure that islands are present. No less than 4, no more than 5
     * @param testMap
     */
    private static void validateMap(Landmass[][] testMap)
    {
        //set the island start count to 0
        int islandCount =0;

        //Iterate over new 2D array and count the number of islands.  If less than 4 rerun the random map generation
        for(Landmass x[]: testMap){
            for(Landmass y: x){
                //if an island count it
                if(y instanceof Island){
                    //increase the counter
                    islandCount++;
                }
            }
        }

        //if count is less than 6 or greater than 8, re-create the map
        if(islandCount < 6 || islandCount >8){
            //call to create the new map
            createMap();
        }
    }

    /**
     * Class to hold coordinates of player
     */
    // TODO nested class?
    public static class Coordinates{
        //define the variables
        //1.1 - Proper use of visibility modifiers
         private int row;
         private int column;

         //constructor for Coordinates
        public Coordinates(int row, int column) {
            this.row = row;
            this.column = column;
        }

        //get row
        public int getRow() {
            return row;
        }

        //set row
        public void setRow(int row) {
            this.row = row;
        }

        //get column
        public int getColumn() {
            return column;
        }

        //set column
        public void setColumn(int column) {
            this.column = column;
        }
    }


    /**
     * Used after a sail and direction command to launch gameplay loop associated with the correct
     * tile landmass
     * @param tile - Pass in the correct landmass tile type
     */
    public static void instanceCheck(Landmass tile) throws SQLException {

        //if OpenWater, launch AtSeaLoop
         if(tile instanceof OpenWater){
             AtSeaLoop.launch();

         }
         //if Island, launch IslandLoop
         else if(tile instanceof Island){
             IslandLoop.launch();
         }

    }


    /**
     * Method to find the "movePlayer" coordinates to movePlayer the player to a new location on the map.
     * @param x
     * @param y
     * @param direction
     * @param playMap
     * @return
     * @throws SQLException
     */
    public static Coordinates movePlayer(int x, int y, Direction direction, Landmass[][] playMap) throws SQLException {

        //create a new instance
         Coordinates returnCoord = new Coordinates(x,y);

             //switch statement to move the player
             switch (direction) {
                 case NORTH:
                     returnCoord.row = x - 1;
                     returnCoord.column = y;
                     instanceCheck(playMap[returnCoord.getRow()][returnCoord.getColumn()]);
                     break;

                 case SOUTH:
                     returnCoord.row = x + 1;
                     returnCoord.column = y;
                     instanceCheck(playMap[returnCoord.getRow()][returnCoord.getColumn()]);
                     break;

                 case WEST:
                     returnCoord.row = x;
                     returnCoord.column = y - 1;
                     instanceCheck(playMap[returnCoord.getRow()][returnCoord.getColumn()]);
                     break;

                 case EAST:
                     returnCoord.row = x;
                     returnCoord.column = y + 1;
                     instanceCheck(playMap[returnCoord.getRow()][returnCoord.getColumn()]);
                     break;

                 case NORTHEAST:
                     returnCoord.row = x - 1;
                     returnCoord.column = y + 1;
                     instanceCheck(playMap[returnCoord.getRow()][returnCoord.getColumn()]);
                     break;

                 case NORTHWEST:
                     returnCoord.row = x - 1;
                     returnCoord.column = y - 1;
                     instanceCheck(playMap[returnCoord.getRow()][returnCoord.getColumn()]);
                     break;

                 case SOUTHEAST:
                     EAST:
                     returnCoord.row = x + 1;
                     returnCoord.column = y + 1;
                     instanceCheck(playMap[returnCoord.getRow()][returnCoord.getColumn()]);
                     break;

                 case SOUTHWEST:
                     returnCoord.row = x + 1;
                     returnCoord.column = y - 1;
                     instanceCheck(playMap[returnCoord.getRow()][returnCoord.getColumn()]);
                     break;

                 default:
                     returnCoord.row = x;
                     returnCoord.column = y;

            }

         //return the coordinate
         return returnCoord;

    }


    /**
     * method to check map
     * @param currentLocation
     * @param map
     * @return
     */
    public static Landmass mapCheck(Coordinates currentLocation, Landmass[][] map){
         Landmass returnLandmass;

         returnLandmass = map[currentLocation.row][currentLocation.column];
         // TODO may need to comment out
         System.out.println(returnLandmass.toString());  //Used to debug

        //return the landmass
        return returnLandmass;
    }

    /**
     * Method to allow the player to use a looking glass to view a direction
     * @param x
     * @param y
     * @param direction
     * @param playMap
     */
    public static void lgView(int x, int y, Direction direction, Landmass[][] playMap) {

         //create a new instance
         Coordinates viewDirection = new Coordinates(x,y);

        //check the direction
        switch (direction) {
            case NORTH:
                viewDirection.row = x - 1;
                viewDirection.column = y;
                break;

            case SOUTH:
                viewDirection.row = x + 1;
                viewDirection.column = y;
                break;

            case WEST:
                viewDirection.row = x;
                viewDirection.column = y - 1;
                break;

            case EAST:
                viewDirection.row = x;
                viewDirection.column = y + 1;
                break;

            case NORTHEAST:
                viewDirection.row = x - 1;
                viewDirection.column = y + 1;
                break;

            case NORTHWEST:
                viewDirection.row = x - 1;
                viewDirection.column = y - 1;
                break;

            case SOUTHEAST:
                EAST:
                viewDirection.row = x + 1;
                viewDirection.column = y + 1;
                break;

            case SOUTHWEST:
                viewDirection.row = x + 1;
                viewDirection.column = y - 1;
                break;

            default:
                viewDirection.row = x;
                viewDirection.column = y;


        }

        //if the direction is an island, display island message
        if(playMap[viewDirection.getRow()][viewDirection.getColumn()]instanceof Island){
            System.out.println("Off in the distance you can see a small speck of land.  An island!");
        }
        //if the direction is an OpenWater, display island message
        if(playMap[viewDirection.getRow()][viewDirection.getColumn()]instanceof OpenWater){
            Random rand = new Random();
            if(rand.nextInt(100) < 70) {
                System.out.println("Nothing but water, as far as the eye can see.");
            }
            //It is a ship that is seen, display ship message
            else{
                System.out.println("Ahoy! Ship on the horizon!! Look's rich to me matey!  Should we try'un catch her?");

                //check the users input
                Scanner userInput = new Scanner(System.in);
                String enteredText = userInput.nextLine();

                //if yes, go to ShipBattle
                if(enteredText.toLowerCase().contains("yes")){
                    ShipBattle.battle();
                }
                //if no, then do not go to battle
                else if(enteredText.toLowerCase().contains("no")){
                    System.out.println("All right, but you are starting to seem a bit yellow.  Argh.  Another day then!");
                }
                //prompt for a yes or no
                else{
                    System.out.println("A simple YES or NO is needed here captain!");
                }
            }
        }
    }
}
