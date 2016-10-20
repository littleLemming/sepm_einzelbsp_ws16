package sepm.ws16.e1327450.dao;

import sepm.ws16.e1327450.domain.Rennergebnis;
import java.util.List;

public interface DAORennergebnis {

    void save(Rennergebnis r) throws PersistenceException;

    Rennergebnis load(int chip_nr) throws PersistenceException;

    void delete(Rennergebnis r) throws PersistenceException;

    void update(Rennergebnis r) throws PersistenceException;

    List<Rennergebnis> loadAll() throws PersistenceException;

    List<Rennergebnis> loadCondition(int renn_id, String chip_nr, int svnr, double min_gesw, double max_gesw, int min_platz, int max_platz) throws PersistenceException;

    int getFreeChip_Nr() throws PersistenceException;

    boolean isFreeChip_Nr(int chip_nr) throws PersistenceException;
}
