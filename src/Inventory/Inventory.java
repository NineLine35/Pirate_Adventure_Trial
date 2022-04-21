package Inventory;

import java.util.ArrayList;

public interface Inventory {
    ArrayList<TreasureItem> treasureItems = new ArrayList<TreasureItem>();
    ArrayList<RepairItem> repairItems = new ArrayList<RepairItem>();

    public void addRepairItem(RepairItem item, int quantity);
    public void addTreasureItem(TreasureItem item, int quantity);
    public void outputInventory();
}
