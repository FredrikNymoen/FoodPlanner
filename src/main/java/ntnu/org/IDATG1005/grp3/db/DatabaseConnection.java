package ntnu.org.IDATG1005.grp3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileInputStream;
import java.io.IOException;

public class DatabaseConnection {

  private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());
  private static Connection connection = null;

  // private constructor instead of implicit
  private DatabaseConnection () {

  }

  public static Connection getConnection() {
    if (connection == null) {
      try (FileInputStream fis = new FileInputStream("src/main/resources/dbConfig.properties")) {
        Properties props = new Properties();
        props.load(fis);

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        connection = DriverManager.getConnection(url, user, password);
      } catch (IOException | SQLException e) {
        logger.log(Level.SEVERE, "Error connecting to database.\n"
            + "Check internet connection, VPN, and db properties", e);
      }
    }
    return connection;
  }
}