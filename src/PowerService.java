package ps.csci3901;
import java.sql.*;
import java.util.Properties;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PowerService {
public  Scanner scanner = new.Scanner(System.in);
        public static Connection connection = null;
        public static Statement statement = null;
        public Resultset result= null;
        public PowerService{
    }
    // Add postal code , population and area from user.
        public boolean addPostalCode (String Postalcodes, int population, int area, Object args) throws SQLException {
            // Executing the SQL Query

            String sql1=" Insert into Postalcode" +
                    "(Postalcodes,population,area)" +
                    "values (?,?,?)";
            // Open the sql connection
            PreparedStatement ps = connection.prepareStatement("use riyap;");
            // Taking inputs from user
            System.out.println("Enter Postalcode: \n");
            ps.setString(1, scanner.nextLine());

            System.out.println("Enter population: \n");
            ps.setInt(2, Int.parseInt(scanner.nextLine());

            System.out.println("Enter Area: \n");
            ps.setInt(3, Int.parseInt(scanner.nextLine());

              int rows= ps.execute("use riyap;");
            //Check the rows affected
              if (rows>o){
                  System.out.println("Record done successfully and is unique.\n");
              }
            // close the sql connection
                ps.close("use riyap;");

                return false;
            }
            // Add the hub with point loction in form of coordinate and set serviced areasfrom the user input.
            public boolean addDistributionHub ( String hubIdentifier, Point location, Set servicedAreas )throws SQLException{
                // Executing the SQL Query
                String sql2=" Insert into DistributerHub"+
                        "(HubIdentifier,location,servicesareas)" +
                        "values (?,?,?)";
                // Open the sql connection
                PreparedStatement ps1 = connection.prepareStatement("use riyap;");
                // Taking inputs from user
                System.out.println("Enter Hub: \n");
                ps1.setString(1, scanner.nextLine());

                System.out.println("Enter Location: \n");
                ps1.setInt(2, scanner.nextLine() );

                System.out.println("Enter Setvice Area: \n");
                ps1.setInt(3, scanner.nextLine());

                int rows= ps1.execute("use riyap;");
                //Check the rows affected
                if (rows>o){
                    System.out.println("Record done successfully and is unique.\n");
                }
                // close the sql connection
                ps1.close("use riyap;");

                return false;
            }
    }
    // Add repaire Estimate time to realte with each hub
    public void hubDamage ( String hubIdentifier, float repairEstimate )throws SQLSyntaxErrorException{

        // Executing the SQL Query
        String sql3=" Insert into hubDamage"+
                "(hub Identifier,repairTime)" +
                "values (?,?,?)";

        // Open the sql connection
        PreparedStatement ps2 = connection.prepareStatement("use riyap;");

        // Taking inputs from user
        System.out.println("Enter Hub which is damaged: \n");
        ps3.setString(1, scanner.nextLine());

        System.out.println("Enter Estimated Time required for Hub Repair: \n");
        ps3.setInt(2, Int.parseInt(scanner.nextLine());
        int rows= ps3.execute("use riyap;");

        //Check the rows affected
        if (rows>o){
            System.out.println("Record done successfully.\n");
        }

        // close the sql connection
        ps3.close("use riyap;");
    }
 // Add employeeID who has repaierd the particular hub.
   public void hubRepair( String hubIdentifier, String employeeId, float repairTime, boolean inService ){

        // Executing the SQL Query
        String sql4=" Insert into hubRepair"+
                "(hubidentifier,employeeID,repairTime)"
                +"values (?,?,?)";

        // Open the sql connection
        PreparedStatement ps2 = connection.prepareStatement("use riyap;");

        // Taking inputs from user
        System.out.println("Enter The employeeID: \n");
        ps4.setString(1, Int.parseInt(scanner.nextLine());
        System.out.println("Enter the hub repaired by employee: \n");
        ps4.setInt(2,scanner.nextLine());
        int rows= ps3.execute("use riyap;");

        //Check the rows affected
        if (rows>o){
            System.out.println("Record done successfully.\n");
        }
        else {
            System.out.println("Entered Hub is not repaired YET");
        }

        // close the sql connection
        ps3.close("use riyap;");
    }
// Fetch how many poeple are currently out of power service.
    public int peopleOutOfService () throw SQLException{

        // Executing the SQL Query
        String sql5="  SELECT" +
        " Postalcode.Postalcodes, DistributionHub.hubidentifier, hubdamage.damagedhub" +
        "From " +
        "Postalcode, DistributionHub, hubdamage"+
        " WHERE  " +
        "DistributionHub.hubidentifier=hubdamage.damagedhub " +
        "AND" +
        " (Select sum(count(population) from Postalcode" +
        "WHERE" +
        " Postalcode.Postalcodes=DistributionHub.hubidentifier)"+
        "Group by" +
        " hubdamage Having Count(HubIdentifier=1/PostalCodes)";

        // Storing the result in tResultSet
        populationResultSet = statement.executeQuery(sql5);

        // Open the sql connection
       Statement statement=connection.createStatement("use riyap");
       Resultset result= statement.execute("use riyap");

       while (result.next()){
           int population = result.getInt("population");
           System.out.println(" Total number of people out of service : /n"+ populationResultSet);
        }

        //Check the rows affected
        if (rows>o){
            System.out.println("Record done successfully.\n");
        }

        // close the sql connection
        ps3.close("use riyap;");
    }
   // Fetch the rate of service Restoration with the help of Postalcode, Repaire Time and Increment.
    public List<Integer> rateOfServiceRestoration (float increment ){
       public list rateOfServiceRestoration = null;
        try {
        Statement statement = .createStatement ( );
      // Executing the SQL Query for Joining the tables
        String s ="select *\n" +
        "from (" +
        "(Postalcode inner join DistributionHub " +
        "on " +
        "Postalcode.Postalcodes=DistributionHub.Postalcodes)\n" +
        "inner join " +
        "hubdamage " +
        "on " +
        "DistributionHub.HubIdentifier= hubdamage.HubIdentifier)";

        ResultSet rs = statement.executeQuery( String.valueOf (rateOfServiceRestoration) );
        while (rs.next()) {
        rateOfServiceRestoration. add(rs.getFloat( "rateofservicerestoration" ));
        }
        // close the sql connection
        rs.close("use riyap");
        } catch (Exception e) {
        System.out.println(e);
        }
        return rateOfServiceRestoration ;
        }
    // Fetch the information of list of postal code which are still under served after repain plan implementation with the help of population.
     public List underservedPostalByPopulation ( int limit )throws SQLException{

        List<String> underservedPostalByPopulation = new ArrayList<String>();
        Iterable<? extends String> populationResultset = null;
        for (String Postalcodes : populationResultset) {
        underservedPostalByPopulation.add
        ( String.valueOf( new Object[]{populationResultset} ) );
      final boolean add;
        add = true;
        }
        // Executing the SQL Query
        List<Postalcode> population = String.Sql;
        String sql="" + "";

        (rs, rowNum) -> new Postalcode(rs.getInt("population"), rs.getString("Postalcodes"),);

        // Open the sql connection
        try (Statement statement = connection.createStatement()) {
        result = statement.execute("use riyap");
        }
        while (result.nextLine()){
        int population = result.getInt("population");
        System.out.println(" Total number of people out of service : /n"+ populationResultSet);
        }
        // close the sql connection
        statement.close();
        return underservedPostalByPopulation();
        }
// Fetch the information of list of postal code which are still under served after repain plan implementation with the help of Area.
       public List underservedPostalByArea ( int limit )throws SQLException{

        List<String> underservedPostalByArea = new ArrayList<String>();
        Iterable<? extends String> areaResultset = null;
        for (String Postalcodes : areaResultset) {
        underservedPostalByArea.add
        ( String.valueOf( new Object[]{AreaResultset} ) );

        // Executing the SQL Query
        List<Postalcode> area = String.Sql;
        String sql="" + "";

        (rs, rowNum)  new Postalcode(rs.getInt("area"), rs.getString("Postalcodes"),);

        // Open the sql connection
        try (Statement statement = connection.createStatement()) {
        result = statement.execute("use riyap");
        }
        while (result.nextLine()){
        int population = result.getInt("population");
        System.out.println(" Total number of people out of service : /n"+ areaResultSet);
        }
        // close the sql connection
        statement.close();
        }
    public static void main (String[] args )  {

    PowerService powerservice= new PowerService();

    // Connecting to Dal Database

    try{
        Class.forName( "com.mysql.cj.jdbc.Driver" );
        String dbURL="jdbc:mysql://db.cs.dal.ca:3306/jdbcdb";
        String CSID="riyap";
        String BANNER_ID="B00930901";
        connection = DriverManager.getConnection( ("jdbc:mysql://db.cs.dal.ca:3306/csci3901/riyap",dbURL, CSID,BANNER_ID );
        statement.execute( "use riyap;" );

       // Getting user input choice for Data Storing.

        System.out.println("Enter choice\n" );
       int choice =Integer.parseInt(scanner.nextLine());
       switch(choice){
            case 1:
                System.out.println("1. Insert Postalcode Information \n");
                powerservice.addPostalCode();
                break;
                case 2:
                    System.out.println("2. Insert Hub Information\n");
                    powerservice.addDistributionHub();
                    break;
           case 3:
               System.out.println("3. Enter The damaged Hub: \n");
               powerservice.hubDamage(  );
               break;
           case 4:
               System.out.println("4. Enter the Hub repaired (Only for Power Service Employees: )\n");
               powerservice.hubRepair( );
               break;
           case 5:
               System.out.println("5.Enter 5 to find out how many people are out of service: \n");
               powerservice.peopleOutOfService ();
               break;
               case 6:
                   System.out.println("6. Enter 6 to find the rate of service will restore : )\n");
                   powerservice.rateofServiceRestoration();
                   break;
           case 7:
            System.out.println("7 Enter 7 to find the under served postal codes on basis of population: )\n");
            powerservice.underservedPostalByPopulation();
               break;
           case 8:
             System.out.println("8. Enter 8 to find the under served postal codes on basis of area : )\n");
             powerservice.underservedPostalByArea();
               break;

               default:
                break;
        }
}
    catch(Exception e){
    throw new RuntimeException("Something went wrong");
        System.out.println("Connection failed");
        System.out.println(e.getMessage());
        }

   }
}


