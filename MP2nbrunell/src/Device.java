
public class Device {
    private String sku;
    private String name;
    private boolean isAvailable;

    public Device(String sku, String name, boolean isAvailable) 
    {
        this.sku = sku;
        this.name = name;
        this.isAvailable = isAvailable;
    }

    public String getSku() 
    {
        return sku;
    }

    public void setSku(String sku) 
    {
        this.sku = sku;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public boolean getAvailable() 
    {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) 
    {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return String.format("%-9s %-30s %-15s", sku, name, isAvailable ? "Available" : "Checked Out");
    }

}
