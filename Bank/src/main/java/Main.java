import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/demo_1";
        String user = "root";
        String password = "india@123";
        Connection cn = DriverManager.getConnection(url, user, password);
      try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter the Account Number: \n");
          double Acc_Number = sc.nextDouble();
          System.out.println("Enter the Bill Month: \n");
          int Month = sc.nextInt();
          System.out.println("Enter the Bill Amount: \n");
          double Bill_amount = sc.nextDouble();
          System.out.println("Account Number: "+(long)Acc_Number);
          System.out.println("Month: "+Month);

          CallableStatement stmt = cn.prepareCall("{ call get_Bank(?,?,?)}");
          stmt.setDouble(1, Acc_Number);
          stmt.setInt(2, Month);
          stmt.setDouble(3, Bill_amount);
          stmt.execute();


        double amount = stmt.getDouble(3);


        System.out.println("Due Amount: "+amount);

      }catch (Exception e){
          e.printStackTrace();
      }
      finally {
          try{
              cn.close();
          }
          catch (Exception e){
              e.printStackTrace();
          }
      }


    }
}
