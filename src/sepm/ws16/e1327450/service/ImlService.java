package sepm.ws16.e1327450.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.dao.*;
import sepm.ws16.e1327450.domain.Jockey;
import sepm.ws16.e1327450.domain.Pferd;
import sepm.ws16.e1327450.domain.Rennergebnis;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ImlService implements Service {

    final static Logger logger = LoggerFactory.getLogger(ImlService.class);
    private DAOPferd daoPferd;
    private DAOJockey daoJockey;
    private DAORennergebnis daoRennergebnis;

    public DAOPferd getDaoPferd() {
        return daoPferd;
    }

    public void setDaoPferd(DAOPferd daoPferd) {
        this.daoPferd = daoPferd;
    }

    public DAOJockey getDaoJockey() {
        return daoJockey;
    }

    public void setDaoJockey(DAOJockey daoJockey) {
        this.daoJockey = daoJockey;
    }

    public DAORennergebnis getDaoRennergebnis() {
        return daoRennergebnis;
    }

    public void setDaoRennergebnis(DAORennergebnis daoRennergebnis) {
        this.daoRennergebnis = daoRennergebnis;
    }

    @Override
    public void savePferd(Pferd pferd) throws ServiceException {
        logger.info("savePferd("+pferd.toString()+")");
        if(pferd.isValidPferd()) {
            try {
                daoPferd.isFreeChip_Nr(pferd.getChip_nr());
            } catch (PersistenceException e) {
                logger.error("could not save "+pferd.toString());
                throw new ServiceException("pferd with this chipnumber already exists");
            }
            try {
                daoPferd.save(pferd);
            } catch(PersistenceException e) {
                logger.error("could not save "+pferd.toString());
                throw new ServiceException(e.getMessage());
            }
        } else {
            logger.error("could not save "+pferd.toString());
            throw new ServiceException("invalid pferd");
        }
    }

    @Override
    public void savePferd(String chip_nr, String name, String rasse, int alter_jahre, String bild, int min_gesw, int max_gesw) throws ServiceException {
        logger.info("savePferd("+chip_nr+","+name+","+rasse+","+alter_jahre+","+bild+","+min_gesw+","+max_gesw+")");
        Pferd pferd = new Pferd(chip_nr,name,rasse,alter_jahre,bild,min_gesw,max_gesw);
        savePferd(pferd);
    }

    @Override
    public void saveJockey(Jockey jockey) throws ServiceException {
        logger.info("saveJockey("+jockey.toString()+")");
        if(jockey.isValidJockey()) {
            try {
                daoJockey.isFreeSvnr(jockey.getSvnr());
            } catch (PersistenceException e) {
                logger.error("could not save "+jockey.toString());
                throw new ServiceException("jockey with this svnr already exists");
            }
            try {
                daoJockey.save(jockey);
            } catch(PersistenceException e) {
                logger.error("could not save "+jockey.toString());
                throw new ServiceException(e.getMessage());
            }
        } else {
            logger.error("could not save "+jockey.toString());
            throw new ServiceException("invalid jockey");
        }
    }

    @Override
    public void saveJockey(int svnr, int können, String name, Date geburtsdatum, int gewicht) throws ServiceException {
        logger.info("saveJockey("+svnr+","+können+","+name+","+geburtsdatum.toString()+","+gewicht+")");
        Jockey jockey = new Jockey(svnr,können,name,geburtsdatum,gewicht);
        saveJockey(jockey);
    }

    @Override
    public Pferd loadPferd(String chip_nr) throws ServiceException {
        logger.info("loadPferd("+chip_nr+")");
        Pferd pferd = null;
        try {
            pferd = daoPferd.load(chip_nr);
        } catch (PersistenceException e) {
            logger.error("could not load "+pferd.toString());
            throw new ServiceException(e.getMessage());
        } return pferd;
    }

    @Override
    public Jockey loadJockey(int svnr) throws ServiceException {
        logger.info("loadJockey("+svnr+")");
       Jockey jockey = null;
        try {
            jockey = daoJockey.load(svnr);
        } catch (PersistenceException e) {
            logger.error("could not load "+jockey.toString());
            throw new ServiceException(e.getMessage());
        } return jockey;
    }

    @Override
    public Rennergebnis loadRennergebnis(int renn_id, String chip_nr, int svnr) throws ServiceException {
        logger.info("loadRennergebnis("+renn_id+","+chip_nr+","+svnr+")");
        Rennergebnis rennergebnis = null;
        try {
            rennergebnis = daoRennergebnis.load(renn_id,chip_nr,svnr);
        } catch (PersistenceException e) {
            logger.error("could not load "+rennergebnis.toString());
            throw new ServiceException(e.getMessage());
        } return rennergebnis;
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
