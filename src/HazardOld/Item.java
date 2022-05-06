package HazardOld;

public enum Item {
    JEWEL("jewel"),
    COIN("coin");

    //instantiate sendItem
    private String sendItem;

    //constructor for Item
    Item(String sendItem){
        this.sendItem = sendItem;
    }

    //get the value to send back
    public String getSendItem(){return sendItem;}
}
