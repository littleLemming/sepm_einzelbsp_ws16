package sepm.ws16.e1327450.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.Pferd;
import java.util.List;

public class DAOImlPferd implements DAOPferd {

    final static Logger logger = LoggerFactory.getLogger(DAOImlPferd.class);

    @Override
    public void save(Pferd p) throws PersistenceException {

    }

    @Override
    public Pferd load(String chip_nr) throws PersistenceException {
        return null;
    }

    @Override
    public void delete(Pferd p) throws PersistenceException {

    }

    @Override
    public void unDelete(Pferd p) throws PersistenceException {

    }

    @Override
    public void update(Pferd p) throws PersistenceException {

    }

    @Override
    public List<Pferd> loadAll() throws PersistenceException {
        return null;
    }

    @Override
    public List<Pferd> loadCondition(String name, int min_alter, int max_alter, boolean deleted, int min_min_gesw, int max_min_gesw, int min_max_gesw, int max_max_gesw) throws PersistenceException {
        return null;
    }

    @Override
    public int getFreeChip_Nr() throws PersistenceException {
        return 0;
    }

    @Override
    public boolean isFreeChip_Nr(int chip_nr) throws PersistenceException {
        return false;
    }
}
