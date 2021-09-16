import java.sql.SQLException;

public interface CustomerInterface {

    void orderItems() throws SQLException;
    void updateOrderStatus() throws SQLException;
    void entryInOrderItems(int orderId, int itemId, int quantity) throws SQLException;
    int getCurrentOrderId() throws SQLException;
    void printReciept(int orderId) throws SQLException;
}
