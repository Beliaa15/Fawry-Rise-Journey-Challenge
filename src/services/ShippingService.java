package services;

import interfaces.Shippable;
import models.CartItem;
import java.util.List;
import java.util.ArrayList;

public class ShippingService {
    private static final double SHIPPING_RATE_PER_KG = 5.0; // $5 per kg
    private static final double BASE_SHIPPING_FEE = 10.0; // Base fee
    
    public static void processShipmentFromCart(List<CartItem> cartItems) {
        List<CartItem> shippableItems = new ArrayList<>();
        for (CartItem item : cartItems) {
            if (item.getProduct().isShippableProduct()) {
                shippableItems.add(item);
            }
        }
            
        if (shippableItems.isEmpty()) {
            System.out.println("No items to ship.");
            return;
        }
        
        System.out.println("\n** Shipment notice **");
        for (CartItem item : shippableItems) {
            // Convert weight to grams for display (total weight for all quantities)
            double totalWeight = item.getProduct().getWeight() * item.getQuantity();
            int weightInGrams = (int)(totalWeight * 1000);
            System.out.printf("%dx %s %dg\n", item.getQuantity(), item.getProduct().getName(), weightInGrams);
        }
        
        double totalWeight = 0.0;
        for (CartItem item : shippableItems) {
            totalWeight += item.getProduct().getWeight() * item.getQuantity();
        }
        
        System.out.printf("Total package weight %.1fkg\n", totalWeight);
    }
    
    public static void processShipment(List<Shippable> shippableItems) {
        if (shippableItems.isEmpty()) {
            System.out.println("No items to ship.");
            return;
        }
        
        System.out.println("\n** Shipment notice **");
        for (Shippable item : shippableItems) {
            // Convert weight to grams for display
            int weightInGrams = (int)(item.getWeight() * 1000);
            System.out.printf("1x %s %dg\n", item.getName(), weightInGrams);
        }
        
        double totalWeight = shippableItems.stream()
            .mapToDouble(Shippable::getWeight)
            .sum();
        
        System.out.printf("Total package weight %.1fkg\n", totalWeight);
    }
    
    public static double calculateShippingFee(List<Shippable> shippableItems) {
        if (shippableItems.isEmpty()) {
            return 0.0;
        }
        
        double totalWeight = shippableItems.stream()
            .mapToDouble(Shippable::getWeight)
            .sum();
        
        return BASE_SHIPPING_FEE + (totalWeight * SHIPPING_RATE_PER_KG);
    }
}
