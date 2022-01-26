package kazantsev.database;

import kazantsev.controller.LibraryServlet;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSourse {

    private static final Logger log = Logger.getLogger(LibraryServlet.class);
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/myproject";
    private static final String USER = "root";
    private static final String PASS = "1234";
    private static final ConnectionSourse instance = new ConnectionSourse();
    public static ConnectionSourse instance() {
        return instance;
    }
    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private ConnectionSourse() {

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            throw new RuntimeException(e);
        }
    }
}
