package crawler;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents Database class for db operations.
 *
 * @author Mohammad Rahman
 *
 * @version 1
 * @since 1
 */
public class Database {

    private static Connection conn = null;
    private static Statement stmt = null;

    //get the logger ready
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * gets the program connect to database
     */
    private static void connect() {
        LOGGER.setLevel(Level.INFO);
        try {
            // db parameters

            String url = "jdbc:sqlite:D:/NetBeansProjects/Crawler/products.db";
            // create a connection to the database
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            LOGGER.info("Connected to sqlite");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * gets the program close db connection
     */
    private static void close() {
        try {
            if (conn != null) {
                conn.close();
                LOGGER.info("Connection closed");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * gets the create database table
     */
    private static void createTable() {

        try {
            connect();
            stmt = conn.createStatement();

            //default product table
            String sql = "CREATE TABLE IF NOT EXISTS PRODUCT "
                    + "(ID INTEGER PRIMARY KEY   AUTOINCREMENT  NOT NULL,"
                    + " NAME          TEXT    NOT NULL, "
                    + " PRICE            DOUBLE     NOT NULL, "
                    + " DESCRIPTION        TEXT, "
                    + " Style         TEXT, "
                    + " Material         TEXT,"
                    + " Pattern         TEXT, "
                    + " Climate         TEXT,"
                    + " URL          TEXT UNIQUE   NOT NULL)";
            stmt.executeUpdate(sql);
            LOGGER.info("Database table created");
            stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            LOGGER.warning("Failed to create database table");
            System.exit(0);
        }

    }

    /**
     * gets the program add record to database
     *
     * @param list
     */
    public static void insert(HashMap<String, String> list) {

        Statement stmt = null;

        try {
            createTable();

            String sql = "INSERT OR REPLACE INTO PRODUCT(NAME,PRICE, DESCRIPTION, STYLE,MATERIAL,PATTERN,CLIMATE, URL) VALUES(?,?,?,?,?,?,?,?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, list.get("Name"));
                pstmt.setString(2, list.get("Description"));
                pstmt.setString(3, list.get("Price"));
                pstmt.setString(4, list.get("Style"));
                pstmt.setString(5, list.get("Material"));
                pstmt.setString(6, list.get("Pattern"));
                pstmt.setString(7, list.get("Climate"));
                pstmt.setString(8, list.get("URL"));
                pstmt.executeUpdate();
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Inserted to database!");
                    LOGGER.info("Inserted to database");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                LOGGER.warning("Failed to insert data in database");
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        close();
    }

    /**
     * gets the program read database table
     *
     * @return List
     */
    public static List<String> readTable() {

        connect();
        List<String> productInfo = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCT";

        try {
            // loop through the result set  
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                productInfo.add(rs.getString("NAME"));
                productInfo.add(rs.getString("DESCRIPTION"));
                productInfo.add(rs.getString("PRICE"));
                productInfo.add(rs.getString("STYLE"));
                productInfo.add(rs.getString("MATERIAL"));
                productInfo.add(rs.getString("PATTERN"));
                productInfo.add(rs.getString("CLIMATE"));
                productInfo.add(rs.getString("URL"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            LOGGER.warning("No such table exists. Please crawl the site first");
            JOptionPane.showMessageDialog(null, "No such table exists. Please crawl the site first");

        }
        return productInfo;

    }

}
