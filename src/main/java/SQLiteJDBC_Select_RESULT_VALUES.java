import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLiteJDBC_Select_RESULT_VALUES
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
      //ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
      ResultSet rs = stmt.executeQuery( "SELECT * FROM RESULT_VALUES;" );
/*       while ( rs.next() ) {
         int id = rs.getInt("id");
         String  name = rs.getString("name");
         int age  = rs.getInt("age");
         String  address = rs.getString("address");
         float salary = rs.getFloat("salary");
         System.out.println( "ID = " + id );
         System.out.println( "NAME = " + name );
         System.out.println( "AGE = " + age );
         System.out.println( "ADDRESS = " + address );
         System.out.println( "SALARY = " + salary );
         System.out.println();
      }*/
      
      Integer identifier;
      String  FROMCUR;
      String  TOCUR;
      Double googleResult;
      Double soapResult;
      
      while ( rs.next() ) {
    	  //int id = rs.getInt("id");
    	  identifier = rs.getInt("ID");
          FROMCUR = rs.getString("fromcur");
          TOCUR = rs.getString("tocur");
          googleResult = rs.getDouble("google");
          soapResult = rs.getDouble("soap");
          System.out.println(identifier + " " + FROMCUR + " " +TOCUR+ " " +googleResult+ " " +soapResult );
          //System.out.println( "NAME = " + name );

          //System.out.println( "fullname = " + fullname );
          //System.out.println();
      }
      
      
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.err.println("FAIL");
      System.exit(0);
    }
    System.out.println("Operation done successfully");
  }
}