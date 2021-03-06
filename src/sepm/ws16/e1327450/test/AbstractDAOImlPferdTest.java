package sepm.ws16.e1327450.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.dao.DAOPferd;
import sepm.ws16.e1327450.dao.PersistenceException;
import org.junit.Test;
import sepm.ws16.e1327450.domain.Pferd;
import sepm.ws16.e1327450.domain.PferdCondition;
import sepm.ws16.e1327450.domain.PferdID;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class AbstractDAOImlPferdTest {

    final static Logger logger = LoggerFactory.getLogger(AbstractDAOImlPferdTest.class);

    protected DAOPferd daoPferd;

    protected void setDAOPferd(DAOPferd daoPferd) {
        this.daoPferd = daoPferd;
    }

    /** load valid chip_nr **/
    @Test
    public void loadWithValid() throws PersistenceException {
        logger.info("createWithValid()");
        Pferd pferd = new Pferd(0,"Philidor","Trakhner",22,"0000_philidor_1.jpg",53,60);
        Pferd loadedPferd = daoPferd.load(new PferdID(0));
        assertTrue(pferd.equals(loadedPferd));
    }

    /** load valid non-existing chip_nr **/
    @Test
    public void loadWithNonExisting() throws PersistenceException {
        logger.info("loadWithNonExisting()");
        Pferd loadedPferd = daoPferd.load(new PferdID(10));
        assertTrue(loadedPferd == null);
    }
    
    /** load all **/
    @Test
    public void loadAllTest() throws PersistenceException {
        logger.info("loadAllTest()");
        List<Pferd> pferdList = new ArrayList<>();
        pferdList.add(new Pferd(0,"Philidor","Trakhner",22,"0000_philidor_1.jpg",53,60));
        pferdList.add(new Pferd(1,"Rusty","Shetland Pony",16,"0001_rusty_0.jpg",40,46));
        pferdList.add(new Pferd(2,"Kori","Huzule",13,"0002_kori_1.jpg",40,51));
        pferdList.add(new Pferd(3,"Jaris","Fjordpony",9,"0003_jaris_2.jpg",56,59));
        pferdList.add(new Pferd(4,"Pia","Haflinger",17,"0004_pia_0.jpg",43,55));
        pferdList.add(new Pferd(5,"Benji","Haflinger-Noriker",15,"0005_benji_1.jpg",40,44));
        pferdList.add(new Pferd(6,"Szilja","Araber",12,"0000_szilja_0.jpg",48,57));
        List<Pferd> pferdListLoaded = daoPferd.loadAll();
        assertTrue(pferdListLoaded.size() == pferdList.size());
        for(Pferd pferd : pferdList) {
            assertTrue(pferdListLoaded.contains(pferd));
        }
    }
    
    /** load conditions **/
    @Test
    public void loadConditionTest() throws PersistenceException {
        logger.info("loadConditionTest()");
        List<Pferd> pferdList = new ArrayList<>();
        Pferd pferd0 = new Pferd(0,"Philidor","Trakhner",22,"0000_philidor_1.jpg",53,60);
        Pferd pferd1 = new Pferd(1,"Rusty","Shetland Pony",16,"0001_rusty_0.jpg",40,46);
        Pferd pferd2 = new Pferd(2,"Kori","Huzule",13,"0002_kori_1.jpg",40,51);
        Pferd pferd3 = new Pferd(3,"Jaris","Fjordpony",9,"0003_jaris_2.jpg",56,59);
        Pferd pferd4 = new Pferd(4,"Pia","Haflinger",17,"0004_pia_0.jpg",43,55);
        Pferd pferd5 = new Pferd(5,"Benji","Haflinger-Noriker",15,"0005_benji_1.jpg",40,44);
        Pferd pferd6 = new Pferd(6,"Szilja","Araber",12,"0000_szilja_0.jpg",48,57);
        List<Pferd> pferdListLoaded = daoPferd.loadCondition(new PferdCondition(null, -1, -1, -1, -1, -1, -1));
        pferdList = daoPferd.loadAll();
        assertTrue(pferdList.size() == pferdListLoaded.size());
        for(Pferd pferd : pferdList) {
            assertTrue(pferdListLoaded.contains(pferd));
        }
        pferdListLoaded = daoPferd.loadCondition(new PferdCondition("Philidor", -1, -1, -1, -1, -1, -1));
        pferdList = new ArrayList<>();
        pferdList.add(pferd0);
        assertTrue(pferdList.size() == pferdListLoaded.size());
        for(Pferd pferd : pferdList) {
            assertTrue(pferdListLoaded.contains(pferd));
        }
        pferdListLoaded = daoPferd.loadCondition(new PferdCondition(null, 4, 10, -1, -1, -1, -1));
        pferdList = new ArrayList<>();
        pferdList.add(pferd3);
        assertTrue(pferdList.size() == pferdListLoaded.size());
        for(Pferd pferd : pferdList) {
            assertTrue(pferdListLoaded.contains(pferd));
        }
        pferdListLoaded = daoPferd.loadCondition(new PferdCondition(null, -1, -1, 47, 53, -1, -1));
        pferdList = new ArrayList<>();
        pferdList.add(pferd0);
        pferdList.add(pferd6);
        assertTrue(pferdList.size() == pferdListLoaded.size());
        for(Pferd pferd : pferdList) {
            assertTrue(pferdListLoaded.contains(pferd));
        }
        pferdListLoaded = daoPferd.loadCondition(new PferdCondition(null, -1, -1, -1, -1, 55, 60));
        pferdList = new ArrayList<>();
        pferdList.add(pferd0);
        pferdList.add(pferd3);
        pferdList.add(pferd4);
        pferdList.add(pferd6);
        assertTrue(pferdList.size() == pferdListLoaded.size());
        for(Pferd pferd : pferdList) {
            assertTrue(pferdListLoaded.contains(pferd));
        }
    }


    /** save with null-value **/
    @Test
    public void createWithNull() throws PersistenceException {
        logger.info("createWithNullShouldThrowException()");
        daoPferd.save(null);
    }

    /** save with valid parameters **/
    @Test
    public void createWithValid() throws PersistenceException {
        logger.info("createWithValid()");
        Pferd pferd = new Pferd(7,"Frosty","Warmblut-Mix",7,"0007_frosty_0.jpg",43,55);
        List<Pferd> pferdList = daoPferd.loadAll();
        assertFalse(pferdList.contains(pferd));
        daoPferd.save(pferd);
        pferdList = daoPferd.loadAll();
        assertTrue(pferdList.contains(pferd));
    }

    /** save with existing chip_nr **/
    @Test
    public void createWithExistingId() throws PersistenceException {
        logger.info("createWithExistingId()");
        Pferd pferd = new Pferd(5,"Frosty","Warmblut-Mix",7,"0007_frosty_0.jpg",43,55);
        List<Pferd> pferdList = daoPferd.loadAll();
        assertFalse(pferdList.contains(pferd));
        daoPferd.save(pferd);
        pferdList = daoPferd.loadAll();
        assertFalse(pferdList.contains(pferd));
    }

    /** save and delete **/
    @Test
    public void deleteTest() throws PersistenceException {
        logger.info("deleteTest()");
        Pferd pferd = new Pferd(7,"Frosty","Warmblut-Mix",7,"0007_frosty_0.jpg",43,55);
        List<Pferd> pferdList = daoPferd.loadAll();
        assertFalse(pferdList.contains(pferd));
        daoPferd.save(pferd);
        pferdList = daoPferd.loadAll();
        assertTrue(pferdList.contains(pferd));
        daoPferd.delete(pferd);
        pferdList = daoPferd.loadAll();
        assertFalse(pferdList.contains(pferd));
    }

    /** delete with non-existing chip_nr **/
    @Test
    public void deleteNonExisting() throws PersistenceException {
        logger.info("deleteNonExisting()");
        Pferd pferd = new Pferd(8,"Luis","Haflinger",7,"0008_luis_0.jpg",40,50);
        List<Pferd> pferdList = daoPferd.loadAll();
        assertFalse(pferdList.contains(pferd));
        daoPferd.delete(pferd);
        pferdList = daoPferd.loadAll();
        assertFalse(pferdList.contains(pferd));
    }

    /** save and then update **/
    @Test
    public void updateTest() throws PersistenceException {
        logger.info("updateTest()");
        Pferd pferd = new Pferd(7,"Frosty","Warmblut-Mix",7,"0007_frosty_0.jpg",43,55);
        List<Pferd> pferdList = daoPferd.loadAll();
        assertFalse(pferdList.contains(pferd));
        daoPferd.save(pferd);
        pferdList = daoPferd.loadAll();
        assertTrue(pferdList.contains(pferd));
        Pferd updatedPferd = new Pferd(7,"Frosty","Pinto-Mix",8,"0007_frosty_0.jpg",45,58);
        pferdList = daoPferd.loadAll();
        assertFalse(pferdList.contains(updatedPferd));
        daoPferd.update(updatedPferd);
        pferdList = daoPferd.loadAll();
        assertFalse(pferdList.contains(pferd));
        assertTrue(pferdList.contains(updatedPferd));
        daoPferd.delete(updatedPferd);
    }

    /** test for free chip-nr **/
    @Test
    public void isFreeChip_NrTest() throws PersistenceException {
        logger.info("isFreeChip_NrTest()");
        assertTrue(daoPferd.isFreeChip_Nr(new PferdID(100)));
        assertFalse(daoPferd.isFreeChip_Nr(new PferdID(0)));
    }

    /** test for free chip-nr **/
    @Test
    public void getFreeChip_NrTest() throws PersistenceException {
        logger.info("getFreeChip_NrTest()");
        List<Integer> chip_nrList = new ArrayList<>();
        for(Pferd pferd : daoPferd.loadAll()) {
            chip_nrList.add(pferd.getChip_nr());
        }
        int chip_nrLoaded = daoPferd.getFreeChip_Nr().getChip_nr();
        assertFalse(chip_nrList.contains(chip_nrLoaded));
    }

}
