package se.david.EmployeeDB.tools;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class JDBCUtil {



    //Properties används för att läsa in databas-konfiguration från filen
//application.properties
//Vi har nu två sådana filer - en för applikationen och en för test

    private static Properties properties = new Properties();

    static {
        try (InputStream input = JDBCUtil.class.getClassLoader().
                getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("Unable to find application.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Failed to load database properties");
        }
    }


    public static Connection getConnection() throws SQLException {

//skapa upp en instans av hsql:s jdbcDriver-klass
        Driver hsqlDriver = new org.hsqldb.jdbcDriver();

//registrera drivern hos klassen DriverManager
        DriverManager.registerDriver(hsqlDriver);

        String dbURL = properties.getProperty("db.url");
        System.out.println("Connecting to database: " + dbURL);
        String user = properties.getProperty("db.user");
        System.out.println("Database user: " + user);
        String password = properties.getProperty("db.password");
        System.out.println("Database password: " + password);

//Använd metoden getConnection i DriverManager för att få en anslutning till databasen
        Connection conn = DriverManager.getConnection(dbURL, user, password);

        boolean connectionOk = conn != null;
        if (connectionOk) {
            System.out.println("Database connection ok");
        }

//Sätt autoCommit till false
        conn.setAutoCommit(false);

//returnera anslutningen
        return conn;
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeStatement(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closePreparedStatement(PreparedStatement pstmt) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit(Connection conn) {
        try {
            if (conn != null) {
                conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection conn) {
        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getDatabaseProductName(Connection conn) {

        try {
            // Hämta databasmetadata
            DatabaseMetaData metadata = conn.getMetaData();

            // Hämta och returnera databasens produktnamn
            return metadata.getDatabaseProductName();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
