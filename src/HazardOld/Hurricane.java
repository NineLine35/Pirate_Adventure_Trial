package HazardOld;

public class Hurricane extends Storm implements Rain{
    private static final int MAX_RAIN = 2;
    private String displayMessage;

    @Override
    public String raining(int amount) {
        displayMessage = "It is not raining";
        /*System.out.println("It is not raining");*/
        return displayMessage;
    }

    @Override
    public int getRainAmount() throws Exception {
        // TODO Auto-generated method stub
        return MAX_RAIN;
    }

    //@Override
    public int calculateMoneyLost(int inches, int money) {
        // TODO Auto-generated method stub
        return money-inches;
    }
}