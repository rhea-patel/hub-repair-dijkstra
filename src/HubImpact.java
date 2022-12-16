package ps.csci3901;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;
import java.sql.DriverManager;

public class HubImpact extends PowerService  {
    List damagedhub = null;
    private Connection h;
    private Connection rp;
    private String ResultSet;

    public List <HubImpact>fixOrder ( int limit )throws SQLException{
        List<HubImpact> list = new ArrayList<> ();
        try {
            Statement statement = h.createStatement ( );
            // Executing the SQL Query
            String s = "SELECT Postalcode.Postalcodes , Postalcode.population\\r\n" +
                    "\"FROM DistributionHub\\n\" +\n" +
                    "\"JOIN hubdamage ON DistributionHub.hubIdentifier = hubdamage.damagedhub\\n\" +\n" +
                    "\"JOIN Postalcode ON DistributionHub.Postalcodes = Postalcode.Postalcodes\\n\"\n" +
                    "\"'ORDER BY Postalcode.population DESC Limit\" *limits\" ;ln) ;";
            ResultSet rs = statement.executeQuery( String.valueOf ( h ) );
            while (rs.next()) {
                damagedhub.get ( rs.getInt ( "fixOrder" ) );
            }
            // close the sql connection
            rs.close();

        }
        catch (Exception e) {
            System.out.println(e);
        }
        return damagedhub;
    }

    public <HubImpact> List <HubImpact>repairPlan(String startHub, int maxDistance, float maxTime, List<HubImpact> HubImpact, ResultSet ty)throws SQLException {
        return repairPlan ( startHub, maxDistance, maxTime, HubImpact, ty, ry );
    }

    public <HubImpact> List <HubImpact>repairPlan (String startHub, int maxDistance, float maxTime, List<HubImpact> HubImpact, ResultSet ty, char[] ry)throws SQLException{
        List<HubImpact> list = new ArrayList<> ();

        try {

            Statement statement;
            statement = rp.createStatement ( );
            // Executing the SQL Query
            String s=" " +
                    "";

            s = ResultSet;
            ty = statement.executeQuery ( String.valueOf ( rp) );
            while (ty.next()) {
                HubImpact.get (ty.getInt ( "fixOrder" ) );
            }
            // close the sql connection
            ty.close();
        } catch (Exception e) {
            System.out.println(ty);
        }
        return HubImpact;
    }
    public static void main( String[] args){
    try{
        // Connecting to Dal Database

            Class.forName( "com.mysql.cj.jdbc.Driver" );
            String dbURL="jdbc:mysql://db.cs.dal.ca:3306/jdbcdb";
            String CSID="riyap";
            String BANNER_ID="B00930901";
            connection = DriverManager.getConnection( ("jdbc:mysql://db.cs.dal.ca:3306/csci3901/riyap","dbURL"," CSID","BANNER_ID");
            final boolean execute;
             if (statement.execute( "use riyap;" ))
             {
                 execute = true;
             }
             else {
                 execute = false;
             }
    }
    catch(Exception e){
        throw new RuntimeException("Something went wrong");
    }
    }
    public void setRp(Connection rp) {
        this.rp = rp;
    }
}
