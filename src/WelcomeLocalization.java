import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class WelcomeLocalization {
    /**
     * Method which will return a language specific intro message based on the users local time and locale
     * @param x - LocalTime
     * @param locale - Locale
     */
    public static void introMessage(LocalTime x, Locale locale) {

        //Create resource bundle object
        ResourceBundle rb= ResourceBundle.getBundle("WelcomeLocal",locale);

        LocalTime startTime = x;

        if(startTime.isAfter(LocalTime.of(23,59)) && startTime.isBefore(LocalTime.of(6, 00))) {
            System.out.println(rb.getString("gameIntro"));
            System.out.println("***********************************");
            System.out.println( rb.getString("earlyMorning"));
            System.out.println("\n");
        }
        else if(startTime.isAfter(LocalTime.of(5,59)) && startTime.isBefore(LocalTime.of(12, 00))) {
            System.out.println(rb.getString("gameIntro"));
            System.out.println("***********************************");
            System.out.println(rb.getString("morning"));
            System.out.println("\n");
        }
        else if(startTime.isAfter(LocalTime.of(11,59)) && startTime.isBefore(LocalTime.of(13, 00))) {
            System.out.println(rb.getString("gameIntro"));
            System.out.println("***********************************");
            System.out.println(rb.getString("lunch"));
            System.out.println("\n");
        }
        else if(startTime.isAfter(LocalTime.of(12,59)) && startTime.isBefore(LocalTime.of(21, 00))) {
            System.out.println(rb.getString("gameIntro"));
            System.out.println("***********************************");
            System.out.println(rb.getString("afternoon"));
            System.out.println("\n");
        }
        else if(startTime.isAfter(LocalTime.of(20,59)) && startTime.isBefore(LocalTime.of(00, 00))) {
            System.out.println(rb.getString("gameIntro"));
            System.out.println("***********************************");
            System.out.println(rb.getString("evening"));
            System.out.println("\n");
        }


    }
}
