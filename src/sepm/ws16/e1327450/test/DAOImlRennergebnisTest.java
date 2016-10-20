package sepm.ws16.e1327450.test;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.dao.DAOImlRennergebnis;
import sepm.ws16.e1327450.dao.DAORennergebnis;
import sepm.ws16.e1327450.dao.H2DBHandler;
import sepm.ws16.e1327450.dao.PersistenceException;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOImlRennergebnisTest extends AbstractDAOImlRennergebnisTest {

    final static Logger logger = LoggerFactory.getLogger(AbstractDAOImlJockeyTest.class);

    private H2DBHandler dbHandler;

    public DAOImlRennergebnisTest(H2DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    @Before
    public void setUp() throws SQLException {
        Connection connection = dbHandler.getConnection();
        logger.info("setUp(), connection: " + connection);
        DAORennergebnis daoRennergebnis = null;
        try {
            daoRennergebnis = new DAOImlRennergebnis(dbHandler.getConnection());
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        setDAORennergebnis(daoRennergebnis);
        dbHandler.getConnection().setAutoCommit(false);
        try {
            loadWithValid();
            loadWithNonExisting();
            loadAllTest();
            loadConditionTest();
            createWithNullShouldThrowException();
            createWithValid();
            createWithExistingId();
            deleteTest();
            deleteNonExisting();
            updateTest();
            isFreeRenn_idTest();
            getFreeRenn_idTest();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws SQLException {
        logger.info("tearDown()");
        dbHandler.getConnection().rollback();
    }

}
