package sepm.ws16.e1327450.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.Jockey;
import sepm.ws16.e1327450.domain.Pferd;
import sepm.ws16.e1327450.domain.Rennergebnis;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class ImlService implements Service {

    final static Logger logger = LoggerFactory.getLogger(ImlService.class);

    @Override
    public void savePferd(Pferd pferd) throws ServiceException {
        logger.info("savePferd("+pferd.toString()+")");
    }

    @Override
    public void savePferd(String chip_nr, String name, String rasse, int alter_jahre, String bild, int min_gesw, int max_gesw) throws ServiceException {
        logger.info("savePferd("+chip_nr+","+name+","+rasse+","+alter_jahre+","+bild+","+min_gesw+","+max_gesw+")");
    }

    @Override
    public void saveJockey(Jockey jockey) throws ServiceException {
        logger.info("saveJockey("+jockey.toString()+")");
    }

    @Override
    public void saveJockey(int svnr, int können, String name, Date geburtsdatum, int gewicht) throws ServiceException {
        logger.info("saveJockey("+svnr+","+können+","+name+","+geburtsdatum.toString()+","+gewicht+")");
    }

    @Override
    public Pferd loadPferd(String chip_nr) throws ServiceException {
        logger.info("loadPferd("+chip_nr+")");
        return null;
    }

    @Override
    public Jockey loadJockey(int svnr) throws ServiceException {
        logger.info("loadJockey("+svnr+")");
        return null;
    }

    @Override
    public Rennergebnis loadRennergebnis(int renn_id, String chip_nr, int svnr) throws ServiceException {
            logger.info("loadRennergebnis("+renn_id+","+chip_nr+","+svnr+")");
        return null;
    }

    @Override
    public void updatePferd(Pferd pferd) throws ServiceException {
        logger.info("updatePferd("+pferd.toString()+")");
    }

    @Override
    public void updatePferd(String chip_nr, String name, String rasse, int alter_jahre, String bild, int min_gesw, int max_gesw) throws ServiceException {
        logger.info("updatePferd("+chip_nr+","+name+","+rasse+","+alter_jahre+","+bild+","+min_gesw+","+max_gesw+")");
    }

    @Override
    public void updateJockey(Jockey jockey) throws ServiceException {
        logger.info("updateJockey("+jockey.toString()+")");
    }

    @Override
    public void updateJockey(int svnr, int können, String name, Date geburtsdatum, int gewicht) throws ServiceException {
        logger.info("updateJockey("+svnr+","+können+","+name+","+geburtsdatum.toString()+","+gewicht+")");
    }

    @Override
    public void deletePferde(Pferd pferd) throws ServiceException {
        logger.info("deletePferd("+pferd.toString()+")");
    }

    @Override
    public void deleteJockey(Jockey jockey) throws ServiceException {
        logger.info("deleteJockey("+jockey.toString()+")");
    }

    @Override
    public List<Pferd> loadAllPferd() throws ServiceException {
        logger.info("loadAllPferd()");
        return null;
    }

    @Override
    public List<Jockey> loadAllJockey() throws ServiceException {
        logger.info("loadAllJockey()");
        return null;
    }

    @Override
    public List<Rennergebnis> loadAllRennergebnis() throws ServiceException {
        logger.info("loadAllRennergebnis()");
        return null;
    }

    @Override
    public List<Pferd> searchPferd(String name, int min_alter, int max_alter, int min_min_gesw, int max_min_gesw, int min_max_gesw, int max_max_gesw) throws ServiceException {
        logger.info("searchPferd("+name+","+min_alter+","+max_alter+","+min_min_gesw+","+max_min_gesw+","+min_max_gesw+","+max_max_gesw+")");
        return null;
    }

    @Override
    public List<Jockey> searchJockey(int minKönnen, int maxKönnen, String name, Date geburtsdatum, int minGewicht, int maxGewicht) {
        logger.info("searchJockey("+minKönnen+","+maxKönnen+","+name+","+geburtsdatum+","+minGewicht+","+maxGewicht+")");
        return null;
    }

    @Override
    public List<Rennergebnis> searchRennergebnis(int renn_id, String chip_nr, int svnr, double min_gesw, double max_gesw, int min_platz, int max_platz) {
        logger.info("searchRennergebnis("+renn_id+","+chip_nr+","+svnr+","+min_gesw+","+max_gesw+","+min_platz+","+max_platz+")");
        return null;
    }

    @Override
    public List<Rennergebnis> doRennsimulation(int renn_id, Map<Pferd, Jockey> participants) {
        logger.info("doRennsimulation("+renn_id+","+participants.toString()+")");
        return null;
    }

    @Override
    public Map<Integer,Integer> doStatistik(String chip_nr, int svnr) {
        logger.info("doStatistik("+chip_nr+","+svnr+")");
        return null;
    }

}
