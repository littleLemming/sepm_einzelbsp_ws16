package sepm.ws16.e1327450.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.After;
import org.junit.Before;
import sepm.ws16.e1327450.dao.DAOJockey;
import sepm.ws16.e1327450.dao.DOAImlJockey;
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
            daoJockey = new DOAImlJockey(dbHandler.getConnection());
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        setDAOJockey(daoJockey);
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
            isFreeSvnrTest();
            getFreeSvnrTest();
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
