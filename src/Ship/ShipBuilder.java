package Ship;

// Using the builder design pattern to allow the Player class to be assigned a specific ship type
public class ShipBuilder {
    public static Ship getShip(String type){
        switch (type){
            case "galleon": return new Galleon();
            case "sloop": return new Sloop();
        }
        throw new UnsupportedOperationException("Unsupported Ship: "+type);
    }
}
