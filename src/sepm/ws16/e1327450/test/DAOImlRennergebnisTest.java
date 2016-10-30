package sepm.ws16.e1327450.test;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.dao.*;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOImlRennergebnisTest extends AbstractDAOImlRennergebnisTest {

    final static Logger logger = LoggerFactory.getLogger(DAOImlRennergebnisTest.class);

    private H2DBHandler dbHandler;

    public DAOImlRennergebnisTest(H2DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    @Before
    public void setUp() throws SQLException {
        Connection connection = dbHandler.getConnection();
        logger.info("setUp(), connection: " + connection);
        DAORennergebnis daoRennergebnis = null;
        DAOPferd daoPferd = null;
        DAOJockey daoJockey = null;
        try {
            daoPferd = new DAOImlPferd(connection);
            daoJockey = new DAOImlJockey(connection);
            daoRennergebnis = new DAOImlRennergebnis(connection,daoPferd,daoJockey);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        setDAORennergebnis(daoRennergebnis);
        setDaoPferd(daoPferd);
        setDaoJockey(daoJockey);
        if(connection != null) connection.setAutoCommit(false);
        try {
            loadWithValid();
            tearDown();
        } catch (PersistenceException e) {
            logger.error("loadWithValid() FAILED");
            e.printStackTrace();
        }
        try {
            loadWithNonExisting();
            tearDown();
        } catch (PersistenceException e) {
            logger.error("loadWithNonExisting() FAILED");
            e.printStackTrace();
        }
        try {
            loadAllTest();
            tearDown();
        } catch (PersistenceException e) {
            logger.error("loadAllTest() FAILED");
            e.printStackTrace();
        }
        try {
            loadConditionTest();
            tearDown();
        } catch (PersistenceException e) {
            logger.error("loadConditionTest() FAILED");
            e.printStackTrace();
        }
        try {
            createWithNull();
            tearDown();
        } catch (PersistenceException e) {
            logger.error("createWithNull() FAILED");
            e.printStackTrace();
        }
        try {
            createWithValid();
            tearDown();
        } catch (PersistenceException e) {
            logger.error("createWithValid() FAILED");
            e.printStackTrace();
        }
        try {
            createWithExistingId();
            tearDown();
        } catch (PersistenceException e) {
            logger.error("createWithExistingId() FAILED");
            e.printStackTrace();
        }
        try {
            deleteTest();
            tearDown();
        } catch (PersistenceException e) {
            logger.error("deleteTest() FAILED");
            e.printStackTrace();
        }
        try {
            deleteNonExisting();
            tearDown();
        } catch (PersistenceException e) {
            logger.error("deleteNonExisting() FAILED");
            e.printStackTrace();
        }
        try {
            updateTest();
            tearDown();
        } catch (PersistenceException e) {
            logger.error("updateTest() FAILED");
            e.printStackTrace();
        }
        try {
            isFreeRenn_idTest();
            tearDown();
        } catch (PersistenceException e) {
            logger.error("isFreeRenn_idTest() FAILED");
            e.printStackTrace();
        }
        try {
            getFreeRenn_idTest();
            tearDown();
        } catch (PersistenceException e) {
            logger.error("getFreeRenn_idTest() FAILED");
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws SQLException {
        logger.info("tearDown()");
        if(dbHandler.getConnection() != null) dbHandler.getConnection().rollback();
    }

}
