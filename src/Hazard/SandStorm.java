package Hazard;

public class SandStorm extends Storm implements Sand{
    private static final int MAX_SAND = 2;

    @Override
    public void SandBlowing(int amount) {
        if(amount > 0 ) {
            System.out.println("The sand is blowing");
        }
        else {
            System.out.println("The sand is not blowing");
        }
    }

    @Override
    public int calculateMoneyLost(int sandAmount, int money) {
        // TODO Auto-generated method stub
        return money-sandAmount;
    }

    @Override
    public int getSandAmount() throws Exception {
        // TODO Auto-generated method stub
        return MAX_SAND;
    }
}
