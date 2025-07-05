package interfaces;

public interface Expirable {
    boolean isExpired();
    String getExpirationDate();
}