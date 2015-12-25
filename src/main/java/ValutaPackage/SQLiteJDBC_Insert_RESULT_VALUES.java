package ValutaPackage;

import java.sql.*;

public class SQLiteJDBC_Insert_RESULT_VALUES
{
  static Connection c = null;

  public static void openConnection(){

    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");
    }
      catch(Exception e){
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.out.println("FAILED");
        System.exit(0);
      }
  }

  public static void closeConnection(){
    try {
      c.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  public static int getLastID(){
    openConnection();
    String sql;
    int LastID=0;
    try{
      Statement stmt = c.createStatement();


    sql = "select max (id) from RESULT_VALUES";
    ResultSet rs = stmt.executeQuery(sql);
    while ( rs.next() ) {
      LastID = rs.getInt("max (id)");
    }
    closeConnection();
    }
    catch (Exception e){
      e.printStackTrace();
    }
    return LastID;
  }
  public static void insertResult(ValutaResultObject vro){
   /* Connection c = null;

    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");
     */
//todo maak getlastID
    try{
      int LastID=getLastID();// = stmt.executeUpdate(sql);
      Statement stmt = null;
      openConnection();
      stmt = c.createStatement();
      String sql;
      sql = "select max (id) from RESULT_VALUES";

      /*System.out.println("LastID: " + LastID);
      ResultSet rs = stmt.executeQuery( "select max (id) from RESULT_VALUES;" );
      //rs.next();
      //LastID = rs.getInt("ID");
      //----
      while ( rs.next() ) {
        //int id = rs.getInt("id");
        //System.out.println(rs.getArray(0));
        LastID = rs.getInt("max (id)");


      }
      ///----
*/

      System.out.println("LastID: " + LastID);
      //String sql = "delete from RESULT_VALUES";
      //stmt.executeUpdate(sql);
      //System.out.println("Records from RESULT_VALUES deleted successfully");
      sql = "INSERT INTO RESULT_VALUES " +
              "VALUES"+
              /*LastID+1

              vro.fromVal

              "(5,'AFA','EUR', '1.1','1.2')";

              */
              "("+(LastID+1)+ ",'" + vro.fromVal + "','" + vro.toVal + "','1.1','1.2')";
      System.out.println("STRING IS: " + sql);
      stmt.executeUpdate(sql);

      stmt.close();
      c.commit();
      closeConnection();
//      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.out.println("FAILED");
      System.exit(0);
    }
    System.out.println("Records created successfully");
  }
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