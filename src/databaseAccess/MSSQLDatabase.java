package databaseAccess;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class MSSQLDatabase implements GetDatabase{

    private static MSSQLDatabase instance;
    protected static final String url = "jdbc:sqlserver://MAD10;userName=PDF_App;password=$DBPW;";
    protected String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    protected String dbName;
    protected Connection conn;
    protected DatabaseMetaData md;
    protected ResultSet rs;

    public static MSSQLDatabase getDatabaseConnector(){
        return instance;
    }

    //Static instantiation block creates single instance on first use of class
    static {
        instance = new MSSQLDatabase();
    }

    //Private constructor enforces singleton pattern to prevent multiple simultaneous database connections
    private MSSQLDatabase(){};

    /**
     * Searches the result set produced by searching for the clients name and returns all the available client data
     *
     * @return an array containing all the available client data
     * @throws SQLException
     */
    private String[] clientInformation(String surname) throws SQLException {
        Statement st = conn.createStatement();
        System.out.println(surname);

        String query = "Select Forenames From Clients Where Surname = '";
        ResultSet rs = st.executeQuery(query + surname + "';");

        Set<String> firstNames = new HashSet<String>();

        while(rs.next()){
            System.out.println(rs.getString("Forenames"));
            firstNames.add(rs.getString("Forenames"));
        }

        //converting the set to an array before it is returned to the form
        String[] returner = (String[]) firstNames.toArray(new String[0]);
        return returner;
    }

    @Override
    public String[] fetchInfoUsingName(String name)
        throws InstantiationException, IllegalAccessException,
               ClassNotFoundException, SQLException {

        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url);
        System.out.println("Connected to MSSQL Database");
        md = conn.getMetaData();

        String[] result = clientInformation(name);

        if(!conn.equals(null)){
            conn.close();
            System.out.println("disconnected from mySQL Database");
        }
        return result;
    }

    public Map<String, String> getPartnerPersonalData(String name)
        throws InstantiationException,
               IllegalAccessException,
               ClassNotFoundException,
               SQLException{
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url);

        System.out.println("Connected to MSSQL Database");
        System.out.println(name);

        Statement st = conn.createStatement();
        String[] names = name.split("/");

        String query = "SELECT * FROM Clients WHERE Surname = '";

        int cliRef = 0;

        ResultSet rs = st.executeQuery(query
                                       + names[0]
                                       + "'"
                                       + "AND Forenames ='"
                                       + names[1]
                                       + "';");

        Map<String,String> pData = new HashMap<String, String>();
        while(rs.next()){
            pData.put("Title", rs.getString("PartnerTitle"));

            //Removing the time portion of the date object returned by the database before adding it to the map
            String dob = null;
            if(rs.getString("PartnerDOB")!= null){
                dob = rs.getString("PartnerDOB").substring(0,10);
            }
            System.out.println("entered partner saving data in database area");
            pData.put("ForeNames", rs.getString("PartnerForenames"));
            pData.put("Surname", rs.getString("PartnerSurname"));
            pData.put("DOB", dob);
            pData.put("HomeTel", rs.getString("PartnerHomeTel"));
            pData.put("WorkTel", rs.getString("PartnerBusTel"));
            pData.put("Mobile", rs.getString("PartnerMobTel"));
            pData.put("HomeAddress1", rs.getString("PartnerAddress1"));
            pData.put("HomeAddress2", rs.getString("PartnerAddress2"));
            pData.put("HomeAddress3", rs.getString("PartnerAddress3"));
            pData.put("HomeAddress4", rs.getString("PartnerAddress4"));
            pData.put("HomeAddress5", rs.getString("PartnerAddress5"));
            pData.put("HomePostCode", rs.getString("PartnerPostCode"));
            pData.put("Email", rs.getString("PartnerEmailAddress"));
            cliRef = rs.getInt("clientRef");
            getPartnerNationalInsuranceNumber(pData, cliRef);
        }


        getNationalInsuranceNumber(pData, cliRef);

        if(!conn.equals(null)){
            conn.close();
            System.out.println("disconnected from mySQL Database");
        }
        return pData;
    }

    @Override
    /**
     *
     */
    public Map<String, String> getClientPersonalData(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url);

        System.out.println("Connected to MSSQL Database");
        System.out.println(name);

        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String[] names = name.split("/");

        String query = "SELECT * FROM Clients WHERE Surname = '";

        int cliRef = 0;

        ResultSet rs = st.executeQuery(query
                                       + names[0]
                                       + "'"
                                       + "AND Forenames ='"
                                       + names[1]
                                       + "';");

        Map<String,String> pData = new HashMap<String, String>();

        boolean hasMoreThanOneRow = rs.first() && rs.next();

        if(!hasMoreThanOneRow){
            System.out.println("No Dupes");
            rs.beforeFirst();
            while(rs.next()){
                System.out.println("No Dupes");
                pData.put("Title", rs.getString("Title"));
                //Removing the time portion of the date object returned by the database before adding it to the map

                String dob = null;
                if(rs.getString("DOB")!= null){
                    dob = rs.getString("DOB").substring(0,10);
                }
                pData.put("ForeNames", "ForeNames");
                pData.put("Surname", "Surname");
                pData.put("DOB", dob);
                pData.put("HomeTel", rs.getString("HomeTel"));
                pData.put("WorkTel", rs.getString("BusTel"));
                pData.put("Mobile", rs.getString("MobTel"));
                pData.put("HomeAddress1", rs.getString("HomeAddress1"));
                pData.put("HomeAddress2", rs.getString("HomeAddress2"));
                pData.put("HomeAddress3", rs.getString("HomeAddress3"));
                pData.put("HomeAddress4", rs.getString("HomeAddress4"));
                pData.put("HomeAddress5", rs.getString("HomeAddress5"));
                pData.put("HomePostCode", rs.getString("HomePostCode"));
                pData.put("Email", rs.getString("EmailAddress"));
                pData.put("PartnerFirstName", rs.getString("PartnerForenames"));
                pData.put("PartnerSurname", rs.getString("PartnerSurname"));
                cliRef = rs.getInt("clientRef");
                getNationalInsuranceNumber(pData, cliRef);
            }
        } else {
            System.out.println("Dupes");

            int i = 0;
            rs.beforeFirst();
            while(rs.next()){
                i++;
                System.out.println(rs.getString("HomePostCode"));
                pData.put("client" + i, rs.getString("HomePostCode"));
            }
        }

        if(!conn.equals(null)){
            conn.close();
            System.out.println("disconnected from mySQL Database");
        }
        return pData;
    }

    /**
     * Fetching the NINO was moved to a different method because it accesses a different table
     * therefore requires a different query execution
     * @param pData - The map containing all the autofill data
     * @param clientRef - The unique reference number of the client under scrutiny
     * @throws SQLException
     */
    private void getNationalInsuranceNumber(Map<String, String> pData, int clientRef) throws SQLException{
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT NINO FROM ClientExtraDetails WHERE ClientRef = " + clientRef);
        while(rs.next()){
            pData.put("NationalInsuranceNumber", rs.getString("NINO"));
        }
    }
    /**
     * Fetching the partner NINO was moved to a different method because it accesses a different table and field
     * therefore requires a different query execution
     * @param pData - The map containing all the autofill data
     * @param clientRef - The unique reference number of the client under scrutiny
     * @throws SQLException
     */
    private void getPartnerNationalInsuranceNumber(Map<String, String> pData, int clientRef) throws SQLException{
        Statement st = conn.createStatement();
        System.out.println(clientRef);
        ResultSet rs = st.executeQuery("SELECT PartnerNINO FROM ClientExtraDetails WHERE ClientRef = " + clientRef);
        System.out.println("Before while loop");
        while(rs.next()){
            pData.put("PartnerNationalInsuranceNumber", rs.getString("PartnerNINO"));
            System.out.println("in database: " + rs.getString("PartnerNINO"));
        }
        System.out.println("After while loop");
    }

    @Override
    /**
     * This method is the same as the one above except requires an additional argument and is only used in the event
     * of multiple clients having the same first name and the same surname
     */
    public Map<String, String> getClientPersonalData(String name, String postcode) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url);

        System.out.println("Connected to MSSQL Database");
        System.out.println(name);

        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String[] names = name.split("/");

        String query = "SELECT * FROM Clients WHERE Surname = '";

        int cliRef = 0;

        ResultSet rs = st.executeQuery(query
                                       + names[0]
                                       + "'"
                                       + "AND Forenames ='"
                                       + names[1]
                                       + "' AND HomePostCode ='"
                                       + postcode
                                       + "';");

        Map<String,String> pData = new HashMap<String, String>();
        rs.beforeFirst();

        while(rs.next()){
            System.out.println("No Dupes");
            pData.put("Title", rs.getString("Title"));
            //Removing the time portion of the date object returned by the database before adding it to the map

            String dob = null;
            if(rs.getString("DOB")!= null){
                dob = rs.getString("DOB").substring(0,10);
            }
            pData.put("ForeNames", "ForeNames");
            pData.put("Surname", "Surname");
            pData.put("DOB", dob);
            pData.put("HomeTel", rs.getString("HomeTel"));
            pData.put("WorkTel", rs.getString("BusTel"));
            pData.put("Mobile", rs.getString("MobTel"));
            pData.put("HomeAddress1", rs.getString("HomeAddress1"));
            pData.put("HomeAddress2", rs.getString("HomeAddress2"));
            pData.put("HomeAddress3", rs.getString("HomeAddress3"));
            pData.put("HomeAddress4", rs.getString("HomeAddress4"));
            pData.put("HomeAddress5", rs.getString("HomeAddress5"));
            pData.put("HomePostCode", rs.getString("HomePostCode"));
            pData.put("Email", rs.getString("EmailAddress"));
            pData.put("PartnerFirstName", rs.getString("PartnerForenames"));
            pData.put("PartnerSurname", rs.getString("PartnerSurname"));
            cliRef = rs.getInt("clientRef");
            getNationalInsuranceNumber(pData, cliRef);
        }

        if(conn != null){
            conn.close();
            System.out.println("disconnected from mySQL Database");
        }

	return pData;
    }
}
