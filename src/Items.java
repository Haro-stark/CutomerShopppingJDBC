import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Items implements ItemsInterface {

    private static String showItemsQuery = "select * from Items";
    public int id;
    public String name;
    public double unitPrice;
    public int quantityAvailable;

    public static void showAvailableItems() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(showItemsQuery);
        int id;
        double unitPrice;
        String name;
        System.out.println("ID\tName\tUnit Price");
        System.out.println("------------------------------------");
        while (resultSet.next()) {
            id = resultSet.getInt("ITEMID");
            name = resultSet.getString("NAME");
            unitPrice = resultSet.getInt("UNITPRICE");
            System.out.println(id+"\t"+name+"\t"+unitPrice);
        }
        DatabaseConnection.endConnection(connection);
    }
    public static int getItemQuantity(int id) throws SQLException{
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select Quantity from items where ItemId="+id);
        int quantity = resultSet.getInt("Quantity");
        DatabaseConnection.endConnection(connection);
        return quantity;
    }
    public static int getItemPrice(int id)  throws SQLException{
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select UnitPrice from items where ItemId="+id);

        int unitPrice=0;
        while(resultSet.next()){
            unitPrice = resultSet.getInt(1);
        }

        DatabaseConnection.endConnection(connection);
        return unitPrice;
    }

}
