import Inventory.*;

public class Item_Testing {
    public static void main(String[] args){
        PlayerInventory playerInv = new PlayerInventory();

        Item plank = new Item(ItemTypes.PLANK_AND_NAILS.toString(), "Planks With Nails", "repair", 10);
        Item ruby = new Item(ItemTypes.RUBY.toString(),  "Shinny Red Gem", "treasure", 15);

        playerInv.addItem(plank,5);
        playerInv.addItem(ruby, 10);
        playerInv.addItem(plank, 5);

        playerInv.outputInventory();
    }
}
