package sepm.ws16.e1327450.test;

import sepm.ws16.e1327450.dao.H2DBHandler;

import java.sql.SQLException;

public class ExecServiceTests {

    public static void main(String[] args) {

        H2DBHandler dbHandler = new H2DBHandler();

        ImlServiceTest serviceTest = new ImlServiceTest(dbHandler);

        try {
            serviceTest.setUp();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            serviceTest.setUp();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
