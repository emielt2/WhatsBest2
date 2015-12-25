import java.sql.*;

public class SQLiteJDBC_Insert_CONV_VALUES
{
  public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      //c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Test2?user=postgres&password=secret");

      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      /*
      String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                   "VALUES (1, 'Paul', 32, 'California', 20000.00 );"; 
      stmt.executeUpdate(sql);

      sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
            "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );"; 
      stmt.executeUpdate(sql);

      sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
            "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );"; 
      stmt.executeUpdate(sql);

      sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
            "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );"; */

      String sql = "delete from CONV_VALUES";
      stmt.executeUpdate(sql);
      System.out.println("Records from CONV_VALUES deleted successfully");
      sql = "INSERT INTO CONV_VALUES " +
              "VALUES"+
      		"(1,'AFA','Afghanistan Afghani'),"+
              "(2, 'EUR', 'Euro')," +
              "(3, 'USD','U.S. Dollar')," +
              "(4, 'QAR', 'Qatar Rial')";

      stmt.executeUpdate(sql);

      stmt.close();
      c.commit();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.out.println("FAILED");
      System.exit(0);
    }
    System.out.println("Records created successfully");
  }
}