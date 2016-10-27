package sepm.ws16.e1327450.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.dao.*;
import sepm.ws16.e1327450.domain.Jockey;
import sepm.ws16.e1327450.domain.Pferd;
import sepm.ws16.e1327450.domain.Rennergebnis;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImlService implements Service {

    final static Logger logger = LoggerFactory.getLogger(ImlService.class);
    private DAOPferd daoPferd;
    private DAOJockey daoJockey;
    private DAORennergebnis daoRennergebnis;

    public ImlService(DAOPferd daoPferd, DAORennergebnis daoRennergebnis, DAOJockey daoJockey) {
        this.daoPferd = daoPferd;
        this.daoRennergebnis = daoRennergebnis;
        this.daoJockey = daoJockey;
    }

    @Override
    public void savePferd(Pferd pferd) throws ServiceException {
        if(pferd == null) return;
        logger.info("savePferd("+pferd.toString()+")");
        if(pferd.isValidPferd()) {
            try {
                if(!daoPferd.isFreeChip_Nr(pferd.getChip_nr())) {
                    logger.error("could not save "+pferd.toString());
                    throw new ServiceException("pferd with this chipnumber already exists");
                }
            } catch (PersistenceException e) {
                logger.error("could not save "+pferd.toString());
                throw new ServiceException(e.getMessage());
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
        if(name == null || rasse == null || bild == null) return;
        logger.info("savePferd("+chip_nr+","+name+","+rasse+","+alter_jahre+","+bild+","+min_gesw+","+max_gesw+")");
        Pferd pferd = new Pferd(chip_nr,name,rasse,alter_jahre,bild,min_gesw,max_gesw);
        savePferd(pferd);
    }

    @Override
    public void saveJockey(Jockey jockey) throws ServiceException {
        if(jockey == null) return;
        logger.info("saveJockey("+jockey.toString()+")");
        if(jockey.isValidJockey()) {
            try {
                if(!daoJockey.isFreeSvnr(jockey.getSvnr())){
                    logger.error("could not save "+jockey.toString());
                    throw new ServiceException("jockey with this svnr already exists");
                }
            } catch (PersistenceException e) {
                logger.error("could not save "+jockey.toString());
                throw new ServiceException(e.getMessage());
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
        if(name == null || geburtsdatum == null) return;
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
        if(chip_nr == null) return null;
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
        if(pferd == null) return;
        logger.info("updatePferd("+pferd.toString()+")");
        if(pferd.isValidPferd()) {
            try {
                if(daoPferd.load(pferd.getChip_nr()) == null) {
                    logger.error("could not update "+pferd.toString());
                    throw new ServiceException("pferd with this chipnumber does not exists");
                }
            } catch (PersistenceException e) {
                logger.error("could not update "+pferd.toString());
                throw new ServiceException(e.getMessage());
            }
            try {
                daoPferd.update(pferd);
            } catch(PersistenceException e) {
                logger.error("could not update "+pferd.toString());
                throw new ServiceException(e.getMessage());
            }
        } else {
            logger.error("could not save "+pferd.toString());
            throw new ServiceException("invalid pferd");
        }
    }

    @Override
    public void updatePferd(String chip_nr, String name, String rasse, int alter_jahre, String bild, int min_gesw, int max_gesw) throws ServiceException {
        if(name == null || rasse == null || bild == null) return;
        logger.info("updatePferd("+chip_nr+","+name+","+rasse+","+alter_jahre+","+bild+","+min_gesw+","+max_gesw+")");
        Pferd pferd = new Pferd(chip_nr,name,rasse,alter_jahre,bild,min_gesw,max_gesw);
        updatePferd(pferd);
    }

    @Override
    public void updateJockey(Jockey jockey) throws ServiceException {
        if(jockey == null) return;
        logger.info("updateJockey("+jockey.toString()+")");
        if(jockey.isValidJockey()) {
            try {
                if(daoJockey.load(jockey.getSvnr()) == null){
                    logger.error("could not update "+jockey.toString());
                    throw new ServiceException("jockey with this svnr does not exists");
                }
            } catch (PersistenceException e) {
                logger.error("could not update "+jockey.toString());
                throw new ServiceException(e.getMessage());
            }
            try {
                daoJockey.update(jockey);
            } catch(PersistenceException e) {
                logger.error("could not update "+jockey.toString());
                throw new ServiceException(e.getMessage());
            }
        } else {
            logger.error("could not save "+jockey.toString());
            throw new ServiceException("invalid jockey");
        }
    }

    @Override
    public void updateJockey(int svnr, int können, String name, Date geburtsdatum, int gewicht) throws ServiceException {
        if(name == null || geburtsdatum == null) return;
        logger.info("updateJockey("+svnr+","+können+","+name+","+geburtsdatum.toString()+","+gewicht+")");
        Jockey jockey = new Jockey(svnr,können,name,geburtsdatum,gewicht);
        updateJockey(jockey);
    }

    @Override
    public void deletePferde(Pferd pferd) throws ServiceException {
        if(pferd == null) return;
        logger.info("deletePferd("+pferd.toString()+")");
        try {
            if(daoPferd.load(pferd.getChip_nr()) == null){
                logger.error("could not delete "+pferd.toString());
                throw new ServiceException("pferd with this chip_nr does not exists");
            }
        } catch (PersistenceException e) {
            logger.error("could not delete "+pferd.toString());
            throw new ServiceException(e.getMessage());
        }
        try {
            daoPferd.delete(pferd);
        } catch (PersistenceException e) {
            logger.error("could not delete "+pferd.toString());
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteJockey(Jockey jockey) throws ServiceException {
        if(jockey == null) return;
        logger.info("deleteJockey("+jockey.toString()+")");
        try {
            if(daoJockey.load(jockey.getSvnr()) == null){
                logger.error("could not delete "+jockey.toString());
                throw new ServiceException("jockey with this svnr does not exists");
            }
        } catch (PersistenceException e) {
            logger.error("could not delete "+jockey.toString());
            throw new ServiceException(e.getMessage());
        }
        try {
            daoJockey.delete(jockey);
        } catch (PersistenceException e) {
            logger.error("could not delete "+jockey.toString());
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Pferd> loadAllPferd() throws ServiceException {
        logger.info("loadAllPferd()");
        try {
            return daoPferd.loadAll();
        } catch(PersistenceException e) {
            logger.error("could not load all pferde");
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Jockey> loadAllJockey() throws ServiceException {
        logger.info("loadAllJockey()");
        try {
            return daoJockey.loadAll();
        } catch(PersistenceException e) {
            logger.error("could not load all jockeys");
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Rennergebnis> loadAllRennergebnis() throws ServiceException {
        logger.info("loadAllRennergebnis()");
        try {
            return daoRennergebnis.loadAll();
        } catch(PersistenceException e) {
            logger.error("could not load all rennergebnise");
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Pferd> searchPferd(String name, int min_alter, int max_alter, int min_min_gesw, int max_min_gesw, int min_max_gesw, int max_max_gesw) throws ServiceException {
        logger.info("searchPferd("+name+","+min_alter+","+max_alter+","+min_min_gesw+","+max_min_gesw+","+min_max_gesw+","+max_max_gesw+")");
        try {
            return daoPferd.loadCondition(name,min_alter,max_alter,min_min_gesw,max_min_gesw,min_max_gesw,max_max_gesw);
        } catch(PersistenceException e) {
            logger.error("could not load pferde");
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Jockey> searchJockey(int minKönnen, int maxKönnen, String name, Date geburtsdatum, int minGewicht, int maxGewicht) throws ServiceException {
        logger.info("searchJockey("+minKönnen+","+maxKönnen+","+name+","+geburtsdatum+","+minGewicht+","+maxGewicht+")");
        try {
            return daoJockey.loadCondition(minKönnen,maxKönnen,name,geburtsdatum,minGewicht,maxGewicht);
        } catch(PersistenceException e) {
            logger.error("could not load all pferde");
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Rennergebnis> searchRennergebnis(int renn_id, String chip_nr, int svnr, double min_gesw, double max_gesw, int min_platz, int max_platz) throws ServiceException {
        logger.info("searchRennergebnis("+renn_id+","+chip_nr+","+svnr+","+min_gesw+","+max_gesw+","+min_platz+","+max_platz+")");
        try {
            return daoRennergebnis.loadCondition(renn_id,chip_nr,svnr,min_gesw,max_gesw,min_platz,max_platz);
        } catch(PersistenceException e) {
            logger.error("could not load all rennergebnise");
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Rennergebnis> doRennsimulation(int renn_id, Map<Pferd, Jockey> participants) throws ServiceException {
        logger.info("doRennsimulation("+renn_id+","+participants.toString()+")");
        return null;
    }

    @Override
    public Map<Integer,Integer> doStatistik(String chip_nr, int svnr) throws ServiceException {
        logger.info("doStatistik("+chip_nr+","+svnr+")");
        return null;
    }

}
