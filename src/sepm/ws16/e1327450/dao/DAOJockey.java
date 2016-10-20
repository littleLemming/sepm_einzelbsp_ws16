package sepm.ws16.e1327450.dao;

import sepm.ws16.e1327450.domain.Jockey;
import java.util.List;
import java.sql.Date;

public interface DAOJockey {

    void save(Jockey j) throws PersistenceException;

    Jockey load(int svnr) throws PersistenceException;

    void delete(Jockey j) throws PersistenceException;

    void update(Jockey j) throws PersistenceException;

    List<Jockey> loadAll() throws PersistenceException;

    List<Jockey> loadCondition(int mindKönnen, int maxKönnen, String name, Date minGeburtsdatum, Date maxGeburtsdatum, int minGewicht, int maxGewicht, boolean deleted) throws PersistenceException;

    int getFreeSvnr() throws PersistenceException;

    boolean isFreeSvnr(int svnr) throws PersistenceException;

}
