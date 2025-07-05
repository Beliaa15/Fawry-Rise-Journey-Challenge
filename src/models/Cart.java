package models;

import interfaces.Shippable;
import services.ShippingService;
import java.util.List;
import java.util.ArrayList;

public class Cart {
    private java.util.List<CartItem> items;

    public Cart() {
        this.items = new java.util.ArrayList<>();
    }

    public void add(Product product, int quantity) {
        if (product.isInStock(quantity)) {
            items.add(new CartItem(product, quantity));
            System.out.println("Added " + quantity + "x " + product.getName() + " to cart");
        } else {
            System.out.println("Error: Not enough stock for " + product.getName());
        }
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public java.util.List<CartItem> getItems() {
        return items;
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public void clear() {
        items.clear();
    }
    
    public boolean checkout(Customer customer) {
        // Check if cart is empty
        if (isEmpty()) {
            System.out.println("Error: Cart is empty. Cannot checkout.");
            return false;
        }
        
        // Check for expired products and out of stock
        for (CartItem item : items) {
            Product product = item.getProduct();
            
            // Check if product is expired (only if it's expirable)
            if (product.isExpirableProduct() && product.isExpired()) {
                System.out.println("Error: Product " + product.getName() + " is expired (Expires: " + product.getExpirationDate() + ")");
                return false;
            }
            
            // Check if product is still in stock
            if (!product.isInStock(item.getQuantity())) {
                System.out.println("Error: Product " + product.getName() + " is out of stock or insufficient quantity available.");
                return false;
            }
        }
        
        // Calculate subtotal
        double subtotal = getSubtotal();
        
        // Collect shippable items and calculate shipping fees
        List<Shippable> shippableItems = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProduct().isShippableProduct()) {
                shippableItems.add(item.getProduct());
            }
        }
        
        double shippingFees = ShippingService.calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFees;
        
        // Check if customer has enough balance
        if (!customer.hasEnoughBalance(totalAmount)) {
            System.out.println("Error: Insufficient balance. Required: $" + String.format("%.2f", totalAmount) + 
                             ", Available: $" + String.format("%.2f", customer.getBalance()));
            return false;
        }
        
        // Process payment and update product quantities
        customer.deductBalance(totalAmount);
        for (CartItem item : items) {
            item.getProduct().decreaseQuantity(item.getQuantity());
        }
        
        // Print checkout details
        System.out.println("\n=== CHECKOUT SUCCESSFUL ===");
        System.out.println("Customer: " + customer.getName());
        System.out.println("\nOrder Details:");
        for (CartItem item : items) {
            System.out.printf("- %dx %s @ $%.2f each = $%.2f\n", 
                item.getQuantity(), 
                item.getProduct().getName(), 
                item.getProduct().getPrice(), 
                item.getTotalPrice());
        }
        System.out.printf("\nOrder Subtotal: $%.2f\n", subtotal);
        System.out.printf("Shipping Fees: $%.2f\n", shippingFees);
        System.out.printf("Total Paid Amount: $%.2f\n", totalAmount);
        System.out.printf("Customer Balance After Payment: $%.2f\n", customer.getBalance());
        
        // Process shipping if there are shippable items
        boolean hasShippableItems = false;
        for (CartItem item : items) {
            if (item.getProduct().isShippableProduct()) {
                hasShippableItems = true;
                break;
            }
        }
            
        if (hasShippableItems) {
            ShippingService.processShipmentFromCart(items);
        }
        
        // Clear the cart
        clear();
        
        return true;
    }
}