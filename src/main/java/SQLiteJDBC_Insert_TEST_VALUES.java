import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLiteJDBC_Insert_TEST_VALUES
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

      String sql = "delete from TEST_VALUES";
      stmt.executeUpdate(sql);
      System.out.println("Records from TEST_VALUES deleted successfully");

      sql = "INSERT INTO TEST_VALUES "  +
    		  "VALUES"+
    		 "(1,'AFA','EUR'),"+
    		 "(2,'AFA','USD'),"+
    		 "(3,'AFA','QAR'),"+
    		 "(4,'EUR','AFA'),"+
    		 "(5,'EUR','USD'),"+
    		 "(6,'EUR','QAR'),"+
    		 "(7,'USD','AFA'),"+
    		 "(8,'USD','EUR'),"+
    		 "(9,'USD','QAR'),"+
    		 "(10,'QAR','AFA'),"+
    		 "(11,'QAR','EUR'),"+
    		 "(12,'QAR','USD')";

      stmt.executeUpdate(sql);

      stmt.close();
      c.commit();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Records created successfully");
  }
}