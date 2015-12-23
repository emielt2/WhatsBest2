import java.sql.*;

public class SQLiteJDBC_Create
{
  public static void mainx( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      //c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Test2?user=postgres&password=secret");

      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String sql;
      /*String sql = "CREATE TABLE COMPANY " +
                   "(ID INT PRIMARY KEY     NOT NULL," +
                   " NAME           TEXT    NOT NULL, " + 
                   " AGE            INT     NOT NULL, " + 
                   " ADDRESS        CHAR(50), " + 
                   " SALARY         REAL)";*/

      sql = "CREATE TABLE TEST_VALUES " +
              "(ID INT PRIMARY KEY     NOT NULL," +
              " FROMCUR           TEXT    NOT NULL, " + 
              " TOCUR       TEXT    NOT NULL)"              ;
      
     /* sql = "CREATE TABLE CONV_VALUES " +
              "(ID INT PRIMARY KEY     NOT NULL," +
              " NAME           TEXT    NOT NULL, " + 
              " FULLNAME       TEXT    NOT NULL)"              ;*/
      /*sql = "CREATE TABLE RESULT_VALUES " +
              "(ID INT PRIMARY KEY     NOT NULL," +
              " FROMCUR           TEXT    NOT NULL, " + 
              " TOCUR           TEXT    NOT NULL, " +
              " GOOGLE           REAL    NOT NULL, " +
              " SOAP       REAL    NOT NULL)"              ;*/

      stmt.executeUpdate(sql);
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Table created successfully");
  }
}