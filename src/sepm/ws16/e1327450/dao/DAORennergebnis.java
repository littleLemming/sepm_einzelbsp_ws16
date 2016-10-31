package sepm.ws16.e1327450.dao;

import sepm.ws16.e1327450.domain.Rennergebnis;
import java.util.List;

public interface DAORennergebnis {

    void save(Rennergebnis r) throws PersistenceException;

    Rennergebnis load(int renn_id, int chip_nr, int svnr) throws PersistenceException;

    void delete(Rennergebnis r) throws PersistenceException;

    void update(Rennergebnis r) throws PersistenceException;

    List<Rennergebnis> loadAll() throws PersistenceException;

    List<Rennergebnis> loadCondition(int renn_id, int chip_nr, int svnr, double min_gesw, double max_gesw, int min_platz, int max_platz) throws PersistenceException;

    int getFreeRenn_id() throws PersistenceException;

    boolean isFreeRenn_id(int renn_id) throws PersistenceException;
}
