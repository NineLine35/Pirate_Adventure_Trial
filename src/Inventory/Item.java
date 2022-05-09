package Inventory;

/**
 * Class for game items
 *
 *
 */
public class Item {
    private String name;
    private int quantity;
    private String description;
    private String itemType;
    private int itemPrice;

    /**
     * Constructor for the class
     * @param name
     * @param description
     * @param itemType
     * @param itemPrice
     */
    public Item(String name, String description, String itemType, int itemPrice){
        this.setName(name);
        this.setDescription(description);
        this.setItemType(itemType);
        this.setItemPrice(itemPrice);
    }

    // Getters and Setters
    public int getQuantity() { return this.quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getName(){ return this.name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getItemType() { return this.itemType; }
    public void setItemType(String itemType) { this.itemType = itemType; }
    public int getItemPrice() { return this.itemPrice; }
    public void setItemPrice(int itemPrice) { this.itemPrice = itemPrice; }

    /**
     * Overrides the tostring method for easier item output
     *
     * @return
     */
    @Override
    public String toString() {
        return this.getQuantity() + " - " + this.getName() + " : " + this.getDescription() + " - " + this.getItemPrice() + " Coins Per Item";
    }
}
