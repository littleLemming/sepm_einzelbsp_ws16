package sepm.ws16.e1327450.test;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.dao.DAOImlPferd;
import sepm.ws16.e1327450.dao.DAOPferd;
import sepm.ws16.e1327450.dao.H2DBHandler;
import sepm.ws16.e1327450.dao.PersistenceException;
import java.sql.Connection;
import java.sql.SQLException;

public class DAOImlPferdTest extends AbstractDAOImlPferdTest {

    final static Logger logger = LoggerFactory.getLogger(DAOImlPferdTest.class);

    private H2DBHandler dbHandler;

    public DAOImlPferdTest(H2DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    @Before
    public void setUp() throws SQLException {
        Connection connection = dbHandler.getConnection();
        logger.info("setUp(), connection: " + connection);
        DAOPferd daoPferd = null;
        try {
            daoPferd = new DAOImlPferd(dbHandler.getConnection());
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        setDAOPferd(daoPferd);
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
            isFreeChip_NrTest();
            getFreeChip_NrTest();
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
