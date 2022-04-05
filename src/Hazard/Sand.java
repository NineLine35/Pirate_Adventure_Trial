package Hazard;

public interface Sand {
    public int getSandAmount() throws Exception;

    //is it heavy
    public static final int MAX_SAND = 2;

    //sand is blowing
    void SandBlowing(int amount);
}

