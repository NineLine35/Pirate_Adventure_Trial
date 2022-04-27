package GameLoop;

public enum OpenLoopOptions {
    EXIT("exit"),
    MAP("map"),
    WHERE("where am i"),
    SAIL("sail");

    //instantiate sendAction
    private String sendEntry;

    //constructor for Action
    OpenLoopOptions(String sendEntry){
        this.sendEntry = sendEntry;
    }

    //get the value to send back
    public String getSendEntry(){return sendEntry;}
}
