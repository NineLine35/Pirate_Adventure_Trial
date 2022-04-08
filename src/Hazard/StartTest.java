package Hazard;

public class StartTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Thunder thunder = new Thunder();

        //generate random number here to send if it is raining or not, if the random number is 0, then check hurricane
        int amount = 3;
        String message = thunder.raining(amount);
        System.out.println(message);
        try {
            int theAmount = thunder.getRainAmount();
            System.out.println(theAmount);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Money lost from Thunder: " + thunder.calculateMoneyLost(10, 20));

        Hurricane hurricane = new Hurricane();
        System.out.println("Money lost from Hurricane: " + hurricane.calculateMoneyLost(10, 10));

        SandStorm sandStorm = new SandStorm();
        System.out.println("Money lost from Sand Storm: " + sandStorm.calculateMoneyLost(5, 10));

        sandStorm.SandBlowing(1);

        Damages damages = new Damages();
        damages.stormDamage(2);
    }
}
