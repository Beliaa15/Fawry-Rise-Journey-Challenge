package models;

class Cart {
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
}