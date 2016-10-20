package sepm.ws16.e1327450.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.dao.DAORennergebnis;
import sepm.ws16.e1327450.dao.PersistenceException;
import sepm.ws16.e1327450.domain.Jockey;
import sepm.ws16.e1327450.domain.Pferd;
import sepm.ws16.e1327450.domain.Rennergebnis;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class AbstractDAOImlRennergebnisTest {

    final static Logger logger = LoggerFactory.getLogger(AbstractDAOImlRennergebnisTest.class);

    protected DAORennergebnis daoRennergebnis;

    protected void setDAORennergebnis(DAORennergebnis daoRennergebnis) {
        this.daoRennergebnis = daoRennergebnis;
    }

    /** load valid renn-id **/
    @Test
    public void loadWithValid() throws PersistenceException {
        logger.info("createWithValid()");
        Rennergebnis rennergebnis = new Rennergebnis(0,new Pferd("0000","Philidor","Trakhner",22,"0000_philidor_1.jpg",53,60),new Jockey(1,29,"Twilight Sparkle",java.sql.Date.valueOf("2003-06-07"),50),48.968,2);
        Rennergebnis loadedRennergebnis = daoRennergebnis.load(0,"0000",1);
        assertTrue(rennergebnis.equals(loadedRennergebnis));
    }

    /** load valid non-existing renn-id, wrong combination of parameters **/
    @Test
    public void loadWithNonExisting() throws PersistenceException {
        logger.info("loadWithNonExisting()");
        Rennergebnis loadedRennergebnis = daoRennergebnis.load(20,"0000",1);
        assertTrue(loadedRennergebnis == null);
        loadedRennergebnis = daoRennergebnis.load(0,"0001",1);
        assertTrue(loadedRennergebnis == null);
    }

    /** load all **/
    @Test
    public void loadAllTest() throws PersistenceException {
        logger.info("loadAllTest()");
        List<Rennergebnis> rennergebnisList = new ArrayList<>();
        rennergebnisList.add(new Rennergebnis(0,new Pferd("0001","Rusty","Shetland Pony",16,"0001_rusty_0.jpg",40,46),new Jockey(1,29,"Twilight Sparkle",java.sql.Date.valueOf("2003-06-07"),50),48.968,2));
        rennergebnisList.add(new Rennergebnis(0,new Pferd("0005","Benji","Haflinger-Noriker",15,"0005_benji_1.jpg",40,44),new Jockey(0,58,"Pinkie Pie",java.sql.Date.valueOf("2004-08-12"),47),46.969,3));
        rennergebnisList.add(new Rennergebnis(0,new Pferd("0000","Philidor","Trakhner",22,"0000_philidor_1.jpg",53,60),new Jockey(6,201,"Princess Luna",java.sql.Date.valueOf("1990-01-04"),59),56.955,1));
        rennergebnisList.add(new Rennergebnis(1,new Pferd("0004","Pia","Haflinger",17,"0004_pia_0.jpg",43,55),new Jockey(2,301,"Rainbow Dash",java.sql.Date.valueOf("2003-05-07"),40),59.167,3));
        rennergebnisList.add(new Rennergebnis(1,new Pferd("0003","Jaris","Fjordpony",9,"0003_jaris_2.jpg",56,59),new Jockey(5,287,"Princess Celestia",java.sql.Date.valueOf("1987-09-08"),61),62.457,1));
        rennergebnisList.add(new Rennergebnis(1,new Pferd("0001","Rusty","Shetland Pony",16,"0001_rusty_0.jpg",40,46),new Jockey(1,29,"Twilight Sparkle",java.sql.Date.valueOf("2003-06-07"),50),48.968,4));
        rennergebnisList.add(new Rennergebnis(1,new Pferd("0006","Szilja","Araber",12,"0000_szilja_0.jpg",48,57),new Jockey(4,48,"Rarity",java.sql.Date.valueOf("2003-10-11"),39),61.120,2));
        List<Rennergebnis> rennergebnisListLoaded = daoRennergebnis.loadAll();
        for(Rennergebnis rennergebnis : rennergebnisList) {
            assertTrue(rennergebnisListLoaded.contains(rennergebnis));
        }
    }

    /** load conditions **/
    @Test
    public void loadConditionTest() throws PersistenceException {
        logger.info("loadConditionTest()");
        List<Rennergebnis> rennergebnisList = new ArrayList<>();
        Rennergebnis rennergebnis0 = new Rennergebnis(0,new Pferd("0001","Rusty","Shetland Pony",16,"0001_rusty_0.jpg",40,46),new Jockey(1,29,"Twilight Sparkle",java.sql.Date.valueOf("2003-06-07"),50),48.968,2);
        Rennergebnis rennergebnis1 = new Rennergebnis(0,new Pferd("0005","Benji","Haflinger-Noriker",15,"0005_benji_1.jpg",40,44),new Jockey(0,58,"Pinkie Pie",java.sql.Date.valueOf("2004-08-12"),47),46.969,3);
        Rennergebnis rennergebnis2 = new Rennergebnis(0,new Pferd("0000","Philidor","Trakhner",22,"0000_philidor_1.jpg",53,60),new Jockey(6,201,"Princess Luna",java.sql.Date.valueOf("1990-01-04"),59),56.955,1);
        Rennergebnis rennergebnis3 = new Rennergebnis(1,new Pferd("0004","Pia","Haflinger",17,"0004_pia_0.jpg",43,55),new Jockey(2,301,"Rainbow Dash",java.sql.Date.valueOf("2003-05-07"),40),59.167,3);
        Rennergebnis rennergebnis4 = new Rennergebnis(1,new Pferd("0003","Jaris","Fjordpony",9,"0003_jaris_2.jpg",56,59),new Jockey(5,287,"Princess Celestia",java.sql.Date.valueOf("1987-09-08"),61),62.457,1);
        Rennergebnis rennergebnis5 = new Rennergebnis(1,new Pferd("0001","Rusty","Shetland Pony",16,"0001_rusty_0.jpg",40,46),new Jockey(1,29,"Twilight Sparkle",java.sql.Date.valueOf("2003-06-07"),50),48.968,4);
        Rennergebnis rennergebnis6 = new Rennergebnis(1,new Pferd("0006","Szilja","Araber",12,"0000_szilja_0.jpg",48,57),new Jockey(4,48,"Rarity",java.sql.Date.valueOf("2003-10-11"),39),61.120,2);
        rennergebnisList = daoRennergebnis.loadAll();
        List<Rennergebnis> rennergebnisListLoaded = daoRennergebnis.loadCondition(-1,null,-1,-1,-1,-1,-1);
        assertTrue(rennergebnisList.size() == rennergebnisListLoaded.size());
        for(Rennergebnis rennergebnis : rennergebnisList) {
            assertTrue(rennergebnisListLoaded.contains(rennergebnis));
        }
        rennergebnisListLoaded = daoRennergebnis.loadCondition(0,null,-1,-1,-1,-1,-1);
        rennergebnisList = new ArrayList<>();
        rennergebnisList.add(rennergebnis0);
        rennergebnisList.add(rennergebnis1);
        rennergebnisList.add(rennergebnis2);
        assertTrue(rennergebnisList.size() == rennergebnisListLoaded.size());
        for(Rennergebnis rennergebnis : rennergebnisList) {
            assertTrue(rennergebnisListLoaded.contains(rennergebnis));
        }
        rennergebnisListLoaded = daoRennergebnis.loadCondition(-1,"0001",-1,-1,-1,-1,-1);
        rennergebnisList = new ArrayList<>();
        rennergebnisList.add(rennergebnis0);
        rennergebnisList.add(rennergebnis5);
        assertTrue(rennergebnisList.size() == rennergebnisListLoaded.size());
        for(Rennergebnis rennergebnis : rennergebnisList) {
            assertTrue(rennergebnisListLoaded.contains(rennergebnis));
        }
        rennergebnisListLoaded = daoRennergebnis.loadCondition(-1,null,2,-1,-1,-1,-1);
        rennergebnisList = new ArrayList<>();
        rennergebnisList.add(rennergebnis3);
        assertTrue(rennergebnisList.size() == rennergebnisListLoaded.size());
        for(Rennergebnis rennergebnis : rennergebnisList) {
            assertTrue(rennergebnisListLoaded.contains(rennergebnis));
        }
        rennergebnisListLoaded = daoRennergebnis.loadCondition(-1,null,-1,45.5,50,-1,-1);
        rennergebnisList = new ArrayList<>();
        rennergebnisList.add(rennergebnis0);
        rennergebnisList.add(rennergebnis1);
        rennergebnisList.add(rennergebnis5);
        assertTrue(rennergebnisList.size() == rennergebnisListLoaded.size());
        for(Rennergebnis rennergebnis : rennergebnisList) {
            assertTrue(rennergebnisListLoaded.contains(rennergebnis));
        }
        rennergebnisListLoaded = daoRennergebnis.loadCondition(-1,null,-1,-1,-1,2,3);
        rennergebnisList = new ArrayList<>();
        rennergebnisList.add(rennergebnis0);
        rennergebnisList.add(rennergebnis1);
        rennergebnisList.add(rennergebnis3);
        rennergebnisList.add(rennergebnis6);
        assertTrue(rennergebnisList.size() == rennergebnisListLoaded.size());
        for(Rennergebnis rennergebnis : rennergebnisList) {
            assertTrue(rennergebnisListLoaded.contains(rennergebnis));
        }
    }

    /** save with null-value **/
    @Test(expected = IllegalArgumentException.class)
    public void createWithNullShouldThrowException() throws PersistenceException {
        logger.info("createWithNullShouldThrowException()");
        daoRennergebnis.save(null);
    }

    /** save with valid parameters **/
    @Test
    public void createWithValid() throws PersistenceException {
        logger.info("createWithValid()");
        Rennergebnis rennergebnis = new Rennergebnis(2,new Pferd("0006","Szilja","Araber",12,"0000_szilja_0.jpg",48,57),new Jockey(4,48,"Rarity",java.sql.Date.valueOf("2003-10-11"),39),59.304,1);
        List<Rennergebnis> rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
        daoRennergebnis.save(rennergebnis);
        rennergebnisList = daoRennergebnis.loadAll();
        assertTrue(rennergebnisList.contains(rennergebnis));
    }

    /** save with existing renn-id/Pferd/Jockey combination **/
    @Test
    public void createWithExistingId() throws PersistenceException {
        logger.info("createWithExistingId()");
        Rennergebnis rennergebnis = new Rennergebnis(1,new Pferd("0004","Pia","Haflinger",17,"0004_pia_0.jpg",43,55),new Jockey(5,287,"Princess Celestia",java.sql.Date.valueOf("1987-09-08"),61),59.167,3);
        List<Rennergebnis> rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
        daoRennergebnis.save(rennergebnis);
        rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
        rennergebnis = new Rennergebnis(1,new Pferd("0004","Pia","Haflinger",17,"0004_pia_0.jpg",43,55),new Jockey(4,48,"Rarity",java.sql.Date.valueOf("2003-10-11"),39),61.120,2);
        rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
        daoRennergebnis.save(rennergebnis);
        rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
        rennergebnis = new Rennergebnis(0,new Pferd("0003","Jaris","Fjordpony",9,"0003_jaris_2.jpg",56,59),new Jockey(6,201,"Princess Luna",java.sql.Date.valueOf("1990-01-04"),59),56.955,1);
        rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
        daoRennergebnis.save(rennergebnis);
        rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
    }

    /** save and delete **/
    @Test
    public void deleteTest() throws PersistenceException {
        logger.info("deleteTest()");
        Rennergebnis rennergebnis = new Rennergebnis(2,new Pferd("0006","Szilja","Araber",12,"0000_szilja_0.jpg",48,57),new Jockey(4,48,"Rarity",java.sql.Date.valueOf("2003-10-11"),39),59.304,1);
        List<Rennergebnis> rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
        daoRennergebnis.save(rennergebnis);
        rennergebnisList = daoRennergebnis.loadAll();
        assertTrue(rennergebnisList.contains(rennergebnis));
        daoRennergebnis.delete(rennergebnis);
        rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
    }

    /** delete non-existing **/
    @Test
    public void deleteNonExisting() throws PersistenceException {
        logger.info("deleteNonExisting()");
        Rennergebnis rennergebnis = new Rennergebnis(1,new Pferd("0006","Szilja","Araber",12,"0000_szilja_0.jpg",48,57),new Jockey(4,48,"Rarity",java.sql.Date.valueOf("2003-10-11"),39),61.120,2);
        List<Rennergebnis> rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
        daoRennergebnis.delete(rennergebnis);
        rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
    }

    /** save and then update **/
    @Test
    public void updateTest() throws PersistenceException {
        logger.info("updateTest()");
        Rennergebnis rennergebnis = new Rennergebnis(2,new Pferd("0006","Szilja","Araber",12,"0000_szilja_0.jpg",48,57),new Jockey(4,48,"Rarity",java.sql.Date.valueOf("2003-10-11"),39),59.304,1);
        List<Rennergebnis> rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
        daoRennergebnis.save(rennergebnis);
        rennergebnisList = daoRennergebnis.loadAll();
        assertTrue(rennergebnisList.contains(rennergebnis));
        Rennergebnis updatedRennergebnis = new Rennergebnis(2,new Pferd("0006","Szilja","Araber",12,"0000_szilja_0.jpg",48,57),new Jockey(4,48,"Rarity",java.sql.Date.valueOf("2003-10-11"),39),59.334,1);
        rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
        daoRennergebnis.update(updatedRennergebnis);
        rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
        assertTrue(rennergebnisList.contains(rennergebnis));
        daoRennergebnis.delete(updatedRennergebnis);
    }

    /** test for free renn_id **/
    @Test
    public void isFreeRenn_idTest() throws PersistenceException {
        logger.info("isFreeRenn_idTest()");
        assertTrue(daoRennergebnis.isFreeRenn_id(10));
        assertFalse(daoRennergebnis.isFreeRenn_id(0));
    }

    /** test for free renn_id **/
    @Test
    public void getFreeRenn_idTest() throws PersistenceException {
        logger.info("getFreeRenn_idTest()");
        List<Integer> renn_idList = new ArrayList<>();
        for(Rennergebnis rennergebnis : daoRennergebnis.loadAll()) {
            renn_idList.add(rennergebnis.getRenn_id());
        }
        Integer renn_idLoaded = daoRennergebnis.getFreeRenn_id();
        assertFalse(renn_idList.contains(renn_idLoaded));
    }
    
}
