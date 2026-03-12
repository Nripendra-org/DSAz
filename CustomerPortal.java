import java.util.*;

/*
 Advanced Customer Obsession Comparison Portal
 Converted from HTML project

 DSA Concepts Used:
 1. List ADT using ArrayList
 2. Linear Search for product search
 3. Sorting to find minimum price
 4. Queue concept for checkout order
*/

class ProductModel {
    String name;
    int[] prices;

    ProductModel(String name, int[] prices) {
        this.name = name;
        this.prices = prices;
    }
}

public class CustomerPortal {

    // List ADT implementation using ArrayList
    static ArrayList<String> cart = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    // Product data using HashMap (Hashing concept)
    static HashMap<String, ArrayList<ProductModel>> productData = new HashMap<>();

    public static void main(String[] args) {

        initializeProducts();

        System.out.println("==== Advanced Customer Comparison Portal ====");

        login();

        while(true) {

            System.out.println("\n1. Search Product");
            System.out.println("2. Show Products");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {

                case 1:
                    searchProduct();
                    break;

                case 2:
                    showProducts();
                    break;

                case 3:
                    showCart();
                    break;

                case 4:
                    checkout();
                    break;

                case 5:
                    System.exit(0);
            }
        }
    }

    static void login() {

        System.out.print("Enter Username: ");
        String user = sc.nextLine();

        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        System.out.println("Login Successful!\n");
    }

    static void initializeProducts() {

        ArrayList<ProductModel> shoes = new ArrayList<>();

        shoes.add(new ProductModel("Puma RS-X", new int[]{3999,3799,3499,3699}));
        shoes.add(new ProductModel("Puma Velocity", new int[]{2999,2799,2899,2950}));
        shoes.add(new ProductModel("Puma Rider", new int[]{4200,3999,3899,4050}));

        productData.put("shoes", shoes);

        ArrayList<ProductModel> mobiles = new ArrayList<>();

        mobiles.add(new ProductModel("iPhone 16", new int[]{79999,77999,75999,78999}));
        mobiles.add(new ProductModel("iPhone 16 Pro", new int[]{109999,107999,105999,108999}));

        productData.put("mobiles", mobiles);
    }

    // Linear Search Algorithm
    static void searchProduct() {

        System.out.print("Enter product name: ");
        String search = sc.nextLine().toLowerCase();

        boolean found = false;

        for(String key : productData.keySet()) {

            if(key.contains(search)) {

                found = true;
                showModels(key);
            }
        }

        if(!found)
            System.out.println("Product not found.");
    }

    static void showProducts() {

        System.out.println("\nAvailable Products:");

        for(String key : productData.keySet())
            System.out.println("- " + key);
    }

    static void showModels(String product) {

        ArrayList<ProductModel> models = productData.get(product);

        System.out.println("\nModels of " + product + ":");

        for(ProductModel model : models) {

            int min = findMin(model.prices);

            System.out.println("\nModel: " + model.name);

            System.out.println("Amazon: " + model.prices[0]);
            System.out.println("Flipkart: " + model.prices[1]);
            System.out.println("Meesho: " + model.prices[2]);
            System.out.println("Ajio: " + model.prices[3]);

            System.out.println("Best Price: " + min);

            System.out.println("1. Add to Cart");
            System.out.println("2. Skip");

            int c = sc.nextInt();

            if(c == 1)
                addToCart(model.name);
        }
    }

    // Sorting / Minimum element finding
    static int findMin(int arr[]) {

        int min = arr[0];

        for(int i=1;i<arr.length;i++) {

            if(arr[i] < min)
                min = arr[i];
        }

        return min;
    }

    // List ADT operation
    static void addToCart(String name) {

        cart.add(name);

        System.out.println(name + " added to cart.");
    }

    static void showCart() {

        System.out.println("\nYour Cart:");

        for(String item : cart)
            System.out.println(item);
    }

    // Queue concept simulation (orders processed in FIFO)
    static void checkout() {

        if(cart.size() == 0) {

            System.out.println("Cart Empty");
            return;
        }

        Queue<String> orderQueue = new LinkedList<>(cart);

        System.out.println("\nProcessing Order:");

        while(!orderQueue.isEmpty()) {

            System.out.println("Ordered: " + orderQueue.poll());
        }

        cart.clear();

        System.out.println("Order placed successfully!");
    }
}