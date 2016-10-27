package sepm.ws16.e1327450.test;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.dao.*;
import sepm.ws16.e1327450.service.ImlService;
import sepm.ws16.e1327450.service.Service;
import sepm.ws16.e1327450.service.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;

public class ImlServiceTest extends AbstractImlServiceTest {

    final static Logger logger = LoggerFactory.getLogger(ImlServiceTest.class);

    private H2DBHandler dbHandler;

    public ImlServiceTest(H2DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    @Before
    public void setUp() throws SQLException {
        Connection connection = dbHandler.getConnection();
        logger.info("setUp(), connection: " + connection);
        DAOPferd daoPferd = null;
        DAORennergebnis daoRennergebnis = null;
        DAOJockey daoJockey = null;
        try {
            daoPferd = new DAOImlPferd(connection);
            daoJockey = new DAOImlJockey(connection);
            daoRennergebnis = new DAOImlRennergebnis(connection,daoPferd,daoJockey);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        Service service = null;
        service = new ImlService(daoPferd,daoRennergebnis,daoJockey);
        setService(service);
        try {
            saveTest();
            tearDown();
        } catch (ServiceException e) {
            logger.error("saveTest() FAILED");
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws SQLException {
        logger.info("tearDown()");
        dbHandler.getConnection().rollback();
    }

}
