/*package Hazard;

import java.util.ArrayList;

public class Environmental {
    private String lookUp;
    private String storyMessage;
    private String hullPoints;
    private String sailDamage;
    private String coinToTake;
    private ArrayList<Environmental> environmentalList;

    *//**
     * This is the constructor for hazard
     *//*
    public Environmental(){}

    *//**
     * This is a constructor to pass values
     * @param lookUp
     * @param storyMessage
     * @param hullPoints
     * @param sailDamage
     *//*
    public Environmental(String lookUp, String storyMessage, String hullPoints, String sailDamage, String coinToTake){
        this.lookUp = lookUp;
        this.storyMessage = storyMessage;
        this.hullPoints = hullPoints;
        this.sailDamage = sailDamage;
        this.coinToTake = coinToTake;
    }

    {
        this.environmentalList = new ArrayList<Environmental>();
    }

    public String getStoryMessage(){return storyMessage;}

    public String getHullPoints(){return hullPoints;}

    public String getSailDamage(){return sailDamage;}

    public String getCoinToTake(){return coinToTake;}

    public String getLookUp(){return lookUp;}

    public ArrayList<Environmental> getEnvironmentalList() {
        return environmentalList;
    }

    public void addHazard(String lookUp, String storyMessage, String hullPoints, String sailDamage, String coinToTake) {
        //create and load clients list
        Environmental environmental;

        //create the client
        environmental = new Environmental(lookUp, storyMessage, hullPoints, sailDamage, coinToTake);

        //add the client to the list
        addHazardList(environmental);
    }

    *//**
     * add a hazard to the list
     * @param environmental
     *//*
    public void addHazardList(Environmental environmental) {
        //add the client
        this.environmentalList.add(environmental);
    }
}*/
