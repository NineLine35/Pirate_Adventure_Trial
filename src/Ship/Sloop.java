package Ship;

/**
 * Sloop type of Ship
 */
public class Sloop extends Ship {

    private int numCannons = 2;
    private int numSails = 2;


    // Constructor that sets the hit points, cargo capacity and sail distance uniquely based on Galleon class ship
    public Sloop() {
        super(15,4,1);
    }

    //Field Getters/Setters
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
