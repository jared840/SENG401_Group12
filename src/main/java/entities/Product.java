package entities;

public class Product {
    private int productId;
    private int supplierID;
    private String name;
    private String description;
    private double price;
    private String category;
    private int stock;

    public Product(int productId, int supplierID, String name, String description, double price, String category, int stock) {
        this.productId = productId;
        this.supplierID = supplierID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }

    // Getter and Setter methods

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setSupplierId(int supplierID) {
        this.supplierID = supplierID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public int getSupplierId() {
        return supplierID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getCategory() {
        return category;
    }

    public void addStock(int quantity) {
        stock += quantity;
    }

    public void removeStock(int quantity) {
        stock -= quantity;
    }

}
