package HazardOld;

public enum Direction {
    NORTH("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west");

    //instantiate sendDirection
    private String sendDirection;

    //constructor for Direction
    Direction(String sendDirection){
        this.sendDirection = sendDirection;
    }

    //get the value to send back
    public String getSendDirection(){return sendDirection;}
}
