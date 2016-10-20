package sepm.ws16.e1327450.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.Rennergebnis;
import java.util.List;

public class DAOImlRennergebnis implements  DAORennergebnis {

    final static Logger logger = LoggerFactory.getLogger(DAORennergebnis.class);

    @Override
    public void save(Rennergebnis r) throws PersistenceException {
        logger.info("save("+r.toString()+")");
    }

    @Override
    public Rennergebnis load(int renn_id, String chip_nr, int svnr) throws PersistenceException {
        logger.info("load("+chip_nr+")");
        return null;
    }

    @Override
    public void delete(Rennergebnis r) throws PersistenceException {
        logger.info("delete("+r.toString()+")");
    }

    @Override
    public void update(Rennergebnis r) throws PersistenceException {
        logger.info("update("+r.toString()+")");
    }

    @Override
    public List<Rennergebnis> loadAll() throws PersistenceException {
        logger.info("loadAll()");
        return null;
    }

    @Override
    public List<Rennergebnis> loadCondition(int renn_id, String chip_nr, int svnr, double min_gesw, double max_gesw, int min_platz, int max_platz) throws PersistenceException {
        logger.info("loadCondition("+renn_id+","+chip_nr+","+svnr+","+min_gesw+","+max_gesw+","+min_platz+","+max_platz+")");
        return null;
    }

    @Override
    public int getFreeRenn_id() throws PersistenceException {
        logger.info("getFreeRenn_id()");
        return 0;
    }

    @Override
    public boolean isFreeRenn_id(int renn_id) throws PersistenceException {
        logger.info("isFreeRenn_id("+renn_id+")");
        return false;
    }
}
