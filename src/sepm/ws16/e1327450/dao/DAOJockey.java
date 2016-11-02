package sepm.ws16.e1327450.dao;

import sepm.ws16.e1327450.domain.Jockey;
import sepm.ws16.e1327450.domain.JockeyCondition;
import sepm.ws16.e1327450.domain.JockeyID;

import java.util.List;
import java.sql.Date;

public interface DAOJockey {

    void save(Jockey j) throws PersistenceException;

    Jockey load(JockeyID jockeyID) throws PersistenceException;

    void delete(Jockey j) throws PersistenceException;

    void update(Jockey j) throws PersistenceException;

    List<Jockey> loadAll() throws PersistenceException;

    List<Jockey> loadCondition(JockeyCondition jockeyCondition) throws PersistenceException;

    JockeyID getFreeSvnr() throws PersistenceException;

    boolean isFreeSvnr(JockeyID jockeyID) throws PersistenceException;

}
