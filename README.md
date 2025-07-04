# Fawry Rise Journey  
## Full Stack Development Internship Challenge

Design an e-commerce system that has these features:

- Define products with name, price and quantity.
- Some products may expire like Cheese and Biscuits while others may not expire like TV and Mobile.
- Some products may require shipping like Cheese and TV, other products like Mobile scratch cards may not require so. Every shippable item should provide its weight.
- Customers should be able to add a product to cart with specific quantity not more than the available product quantity.
- Customers are able to do checkout with items in the cart.
  - Print in the console checkout details like:
    - order subtotal (sum of all items’ prices)
    - shipping fees
    - paid amount (subtotal + shipping fees)
    - customer current balance after payment
  - Give an error if:
    - Cart is empty
    - Customer's balance is insufficient.
    - One product is out of stock or expired.
  - If applicable, collect all items that need to be shipped and send them to ShippingService which accepts a list of objects implementing an interface containing only:
    - `String getName()`
    - `double getWeight()` methods.

## Fawry Quantum Internship Challenge

**Notes:**

- The solution is to be submitted with any programming language (Java is recommended).
- Cover corner cases, provide assumptions when needed.
- Solutions based on AI-generated content are not permitted.
- Provide code examples that prove your code is working as expected covering all use cases, for example:

```java
cart.add(cheese, 2);
cart.add(tv, 3);
cart.add(scratchCard, 1);
checkout(customer, cart);

** Shipment notice **
2x Cheese 400g
1x Biscuits 700g
Total package weight 1.1kg

** Checkout receipt **
2x Cheese 200
1x Biscuits 150
----------------------
Subtotal 350
Shipping 30
Amount 380```