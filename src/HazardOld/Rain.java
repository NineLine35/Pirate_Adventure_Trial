package HazardOld;

public interface Rain {
    public int getRainAmount() throws Exception;

    //is it heavy
    public static final int MAX_RAIN = 3;

    //max inches
    public String raining(int amount);
}