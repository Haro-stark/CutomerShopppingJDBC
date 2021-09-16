import java.sql.*;
import java.util.Scanner;

public class main {

    public static char askUserType() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Are you a customer or an admin");
        char userType = sc.next().charAt(0);
        return userType;

    }

    public static void main(String[] a) throws SQLException {
        //Asking about the type of user
        char userType = askUserType();

        if(userType=='a' || userType=='A'){
            Admin admin = new Admin();
            //print the while menu available
            Items.showAvailableItems();

            //asking admin what type of operation you want to perform
            char adminOperation = Admin.askAdminOperation();
            if(adminOperation == 'm' || adminOperation == 'M'){
                //Modify the items by admin
                admin.Modify();
            }else{
                //Add a new item by admin
                admin.Add();
            }
        }else{
            Customer customer = new Customer();
            //print the while menu available
            Items.showAvailableItems();

            customer.orderItems();

            //Ask the customer for items id

        }



        //---------------old--------------------------------------------

/*
        Connection con = ConnectionFactory.getConnection();
        Statement stat = con.createStatement();
        String query = "SELECT NAME FROM Lecture";
        ResultSet years = stat.executeQuery(query);
*/

        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:~/shop", "haroon", "123456");
            // add application code here
            Statement st = conn.createStatement();
            st.executeUpdate("CREATE TABLE ITEMS (\n" +
                    "                ID int,\n" +
                    "        Name varchar(255),\n" +
                    "                UnitPrice double, \n" +
                    "                ID varchar(255) ,\n" +
                    "                Address varchar(255),\n" +
                    "                City varchar(255)\n" +
                    ");");

            st.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException  e) {

        }

    }
}