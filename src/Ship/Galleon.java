package Ship;

public class Galleon extends Ship {

    private int numCannons = 4;
    private int numSails = 4;

    public Galleon(int hitPoints, int cargoCapacity, int sailDistance) {
        super(hitPoints, cargoCapacity, sailDistance);
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
