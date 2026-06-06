package main.java.rvt;

import java.sql.*;
import java.util.Scanner;

public class Main {

    static final String URL = "jdbc:sqlite:shop.db";

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS categories (id INTEGER PRIMARY KEY, name TEXT NOT NULL)");
            conn.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS products (id INTEGER PRIMARY KEY, name TEXT NOT NULL, price REAL NOT NULL, category_id INTEGER, FOREIGN KEY(category_id) REFERENCES categories(id))");

            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("\n1-Pievienot kategoriju  2-Pievienot produktu  3-Kategorijas  4-Produkti  5-Meklēt  0-Iziet");
                switch (sc.nextLine()) {
                    case "1" -> {
                        System.out.print("Nosaukums: ");
                        PreparedStatement ps = conn.prepareStatement("INSERT INTO categories(name) VALUES(?)");
                        ps.setString(1, sc.nextLine());
                        ps.executeUpdate();
                    }
                    case "2" -> {
                        showCategories(conn);
                        System.out.print("Nosaukums: "); String name = sc.nextLine();
                        System.out.print("Cena: ");      double price = Double.parseDouble(sc.nextLine());
                        System.out.print("Kat. ID: ");   int catId = Integer.parseInt(sc.nextLine());
                        PreparedStatement ps = conn.prepareStatement("INSERT INTO products(name, price, category_id) VALUES(?,?,?)");
                        ps.setString(1, name); ps.setDouble(2, price); ps.setInt(3, catId);
                        ps.executeUpdate();
                    }
                    case "3" -> showCategories(conn);
                    case "4" -> showProducts(conn, null);
                    case "5" -> { System.out.print("Kat. ID vai nosaukums: "); showProducts(conn, sc.nextLine()); }
                    case "0" -> { return; }
                }
            }
        }
    }

    static void showCategories(Connection conn) throws SQLException {
        ResultSet rs = conn.prepareStatement("SELECT * FROM categories ORDER BY id").executeQuery();
        while (rs.next()) System.out.println(rs.getInt("id") + ": " + rs.getString("name"));
    }

    static void showProducts(Connection conn, String filter) throws SQLException {
        String sql = filter == null
            ? "SELECT p.id, p.name, p.price, c.name AS cat FROM products p JOIN categories c ON p.category_id=c.id"
            : "SELECT p.id, p.name, p.price, c.name AS cat FROM products p JOIN categories c ON p.category_id=c.id WHERE c.id=? OR c.name=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        if (filter != null) { ps.setString(1, filter); ps.setString(2, filter); }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) System.out.println(rs.getInt("id") + ": " + rs.getString("name") + " - " + rs.getDouble("price") + " EUR (" + rs.getString("cat") + ")");
    }
}