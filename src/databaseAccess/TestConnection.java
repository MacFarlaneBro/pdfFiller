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
        db.loadDriver();
    }
}

class DB extends Database{

	public void loadDriver() {
		try{
			//This attempts to load the jdbc-odbc bridge driver which should be present on sun Jdk implementations
			//Apparently ODBC is only present on windows systems so there you go
			//Not only that but ODBC is going to be completely removed in Java 8 which seems like an odd move for 
			//language famed for its purported backwards compatibility but there you go again.
//			Class c = Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//			System.out.println("Loaded " + c);
			
			//Try to load an Oracle driver
			Class d = Class.forName(msSqlDriver);
			System.out.println("Loaded " + d);
		} catch (ClassNotFoundException ex){
			System.err.println(ex);
		}
		
	}
	

};