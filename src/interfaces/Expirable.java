package interfaces;

interface Expirable {
    boolean isExpired();
    String getExpirationDate();
}