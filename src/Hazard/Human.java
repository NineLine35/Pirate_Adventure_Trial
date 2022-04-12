package Hazard;

import java.util.ArrayList;

public class Human extends Hazard{
    private String lookUp;
    private String storyMessage;
    private String percentToTake;
    private ArrayList<Human> humanList;

    public Human(){}

    public Human(String lookUp, String storyMessage,String percentToTake){
        this.lookUp = lookUp;
        this.storyMessage = storyMessage;
        this.percentToTake = percentToTake;
    }

    {
        this.humanList = new ArrayList<Human>();
    }

    public String getStoryMessage(){return storyMessage;}

    public String getPercentToTake(){return percentToTake;}

    public String getLookUp(){return lookUp;}

    public ArrayList<Human> getHumanList(){
        return humanList;
    }

    public void addHazard(String lookUp, String storyMessage, String percentToTake) {
        //create and load clients list
        Human human;

        //create the client
        human = new Human(lookUp, storyMessage, percentToTake);

        //add the client to the list
        addHumanList(human);
    }

    public void addHumanList(Human human) {
        //add the client
        this.humanList.add(human);
    }

    @Override
    public int calculateLoss(int amountToLose, int takeFrom) {
        return 0;
    }
}
