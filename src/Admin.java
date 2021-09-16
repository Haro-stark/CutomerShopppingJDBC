import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Admin implements AdminInterface{


    @Override
    public void Modify() throws SQLException {
        Scanner  sc = new Scanner(System.in);
        Items items= new Items();

        System.out.println("Enter the item id: ");
        int id = sc.nextInt();
        System.out.println("Modify the item quantity availability: ");
        int quantityAvailable = sc.nextInt();
        System.out.println("Modify the item unit price: ");
        int unitPrice = sc.nextInt();

        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE Items\n" +
                "SET QuantityAvailable = "+quantityAvailable+", UnitPrice= "+unitPrice+"\n" +
                "WHERE ItemID = "+id+";");
        System.out.println("updated successfully...");
        DatabaseConnection.endConnection(connection);
    }
    @Override
    public void Add() throws SQLException {
        Items items = new Items();
        Scanner  sc = new Scanner(System.in);

        System.out.println("Enter item's name: ");
        items.name = sc.nextLine();
        System.out.println("Enter item's quantity available: ");
        items.quantityAvailable = sc.nextInt();
        System.out.println("Enter item's unit price: ");
        items.unitPrice = sc.nextDouble();

        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO Items (Name, UnitPrice, QuantityAvailable)\n" +
                "VALUES ('"+items.name+"', "+items.unitPrice+", "+items.quantityAvailable+");");
        System.out.println("added successfully...");
        DatabaseConnection.endConnection(connection);
    }

    public static char askAdminOperation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Press M/m to modify the Items.\n2. Press A/a to Add a new Item.");
        char askAdminOperation = sc.next().charAt(0);

        return askAdminOperation;
    }
}
