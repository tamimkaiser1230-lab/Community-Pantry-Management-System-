package Item;


import java.util.*;

public class Main {

    static HashMap<String, Item> items = new HashMap<>();

    static Queue<PantryRequest> pendingRequests =
            new ArrayDeque<>();

    static ArrayList<PantryRequest> history =
            new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

    static int nextArrival = 1;

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println(" COMMUNITY PANTRY MANAGEMENT");
            System.out.println("================================");

            System.out.println("1. Register Item");
            System.out.println("2. Find Item");
            System.out.println("3. Register Request");
            System.out.println("4. Process FIFO Requests");
            System.out.println("5. Display Request History");
            System.out.println("6. Low-Stock Report");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");

            choice = readInteger("Choice");

            switch (choice) {

                case 1:
                    registerItem();
                    break;

                case 2:
                    findItem();
                    break;

                case 3:
                    registerRequest();
                    break;

                case 4:
                    processRequests();
                    break;

                case 5:
                    displayHistory();
                    break;

                case 6:
                    lowStockReport();
                    break;

                case 7:
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid menu choice.");
            }

        } while (choice != 7);

        scanner.close();
    }

private static void findItem() {

    System.out.println("\n--- FIND ITEM ---");

    System.out.print("Enter Item ID: ");

    String id = scanner.nextLine().trim().toUpperCase();

    Item item = items.get(id);

    if (item == null) {

        System.out.println("Item not found.");

    } else {

        System.out.println("Item ID: " + item.id);
        System.out.println("Item Name: " + item.name);
        System.out.println("Available Stock: " + item.stock);
    }
}


private static void registerRequest() {

    System.out.println("\n--- REGISTER REQUEST ---");

    System.out.print("Enter Request ID: ");
    String requestID = scanner.nextLine().trim().toUpperCase();

    if (!requestID.matches("Q[0-9]{3}")) {
        System.out.println("Invalid Request ID.");
        return;
    }

    for (PantryRequest r : history) {
        if (r.requestID.equals(requestID)) {
            System.out.println("Request ID already exists.");
            return;
        }
    }

    System.out.print("Enter Item ID: ");
    String itemID = scanner.nextLine().trim().toUpperCase();

    if (!items.containsKey(itemID)) {
        System.out.println("Item does not exist.");
        return;
    }

    System.out.print("Enter Quantity: ");
    int quantity = readInteger("quantity");

    if (quantity < 1 || quantity > 1000) {
        System.out.println("Quantity must be between 1 and 1000.");
        return;
    }

    PantryRequest request = new PantryRequest(
        requestID, itemID, quantity, nextArrival
    );

    pendingRequests.add(request);
    history.add(request);

    nextArrival++;

    System.out.println("Request registered successfully!");
    System.out.println("Status: PENDING");
}



private static void processRequests() {

    System.out.println("\n--- PROCESSING FIFO REQUESTS ---");

    if (pendingRequests.isEmpty()) {
        System.out.println("No pending requests.");
        return;
    }

    while (!pendingRequests.isEmpty()) {

        PantryRequest request = pendingRequests.poll();

        Item item = items.get(request.itemID);

        if (item.stock >= request.quantity) {

            item.stock = item.stock - request.quantity;

            request.status = "FULFILLED";

        } else {

            request.status = "REJECTED";
        }

        System.out.println(
            request.requestID + " - " + request.status
        );

        System.out.println(
            "Remaining stock: " + item.stock
        );
    }

    System.out.println("FIFO processing completed.");
}

private static void lowStockReport() {

    System.out.println("\n--- LOW STOCK REPORT ---");

    System.out.print("Enter stock threshold: ");
    int threshold = readInteger("threshold");

    if (threshold < 0) {
        System.out.println("Threshold cannot be negative.");
        return;
    }

    boolean found = false;

    for (Item item : items.values()) {

        if (item.stock <= threshold) {

            System.out.println(
                item.id + " - " + item.name
                + " - Stock: " + item.stock
            );

            found = true;
        }
    }

    if (!found) {
        System.out.println("No low-stock items found.");
    }
}



