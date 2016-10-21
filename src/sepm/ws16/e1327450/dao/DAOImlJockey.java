package sepm.ws16.e1327450.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.Jockey;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class DAOImlJockey implements DAOJockey {

    final static Logger logger = LoggerFactory.getLogger(DAOImlJockey.class);
    private Connection connection;

    public DAOImlJockey(Connection connection) throws PersistenceException {
        logger.info("DAOImlJockey("+connection+")");
        this.connection = connection;
    }

    @Override
    public void save(Jockey j) throws PersistenceException {
        logger.info("save("+j.toString()+")");
    }

    @Override
    public Jockey load(int svnr) throws PersistenceException {
        logger.info("load("+svnr+")");
        return null;
    }

    @Override
    public void delete(Jockey j) throws PersistenceException {
        logger.info("delete("+j.toString()+")");
    }

    @Override
    public void update(Jockey j) throws PersistenceException {
        logger.info("update("+j.toString()+")");
    }

    @Override
    public List<Jockey> loadAll() throws PersistenceException {
        logger.info("loadAll()");
        return null;
    }

    @Override
    public List<Jockey> loadCondition(int mindKönnen, int maxKönnen, String name, Date minGeburtsdatum, Date maxGeburtsdatum, int minGewicht, int maxGewicht) throws PersistenceException {
        logger.info("loadCondition("+mindKönnen+","+maxKönnen+","+name+","+minGeburtsdatum+","+maxGeburtsdatum+","+minGewicht+","+maxGewicht);
        return null;
    }

    @Override
    public int getFreeSvnr() throws PersistenceException {
        logger.info("getFreeSvnr()");
        return 0;
    }

    @Override
    public boolean isFreeSvnr(int svnr) throws PersistenceException {
        logger.info("isFreeSvnr("+svnr+")");
        return false;
    }
}
