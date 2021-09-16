import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Customer implements CustomerInterface {
    private boolean isTelenorUser;
    private double totalPrice;
    private boolean orderCompleted;
    @Override
    public void orderItems() throws SQLException {
        Scanner sc = new Scanner(System.in);

        this.orderCompleted = false;
        this.updateOrderStatus();
        int orderId = this.getCurrentOrderId();
        while(true){
            System.out.println("Enter product id: ");
            int id = sc.nextInt();
            System.out.println("Enter product quantity: ");
            int quantity = sc.nextInt();

            this.entryInOrderItems(orderId, id, quantity);

            System.out.println("Do you want to add another item? (y/n): ");
            char cont = sc.next().charAt(0);
            if(cont == 'n' || cont == 'N') break;
        }
        this.orderCompleted = true;
        this.updateOrderStatus();
    }

    @Override
    public void updateOrderStatus() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();

        if(this.orderCompleted == false) {
            statement.execute("Insert into Orders(Status)\n" +
                    "Values('pending');");
            System.out.println("order is pending...");
        }else {
            int orderID = getCurrentOrderId();
            statement.execute("Update Orders\n" +
                    "Set Status = 'completed' where OrderID = "+orderID+";");
            System.out.println("order is completed!");
        }


        DatabaseConnection.endConnection(connection);
    }

    @Override
    public void entryInOrderItems(int orderId, int itemId, int quantity) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();

        int totalPrice, unitPrice;
        unitPrice = Items.getItemPrice(itemId);
        totalPrice = unitPrice*quantity;
        System.out.println("Order id: "+orderId);
        statement.execute("Insert into OrderItems(OrderID, ItemID, Quantity, TotalPrice) " +
                "Values ("+orderId+","+itemId+","+quantity+","+totalPrice+")");

        DatabaseConnection.endConnection(connection);
    }

    @Override
    public int getCurrentOrderId() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("select max(OrderID) from Orders");
        int orderID=0;
        while(rs.next()){
            orderID = rs.getInt(1);
        }
        DatabaseConnection.endConnection(connection);
        return orderID;
    }
}
