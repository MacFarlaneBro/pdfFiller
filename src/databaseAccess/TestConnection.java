package databaseAccess;

import java.sql.*;

public class TestConnection{
    public static void main(String[] args) throws SQLException{
        // 
        DB db = new DB();
        /*
         * Below is the connection String for the sqlserver, I'm going to start by specifying the format.
         * jdbc:sqlserver:// - this is a required string known as the sub-protocol
         * [servername] - The address of the server to connect to, either a DNS or an IP address or localhost
         * if the server is hosted on the machine running the software, If this is not specified then the server
         * name must be specified in the properties collection
         * [instanceName] - The instance to connect to on servername, if not specified then the default is made
         * [portNumber] the port on servername to connect to, if not specified then default 1433 is used
         * [property]one or more optional connection properties
         */
        db.dbConnect("jdbc:sqlserver://localhost:1433", "charlieb", "brodiec");
    }
}

class DB{
	
	Connection conn;
	
    public void dbConnect(  String db_connect_string, 
                            String db_userid, 
                            String db_password) throws SQLException{
        try{
        Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
            Connection conn = DriverManager.getConnection(
                            db_connect_string, 
                        db_userid, 
                        db_password);
            System.out.println( "connected" );
        }
        catch( Exception e ){
            e.printStackTrace();
        } finally {
        	conn.close();
        }
    }
};