private static void displayHistory() {

    System.out.println("\n--- REQUEST HISTORY ---");

    if (history.isEmpty()) {
        System.out.println("No request history available.");
        return;
    }

    for (PantryRequest request : history) {

        System.out.println("------------------------");

        System.out.println("Request ID: " + request.requestID);

        System.out.println("Item ID: " + request.itemID);

        System.out.println("Quantity: " + request.quantity);

        System.out.println("Arrival Number: " + request.arrivalNumber);

        System.out.println("Status: " + request.status);
    }

    System.out.println("------------------------");
}


	static int readInteger(String fieldName) {

        while (true) {

            try {
                return Integer.parseInt(
                        scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.print(
                        "Invalid number. Enter " + fieldName + " again: ");
            }
        }
    }

static void registerItem() {

    System.out.println("\n--- REGISTER ITEM ---");

    System.out.print("Enter Item ID: ");
    String id = scanner.nextLine().trim().toUpperCase();

    if (!id.matches("[A-Z][0-9]{3}")) {
        System.out.println("Invalid Item ID.");
        return;
    }

    if (items.containsKey(id)) {
        System.out.println("Item ID already exists.");
        return;
    }

    System.out.print("Enter Item Name: ");
    String name = scanner.nextLine().trim();

    if (name.length() < 1 || name.length() > 40) {
        System.out.println("Name must be 1-40 characters.");
        return;
    }

    System.out.print("Enter Initial Stock: ");
    int stock = readInteger("stock");

    if (stock < 0 || stock > 1000) {
        System.out.println("Invalid stock amount.");
        return;
    }

    Item item = new Item(id, name, stock);
    items.put(id, item);

    System.out.println("Item registered successfully!");
}

static void findItem1() {

    System.out.println("\n--- FIND ITEM ---");

    System.out.print("Enter Item ID: ");

    String id = scanner.nextLine().trim().toUpperCase();

    Item item = items.get(id);

    if (item == null) {

        System.out.println("Item not found.");

    } else {

        System.out.println("Item ID: " + item.id);
        System.out.println("Item Name: " + item.name);
        System.out.println("Available Stock: " + item.stock);
    }
}

static void registerRequest1() {

    System.out.println("\n--- REGISTER REQUEST ---");

    System.out.print("Enter Request ID: ");

    String requestID = scanner.nextLine()
            .trim().toUpperCase();

    if (!requestID.matches("Q[0-9]{3}")) {
        System.out.println("Invalid Request ID.");
        return;
    }

    for (PantryRequest r : history) {

        if (r.requestID.equals(requestID)) {

            System.out.println("Request ID already exists.");
            return;
        }
    }

    System.out.print("Enter Item ID: ");

    String itemID = scanner.nextLine()
            .trim().toUpperCase();

    if (!items.containsKey(itemID)) {

        System.out.println("Item does not exist.");
        return;
    }

    System.out.print("Enter Quantity: ");

    int quantity = readInteger("quantity");

    if (quantity < 1 || quantity > 1000) {

        System.out.println("Quantity must be 1-1000.");
        return;
    }

    PantryRequest request = new PantryRequest(
            requestID, itemID, quantity, nextArrival);

    pendingRequests.add(request);

    history.add(request);

    nextArrival++;

    System.out.println("Request registered successfully!");
    System.out.println("Status: PENDING");
}

static void processRequests1() {

    System.out.println("\n--- PROCESSING FIFO REQUESTS ---");

    if (pendingRequests.isEmpty()) {

        System.out.println("No pending requests.");
        return;
    }

    while (!pendingRequests.isEmpty()) {

        PantryRequest request = pendingRequests.poll();

        if (!request.status.equals("PENDING")) {
            continue;
        }

        Item item = items.get(request.itemID);

        if (item.stock >= request.quantity) {

            item.stock = item.stock - request.quantity;

            request.status = "FULFILLED";

        } else {

            request.status = "REJECTED";
        }

        System.out.println(
                request.requestID + " - " + request.status
                + " - Stock remaining: " + item.stock);
    }

    System.out.println("FIFO processing completed.");
}

static void displayHistory1() {

    System.out.println("\n--- REQUEST HISTORY ---");

    if (history.isEmpty()) {

        System.out.println("No request history available.");
        return;
    }

    for (PantryRequest request : history) {

        System.out.println(
                "Request ID: " + request.requestID);

        System.out.println(
                "Item ID: " + request.itemID);

        System.out.println(
                "Quantity: " + request.quantity);

        System.out.println(
                "Arrival Number: " + request.arrivalNumber);

        System.out.println(
                "Status: " + request.status);

        System.out.println("------------------------");
    }
}

static void lowStockReport1() {

    System.out.println("\n--- LOW STOCK REPORT ---");

    System.out.print("Enter stock threshold: ");

    int threshold = readInteger("threshold");

    if (threshold < 0) {

        System.out.println("Threshold cannot be negative.");
        return;
    }

    boolean found = false;

    for (Item item : items.values()) {

        if (item.stock <= threshold) {

            System.out.println(
                    item.id + " - " + item.name
                    + " - Stock: " + item.stock);

            found = true;
        }
    }

    if (!found) {

        System.out.println("No low-stock items found.");
    }
}

}
