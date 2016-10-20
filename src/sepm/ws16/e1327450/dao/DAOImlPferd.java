package sepm.ws16.e1327450.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.Pferd;
import java.util.List;

public class DAOImlPferd implements DAOPferd {

    final static Logger logger = LoggerFactory.getLogger(DAOImlPferd.class);

    @Override
    public void save(Pferd p) throws PersistenceException {
        logger.info("save("+p.toString()+")");
    }

    @Override
    public Pferd load(String chip_nr) throws PersistenceException {
        logger.info("load("+chip_nr+")");
        return null;
    }

    @Override
    public void delete(Pferd p) throws PersistenceException {
        logger.info("delete("+p.toString()+")");
    }

    @Override
    public void update(Pferd p) throws PersistenceException {
        logger.info("update("+p.toString()+")");
    }

    @Override
    public List<Pferd> loadAll() throws PersistenceException {
        logger.info("loadAll()");
        return null;
    }

    @Override
    public List<Pferd> loadCondition(String name, int min_alter, int max_alter, int min_min_gesw, int max_min_gesw, int min_max_gesw, int max_max_gesw) throws PersistenceException {
        logger.info("loadCondition("+name+","+min_alter+","+max_alter+","+min_min_gesw+","+max_min_gesw+","+min_max_gesw+","+max_max_gesw+")");
        return null;
    }

    @Override
    public String getFreeChip_Nr() throws PersistenceException {
        logger.info("getFreeChip_Nr()");
        return null;
    }

    @Override
    public boolean isFreeChip_Nr(String chip_nr) throws PersistenceException {
        logger.info("isFreeChip_Nr("+chip_nr+")");
        return false;
    }
}
