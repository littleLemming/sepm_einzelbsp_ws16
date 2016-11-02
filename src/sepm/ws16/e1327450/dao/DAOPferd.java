package sepm.ws16.e1327450.dao;

import sepm.ws16.e1327450.domain.Pferd;
import sepm.ws16.e1327450.domain.PferdCondition;
import sepm.ws16.e1327450.domain.PferdID;

import java.sql.Date;
import java.util.List;

public interface DAOPferd {

    void save(Pferd p) throws PersistenceException;

    Pferd load(PferdID pferdID) throws PersistenceException;

    void delete(Pferd p) throws PersistenceException;

    void update(Pferd p) throws PersistenceException;

    List<Pferd> loadAll() throws PersistenceException;

    List<Pferd> loadCondition(PferdCondition pferdCondition) throws PersistenceException;

    PferdID getFreeChip_Nr() throws PersistenceException;

    boolean isFreeChip_Nr(PferdID pferdID) throws PersistenceException;

}
