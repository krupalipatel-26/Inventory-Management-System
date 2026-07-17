import java.util.*;

class Product {
    int id;
    String name;
    int quantity;
    double price;

    Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | Qty: " + quantity + " | $" + price;
    }
}

public class InventorySystem {

    static Scanner sc = new Scanner(System.in);
    static HashMap<Integer, Product> products = new HashMap<>();

    public static void main(String[] args) {

        if (!login()) {
            System.out.println("Too many failed attempts. Exiting...");
            return;
        }

        int choice;

        do {
            System.out.println("\n===== INVENTORY MENU =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Sell Product");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> viewProducts();
                case 3 -> updateProduct();
                case 4 -> deleteProduct();
                case 5 -> sellProduct();
                case 6 -> System.out.println("Thank You!");
                default -> System.out.println("Invalid Choice!");
            }

        } while (choice != 6);
    }

    //  Login System
    static boolean login() {
        String correctUser = "admin";
        String correctPass = "1234";

        int attempts = 3;

        while (attempts > 0) {
            System.out.print("Username: ");
            String user = sc.next();
            System.out.print("Password: ");
            String pass = sc.next();

            if (user.equals(correctUser) && pass.equals(correctPass)) {
                System.out.println("Login Successful!");
                return true;
            } else {
                attempts--;
                System.out.println("Wrong Credentials! Attempts left: " + attempts);
            }
        }
        return false;
    }

    //  Add Product
    static void addProduct() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        products.put(id, new Product(id, name, qty, price));
        System.out.println("Product Added Successfully!");
    }

    //  View Products
    static void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("No Products Available!");
            return;
        }

        for (Product p : products.values()) {
            System.out.println(p);
            if (p.quantity < 5) {
                System.out.println("@ Low Stock Warning!");
            }
        }
    }

    //  Update Product
    static void updateProduct() {
        System.out.print("Enter Product ID to update: ");
        int id = sc.nextInt();

        if (!products.containsKey(id)) {
            System.out.println("Product Not Found!");
            return;
        }

        System.out.print("Enter New Quantity: ");
        int qty = sc.nextInt();

        System.out.print("Enter New Price: ");
        double price = sc.nextDouble();

        Product p = products.get(id);
        p.quantity = qty;
        p.price = price;

        System.out.println("Product Updated Successfully!");
    }

    //  Delete Product
    static void deleteProduct() {
        System.out.print("Enter Product ID to delete: ");
        int id = sc.nextInt();

        if (products.remove(id) != null) {
            System.out.println("Product Deleted!");
        } else {
            System.out.println("Product Not Found!");
        }
    }

    //  Sell Product
    static void sellProduct() {
        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        if (!products.containsKey(id)) {
            System.out.println("Product Not Found!");
            return;
        }

        System.out.print("Enter Quantity to Sell: ");
        int qty = sc.nextInt();

        Product p = products.get(id);

        if (p.quantity < qty) {
            System.out.println("Insufficient Stock!");
            return;
        }

        p.quantity -= qty;
        double total = qty * p.price;

        System.out.println("Total Bill: $" + total);
    }
}