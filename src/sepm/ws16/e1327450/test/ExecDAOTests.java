package sepm.ws16.e1327450.test;

import sepm.ws16.e1327450.dao.H2DBHandler;

import java.sql.SQLException;

public class ExecDAOTests {

    public static void main(String[] args) {

        H2DBHandler dbHandler = new H2DBHandler();

        DAOImlJockeyTest daoJockeyTest = new DAOImlJockeyTest(dbHandler);
        DAOImlPferdTest daoPferdTest = new DAOImlPferdTest(dbHandler);
        DAOImlRennergebnisTest daoRennergebnisTest = new DAOImlRennergebnisTest(dbHandler);

        try {
            daoJockeyTest.setUp();
            daoJockeyTest.tearDown();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            daoPferdTest.setUp();
            daoPferdTest.tearDown();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            daoRennergebnisTest.setUp();
            daoRennergebnisTest.tearDown();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
