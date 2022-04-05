package Hazard;

public class Hurricane extends Storm implements Rain{
    private static final int MAX_RAIN = 2;

    @Override
    public void raining() {
        System.out.println("It is not raining");
    }

    @Override
    public int getRainAmount() throws Exception {
        // TODO Auto-generated method stub
        return MAX_RAIN;
    }

    @Override
    public int calculateMoneyLost(int inches, int money) {
        // TODO Auto-generated method stub
        return money-inches;
    }
}
