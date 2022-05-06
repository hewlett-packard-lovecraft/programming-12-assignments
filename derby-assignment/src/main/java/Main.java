import java.sql.*;

public class Main {
    public static Connection connect(String driver, String dbname, String connectionURL) throws SQLException {
        Connection conn = DriverManager.getConnection(connectionURL);
        return conn;
    }

    public static void main(String[] args) {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        String dbName="jdbcDemoDB";
        String connectionURL = "jdbc:derby:" + dbName + ";create=true";
        String createString = "CREATE TABLE WISH_LIST "
                + "(WISH_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY "
                + " WISH_ITEM VARCHAR(32) NOT NULL) ";

        try {
            connect()
        }
    }
}
