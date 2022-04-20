package MapFiles;

public class OpenWater extends Landmass {

    public OpenWater(int size) {
        super(size);
    }

    /**
     * Overriding toString to return a specific "Open Water" string.  This helps clean up the treasure map generation
     * @return "{Open Water}"
     */
    @Override
    public String toString(){
        return "{Open Water}";
    }
}
