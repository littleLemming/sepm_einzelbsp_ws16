package sepm.ws16.e1327450.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.dao.PersistenceException;
import sepm.ws16.e1327450.domain.Jockey;
import sepm.ws16.e1327450.domain.Pferd;
import sepm.ws16.e1327450.service.Service;
import sepm.ws16.e1327450.service.ServiceException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AbstractImlServiceTest {

    final static Logger logger = LoggerFactory.getLogger(AbstractImlServiceTest.class);

    protected Service service;

    public void setService(Service service) {
        this.service = service;
    }

    /** save **/
    @Test
    public void saveTest() throws ServiceException {
        logger.info("saveTest()");
        service.saveJockey(null);
        Jockey jockey0 = new Jockey(20,58,"Pinkie Pie",java.sql.Date.valueOf("2004-08-12"),47);
        Jockey jockey1 = new Jockey(25,58,"Pinkie Pie",java.sql.Date.valueOf("2004-08-12"),-2);
        Jockey jockey2 = new Jockey(0,58,"Pinkie Pie",java.sql.Date.valueOf("2004-08-12"),8);
        assertFalse(service.loadAllJockey().contains(jockey0));
        service.saveJockey(jockey0);
        assertTrue(service.loadAllJockey().contains(jockey0));
        assertFalse(service.loadAllJockey().contains(jockey1));
        service.saveJockey(jockey1);
        assertFalse(service.loadAllJockey().contains(jockey1));
        assertFalse(service.loadAllJockey().contains(jockey2));
        service.saveJockey(jockey2);
        assertFalse(service.loadAllJockey().contains(jockey2));
        service.savePferd(null);
        Pferd pferd0 = new Pferd("1000","Philidor","Trakhner",22,"0000_philidor_1.jpg",53,60);
        Pferd pferd1 = new Pferd("1000","Philidor","Trakhner",22,"0000_philidor_1.jpg",30,60);
        Pferd pferd2 = new Pferd("1000","Philidor","Trakhner",22,"0000_philidor_1.jpg",40,61);
        Pferd pferd3 = new Pferd("1000","Philidor","Trakhner",22,"0000_philidor_1.jpg",50,49);
        Pferd pferd4 = new Pferd("1000","Philidor","Trakhner",3,"0000_philidor_1.jpg",50,49);
        assertFalse(service.loadAllPferd().contains(pferd0));
        service.savePferd(pferd0);
        assertTrue(service.loadAllPferd().contains(pferd0));
        assertFalse(service.loadAllPferd().contains(pferd1));
        service.savePferd(pferd1);
        assertFalse(service.loadAllPferd().contains(pferd1));
        assertFalse(service.loadAllPferd().contains(pferd2));
        service.savePferd(pferd2);
        assertFalse(service.loadAllPferd().contains(pferd2));
        assertFalse(service.loadAllPferd().contains(pferd3));
        service.savePferd(pferd3);
        assertFalse(service.loadAllPferd().contains(pferd3));
        assertFalse(service.loadAllPferd().contains(pferd4));
        service.savePferd(pferd4);
        assertFalse(service.loadAllPferd().contains(pferd4));
    }

}
