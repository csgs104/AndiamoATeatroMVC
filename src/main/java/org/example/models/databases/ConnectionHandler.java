package org.example.models.databases;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

public class ConnectionHandler {

    private static final String DB_Url = "jdbc:";
    private static final Path PROPRERTIES_PATH = Paths.get("src","main","java","org.example","resources","postgresql.properties");
    private Connection connection;
    private Properties dbProp;

    public ConnectionHandler() throws IOException {
        try(BufferedReader bf = Files.newBufferedReader(PROPRERTIES_PATH)){
            dbProp = new Properties();
            dbProp.load(bf);
        }
    }

    private String getDbUrl(){
        return DB_Url +
                dbProp.getProperty("driver") + "://" +
                dbProp.getProperty("host") + ":" +
                dbProp.getProperty("port") + "/" +
                dbProp.getProperty("name");
    }

    private Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()){
            connection = DriverManager.getConnection(getDbUrl(),dbProp);
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        if(connection != null || !connection.isClosed()){
            connection.close();
            connection = null;
        }
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        Connection con =  getConnection();

        return con.prepareStatement(sql);

    }

}