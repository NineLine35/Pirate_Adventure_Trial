package Inventory;

public abstract class Item {
    private String name;
    private int quantity;
    public String description;

    // Constructor if quantity and name are given
    public Item(String name, String description){
        this.setName(name);
        this.setDescription(description);
    }

    // Getters and setters
    public int getQuantity() { return this.quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getName(){ return this.name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return this.getQuantity() + " " + this.getName();
    }
}
