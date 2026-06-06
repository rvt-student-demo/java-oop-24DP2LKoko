package main.java.rvt;
 
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
 
public class Store {
    private Warehouse warehouse;
    private Scanner scanner;
 
    public Store(Warehouse warehouse, Scanner scanner) {
        this.warehouse = warehouse;
        this.scanner = scanner;
    }
 
    public void shop(String customer) {
        ShoppingCart cart = new ShoppingCart();
        System.out.println("Welcome to the store " + customer);
        System.out.println("our selection:");
        for (String product : this.warehouse.products()) {
            System.out.println(product);
        }
        while (true) {
            System.out.print("What to put in the cart (press enter to go to the register): ");
            String product = scanner.nextLine();
            if (product.isEmpty()) {
                break;
            }
            if (warehouse.take(product)) {
                cart.add(product, warehouse.price(product));
            } else {
                System.out.println("there is no product");
            }
        }
        System.out.println("your shoppingcart contents:");
        cart.print();
        System.out.println("total: " + cart.price());
    }
 
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct("coffee", 5, 10);
        warehouse.addProduct("milk", 3, 20);
        warehouse.addProduct("cream", 2, 55);
        warehouse.addProduct("bread", 7, 8);
 
        Store store = new Store(warehouse, new Scanner(System.in));
        store.shop("John");
    }
}
 
// Glaba preces, to cenas un krajumus
class Warehouse {
    private Map<String, Integer> prices;
    private Map<String, Integer> stock;
 
    public Warehouse() {
        this.prices = new HashMap<>();
        this.stock = new HashMap<>();
    }
 
    public void addProduct(String product, int price, int stock) {
        prices.put(product, price);
        this.stock.put(product, stock);
    }
 
    public int price(String product) {
        return prices.getOrDefault(product, -99);
    }
 
    public int stock(String product) {
        return stock.getOrDefault(product, 0);
    }
 
    // Samazina krajumu par 1, atgriez false ja preces nav
    public boolean take(String product) {
        if (stock(product) > 0) {
            stock.put(product, stock.get(product) - 1);
            return true;
        }
        return false;
    }
 
    public Set<String> products() {
        return new HashSet<>(prices.keySet());
    }
}
 
// Pievieno preci vai palielina daudzumu ja jau ir groza
class ShoppingCart {
    private Map<String, Item> itemMap;
 
    public ShoppingCart() {
        this.itemMap = new HashMap<>();
    }
 
    public void add(String product, int price) {
        if (itemMap.containsKey(product)) {
            itemMap.get(product).increaseQuantity();
        } else {
            itemMap.put(product, new Item(product, 1, price));
        }
    }
 
    public int price() {
        int total = 0;
        for (Item item : itemMap.values()) {
            total += item.price();
        }
        return total;
    }
 
    public void print() {
        for (Item item : itemMap.values()) {
            System.out.println(item);
        }
    }
}
 
class Item {
    private String product;
    private int qty;
    private int unitPrice;
 
    public Item(String product, int qty, int unitPrice) {
        this.product = product;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }
 
    public int price() {
        return this.unitPrice * qty;
    }
 
    public void increaseQuantity() {
        this.qty++;
    }
 
    @Override
    public String toString() {
        return this.product + ": " + this.qty;
    }
}