package sepm.ws16.e1327450.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.dao.PersistenceException;
import sepm.ws16.e1327450.domain.*;
import sepm.ws16.e1327450.service.Service;
import sepm.ws16.e1327450.service.ServiceException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Jockey jockey0 = new Jockey(20,58.0,"Pinkie Pie",java.sql.Date.valueOf("2004-08-12"),47);
        Jockey jockey1 = new Jockey(25,58.0,"Pinkie Pie",java.sql.Date.valueOf("2004-08-12"),-2);
        Jockey jockey2 = new Jockey(0,58.0,"Pinkie Pie",java.sql.Date.valueOf("2004-08-12"),8);
        assertFalse(service.loadAllJockey().contains(jockey0));
        service.saveJockey(jockey0);
        assertTrue(service.loadAllJockey().contains(jockey0));
        service.deleteJockey(jockey0);
        assertFalse(service.loadAllJockey().contains(jockey0));
        assertFalse(service.loadAllJockey().contains(jockey1));
        service.saveJockey(jockey1);
        assertFalse(service.loadAllJockey().contains(jockey1));
        assertFalse(service.loadAllJockey().contains(jockey2));
        service.saveJockey(jockey2);
        assertFalse(service.loadAllJockey().contains(jockey2));
        service.savePferd(null);
        Pferd pferd0 = new Pferd(1000,"Philidor","Trakhner",22,"0000_philidor_1.jpg",53,60);
        Pferd pferd1 = new Pferd(1000,"Philidor","Trakhner",22,"0000_philidor_1.jpg",30,60);
        Pferd pferd2 = new Pferd(1000,"Philidor","Trakhner",22,"0000_philidor_1.jpg",40,61);
        Pferd pferd3 = new Pferd(1000,"Philidor","Trakhner",22,"0000_philidor_1.jpg",50,49);
        Pferd pferd4 = new Pferd(1000,"Philidor","Trakhner",3,"0000_philidor_1.jpg",50,49);
        assertFalse(service.loadAllPferd().contains(pferd0));
        service.savePferd(pferd0);
        assertTrue(service.loadAllPferd().contains(pferd0));
        service.deletePferde(pferd0);
        assertFalse(service.loadAllPferd().contains(pferd0));
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

    /** feedback & valid **/
    @Test
    public void valid_feedbackTest() throws ServiceException {
        Jockey jockey0 = new Jockey(20,58.0,"Pinkie Pie",java.sql.Date.valueOf("2004-08-12"),47);
        Jockey jockey1 = new Jockey(25,58.0,"Pinkie Pie",java.sql.Date.valueOf("2004-08-12"),-2);
        Jockey jockey2 = new Jockey(0,58.0,"Pinkie Pie",java.sql.Date.valueOf("2004-08-12"),8);
        assertFalse(service.validJockey(null));
        assertTrue(service.feedbackJockey(null).equals("kein Jockey angegeben"));
        assertFalse(service.validJockey(new Jockey(-1,20,null,null,30)));
        assertTrue(service.feedbackJockey(new Jockey(-1,20,null,null,30)).equals("keine gültige Svnr angegeben | untergewichtige Jockey dürfen nicht reiten - min 40 | kein Name angegeben | kein Geburtsdatum angegeben"));
        assertTrue(service.validJockey(jockey0));
        assertTrue(service.feedbackJockey(jockey0).equals("Jockey in Ordnung"));
        assertFalse(service.validJockey(jockey1));
        assertFalse(service.validJockey(jockey2));
        assertFalse(service.validPferd(null));
        assertTrue(service.feedbackPferd(null).equals("kein Pferd angegeben"));
    }

    @Test
    public void doStatistikTest() throws ServiceException {
        assertTrue(service.doStatistik(new StatistikData(-1,-1)).getStatistik() == null);
        assertTrue(service.doStatistik(new StatistikData(-1,18)).getStatistik() == null);
        assertTrue(service.doStatistik(new StatistikData(0,18)).getStatistik() == null);
        assertTrue(service.doStatistik(new StatistikData(20,18)).getStatistik() == null);
        Map<Integer,Integer> statistik = new HashMap<>();
        statistik.put(1,0);
        statistik.put(2,1);
        statistik.put(3,0);
        statistik.put(4,1);
        Map<Integer,Integer> loadedStatistik = service.doStatistik(new StatistikData(1,-1)).getStatistik();
        assertTrue(statistik.size()==loadedStatistik.size());
        for(Integer i : loadedStatistik.keySet()) {
            assertTrue(statistik.containsKey(i));
            assertTrue(statistik.get(i)==loadedStatistik.get(i));
        }
        loadedStatistik = service.doStatistik(new StatistikData(1,1)).getStatistik();
        assertTrue(statistik.size()==loadedStatistik.size());
        for(Integer i : loadedStatistik.keySet()) {
            assertTrue(statistik.containsKey(i));
            assertTrue(statistik.get(i)==loadedStatistik.get(i));
        }
        statistik = new HashMap<>();
        loadedStatistik = service.doStatistik(new StatistikData(2,-1)).getStatistik();
        assertTrue(statistik.size()==loadedStatistik.size());
        for(Integer i : loadedStatistik.keySet()) {
            assertTrue(statistik.containsKey(i));
            assertTrue(statistik.get(i)==loadedStatistik.get(i));
        }
        statistik = new HashMap<>();
        loadedStatistik = service.doStatistik(new StatistikData(3,5)).getStatistik();
        statistik.put(1,1);
        assertTrue(statistik.size()==loadedStatistik.size());
        for(Integer i : loadedStatistik.keySet()) {
            assertTrue(statistik.containsKey(i));
            assertTrue(statistik.get(i)==loadedStatistik.get(i));
        }
        statistik = new HashMap<>();
        loadedStatistik = service.doStatistik(new StatistikData(-1,4)).getStatistik();
        statistik.put(1,0);
        statistik.put(2,1);
        assertTrue(statistik.size()==loadedStatistik.size());
        for(Integer i : loadedStatistik.keySet()) {
            assertTrue(statistik.containsKey(i));
            assertTrue(statistik.get(i)==loadedStatistik.get(i));
        }
    }

    @Test
    public void doRennenTest() throws ServiceException {
        assertTrue(service.doRennsimulation(new RennsimulationData(0,null)) == null);
        assertTrue(service.doRennsimulation(new RennsimulationData(7,null)) == null);
        Map<Pferd, Jockey> participants = new HashMap<>();
        participants.put(service.loadPferd(new PferdID(0)),service.loadJockey(new JockeyID(0)));
        participants.put(service.loadPferd(new PferdID(1)),service.loadJockey(new JockeyID(1)));
        participants.put(service.loadPferd(new PferdID(2)),service.loadJockey(new JockeyID(2)));
        participants.put(service.loadPferd(new PferdID(3)),service.loadJockey(new JockeyID(3)));
        List<Rennergebnis> rennergebnisList = service.doRennsimulation(new RennsimulationData(8,participants));
        Rennergebnis rennergebnis1 = null;
        Rennergebnis rennergebnis2 = null;
        Rennergebnis rennergebnis3 = null;
        Rennergebnis rennergebnis4 = null;
        for(Rennergebnis rennergebnis : rennergebnisList) {
            if(rennergebnis.getPlatz() == 1) {
                rennergebnis1 = rennergebnis;
            } else if(rennergebnis.getPlatz() == 2) {
                rennergebnis2 = rennergebnis;
            } else if(rennergebnis.getPlatz() == 3) {
                rennergebnis3 = rennergebnis;
            } else if(rennergebnis.getPlatz() == 4) {
                rennergebnis4 = rennergebnis;
            }
        }
        assertTrue(rennergebnis1.getDgeschw() >= rennergebnis2.getDgeschw());
        assertTrue(rennergebnis2.getDgeschw() >= rennergebnis3.getDgeschw());
        assertTrue(rennergebnis3.getDgeschw() >= rennergebnis4.getDgeschw());
    }

    @Test
    public void doRennen_jockey_twice_Test() throws ServiceException {
        Map<Pferd, Jockey> participants = new HashMap<>();
        participants.put(service.loadPferd(new PferdID(0)),service.loadJockey(new JockeyID(0)));
        participants.put(service.loadPferd(new PferdID(1)),service.loadJockey(new JockeyID(0)));
        boolean thrown = false;
        try {
            service.doRennsimulation(new RennsimulationData(5, participants));
        } catch(ServiceException e) {
            thrown = true;
        } assertTrue(thrown);
    }

    @Test
    public void doRennen_invalid_jockeys_Test() throws ServiceException {
        Map<Pferd, Jockey> participants = new HashMap<>();
        Jockey jockey = new Jockey(3,301.0,"Rainbow Dash",java.sql.Date.valueOf("2003-05-07"),40);
        participants.put(service.loadPferd(new PferdID(1)),jockey);
        boolean thrown = false;
        try {
            service.doRennsimulation(new RennsimulationData(17, participants));
        } catch(ServiceException e) {
            thrown = true;
        } assertTrue(thrown);
        participants = new HashMap<>();
        jockey = new Jockey(10,301.0,"Rainbow Dash",java.sql.Date.valueOf("2003-05-07"),40);
        participants.put(service.loadPferd(new PferdID(1)),jockey);
        thrown = false;
        try {
            service.doRennsimulation(new RennsimulationData(7, participants));
        } catch(ServiceException e) {
            thrown = true;
        } assertTrue(thrown);
    }

    @Test
    public void doRennen_invalid_pferd_Test() throws ServiceException {
        Map<Pferd, Jockey> participants = new HashMap<>();
        Pferd pferd = new Pferd(2,"Rusty","Shetland Pony",16,"0001_rusty_0.jpg",40,46);
        participants.put(pferd,service.loadJockey(new JockeyID(0)));
        boolean thrown = false;
        try {
            service.doRennsimulation(new RennsimulationData(7, participants));
        } catch(ServiceException e) {
            thrown = true;
        } assertTrue(thrown);
        participants = new HashMap<>();
        pferd = new Pferd(22,"Rusty","Shetland Pony",16,"0001_rusty_0.jpg",40,46);
        participants.put(pferd,service.loadJockey(new JockeyID(0)));
        thrown = false;
        try {
            service.doRennsimulation(new RennsimulationData(7, participants));
        } catch(ServiceException e) {
            thrown = true;
        } assertTrue(thrown);
    }

}
