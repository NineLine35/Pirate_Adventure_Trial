package MapFiles;

/**
 * Class OpenWater
 */
public class OpenWater extends Landmass {

    /**
     * constructor for OpenWater
     * @param size
     */
    public OpenWater(int size) {
        super(size);
    }

    /**
     * Overriding toString to return a specific "Open Water" string.  This helps clean up the treasure map generation
     * @return "{Open Water}"
     */
    //1.4 override of .toString()
    @Override
    public String toString(){
        return "{Open Water}";
    }
}
