package Hazard;

import Inventory.ItemDatabase;
import Player.Player;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import Player.HighScoreTracker;

/**
 * class for Hazard
 */
public class Hazard {
    //define the variables
    private int lookUp;
    private String storyMessage;
    private int hullPoints;
    private int sailDamage;
    private int coinToTake;
    private ArrayList<Hazard> hazardList;

    /**
     * Constructor for Hazard
     */
    public Hazard() {
    }

    /**
     * Constructor for hazard
     * @param lookUp
     * @param storyMessage
     * @param hullPoints
     * @param sailDamage
     * @param coinToTake
     */
    //1.3 overloaded constructor
    public Hazard(int lookUp, String storyMessage, int hullPoints, int sailDamage, int coinToTake) {
        this.lookUp = lookUp;
        this.storyMessage = storyMessage;
        this.hullPoints = hullPoints;
        this.sailDamage = sailDamage;
        this.coinToTake = coinToTake;
    }

    //getters and setters
    {
        //3.2 ArrayList
        this.hazardList = new ArrayList<Hazard>();
    }

    // 2.2 - Valid use of encapsulation
    //get story message
    public String getStoryMessage() {
        return storyMessage;
    }

    //get hull points
    public int getHullPoints() {
        return hullPoints;
    }

    //get sail damage
    public int getSailDamage() {
        return sailDamage;
    }

    //get coin
    public int getCoinToTake() {
        return coinToTake;
    }

    //get look up
    public int getLookUp() {
        return lookUp;
    }

    //get hazard list
    public ArrayList<Hazard> getHazardList() {
        return hazardList;
    }

    /**
     * method to add a hazard
     * @param lookUp
     * @param storyMessage
     * @param hullPoints
     * @param sailDamage
     * @param coinToTake
     */
   public void addHazard(int lookUp, String storyMessage, int hullPoints, int sailDamage, int coinToTake) {
        //create and load hazard list
       Hazard hazard;

        //create the hazard
       hazard = new Hazard(lookUp, storyMessage, hullPoints, sailDamage, coinToTake);

      //add the hazard to the list
       addHazardList(hazard);
    }

    /**
     * method to add the hazard to the list
     * @param hazard
     */
    public void addHazardList(Hazard hazard) {
        //add the hazard
        this.hazardList.add(hazard);
    }

    /**
     * Method to calculate the hazard loss and ot know if ship sank
     * @param hitPoints
     * @param coinLoss
     * @param sailDamage
     */
    //1.3 overloaded method
    public void hazardCalc(Supplier<Integer> hitPoints, Supplier<Integer> coinLoss, Supplier<Integer> sailDamage) throws IOException, SQLException {
        //these messages can be cleaned up later when we no longer need them, we just need to keep the calculation portion
        // Use code below for hazard debugging:
    /*      System.out.println("current hull points " + Player.getInstance().getShip().getHullHitPoints());

        Player.getInstance().getShip().setHullHitPoints(Player.getInstance().getShip().getHullHitPoints() - hitPoints.get());
        System.out.println("after hull points " + + Player.getInstance().getShip().getHullHitPoints());

        System.out.println("current sail points " + Player.getInstance().getShip().getSailHealth());

        Player.getInstance().getShip().setSailHealth(Player.getInstance().getShip().getSailHealth() - sailDamage.get());
        System.out.println("after sail points " + + Player.getInstance().getShip().getSailHealth());

        System.out.println("current coin " + Player.getInstance().getChest());

        Player.getInstance().setChest(Player.getInstance().getChest() - coinLoss.get());
        System.out.println("after coin " + + Player.getInstance().getChest());*/

        //I added this in place of the above if we rather use this

        System.out.println("You took " +  hitPoints.get() + " hits to your hull.");
        //remove the hits from the total hull points
        Player.getInstance().getShip().setHullHitPoints(Player.getInstance().getShip().getHullHitPoints() - hitPoints.get());

        System.out.println("You lost " +  sailDamage.get() + " percent of your sails.");
        //remove the damage to the total sail points
        Player.getInstance().getShip().setSailHealth(Player.getInstance().getShip().getSailHealth() - sailDamage.get());

        //remove the coin from the chest
        Player.getInstance().setChest(Player.getInstance().getChest() - coinLoss.get());

        //get what the player now has for coin
        int coins = Player.getInstance().getChest();

        if(coins < 0){
            System.out.println("You lost " +  coinLoss.get() + " coins from your chest and it looks like you are in the negative.  Better get trading!");
        }
        else
        {
            System.out.println("You lost " +  coinLoss.get() + " coins from your chest.");
        }

        //if you are at 0 or less, your ship has sunk
        if(Player.getInstance().getShip().getHullHitPoints() <= 0){
            //display message to player that their ship has sunk
            System.out.println("\nOh no! Yer ship been down to Davy Jones' Locker!");

            if(Player.getInstance().getChest() <= 0){
                System.out.println("You did not collect any coins to track in high score.  Better luck next time!");
            }
            else {
                //report high score
                HighScoreTracker.writeHighScores();

                //out of 20 days
                System.out.println("Your ending coin value: " + Player.getInstance().getChest());

                //show high scores
                System.out.println("Check out where you are in high scores:\n");
                HighScoreTracker.readHighScores();
            }
            //10.2 close database
            ItemDatabase.closeDatabaseConnection();

            System.exit(0);
        }
    }
}


