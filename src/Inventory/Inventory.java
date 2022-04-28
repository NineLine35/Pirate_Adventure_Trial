package Inventory;
import java.util.ArrayList;
import java.util.Arrays;

public interface Inventory {
    ArrayList<RepairItem> repairItems = new ArrayList<RepairItem>();
    ArrayList<TreasureItem> treasureItems = new ArrayList<TreasureItem>();

    public void addRepairItem(RepairItem repairItem, int quantity);
    public void addTreasureItem(TreasureItem treasureItem, int quantity);
    public void outputInventory();

}
