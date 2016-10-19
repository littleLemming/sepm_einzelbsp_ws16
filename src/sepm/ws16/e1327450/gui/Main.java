package sepm.ws16.e1327450.gui;

import sepm.ws16.e1327450.dao.H2DBHandler;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        H2DBHandler dbHandler = new H2DBHandler();

        dbHandler.getConnection();
        dbHandler.closeConnection();

    }

}
