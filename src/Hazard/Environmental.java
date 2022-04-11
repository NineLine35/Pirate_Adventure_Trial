package Hazard;

import java.util.ArrayList;

public class Environmental extends Hazard{
    private String lookUp;
    private String storyMessage;
    private String pointsToTake;
    private String sailDamage;
    private ArrayList<Environmental> environmentalList;

    /**
     * This is the constructor for hazard
     */
    public Environmental(){}

    /**
     * This is a constructor to pass values
     * @param lookUp
     * @param storyMessage
     * @param pointsToTake
     * @param sailDamage
     */
    public Environmental(String lookUp, String storyMessage, String pointsToTake, String sailDamage){
        this.lookUp = lookUp;
        this.storyMessage = storyMessage;
        this.pointsToTake = pointsToTake;
        this.sailDamage = sailDamage;
    }

    {
        this.environmentalList = new ArrayList<Environmental>();
    }

    public String getStoryMessage(){return storyMessage;}

    public String getPointsToTake(){return pointsToTake;}

    public String getSailDamage(){return sailDamage;}

    public String getLookUp(){return lookUp;}

    public ArrayList<Environmental> getEnvironmentalList() {
        return environmentalList;
    }

    public void addHazard(String lookUp, String storyMessage, String pointsToTake, String sailDamage) {
        //create and load clients list
        Environmental environmental;

        //create the client
        environmental = new Environmental(lookUp, storyMessage, pointsToTake, sailDamage);

        //add the client to the list
        addHazardList(environmental);
    }

    /**
     * add a hazard to the list
     * @param environmental
     */
    public void addHazardList(Environmental environmental) {
        //add the client
        this.environmentalList.add(environmental);
    }

    @Override
    public int calculateLoss(int amountToLose, int takeFrom) {
        return 0;
    }
}
