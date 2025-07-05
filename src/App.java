import models.*;

public class App {
    public static void main(String[] args) {
        System.out.println("=== E-COMMERCE SYSTEM DEMO ===\n");

        // Create products
        Product mobile = new Product("iPhone 16", 999.99, 5);
        Product tv = new Product("Samsung TV", 799.99, 3, 15.5);
        Product cheese = new Product("Cheddar Cheese", 12.99, 10, "2025-08-15", 0.5);
        Product expiredCheese = new Product("2areesh Cheese", 8.99, 5, "2025-01-01", 0.3);
        Product cookies = new Product("Cookies", 5.99, 20, "2025-12-31", 0.2);
        Product scratchCard = new Product("Scratch Card", 2.99, 50);

        // Create customers
        Customer customer1 = new Customer("Ahmed", 2000.0);
        Customer customer2 = new Customer("Sara", 50.0); // Low balance customer

        System.out.println("Available Products:");
        System.out.println("- " + mobile);
        System.out.println("- " + tv);
        System.out.println("- " + cheese);
        System.out.println("- " + expiredCheese);
        System.out.println("- " + cookies);
        System.out.println("- " + scratchCard);

        System.out.println("\nCustomers:");
        System.out.println("- " + customer1);
        System.out.println("- " + customer2);

        // Scenario 1: Successful checkout with shipping
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SCENARIO 1: Successful Checkout with Shipping");
        System.out.println("=".repeat(50));

        Cart cart1 = new Cart();
        cart1.add(tv, 1); // Shippable item
        cart1.add(cheese, 2); // Shippable and expirable item
        cart1.add(mobile, 1); // Non-shippable item
        cart1.add(scratchCard, 3); // Non-shippable, non-expirable item

        cart1.checkout(customer1);

        // Scenario 2: Empty cart checkout
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SCENARIO 2: Empty Cart Checkout");
        System.out.println("=".repeat(50));

        Cart emptyCart = new Cart();
        emptyCart.checkout(customer1);

        // Scenario 3: Insufficient balance
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SCENARIO 3: Insufficient Balance");
        System.out.println("=".repeat(50));

        Cart cart2 = new Cart();
        cart2.add(mobile, 1);
        cart2.checkout(customer2); // Customer2 has only $50, iPhone costs $999.99

        // Scenario 4: Expired product
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SCENARIO 4: Expired Product");
        System.out.println("=".repeat(50));

        Cart cart3 = new Cart();
        cart3.add(expiredCheese, 1);
        cart3.add(cookies, 2);
        cart3.checkout(customer1);

        // Scenario 5: Out of stock
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SCENARIO 5: Out of Stock");
        System.out.println("=".repeat(50));

        Cart cart4 = new Cart();
        cart4.add(mobile, 10); // Only 5 in stock, but requesting 10
        cart4.checkout(customer1);

        // Scenario 6: Non-shippable items only
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SCENARIO 6: Non-Shippable Items Only");
        System.out.println("=".repeat(50));

        // Create a fresh customer with sufficient balance for this scenario
        Customer customer3 = new Customer("Same7", 2000.0);
        
        Cart cart5 = new Cart();
        cart5.add(mobile, 1);
        cart5.add(scratchCard, 5);
        cart5.add(scratchCard,2);
        cart5.checkout(customer3);

        System.out.println("\n=== DEMO COMPLETE ===");

        // Show final customer balances
        System.out.println("\nFinal Customer Status:");
        System.out.println("- " + customer1);
        System.out.println("- " + customer2);
    }
}
