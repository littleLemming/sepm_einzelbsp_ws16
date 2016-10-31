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
        } catch (ServiceException e) {
            logger.error("saveTest() FAILED");
            e.printStackTrace();
        } try {
            valid_feedbackTest();
        } catch (ServiceException e) {
            logger.error("valid_feedbackTest() FAILED");
            e.printStackTrace();
        } try {
            doStatistikTest();
        } catch (ServiceException e) {
            logger.error("doStatistikTest() FAILED");
            e.printStackTrace();
        } try {
            doRennen_jockey_twice_Test();
        } catch (ServiceException e) {
            logger.error("doRennen_jockey_twice_Test() FAILED");
            e.printStackTrace();
        } try {
            doRennen_invalid_jockeys_Test();
        } catch (ServiceException e) {
            logger.error("doRennen_invalid_jockeys_Test() FAILED");
            e.printStackTrace();
        } try {
            doRennen_invalid_pferd_Test();
        } catch (ServiceException e) {
            logger.error("doRennen_invalid_pferd_Test() FAILED");
            e.printStackTrace();
        } try {
            doRennenTest();
        } catch (ServiceException e) {
            logger.error("doRennenTest() FAILED");
            e.printStackTrace();
        }
    }

}
