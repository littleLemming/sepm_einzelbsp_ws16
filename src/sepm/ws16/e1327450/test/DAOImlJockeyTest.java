package sepm.ws16.e1327450.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.After;
import org.junit.Before;
import sepm.ws16.e1327450.dao.DAOImlJockey;
import sepm.ws16.e1327450.dao.DAOJockey;
import sepm.ws16.e1327450.dao.H2DBHandler;
import sepm.ws16.e1327450.dao.PersistenceException;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOImlJockeyTest extends AbstractDAOImlJockeyTest {

    final static Logger logger = LoggerFactory.getLogger(DAOImlJockeyTest.class);

    private H2DBHandler dbHandler;

    public DAOImlJockeyTest(H2DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    @Before
    public void setUp() throws SQLException {
        Connection connection = dbHandler.getConnection();
        logger.info("setUp(), connection: " + connection);
        DAOJockey daoJockey = null;
        try {
            daoJockey = new DAOImlJockey(dbHandler.getConnection());
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        setDAOJockey(daoJockey);
        dbHandler.getConnection().setAutoCommit(false);
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
            isFreeSvnrTest();
            tearDown();
        } catch (PersistenceException e) {
            logger.error("isFreeSvnrTest() FAILED");
            e.printStackTrace();
        }
        try {
            getFreeSvnrTest();
            tearDown();
        } catch (PersistenceException e) {
            logger.error("getFreeSvnrTest() FAILED");
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws SQLException {
        logger.info("tearDown()");
        dbHandler.getConnection().rollback();
    }

}
