package models;

import interfaces.Expirable;
import interfaces.Shippable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Product implements Expirable, Shippable {
    private String name;
    private double price;
    private int quantity;
    private boolean isExpirable;
    private String expirationDate;
    private boolean isShippable;
    private double weight;

    // Constructor for non-expirable, non-shippable products (like Mobile, ScratchCard)
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isExpirable = false;
        this.isShippable = false;
        this.expirationDate = null;
        this.weight = 0.0;
    }

    // Constructor for expirable and shippable products (like Cheese, Cookies)
    public Product(String name, double price, int quantity, String expirationDate, double weight) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isExpirable = true;
        this.expirationDate = expirationDate;
        this.isShippable = true;
        this.weight = weight;
    }

    // Constructor for shippable but non-expirable products (like TV)
    public Product(String name, double price, int quantity, double weight) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isExpirable = false;
        this.expirationDate = null;
        this.isShippable = true;
        this.weight = weight;
    }

    // Constructor for expirable but non-shippable products (if needed)
    public Product(String name, double price, int quantity, String expirationDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isExpirable = true;
        this.expirationDate = expirationDate;
        this.isShippable = false;
        this.weight = 0.0;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isInStock(int requestedQuantity) {
        return this.quantity >= requestedQuantity;
    }

    public void decreaseQuantity(int amount) {
        if (amount <= this.quantity) {
            this.quantity -= amount;
        }
    }

    // Expirable interface implementation
    @Override
    public boolean isExpired() {
        if (!isExpirable || expirationDate == null) {
            return false;
        }
        try {
            LocalDate expDate = LocalDate.parse(expirationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return LocalDate.now().isAfter(expDate);
        } catch (Exception e) {
            return false; // If date parsing fails, assume not expired
        }
    }

    @Override
    public String getExpirationDate() {
        return isExpirable ? expirationDate : null;
    }

    // Shippable interface implementation
    @Override
    public double getWeight() {
        return isShippable ? weight : 0.0;
    }

    // Utility methods to check product capabilities
    public boolean isExpirableProduct() {
        return isExpirable;
    }

    public boolean isShippableProduct() {
        return isShippable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s - $%.2f (Stock: %d)", name, price, quantity));
        
        if (isExpirable) {
            sb.append(String.format(" [Expires: %s]", expirationDate));
        }
        
        if (isShippable) {
            sb.append(String.format(" [Weight: %.2f kg]", weight));
        }
        
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Product product = (Product) obj;
        return name != null ? name.equals(product.name) : product.name == null;
    }
}
