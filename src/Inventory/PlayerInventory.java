package Inventory;

import java.util.ArrayList;
import java.util.Locale;

public class PlayerInventory implements Inventory{
    public void PlayerInventory (){}

    public RepairItem findRepairItem(String itemName){
        RepairItem foundItem = null;
        for (int i = 0; i < repairItems.size(); i++){
            if (repairItems.get(i).getName() == itemName.toUpperCase(Locale.ROOT).replace(" ","_")){
                foundItem = repairItems.get(i);
            }
        }
        return foundItem;
    }

    public void addRepairItem(RepairItem item, int quantity) {
        boolean hasDupe = false;
        int dupePos = 0;
        item.setQuantity(quantity);

        // Runs through list of items to check for duplicates
        for (int i = 0; i < repairItems.size(); i++){
            // Duplicate is found and values are set to be added
            if (item == this.repairItems.get(i)){
                hasDupe = true;
                dupePos = i;
            }
        }

        // Item is a duplicate
        if (hasDupe){
            // Updates the quantity of the item
            this.repairItems.get(dupePos).setQuantity(quantity += this.repairItems.get(dupePos).getQuantity());
        } else{
            // Adds the item to the list
            this.repairItems.add(item);
        }
    }

    public void addTreasureItem(TreasureItem item, int quantity) {
        boolean hasDupe = false;
        int dupePos = 0;
        item.setQuantity(quantity);

        // Runs through list of items to check for duplicates
        for (int i = 0; i < this.treasureItems.size(); i++){
            // Duplicate is found and values are set to be added
            if (item == this.treasureItems.get(i)){
                hasDupe = true;
                dupePos = i;
            }
        }

        // Item is a duplicate
        if (hasDupe){
            // Updates the quantity of the item
            this.treasureItems.get(dupePos).setQuantity(quantity += this.treasureItems.get(dupePos).getQuantity());
        } else{
            // Adds the item to the list
            this.treasureItems.add(item);
        }
    }

    public void useRepairItem(RepairItem repairItem){
        boolean exists = false;
        // Runs through items
        for(int i = 0; i < this.repairItems.size(); i++){
            // Item is found
            if (this.repairItems.get(i) == repairItem){
                // Item is used
                this.repairItems.remove(i);
                // Boolean is changed to output to user
                exists = true;
            }
        }

        if (exists == false){
            System.out.println("You Do Not Have That Item To Use Sailor");
        }
    }

    public void sellTreasure(TreasureItem treasureItem){
        boolean exists = false;
        // Runs through items
        for (int i = 0; i < this.repairItems.size(); i++){
            // Item is found
            if (this.treasureItems.get(i) == treasureItem){
                // Item is used
                treasureItems.remove(i);
                // Boolean is changed to output to user
                exists = true;
            }
        }

        if (exists == false){
            System.out.println("You Can't Sell Items You Don't Have Sailor");
        }
    }

    public void outputInventory() {
        System.out.println("--Repair Items--");
        // Run through all items in inventory
        for (int i = 0; i < this.repairItems.size(); i++){
            // Print out items currently in inventory
            System.out.println(this.repairItems.get(i).toString());
        }
        System.out.println("--Treasure Items--");
        for (int i = 0; i < this.treasureItems.size(); i++) {
            // Print out items currently in inventory
            System.out.println(this.treasureItems.get(i).toString());
        }
    }
}
