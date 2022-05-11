package Ship;

/**
 * Galleon type ship
 */
public class Galleon extends Ship {

    private int numCannons = 4;
    private int numSails = 4;


    // Constructor that sets the hit points, cargo capacity and sail distance uniquely based on Galleon class ship
    public Galleon() {
        super(20,10,1);
    }

    //Field Getters/Setters
    // 2.5 - Valid use of encapsulation
    public int getNumCannons() {
        return numCannons;
    }

    public void setNumCannons(int numCannons) {
        this.numCannons = numCannons;
    }

    public int getNumSails() {
        return numSails;
    }
    public void setNumSails(int numSails) {
        this.numSails = numSails;
    }
}
