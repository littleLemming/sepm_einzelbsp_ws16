package sepm.ws16.e1327450.service;

import sepm.ws16.e1327450.domain.Jockey;
import sepm.ws16.e1327450.domain.Pferd;
import sepm.ws16.e1327450.domain.Rennergebnis;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class ImlService implements Service {

    @Override
    public void savePferd(Pferd pferd) throws ServiceException {

    }

    @Override
    public void saveJockey(Jockey jockey) throws ServiceException {

    }

    @Override
    public Pferd loadPferd(String chip_nr) throws ServiceException {
        return null;
    }

    @Override
    public Jockey loadJockey(int svnr) throws ServiceException {
        return null;
    }

    @Override
    public Rennergebnis loadRennergebnis(int renn_id, String chip_nr, int svnr) throws ServiceException {
        return null;
    }

    @Override
    public void updatePferd(Pferd pferd) throws ServiceException {

    }

    @Override
    public void updateJockey(Jockey jockey) throws ServiceException {

    }

    @Override
    public void deletePferde(Pferd pferd) throws ServiceException {

    }

    @Override
    public void deleteJockey(Jockey jockey) throws ServiceException {

    }

    @Override
    public List<Pferd> loadAllPferd() throws ServiceException {
        return null;
    }

    @Override
    public List<Jockey> loadAllJockey() throws ServiceException {
        return null;
    }

    @Override
    public List<Rennergebnis> loadAllRennergebnis() throws ServiceException {
        return null;
    }

    @Override
    public List<Pferd> searchPferd(String name, int min_alter, int max_alter, int min_min_gesw, int max_min_gesw, int min_max_gesw, int max_max_gesw) throws ServiceException {
        return null;
    }

    @Override
    public List<Jockey> searchJockey(int mindKönnen, int maxKönnen, String name, Date geburtsdatum, int minGewicht, int maxGewicht) {
        return null;
    }

    @Override
    public List<Rennergebnis> searchRennergebnis(int renn_id, String chip_nr, int svnr, double min_gesw, double max_gesw, int min_platz, int max_platz) {
        return null;
    }

    @Override
    public void doRennsimulation(int renn_id, Map<Pferd, Jockey> participants) {

    }

    @Override
    public void doStatistik(Pferd pferd, Jockey jockey) {

    }

}
