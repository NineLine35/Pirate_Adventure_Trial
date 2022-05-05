package Hazard;

import Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

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
     * Constructor for hazard with overloads 1.3
     * @param lookUp
     * @param storyMessage
     * @param hullPoints
     * @param sailDamage
     * @param coinToTake
     */
    public Hazard(int lookUp, String storyMessage, int hullPoints, int sailDamage, int coinToTake) {
        this.lookUp = lookUp;
        this.storyMessage = storyMessage;
        this.hullPoints = hullPoints;
        this.sailDamage = sailDamage;
        this.coinToTake = coinToTake;
    }

    //getters and setters
    {
        this.hazardList = new ArrayList<Hazard>();
    }

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
        //create and load clients list
       Hazard hazard;

        //create the client
       hazard = new Hazard(lookUp, storyMessage, hullPoints, sailDamage, coinToTake);

      //add the client to the list
       addHazardList(hazard);
    }

    /**
     * method to add the hazard to the list
     * @param hazard
     */
    public void addHazardList(Hazard hazard) {
        //add the client
        this.hazardList.add(hazard);
    }

    /**
     * Method to calculate the hazard loss and ot know if ship sank
     * @param hitPoints
     * @param coinLoss
     * @param sailDamage
     */
    public void hazardCalc(Supplier<Integer> hitPoints, Supplier<Integer> coinLoss, Supplier<Integer> sailDamage){
        //these messages can be cleaned up later when we no longer need them, we just need to keep the cacluation portion
        System.out.println("current hull points " + Player.getInstance().getShip().getHullHitPoints());

        Player.getInstance().getShip().setHullHitPoints(Player.getInstance().getShip().getHullHitPoints() - hitPoints.get());
        System.out.println("after hull points " + + Player.getInstance().getShip().getHullHitPoints());

        System.out.println("current sail points " + Player.getInstance().getShip().getSailHealth());

        Player.getInstance().getShip().setSailHealth(Player.getInstance().getShip().getSailHealth() - sailDamage.get());
        System.out.println("after sail points " + + Player.getInstance().getShip().getSailHealth());

        System.out.println("current coin " + Player.getInstance().getChest());

        Player.getInstance().setChest(Player.getInstance().getChest() - coinLoss.get());
        System.out.println("after coin " + + Player.getInstance().getChest());

        //if you are at 0 or less, your ship has sunk
        if(Player.getInstance().getShip().getHullHitPoints() <= 0){
            System.out.println("Looks like yer ship been down to Davy Jones' Locker!  Better luck next time!");
            System.exit(0);
        }
    }
}


