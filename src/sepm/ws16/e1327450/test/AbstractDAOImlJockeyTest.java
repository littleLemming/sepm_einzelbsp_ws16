package sepm.ws16.e1327450.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.dao.DAOJockey;
import sepm.ws16.e1327450.dao.PersistenceException;
import sepm.ws16.e1327450.domain.Jockey;
import sepm.ws16.e1327450.domain.JockeyCondition;
import sepm.ws16.e1327450.domain.JockeyID;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class AbstractDAOImlJockeyTest {

    final static Logger logger = LoggerFactory.getLogger(AbstractDAOImlJockeyTest.class);
    
    protected DAOJockey daoJockey;

    protected void setDAOJockey(DAOJockey daoJockey) {
        this.daoJockey = daoJockey;
    }

    /** load valid svnr **/
    @Test
    public void loadWithValid() throws PersistenceException {
        logger.info("loadWithValid()");
        Jockey jockey = new Jockey(0,58.0,"Pinkie Pie",java.sql.Date.valueOf("2004-08-12"),47);
        JockeyID jid = new JockeyID(0);
        Jockey loadedJockey = daoJockey.load(jid);
        assertTrue(jockey.equals(loadedJockey));
    }

    /** load valid non-existing svnr **/
    @Test
    public void loadWithNonExisting() throws PersistenceException {
        logger.info("loadWithNonExisting()");
        Jockey loadedJockey = daoJockey.load(new JockeyID(20));
        assertTrue(loadedJockey == null);
    }

    /** load all **/
    @Test
    public void loadAllTest() throws PersistenceException {
        logger.info("loadAllTest()");
        List<Jockey> jockeyList = new ArrayList<>();
        jockeyList.add(new Jockey(0,58.0,"Pinkie Pie",java.sql.Date.valueOf("2004-08-12"),47));
        jockeyList.add(new Jockey(1,29.0,"Twilight Sparkle",java.sql.Date.valueOf("2003-06-07"),50));
        jockeyList.add(new Jockey(2,301.0,"Rainbow Dash",java.sql.Date.valueOf("2003-05-07"),40));
        jockeyList.add(new Jockey(3,30.0,"Fluttershy",java.sql.Date.valueOf("2004-01-04"),39));
        jockeyList.add(new Jockey(4,48.0,"Rarity",java.sql.Date.valueOf("2003-10-11"),39));
        jockeyList.add(new Jockey(5,287.0,"Princess Celestia",java.sql.Date.valueOf("1987-09-08"),61));
        jockeyList.add(new Jockey(6,201.0,"Princess Luna",java.sql.Date.valueOf("1990-01-04"),59));
        List<Jockey> jockeyListLoaded = daoJockey.loadAll();
        for (Jockey jockey : jockeyListLoaded) {
            logger.info(jockey.toString());
        }
        for(Jockey jockey : jockeyList) {
            assertTrue(jockeyListLoaded.contains(jockey));
        }
    }

    /** load conditions **/
    @Test
    public void loadConditionTest() throws PersistenceException {
        logger.info("loadConditionTest()");
        List<Jockey> jockeyList = new ArrayList<>();
        Jockey jockey0 = new Jockey(0,58.0,"Pinkie Pie",java.sql.Date.valueOf("2004-08-12"),47);
        Jockey jockey1 = new Jockey(1,29.0,"Twilight Sparkle",java.sql.Date.valueOf("2003-06-07"),50);
        Jockey jockey2 = new Jockey(2,301.0,"Rainbow Dash",java.sql.Date.valueOf("2003-05-07"),40);
        Jockey jockey3 = new Jockey(3,30.0,"Fluttershy",java.sql.Date.valueOf("2004-01-04"),39);
        Jockey jockey4 = new Jockey(4,48.0,"Rarity",java.sql.Date.valueOf("2003-10-11"),39);
        Jockey jockey5 = new Jockey(5,287.0,"Princess Celestia",java.sql.Date.valueOf("1987-09-08"),61);
        Jockey jockey6 = new Jockey(6,201.0,"Princess Luna",java.sql.Date.valueOf("1990-01-04"),59);
        List<Jockey> jockeyListLoaded = daoJockey.loadCondition(new JockeyCondition(-1,-1,null,null,-1,-1));
        jockeyList = daoJockey.loadAll();
        assertTrue(jockeyList.size() == jockeyListLoaded.size());
        for(Jockey jockey : jockeyList) {
            assertTrue(jockeyListLoaded.contains(jockey));
        }
        jockeyListLoaded = daoJockey.loadCondition(new JockeyCondition(50.0,100.0,null,null,-1,-1));
        jockeyList = new ArrayList<>();
        jockeyList.add(jockey0);
        assertTrue(jockeyList.size() == jockeyListLoaded.size());
        for(Jockey jockey : jockeyList) {
            assertTrue(jockeyListLoaded.contains(jockey));
        }
        jockeyListLoaded = daoJockey.loadCondition(new JockeyCondition(-1,-1,"Princess Luna",null,-1,-1));
        jockeyList = new ArrayList<>();
        jockeyList.add(jockey6);
        assertTrue(jockeyList.size() == jockeyListLoaded.size());
        for(Jockey jockey : jockeyList) {
            assertTrue(jockeyListLoaded.contains(jockey));
        }
        jockeyListLoaded = daoJockey.loadCondition(new JockeyCondition(-1,-1,null,java.sql.Date.valueOf("2003-06-07"),-1,-1));
        jockeyList = new ArrayList<>();
        jockeyList.add(jockey1);
        assertTrue(jockeyList.size() == jockeyListLoaded.size());
        for(Jockey jockey : jockeyList) {
            assertTrue(jockeyListLoaded.contains(jockey));
        }
        jockeyListLoaded = daoJockey.loadCondition(new JockeyCondition(-1,-1,null,null,40,60));
        jockeyList = new ArrayList<>();
        jockeyList.add(jockey0);
        jockeyList.add(jockey1);
        jockeyList.add(jockey2);
        jockeyList.add(jockey6);
        logger.info(jockeyList.size() + "     " + jockeyListLoaded.size());
        assertTrue(jockeyList.size() == jockeyListLoaded.size());
        for(Jockey jockey : jockeyList) {
            assertTrue(jockeyListLoaded.contains(jockey));
        }
    }

    /** save with null-value **/
    @Test(expected = IllegalArgumentException.class)
    public void createWithNull() throws PersistenceException {
        logger.info("createWithNull()");
        daoJockey.save(null);
    }

    /** save with valid parameters **/
    @Test
    public void createWithValid() throws PersistenceException {
        logger.info("createWithValid()");
        Jockey jockey = new Jockey(7,46.2,"Applejack",java.sql.Date.valueOf("2005-07-04"),60);
        List<Jockey> jockeyList = daoJockey.loadAll();
        assertFalse(jockeyList.contains(jockey));
        daoJockey.save(jockey);
        jockeyList = daoJockey.loadAll();
        assertTrue(jockeyList.contains(jockey));
    }

    /** save with existing svnr **/
    @Test
    public void createWithExistingId() throws PersistenceException {
        logger.info("createWithExistingId()");
        Jockey jockey = new Jockey(6,46.2,"Applejack",java.sql.Date.valueOf("2005-07-04"),60);
        List<Jockey> jockeyList = daoJockey.loadAll();
        assertFalse(jockeyList.contains(jockey));
        daoJockey.save(jockey);
        jockeyList = daoJockey.loadAll();
        assertFalse(jockeyList.contains(jockey));
    }

    /** save and delete **/
    @Test
    public void deleteTest() throws PersistenceException {
        logger.info("deleteTest()");
        Jockey jockey = new Jockey(7,46.2,"Applejack",java.sql.Date.valueOf("2005-07-04"),60);
        List<Jockey> jockeyList = daoJockey.loadAll();
        assertFalse(jockeyList.contains(jockey));
        daoJockey.save(jockey);
        jockeyList = daoJockey.loadAll();
        assertTrue(jockeyList.contains(jockey));
        daoJockey.delete(jockey);
        jockeyList = daoJockey.loadAll();
        assertFalse(jockeyList.contains(jockey));
    }

    /** delete with non-existing svnr **/
    @Test
    public void deleteNonExisting() throws PersistenceException {
        logger.info("deleteNonExisting()");
        Jockey jockey = new Jockey(7,46.2,"Applejack",java.sql.Date.valueOf("2005-07-04"),60);
        List<Jockey> jockeyList = daoJockey.loadAll();
        assertFalse(jockeyList.contains(jockey));
        daoJockey.delete(jockey);
        jockeyList = daoJockey.loadAll();
        assertFalse(jockeyList.contains(jockey));
    }

    /** save and then update **/
    @Test
    public void updateTest() throws PersistenceException {
        logger.info("updateTest()");
        Jockey jockey = new Jockey(7,46.2,"Applejack",java.sql.Date.valueOf("2005-07-04"),60);
        List<Jockey> jockeyList = daoJockey.loadAll();
        assertFalse(jockeyList.contains(jockey));
        daoJockey.save(jockey);
        jockeyList = daoJockey.loadAll();
        assertTrue(jockeyList.contains(jockey));
        Jockey updatedJockey = new Jockey(7,55.7,"Applejack",java.sql.Date.valueOf("2005-07-04"),59);
        jockeyList = daoJockey.loadAll();
        assertFalse(jockeyList.contains(updatedJockey));
        daoJockey.update(updatedJockey);
        jockeyList = daoJockey.loadAll();
        assertFalse(jockeyList.contains(jockey));
        assertTrue(jockeyList.contains(updatedJockey));
        daoJockey.delete(updatedJockey);
    }

    /** test for free svnr **/
    @Test
    public void isFreeSvnrTest() throws PersistenceException {
        logger.info("isFreeChip_NrTest()");
        assertTrue(daoJockey.isFreeSvnr(new JockeyID(33)));
        assertFalse(daoJockey.isFreeSvnr(new JockeyID(2)));
    }

    /** test for free svnr **/
    @Test
    public void getFreeSvnrTest() throws PersistenceException {
        logger.info("getFreeChip_NrTest()");
        List<Integer> svnrList = new ArrayList<>();
        for(Jockey jockey : daoJockey.loadAll()) {
            svnrList.add(jockey.getSvnr());
        }
        Integer svnrLoaded = daoJockey.getFreeSvnr().getSvnr();
        assertFalse(svnrList.contains(svnrLoaded));
    }
    
}
