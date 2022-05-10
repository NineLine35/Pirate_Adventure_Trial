package GameLoop;

/**
 * Enum for opening loop options
 */
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
    INVENTORY("inventory"),
    USE("use"),
    DATE("date");

    //instantiate sendAction
    private String sendEntry;

    /**
     * Constructor for OpeningLoopOptions
     * @param sendEntry
     */
    OpenLoopOptions(String sendEntry){
        this.sendEntry = sendEntry;
    }

    //get the value to send back
    public String getSendEntry(){return sendEntry;}
}

