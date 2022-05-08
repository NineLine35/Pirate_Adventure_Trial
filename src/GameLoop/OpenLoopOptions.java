package GameLoop;

public enum OpenLoopOptions {
    EXIT("exit"),
    MAP("map"),
    WHERE("where"),
    AM("am"),
    I("i"),
    SAIL("sail"),
    HELP("help"),
    LOOK("look"),
    STATUS("status"),
    DEBUGBATTLE("debugBattle"),
    INVENTORY("inventory");


    //instantiate sendAction
    private String sendEntry;

    //constructor for Action
    OpenLoopOptions(String sendEntry){
        this.sendEntry = sendEntry;
    }

    //get the value to send back
    public String getSendEntry(){return sendEntry;}
}

