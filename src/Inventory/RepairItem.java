package Inventory;

public class RepairItem extends Item{
    private String repairType;
    private int repairAmount;

    public RepairItem (String name, String description, String repairType, int repairAmount){
        super(name, description);
        setRepairType(repairType);
        setRepairAmount(repairAmount);
    }

    // Getters and setters
    public String getRepairType(){ return this.repairType; }
    public void setRepairType(String repairType){ this.repairType = repairType; }
    public int getRepairAmount() { return this.repairAmount; }
    public void setRepairAmount(int repairAmount) { this.repairAmount = repairAmount; }
}
