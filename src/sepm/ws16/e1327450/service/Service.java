package sepm.ws16.e1327450.service;

import sepm.ws16.e1327450.domain.Jockey;
import sepm.ws16.e1327450.domain.Pferd;
import sepm.ws16.e1327450.domain.Rennergebnis;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Service {

    void savePferd(Pferd pferd) throws ServiceException;

    void savePferd(String chip_nr, String name, String rasse, int alter_jahre, String bild, int min_gesw, int max_gesw) throws ServiceException;

    void saveJockey(Jockey jockey) throws ServiceException;

    void saveJockey(int svnr, int können, String name, Date geburtsdatum, int gewicht) throws ServiceException;

    Pferd loadPferd(String chip_nr) throws ServiceException;

    Jockey loadJockey(int svnr) throws ServiceException;

    Rennergebnis loadRennergebnis(int renn_id, String chip_nr, int svnr) throws ServiceException;

    void updatePferd(Pferd pferd) throws ServiceException;

    void updatePferd(String chip_nr, String name, String rasse, int alter_jahre, String bild, int min_gesw, int max_gesw) throws ServiceException;

    void updateJockey(Jockey jockey) throws ServiceException;

    void updateJockey(int svnr, int können, String name, Date geburtsdatum, int gewicht) throws ServiceException;

    void deletePferde(Pferd pferd) throws ServiceException;

    void deleteJockey(Jockey jockey) throws ServiceException;

    List<Pferd> loadAllPferd() throws ServiceException;

    List<Jockey> loadAllJockey() throws ServiceException;

    List<Rennergebnis> loadAllRennergebnis() throws ServiceException;

    List<Pferd> searchPferd(String name, int min_alter, int max_alter, int min_min_gesw, int max_min_gesw, int min_max_gesw, int max_max_gesw) throws ServiceException;

    List<Jockey> searchJockey(int mindKönnen, int maxKönnen, String name, Date geburtsdatum, int minGewicht, int maxGewicht);

    List<Rennergebnis> searchRennergebnis(int renn_id, String chip_nr, int svnr, double min_gesw, double max_gesw, int min_platz, int max_platz);

    void doRennsimulation(int renn_id, Map<Pferd, Jockey> participants);

    void doStatistik(Pferd pferd, Jockey jockey);

}