package sepm.ws16.e1327450.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.Rennergebnis;
import java.util.List;

public class DAOImlRennergebnis implements  DAORennergebnis {

    final static Logger logger = LoggerFactory.getLogger(DAORennergebnis.class);

    @Override
    public void save(Rennergebnis r) throws PersistenceException {

    }

    @Override
    public Rennergebnis load(int chip_nr) throws PersistenceException {
        return null;
    }

    @Override
    public void delete(Rennergebnis r) throws PersistenceException {

    }

    @Override
    public void update(Rennergebnis r) throws PersistenceException {

    }

    @Override
    public List<Rennergebnis> loadAll() throws PersistenceException {
        return null;
    }

    @Override
    public List<Rennergebnis> loadCondition(int renn_id, String chip_nr, int svnr, double min_gesw, double max_gesw, int min_platz, int max_platz) throws PersistenceException {
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
