package sepm.ws16.e1327450.dao;

import sepm.ws16.e1327450.domain.RennID;
import sepm.ws16.e1327450.domain.Rennergebnis;
import sepm.ws16.e1327450.domain.RennergebnisID;

import java.util.List;

public interface DAORennergebnis {

    void save(Rennergebnis r) throws PersistenceException;

    Rennergebnis load(RennergebnisID rennergebnisID) throws PersistenceException;

    List<Rennergebnis> loadAll() throws PersistenceException;

    List<Rennergebnis> loadCondition(int renn_id, int chip_nr, int svnr, double min_gesw, double max_gesw, int min_platz, int max_platz) throws PersistenceException;

    int getFreeRenn_id() throws PersistenceException;

    boolean isFreeRenn_id(RennID rennID) throws PersistenceException;
}
