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
        } catch (PersistenceException e) {
            logger.error("loadWithValid() FAILED");
            e.printStackTrace();
        }
        try {
            loadWithNonExisting();
        } catch (PersistenceException e) {
            logger.error("loadWithNonExisting() FAILED");
            e.printStackTrace();
        }
        try {
            loadAllTest();
        } catch (PersistenceException e) {
            logger.error("loadAllTest() FAILED");
            e.printStackTrace();
        }
        try {
            loadConditionTest();
        } catch (PersistenceException e) {
            logger.error("loadConditionTest() FAILED");
            e.printStackTrace();
        }
        try {
            createWithNullShouldThrowException();
        } catch (PersistenceException e) {
            logger.error("createWithNullShouldThrowException() FAILED");
            e.printStackTrace();
        }
        try {
            createWithValid();
        } catch (PersistenceException e) {
            logger.error("createWithValid() FAILED");
            e.printStackTrace();
        }
        try {
            createWithExistingId();
        } catch (PersistenceException e) {
            logger.error("createWithExistingId() FAILED");
            e.printStackTrace();
        }
        try {
            deleteTest();
        } catch (PersistenceException e) {
            logger.error("deleteTest() FAILED");
            e.printStackTrace();
        }
        try {
            deleteNonExisting();
        } catch (PersistenceException e) {
            logger.error("deleteNonExisting() FAILED");
            e.printStackTrace();
        }
        try {
            updateTest();
        } catch (PersistenceException e) {
            logger.error("updateTest() FAILED");
            e.printStackTrace();
        }
        try {
            isFreeRenn_idTest();
        } catch (PersistenceException e) {
            logger.error("isFreeRenn_idTest() FAILED");
            e.printStackTrace();
        }
        try {
            getFreeRenn_idTest();
        } catch (PersistenceException e) {
            logger.error("getFreeRenn_idTest() FAILED");
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws SQLException {
        logger.info("tearDown()");
        dbHandler.getConnection().rollback();
    }

}
