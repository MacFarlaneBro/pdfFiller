package databaseAccess;

import java.sql.*;

public class TestConnection{
    public static void main(String[] args){
        // Neue DB und los geht's :)
        DB db = new DB();
        db.dbConnect("jdbc:sqlserver://localhost:1433/muff", "user", "pw" );
    }
}

class DB{
    public void dbConnect(  String db_connect_string, 
                            String db_userid, 
                            String db_password){
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
        }
    }
};