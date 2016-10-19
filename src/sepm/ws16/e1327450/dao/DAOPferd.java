package sepm.ws16.e1327450.dao;

import sepm.ws16.e1327450.domain.Pferd;

import java.sql.Date;
import java.util.List;

public interface DAOPferd {

    void save(Pferd p) throws PersistenceException;

    Pferd load(int chip_nr) throws PersistenceException;

    void delete(Pferd p) throws PersistenceException;

    void unDelete(Pferd p) throws PersistenceException;

    void update(Pferd p) throws PersistenceException;

    List<Pferd> loadAll() throws PersistenceException;

    List<Pferd> loadCondition(String name, int min_alter, int max_alter, boolean deleted, int min_min_gesw, int max_min_gesw, int min_max_gesw, int max_max_gesw) throws PersistenceException;

    int getFreeChip_Nr() throws PersistenceException;

    boolean isFreeChip_Nr(int chip_nr) throws PersistenceException;

}
