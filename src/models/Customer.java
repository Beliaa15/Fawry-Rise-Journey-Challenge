package models;

class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public boolean hasEnoughBalance(double amount) {
        return balance >= amount;
    }

    public void deductBalance(double amount) {
        if (hasEnoughBalance(amount)) {
            balance -= amount;
        }
    }

    @Override
    public String toString() {
        return String.format("%s (Balance: $%.2f)", name, balance);
    }
}
