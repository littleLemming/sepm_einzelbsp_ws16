package sepm.ws16.e1327450.service;

import sepm.ws16.e1327450.domain.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Service {

    boolean savePferd(Pferd pferd) throws ServiceException;

    boolean saveJockey(Jockey jockey) throws ServiceException;

    Pferd loadPferd(PferdID pferdID) throws ServiceException;

    Jockey loadJockey(JockeyID jockeyID) throws ServiceException;

    Rennergebnis loadRennergebnis(int renn_id, int chip_nr, int svnr) throws ServiceException;

    boolean updatePferd(Pferd pferd) throws ServiceException;

    boolean updateJockey(Jockey jockey) throws ServiceException;

    boolean validPferd(Pferd pferd) throws ServiceException;

    String feedbackPferd(Pferd pferd) throws ServiceException;

    boolean validJockey(Jockey jockey) throws ServiceException;

    String feedbackJockey(Jockey jockey) throws ServiceException;

    void deletePferde(Pferd pferd) throws ServiceException;

    void deleteJockey(Jockey jockey) throws ServiceException;

    List<Pferd> loadAllPferd() throws ServiceException;

    List<Jockey> loadAllJockey() throws ServiceException;

    List<Rennergebnis> loadAllRennergebnis() throws ServiceException;

    List<Pferd> searchPferd(PferdCondition pferdCondition) throws ServiceException;

    List<Jockey> searchJockey(JockeyCondition jockeyCondition) throws ServiceException;

    List<Rennergebnis> searchRennergebnis(int renn_id, int chip_nr, int svnr, double min_gesw, double max_gesw, int min_platz, int max_platz) throws ServiceException;

    List<Rennergebnis> doRennsimulation(int renn_id, Map<Pferd, Jockey> participants) throws ServiceException;

    Map<Integer,Integer> doStatistik(int chip_nr, int svnr) throws ServiceException;

}