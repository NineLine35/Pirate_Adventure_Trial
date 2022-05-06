package HazardOld;

public enum Actions {
        PICKUP("pickup"),
        MOVE("move"),
        LOOK("look");

        //instantiate sendAction
        private String sendAction;

        //constructor for Action
        Actions(String sendAction){
                this.sendAction = sendAction;
        }

        //get the value to send back
        public String getSendAction(){return sendAction;}
}
