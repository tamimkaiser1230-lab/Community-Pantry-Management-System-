package Item;


public class PantryRequest {

    String requestID;
    String itemID;
    int quantity;
    int arrivalNumber;
    String status;

    public PantryRequest(String requestID, String itemID,
                         int quantity, int arrivalNumber) {

        this.requestID = requestID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.arrivalNumber = arrivalNumber;
        this.status = "PENDING";
    }
}
