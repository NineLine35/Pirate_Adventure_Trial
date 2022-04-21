import Inventory.RepairItem;
import Inventory.RepairItemTypes;
import Inventory.TreasureItem;
import Inventory.TreasureItemTypes;
import Inventory.PlayerInventory;

public class Item_Testing {
    public static void main(String[] args){
        PlayerInventory playerInv = new PlayerInventory();

        RepairItem plank = new RepairItem(RepairItemTypes.PLANK_NAILS.toString(),"Wooden Plank", "Hull",5);
        TreasureItem ruby = new TreasureItem(TreasureItemTypes.RUBY.toString(),  "Shinny Red Gem");

        playerInv.addRepairItem(plank, 5);
        playerInv.addTreasureItem(ruby, 5);

        playerInv.outputInventory();
    }
}
