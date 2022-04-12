package Inventory;

public class TreasureItem extends Item{
    public TreasureItem (String name, int quantity){
        super(name, quantity);
    }
    public TreasureItem (String name, String repairType, int repairAmount){
        super (name);
    }
}
