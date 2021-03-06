package sepm.ws16.e1327450.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

public class H2DBHandler {

    final static Logger logger = LoggerFactory.getLogger(H2DBHandler.class);
    private static Connection connection = null;

    public static Connection getConnection() {
        if(connection == null) {
            logger.info("no database-connection established yet");
            try {
                logger.info("trying to connect to h2 driver");
                Class.forName("org.h2.Driver");
            } catch (Exception e) {
                logger.error("could not connect to h2 driver");
                return null;
            }
            logger.info("successfully connected to h2 driver");
            try {
                logger.info("trying to set up database-connection");
                connection = DriverManager.getConnection("jdbc:h2:~/database_ws16", "sa", "");
            } catch (Exception e) {
                logger.error("could not get database-connection");
                return null;
            }
            logger.info("succesfully set up database-connection. connection : " + connection);
        }
        return connection;
    }

    public static void closeConnection() {
        if(connection != null) {
            logger.debug("there is a database-connection");
            try {
                logger.debug("trying to close database-connection");
                connection.close();
                logger.debug("succesfully closed databse-connection");
            } catch (Exception e) {
                logger.debug("could not close database-connection");
            }
        }
    }

}
