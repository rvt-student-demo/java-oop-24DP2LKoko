package main.java.rvt;

public record Category(int id, String name) {
    public String toString() { return id + ": " + name; }
}