package ps.csci3901;
import java.sql.DriverManager;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DamagedPostalCodes extends PowerService {

    private Connection c;
    private List<DamagedPostalCodes> Postalcodes;

    public List<DamagedPostalCodes> mostDamagedPostalCodes (int limit){
        List<DamagedPostalCodes> list = new ArrayList<> ();

        try {
           Statement statement = c.createStatement();
            // Executing the SQL Query
           String s = "SELECT Postalcode.Postalcodes, hubdamage.repairTime\\r\n" +
                   "\"FROM DistributionHub\\n\" +\n" +
                   "\"JOIN hubdamage ON DistributionHub.hubIdentifier = hubdamage.damagedhub\\n\" +\n" +
                   "\"JOIN Postalcode ON DistributionHub.Postalcodes = Postalcode.Postalcodes\\n\"\n" +
                   "\"'ORDER BY hubdamage.repairTime DESC Limit\" *limits\" ;ln) ;";

            ResultSet rs = statement.executeQuery(s);

           while (rs.next()) {
               Postalcodes.get ( rs.getInt ( "limit" ) );
           }
            // close the sql connection
           rs.close();
       } catch (Exception e) {
           System.out.println(e);
       }
        return Postalcodes;
   }
    public static void main( String[] args){
        try{
            // Connecting to Dal Database

            Class.forName( "com.mysql.cj.jdbc.Driver" );
            String dbURL="jdbc:mysql://db.cs.dal.ca:3306/jdbcdb";
            String CSID="riyap";
            String BANNER_ID="B00930901";
            connection = DriverManager.getConnection("jdbc:mysql://db.cs.dal.ca:3306/csci3901/riyap")("dbURL","CSID","BANNER_ID ");
            statement.execute( "use riyap;" );
        }
        catch(Exception e){
            throw new RuntimeException("Something went wrong");

        }
    }

}
