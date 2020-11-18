package ca.ubc.cs304.database;

import ca.ubc.cs304.exceptions.ServerErrorException;

import java.sql.Connection;

public class Controller {
    public static final String DB_USERNAME = "ora_eason123";
    public static final String DB_PASSWORD = "a69398386";
    public static final String EXCEPTION_TAG = "[EXCEPTION]";
    public static final String WARNING_TAG = "[WARNING]";
    public String serverErrorMsg = "Server Error";

    public Connection connection;
    private DatabaseConnectionHandler db;

    public Controller(DatabaseConnectionHandler db) throws ServerErrorException {
        this.db =db;
        connection = db.getConnection();
    }

    public void rollbackConnection(){
        db.rollbackConnection();
    }

}
