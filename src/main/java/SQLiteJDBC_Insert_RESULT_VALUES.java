import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLiteJDBC_Insert_RESULT_VALUES
{
  public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
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

      String sql = "delete from RESULT_VALUES";
      stmt.executeUpdate(sql);
      System.out.println("Records from RESULT_VALUES deleted successfully");
      sql = "INSERT INTO RESULT_VALUES " +
              "VALUES"+
      		"(1,'AFA','EUR', '1.1','1.2'),"+
              "(2, 'EUR', 'QAR', '1.12345','1.0')," +
              "(3, 'USD','EUR','3.1','3.2')," +
              "(4, 'QAR', 'EUR','4','4.1')";

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