package sepm.ws16.e1327450.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.dao.DAOJockey;
import sepm.ws16.e1327450.dao.DAOPferd;
import sepm.ws16.e1327450.dao.DAORennergebnis;
import sepm.ws16.e1327450.dao.PersistenceException;
import sepm.ws16.e1327450.domain.*;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class AbstractDAOImlRennergebnisTest {

    final static Logger logger = LoggerFactory.getLogger(AbstractDAOImlRennergebnisTest.class);

    protected DAORennergebnis daoRennergebnis;
    protected DAOJockey daoJockey;
    protected DAOPferd daoPferd;

    protected void setDAORennergebnis(DAORennergebnis daoRennergebnis) {
        this.daoRennergebnis = daoRennergebnis;
    }

    public void setDaoJockey(DAOJockey daoJockey) {
        this.daoJockey = daoJockey;
    }

    public void setDaoPferd(DAOPferd daoPferd) {
        this.daoPferd = daoPferd;
    }

    /** load valid renn-id **/
    @Test
    public void loadWithValid() throws PersistenceException {
        logger.info("createWithValid()");
        Rennergebnis rennergebnis = new Rennergebnis(0,daoPferd.load(new PferdID(0)),daoJockey.load(new JockeyID(6)),63.663,58.7,1.01,1.073,1);
        Rennergebnis loadedRennergebnis = daoRennergebnis.load(new RennergebnisID(0,0,6));
        assertTrue(rennergebnis.equals(loadedRennergebnis));
    }

    /** load valid non-existing renn-id, wrong combination of parameters **/
    @Test
    public void loadWithNonExisting() throws PersistenceException {
        logger.info("loadWithNonExisting()");
        Rennergebnis loadedRennergebnis = daoRennergebnis.load(new RennergebnisID(20,0,1));
        assertTrue(loadedRennergebnis == null);
        loadedRennergebnis = daoRennergebnis.load(new RennergebnisID(0,1,5));
        assertTrue(loadedRennergebnis == null);
    }

    /** load all **/
    @Test
    public void loadAllTest() throws PersistenceException {
        logger.info("loadAllTest()");
        List<Rennergebnis> rennergebnisList = new ArrayList<>();
        rennergebnisList.add(new Rennergebnis(0,daoPferd.load(new PferdID(1)),daoJockey.load(new JockeyID(1)),47.257,45.2,0.98,1.0668,2));
        rennergebnisList.add(new Rennergebnis(0,daoPferd.load(new PferdID(5)),daoJockey.load(new JockeyID(0)),42.381,40.8,0.97,1.07,3));
        rennergebnisList.add(new Rennergebnis(0,daoPferd.load(new PferdID(0)),daoJockey.load(new JockeyID(6)),63.663,58.7,1.01,1.073,1));
        rennergebnisList.add(new Rennergebnis(1,daoPferd.load(new PferdID(4)),daoJockey.load(new JockeyID(2)),58.584,53.9,1.012,1.074,3));
        rennergebnisList.add(new Rennergebnis(1,daoPferd.load(new PferdID(3)),daoJockey.load(new JockeyID(5)),64.424,58.8,1.02,1.074,1));
        rennergebnisList.add(new Rennergebnis(1,daoPferd.load(new PferdID(1)),daoJockey.load(new JockeyID(1)),49.075,46.0,1.0,1.066,4));
        rennergebnisList.add(new Rennergebnis(1,daoPferd.load(new PferdID(6)),daoJockey.load(new JockeyID(4)),64.042,57.0,1.05,1.07,2));
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
        Rennergebnis rennergebnis0 = new Rennergebnis(0,daoPferd.load(new PferdID(1)),daoJockey.load(new JockeyID(1)),47.257,45.2,0.98,1.0668,2);
        Rennergebnis rennergebnis1 = new Rennergebnis(0,daoPferd.load(new PferdID(5)),daoJockey.load(new JockeyID(0)),42.381,40.8,0.97,1.07,3);
        Rennergebnis rennergebnis2 = new Rennergebnis(0,daoPferd.load(new PferdID(0)),daoJockey.load(new JockeyID(6)),63.663,58.7,1.01,1.073,1);
        Rennergebnis rennergebnis3 = new Rennergebnis(1,daoPferd.load(new PferdID(4)),daoJockey.load(new JockeyID(2)),58.584,53.9,1.012,1.074,3);
        Rennergebnis rennergebnis4 = new Rennergebnis(1,daoPferd.load(new PferdID(3)),daoJockey.load(new JockeyID(5)),64.424,58.8,1.02,1.074,1);
        Rennergebnis rennergebnis5 = new Rennergebnis(1,daoPferd.load(new PferdID(1)),daoJockey.load(new JockeyID(1)),49.075,46.0,1.0,1.066,4);
        Rennergebnis rennergebnis6 = new Rennergebnis(1,daoPferd.load(new PferdID(6)),daoJockey.load(new JockeyID(4)),64.042,57.0,1.05,1.07,2);
        rennergebnisList = daoRennergebnis.loadAll();
        List<Rennergebnis> rennergebnisListLoaded = daoRennergebnis.loadCondition(new RennergebnisCondition(-1,-1,-1,-1,-1,-1,-1));
        assertTrue(rennergebnisList.size() == rennergebnisListLoaded.size());
        for(Rennergebnis rennergebnis : rennergebnisList) {
            assertTrue(rennergebnisListLoaded.contains(rennergebnis));
        }
        rennergebnisListLoaded = daoRennergebnis.loadCondition(new RennergebnisCondition(0,-1,-1,-1,-1,-1,-1));
        rennergebnisList = new ArrayList<>();
        rennergebnisList.add(rennergebnis0);
        rennergebnisList.add(rennergebnis1);
        rennergebnisList.add(rennergebnis2);
        assertTrue(rennergebnisList.size() == rennergebnisListLoaded.size());
        for(Rennergebnis rennergebnis : rennergebnisList) {
            assertTrue(rennergebnisListLoaded.contains(rennergebnis));
        }
        rennergebnisListLoaded = daoRennergebnis.loadCondition(new RennergebnisCondition(-1,1,-1,-1,-1,-1,-1));
        rennergebnisList = new ArrayList<>();
        rennergebnisList.add(rennergebnis0);
        rennergebnisList.add(rennergebnis5);
        assertTrue(rennergebnisList.size() == rennergebnisListLoaded.size());
        for(Rennergebnis rennergebnis : rennergebnisList) {
            assertTrue(rennergebnisListLoaded.contains(rennergebnis));
        }
        rennergebnisListLoaded = daoRennergebnis.loadCondition(new RennergebnisCondition(-1,-1,2,-1,-1,-1,-1));
        rennergebnisList = new ArrayList<>();
        rennergebnisList.add(rennergebnis3);
        assertTrue(rennergebnisList.size() == rennergebnisListLoaded.size());
        for(Rennergebnis rennergebnis : rennergebnisList) {
            assertTrue(rennergebnisListLoaded.contains(rennergebnis));
        }
        rennergebnisListLoaded = daoRennergebnis.loadCondition(new RennergebnisCondition(-1,-1,-1,45.5,50,-1,-1));
        rennergebnisList = new ArrayList<>();
        rennergebnisList.add(rennergebnis0);
        rennergebnisList.add(rennergebnis5);
        assertTrue(rennergebnisList.size() == rennergebnisListLoaded.size());
        for(Rennergebnis rennergebnis : rennergebnisList) {
            assertTrue(rennergebnisListLoaded.contains(rennergebnis));
        }
        rennergebnisListLoaded = daoRennergebnis.loadCondition(new RennergebnisCondition(-1,-1,-1,-1,-1,2,3));
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
    @Test
    public void createWithNull() throws PersistenceException {
        logger.info("createWithNullShouldThrowException()");
        Rennergebnis rennergebnis = null;
        List<Rennergebnis> rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
        daoRennergebnis.save(rennergebnis);
        assertFalse(rennergebnisList.contains(rennergebnis));
    }

    /** save with valid parameters **/
    @Test
    public void createWithValid() throws PersistenceException {
        logger.info("createWithValid()");
        Rennergebnis rennergebnis = new Rennergebnis(2,new Pferd(6,"Szilja","Araber",12,"0000_szilja_0.jpg",48,57),new Jockey(4,48,"Rarity",java.sql.Date.valueOf("2003-10-11"),39),64.042,57.0,1.05,1.07,1);
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
        Rennergebnis rennergebnis = new Rennergebnis(1,new Pferd(4,"Pia","Haflinger",17,"0004_pia_0.jpg",43,55),new Jockey(5,287,"Princess Celestia",java.sql.Date.valueOf("1987-09-08"),61),49.075,46.0,1.0,1.066,4);
        List<Rennergebnis> rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
        daoRennergebnis.save(rennergebnis);
        rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
        rennergebnis = new Rennergebnis(1,new Pferd(4,"Pia","Haflinger",17,"0004_pia_0.jpg",43,55),new Jockey(4,48,"Rarity",java.sql.Date.valueOf("2003-10-11"),39),49.075,46.0,1.0,1.066,4);
        rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
        daoRennergebnis.save(rennergebnis);
        rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
        rennergebnis = new Rennergebnis(0,new Pferd(3,"Jaris","Fjordpony",9,"0003_jaris_2.jpg",56,59),new Jockey(6,201,"Princess Luna",java.sql.Date.valueOf("1990-01-04"),59),49.075,46.0,1.0,1.066,4);
        rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
        daoRennergebnis.save(rennergebnis);
        rennergebnisList = daoRennergebnis.loadAll();
        assertFalse(rennergebnisList.contains(rennergebnis));
    }

    /** test for free renn_id **/
    @Test
    public void isFreeRenn_idTest() throws PersistenceException {
        logger.info("isFreeRenn_idTest()");
        assertTrue(daoRennergebnis.isFreeRenn_id(new RennID(10)));
        assertFalse(daoRennergebnis.isFreeRenn_id(new RennID(0)));
    }

    /** test for free renn_id **/
    @Test
    public void getFreeRenn_idTest() throws PersistenceException {
        logger.info("getFreeRenn_idTest()");
        List<Integer> renn_idList = new ArrayList<>();
        for(Rennergebnis rennergebnis : daoRennergebnis.loadAll()) {
            renn_idList.add(rennergebnis.getRenn_id());
        }
        Integer renn_idLoaded = daoRennergebnis.getFreeRenn_id().getRenn_id();
        assertFalse(renn_idList.contains(renn_idLoaded));
    }
    
}
