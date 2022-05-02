package Hazard;

import Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Hazard {
    private String lookUp;
    private String storyMessage;
    private String hullPoints;
    private String sailDamage;
    private String coinToTake;
    private ArrayList<Hazard> hazardList;

    public Hazard() {
    }

    public Hazard(String lookUp, String storyMessage, String hullPoints, String sailDamage, String coinToTake) {
        this.lookUp = lookUp;
        this.storyMessage = storyMessage;
        this.hullPoints = hullPoints;
        this.sailDamage = sailDamage;
        this.coinToTake = coinToTake;
    }

    {
        this.hazardList = new ArrayList<Hazard>();
    }

    public String getStoryMessage() {
        return storyMessage;
    }

    public String getHullPoints() {
        return hullPoints;
    }

    public String getSailDamage() {
        return sailDamage;
    }

    public String getCoinToTake() {
        return coinToTake;
    }

    public String getLookUp() {
        return lookUp;
    }

    public ArrayList<Hazard> getHazardList() {
        return hazardList;
    }

    public void addHazard(String lookUp, String storyMessage, String hullPoints, String sailDamage, String coinToTake) {
        //create and load clients list
        Hazard hazard;

        //create the client
        hazard = new Hazard(lookUp, storyMessage, hullPoints, sailDamage, coinToTake);

        //add the client to the list
        addHazardList(hazard);
    }

    public void addHazardList(Hazard hazard) {
        //add the client
        this.hazardList.add(hazard);
    }

    public void hazardCalc(Supplier<String> hitPoints, Supplier<String> coinLoss, Supplier<String> sailDamage){
        System.out.println("current hull points " + Player.getInstance().getShip().getHullHitPoints());

        Player.getInstance().getShip().setHullHitPoints(Player.getInstance().getShip().getHullHitPoints() - Integer.parseInt(hitPoints.get()));
        System.out.println("after hull points " + + Player.getInstance().getShip().getHullHitPoints());

        System.out.println("current sail points " + Player.getInstance().getShip().getSailHealth());

        Player.getInstance().getShip().setSailHealth(Player.getInstance().getShip().getSailHealth() - Integer.parseInt(sailDamage.get()));
        System.out.println("after sail points " + + Player.getInstance().getShip().getSailHealth());

        System.out.println("current coin " + Player.getInstance().getChest());

        Player.getInstance().setChest(Player.getInstance().getChest() - Integer.parseInt(coinLoss.get()));
        System.out.println("after coin " + + Player.getInstance().getChest());
    }
}

