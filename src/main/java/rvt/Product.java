package main.java.rvt;

public record Product(int id, String name, double price, int categoryId) {
    public String toString() { return id + ": " + name + " - " + price + " EUR (kat. " + categoryId + ")"; }
}