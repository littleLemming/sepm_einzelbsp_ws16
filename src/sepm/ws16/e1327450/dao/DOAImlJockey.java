package sepm.ws16.e1327450.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.Jockey;

import java.sql.Date;
import java.util.List;

public class DOAImlJockey implements DAOJockey {

    final static Logger logger = LoggerFactory.getLogger(DOAImlJockey.class);

    @Override
    public void save(Jockey j) throws PersistenceException {

    }

    @Override
    public Jockey load(int svnr) throws PersistenceException {
        return null;
    }

    @Override
    public void delete(Jockey j) throws PersistenceException {

    }

    @Override
    public void update(Jockey j) throws PersistenceException {

    }

    @Override
    public List<Jockey> loadAll() throws PersistenceException {
        return null;
    }

    @Override
    public List<Jockey> loadCondition(int mindKönnen, int maxKönnen, String name, Date minGeburtsdatum, Date maxGeburtsdatum, int minGewicht, int maxGewicht, boolean deleted) throws PersistenceException {
        return null;
    }

    @Override
    public int getFreeSvnr() throws PersistenceException {
        return 0;
    }

    @Override
    public boolean isFreeSvnr(int svnr) throws PersistenceException {
        return false;
    }
}
