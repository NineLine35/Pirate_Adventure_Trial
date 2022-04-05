package Hazard;

public class StartTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Thunder thunder = new Thunder();

        try {
            thunder.getRainAmount();
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
        damages.stormDamage(1);
    }
}
