package MapFiles;

public class Island extends Landmass {

    boolean hasPort;

    public Island(int size) {
        super(size);
    }


    protected void refreshBooty(){
        System.out.println("Booty has been refreshed!");
    }

}
