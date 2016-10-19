package sepm.ws16.e1327450.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.dao.DAOPferd;
import sepm.ws16.e1327450.dao.PersistenceException;
import org.junit.Test;
import sepm.ws16.e1327450.domain.Pferd;

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
        Pferd pferd = new Pferd("0000","Philidor","Trakhner",22,"0000_philidor_1.jpg",false,53,60);
        Pferd loadedPferd = daoPferd.load("0000");
        assertTrue(pferd.equals(loadedPferd));
    }

    /** load valid non-existing chip_nr **/
    @Test
    public void loadWithNonExisting() throws PersistenceException {
        logger.info("loadWithNonExisting()");
        Pferd loadedPferd = daoPferd.load("1000");
        assertTrue(loadedPferd == null);
    }
    
    /** load all **/
    @Test
    public void loadAll() throws PersistenceException {
        logger.info("loadAll()");
        List<Pferd> pferdList = new ArrayList<>();
        pferdList.add(new Pferd("0000","Philidor","Trakhner",22,"0000_philidor_1.jpg",false,53,60));
        pferdList.add(new Pferd("0001","Rusty","Shetland Pony",16,"0001_rusty_0.jpg",false,40,46));
        pferdList.add(new Pferd("0002","Kori","Huzule",13,"0002_kori_1.jpg",false,40,51));
        pferdList.add(new Pferd("0003","Jaris","Fjordpony",9,"0003_jaris_2.jpg",false,56,59));
        pferdList.add(new Pferd("0004","Pia","Haflinger",17,"0004_pia_0.jpg",false,43,55));
        pferdList.add(new Pferd("0005","Benji","Haflinger-Noriker",15,"0005_benji_1.jpg",false,40,44));
        pferdList.add(new Pferd("0006","Szilja","Araber",12,"0000_szilja_0.jpg",false,48,57));
        List<Pferd> pferdListLoaded = daoPferd.loadAll();
        for(Pferd pferd : pferdList) {
            assertTrue(pferdListLoaded.contains(pferd));
        }
    }
    
    /** load conditions **/
    @Test
    public void loadCondition() throws PersistenceException {
        logger.info("loadCondition()");
        List<Pferd> pferdList = new ArrayList<>();
        Pferd pferd0 = new Pferd("0000","Philidor","Trakhner",22,"0000_philidor_1.jpg",false,53,60);
        Pferd pferd1 = new Pferd("0001","Rusty","Shetland Pony",16,"0001_rusty_0.jpg",false,40,46);
        Pferd pferd2 = new Pferd("0002","Kori","Huzule",13,"0002_kori_1.jpg",false,40,51);
        Pferd pferd3 = new Pferd("0003","Jaris","Fjordpony",9,"0003_jaris_2.jpg",false,56,59);
        Pferd pferd4 = new Pferd("0004","Pia","Haflinger",17,"0004_pia_0.jpg",false,43,55);
        Pferd pferd5 = new Pferd("0005","Benji","Haflinger-Noriker",15,"0005_benji_1.jpg",false,40,44);
        Pferd pferd6 = new Pferd("0006","Szilja","Araber",12,"0000_szilja_0.jpg",false,48,57);
        List<Pferd> pferdListLoaded = daoPferd.loadCondition(null, -1, -1, false, -1, -1, -1, -1);
        pferdList = daoPferd.loadAll();
        assertTrue(pferdList.size() == pferdListLoaded.size());
        for(Pferd pferd : pferdList) {
            assertTrue(pferdListLoaded.contains(pferd));
        }
        pferdListLoaded = daoPferd.loadCondition("Philidor", -1, -1, false, -1, -1, -1, -1);
        pferdList.add(pferd0);
        assertTrue(pferdList.size() == pferdListLoaded.size());
        for(Pferd pferd : pferdList) {
            assertTrue(pferdListLoaded.contains(pferd));
        }
        pferdListLoaded = daoPferd.loadCondition(null, 4, 10, false, -1, -1, -1, -1);
        pferdList = new ArrayList<>();
        pferdList.add(pferd3);
        assertTrue(pferdList.size() == pferdListLoaded.size());
        for(Pferd pferd : pferdList) {
            assertTrue(pferdListLoaded.contains(pferd));
        }
        pferdListLoaded = daoPferd.loadCondition(null, -1, -1, false, 47, 53, -1, -1);
        pferdList = new ArrayList<>();
        pferdList.add(pferd0);
        pferdList.add(pferd6);
        assertTrue(pferdList.size() == pferdListLoaded.size());
        for(Pferd pferd : pferdList) {
            assertTrue(pferdListLoaded.contains(pferd));
        }
        pferdListLoaded = daoPferd.loadCondition(null, -1, -1, false, -1, -1, 55, 60);
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
    @Test(expected = IllegalArgumentException.class)
    public void createWithNullShouldThrowException() throws PersistenceException {
        logger.info("createWithNullShouldThrowException()");
        daoPferd.save(null);
    }

    /** save with valid parameters **/
    @Test
    public void createWithValid() throws PersistenceException {
        logger.info("createWithValid()");
        Pferd pferd = new Pferd("0007","Frosty","Warmblut-Mix",7,"0007_frosty_0.jpg",false,43,55);
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
        Pferd pferd = new Pferd("0005","Frosty","Warmblut-Mix",7,"0007_frosty_0.jpg",false,43,55);
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
        Pferd pferd = new Pferd("0007","Frosty","Warmblut-Mix",7,"0007_frosty_0.jpg",false,43,55);
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
        Pferd pferd = new Pferd("0008","Luis","Haflinger",7,"0008_luis_0.jpg",false,40,50);
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
        Pferd pferd = new Pferd("0007","Frosty","Warmblut-Mix",7,"0007_frosty_0.jpg",false,43,55);
        List<Pferd> pferdList = daoPferd.loadAll();
        assertFalse(pferdList.contains(pferd));
        daoPferd.save(pferd);
        pferdList = daoPferd.loadAll();
        assertTrue(pferdList.contains(pferd));
        Pferd updatedPferd = new Pferd("0007","Frosty","Pinto-Mix",8,"0007_frosty_0.jpg",false,45,58);
        pferdList = daoPferd.loadAll();
        assertFalse(pferdList.contains(pferd));
        daoPferd.update(updatedPferd);
        pferdList = daoPferd.loadAll();
        assertFalse(pferdList.contains(pferd));
        assertTrue(pferdList.contains(pferd));
    }

}
