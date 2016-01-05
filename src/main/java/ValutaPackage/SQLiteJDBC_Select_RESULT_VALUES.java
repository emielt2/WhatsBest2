package ValutaPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLiteJDBC_Select_RESULT_VALUES
{
  public ValutaResultObject[] getRESULT_VALUES( String ...args )
  {


    Connection c = null;
    Statement stmt = null;
    //int rowAmount = new SQLiteJDBC_Insert_RESULT_VALUES().getLastID();
    ValutaResultObject[] vro;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      ResultSet rs;
      rs = stmt.executeQuery( "SELECT count(*) FROM RESULT_VALUES;" );
      int rowAmount=0;
      while (rs.next()) {
        rowAmount = rs.getInt("count(*)");
      }
      vro = new ValutaResultObject[rowAmount];
      rs = stmt.executeQuery( "SELECT * FROM RESULT_VALUES;" );
      int counter=0;

      while ( rs.next() ) {
        vro[counter] = new ValutaResultObject();
    	  vro[counter].UID = rs.getInt("ID");
          vro[counter].fromVal = rs.getString("fromcur");
          vro[counter].toVal = rs.getString("tocur");
          vro[counter].googleResult = rs.getDouble("google");
          vro[counter].soapResult = rs.getDouble("soap");
          System.out.println(vro[counter].UID + " " + vro[counter].fromVal + " " +vro[counter].toVal+ " " +vro[counter].googleResult+ " " +vro[counter].soapResult );
          counter++;
          //System.out.println( "NAME = " + name );

          //System.out.println( "fullname = " + fullname );
          //System.out.println();
      }
      
      
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.err.println("FAIL_selectresult");
      System.exit(0);
      vro = new ValutaResultObject[0];
    }
    System.out.println("Operation done successfully");
    //String[] returnString = new String{"a"+"b"};
    //return returnString;
    //return new String[]{"aa","bb"};
    return vro;
  }

}