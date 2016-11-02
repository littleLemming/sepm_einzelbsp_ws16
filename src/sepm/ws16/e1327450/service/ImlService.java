package sepm.ws16.e1327450.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.dao.*;
import sepm.ws16.e1327450.domain.*;

import java.lang.*;
import java.sql.Connection;
import java.sql.Date;
import java.util.*;

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

    public ImlService() throws  ServiceException {
        H2DBHandler dbHandler = new H2DBHandler();
        Connection connection = dbHandler.getConnection();
        try {
            this.daoPferd = new DAOImlPferd(connection);
            this.daoJockey = new DAOImlJockey(connection);
            this.daoRennergebnis = new DAOImlRennergebnis(connection, this.daoPferd, this.daoJockey);
        } catch (PersistenceException e) {
            logger.error("could not set up DAOs");
            throw new ServiceException("failed to set up the required service");
        }
    }

    @Override
    public boolean savePferd(Pferd pferd) throws ServiceException {
        if(pferd == null) return false;
        logger.info("savePferd("+pferd.toString()+")");
        if(pferd.isValidPferd()) {
            try {
                if(!daoPferd.isFreeChip_Nr(new PferdID(pferd.getChip_nr()))) {
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
            return false;
        } return true;
    }

    @Override
    public boolean saveJockey(Jockey jockey) throws ServiceException {
        if(jockey == null) return false;
        logger.info("saveJockey("+jockey.toString()+")");
        if(jockey.isValidJockey()) {
            try {
                if(!daoJockey.isFreeSvnr(new JockeyID(jockey.getSvnr()))){
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
            return false;
        } return true;
    }

    @Override
    public Pferd loadPferd(PferdID pferdID) throws ServiceException {
        int chip_nr = pferdID.getChip_nr();
        logger.info("loadPferd("+chip_nr+")");
        Pferd pferd = null;
        try {
            pferd = daoPferd.load(pferdID);
        } catch (PersistenceException e) {
            logger.error("could not load pferd with chip-number "+chip_nr);
            throw new ServiceException(e.getMessage());
        } return pferd;
    }

    @Override
    public Jockey loadJockey(JockeyID jockeyID) throws ServiceException {
        int svnr = jockeyID.getSvnr();
        logger.info("loadJockey("+svnr+")");
        Jockey jockey = null;
        try {
            jockey = daoJockey.load(new JockeyID(svnr));
        } catch (PersistenceException e) {
            logger.error("could not load jockey with svnr "+svnr);
            throw new ServiceException(e.getMessage());
        } return jockey;
    }

    @Override
    public Rennergebnis loadRennergebnis(RennergebnisID rennergebnisID) throws ServiceException {
        int renn_id = rennergebnisID.getRenn_id();
        int chip_nr = rennergebnisID.getChip_nr();
        int svnr = rennergebnisID.getSvnr();
        logger.info("loadRennergebnis("+renn_id+","+chip_nr+","+svnr+")");
        Rennergebnis rennergebnis = null;
        try {
            rennergebnis = daoRennergebnis.load(rennergebnisID);
        } catch (PersistenceException e) {
            logger.error("could not load rennergebnis with renn-id "+renn_id+", chip-number "+chip_nr+", svnr "+svnr);
            throw new ServiceException(e.getMessage());
        } return rennergebnis;
    }

    @Override
    public boolean updatePferd(Pferd pferd) throws ServiceException {
        if(pferd == null) return false;
        logger.info("updatePferd("+pferd.toString()+")");
        if(pferd.isValidPferd()) {
            try {
                if(daoPferd.load(new PferdID(pferd.getChip_nr())) == null) {
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
            return false;
        } return true;
    }

    @Override
    public boolean updateJockey(Jockey jockey) throws ServiceException {
        if(jockey == null) return false;
        logger.info("updateJockey("+jockey.toString()+")");
        if(jockey.isValidJockey()) {
            try {
                if(daoJockey.load(new JockeyID(jockey.getSvnr())) == null){
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
            return false;
        } return true;
    }

    @Override
    public boolean validPferd(Pferd pferd) throws ServiceException {
        if(pferd == null) return false;
        logger.info("validPferd("+pferd.toString()+")");
        return pferd.isValidPferd();
    }

    @Override
    public String feedbackPferd(Pferd pferd) throws ServiceException {
        if(pferd == null) return "kein Pferd angegeben";
        logger.info("feedbackPferd("+pferd.toString()+")");
        if(validPferd(pferd)) return "Pferd in Ordnung";
        String s = "";
        if(pferd.getChip_nr() == -1) {
            s += "keine Chip-Nummer angegeben | ";
        }
        if(pferd.getAlter_jahre() < 4 || pferd.getAlter_jahre() > 30) s += "Pferd muss zwischen 4 und 30 sein um Rennen laufen zu können | ";
        if(pferd.getMin_gesw() > pferd.getMax_gesw()) s += "die minimale Geschwindigkeit muss kleiner als die maximale Geschdindigkeit sein | ";
        if(pferd.getMin_gesw() < 40) s += "die minimale Geschwindikeit muss mindesens 40 betragen | ";
        if(pferd.getMax_gesw() > 60) s += "die maximale Geschwindigketi kann maximal 60 betragen | ";
        if(pferd.getName() == null) s += "kein Name angegeben | ";
        if(pferd.getRasse() == null) s += "keine Rasse angegeben | ";
        if(pferd.getBild() == null) s += "kein Bild angegeben | ";
        //TODO check if bild valid
        if (s != null && s.length() > 3) {
            s = s.substring(0, s.length() - 3);
        }
        return s;
    }

    @Override
    public boolean validJockey(Jockey jockey) throws ServiceException {
        if(jockey == null) return false;
        logger.info("validJockey("+jockey.toString()+")");
        return jockey.isValidJockey();
    }

    @Override
    public String feedbackJockey(Jockey jockey) throws ServiceException {
        if(jockey == null) return "kein Jockey angegeben";
        logger.info("feedbackJockey("+jockey.toString()+")");
        if(validJockey(jockey)) return "Jockey in Ordnung";
        String s = "";
        if(jockey.getSvnr() < 0) s += "keine gültige Svnr angegeben | ";
        if(jockey.getGewicht() < 40) s += "untergewichtige Jockey dürfen nicht reiten - min 40 | ";
        if(jockey.getName() == null) s += "kein Name angegeben | ";
        if(jockey.getGeburtsdatum() == null) s += "kein Geburtsdatum angegeben | ";
        if (s != null && s.length() > 3) {
            s = s.substring(0, s.length() - 3);
        }
        return s;
    }

    @Override
    public void deletePferde(Pferd pferd) throws ServiceException {
        if(pferd == null) return;
        logger.info("deletePferd("+pferd.toString()+")");
        try {
            if(daoPferd.load(new PferdID(pferd.getChip_nr())) == null){
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
            if(daoJockey.load(new JockeyID(jockey.getSvnr())) == null){
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
    public List<Pferd> searchPferd(PferdCondition pferdCondition) throws ServiceException {
        logger.info("searchPferd()");
        try {
            return daoPferd.loadCondition(pferdCondition);
        } catch(PersistenceException e) {
            logger.error("could not load pferde");
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Jockey> searchJockey(JockeyCondition jockeyCondition) throws ServiceException {
        double minKönnen = jockeyCondition.getMinKönnen();
        double maxKönnen = jockeyCondition.getMaxKönnen();
        String name = jockeyCondition.getName();
        Date geburtsdatum = jockeyCondition.getGeburtsdatum();
        int minGewicht = jockeyCondition.getMinGewicht();
        int maxGewicht = jockeyCondition.getMaxGewicht();
        logger.info("searchJockey("+minKönnen+","+maxKönnen+","+name+","+geburtsdatum+","+minGewicht+","+maxGewicht+")");
        try {
            return daoJockey.loadCondition(jockeyCondition);
        } catch(PersistenceException e) {
            logger.error("could not load all pferde");
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Rennergebnis> searchRennergebnis(RennergebnisCondition rennergebnisCondition) throws ServiceException {
        if (rennergebnisCondition == null) return null;
        int renn_id = rennergebnisCondition.getRenn_id();
        int chip_nr = rennergebnisCondition.getChip_nr();
        int svnr = rennergebnisCondition.getSvnr();
        double min_gesw = rennergebnisCondition.getMin_gesw();
        double max_gesw = rennergebnisCondition.getMax_gesw();
        int min_platz = rennergebnisCondition.getMin_platz();
        int max_platz = rennergebnisCondition.getMax_platz();
        logger.info("searchRennergebnis("+renn_id+","+chip_nr+","+svnr+","+min_gesw+","+max_gesw+","+min_platz+","+max_platz+")");
        try {
            return daoRennergebnis.loadCondition(rennergebnisCondition);
        } catch(PersistenceException e) {
            logger.error("could not load all rennergebnise");
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Rennergebnis> doRennsimulation(RennsimulationData rennsimulationData) throws ServiceException {
        int renn_id = rennsimulationData.getRenn_id();
        Map<Pferd, Jockey> participants = rennsimulationData.getParticipants();
        if(participants == null) return null;
        logger.info("doRennsimulation("+renn_id+","+participants.toString()+")");
        int final_renn_id = renn_id;
        try {
            if(renn_id != -1 && !daoRennergebnis.isFreeRenn_id(new RennID(renn_id))) return null;
            if(renn_id != -1) {
                final_renn_id = renn_id;
            } else {
                final_renn_id = daoRennergebnis.getFreeRenn_id();
            }
        } catch (PersistenceException e) {
            logger.error("could sort out renn-id");
            throw new ServiceException(e.getMessage());
        }
        List<Rennergebnis> ergebnisse = new ArrayList<>();
        for(Pferd pferd : participants.keySet()) {
            try {
                Pferd p = daoPferd.load(new PferdID(pferd.getChip_nr()));
                if(p == null) {
                    logger.error("pferd with this chip-nr not in database");
                    throw new ServiceException("no pferd with chip-number " + pferd.getChip_nr());
                }
                if(!p.equals(pferd)) {
                    logger.error("different pferd in database");
                    throw new ServiceException(pferd.toString() + " not in database");
                }
            } catch (PersistenceException e) {
                logger.error("pferd with this chip-nr not in database");
                throw new ServiceException("no pferd with chip-number " + pferd.getChip_nr());
            }
            try {
                Jockey j = daoJockey.load(new JockeyID(participants.get(pferd).getSvnr()));
                if(j == null) {
                    logger.error("jockey with this svnr not in database");
                    throw new ServiceException("no jockey with svnr " + participants.get(pferd).getSvnr());
                }
                if(!j.equals(participants.get(pferd))) {
                    logger.error("different jockey in database");
                    throw new ServiceException(participants.get(pferd).toString() + " not in database");
                }
            } catch (PersistenceException e) {
                logger.error("jockey with this svnr not in database");
                throw new ServiceException("no jockey with svnr " + participants.get(pferd).getSvnr());
            }
            for(Rennergebnis rennergebnis : ergebnisse) {
                if(rennergebnis.getPferd().equals(pferd)) {
                    logger.error("pferd can't run twice in one race");
                    throw new ServiceException("pferd " + pferd.toString() + " can't run twice in race");
                } else if(rennergebnis.getJockey().equals(participants.get(pferd))) {
                    logger.error("jockey can't ride twice in one race");
                    throw new ServiceException("jockey " + participants.get(pferd).toString() + " can't ride twice in race");
                }
            }
            double kb = 1+(0.15*1/Math.PI*Math.atan(1/5*participants.get(pferd).getKoennen()));
            Random r = new Random();
            double pferdGesch = pferd.getMin_gesw() + (pferd.getMax_gesw() - pferd.getMin_gesw()) * r.nextDouble();
            double glueck = 0.95 + (1.05 - 0.95) * r.nextDouble();
            double geschw = pferdGesch*kb*glueck;
            ergebnisse.add(new Rennergebnis(final_renn_id,pferd,participants.get(pferd),geschw,pferdGesch,glueck,kb,-1));
        }
        Collections.sort(ergebnisse, new Comparator<Rennergebnis>() {
            @Override
            public int compare(Rennergebnis r1, Rennergebnis r2) {
                return Double.compare(r1.getDgeschw(), r2.getDgeschw());
            }
        });
        if(ergebnisse == null || ergebnisse.size() < 1) return ergebnisse;
        double last = ergebnisse.get(ergebnisse.size()-1).getDgeschw();
        int platz = 1;
        for(int i = ergebnisse.size()-1; i >= 0 ; i--) {
            if(ergebnisse.get(i).getDgeschw() != last) {
                platz += 1;
            } ergebnisse.get(i).setPlatz(platz);
        }
        return ergebnisse;
    }

    @Override
    public Statistik doStatistik(StatistikData statistikData) throws ServiceException {
        int chip_nr = statistikData.getChip_nr();
        int svnr = statistikData.getSvnr();
        logger.info("doStatistik("+chip_nr+","+svnr+")");
        Map<Integer,Integer> statistik = new HashMap<>();
        if(chip_nr == -1 && svnr == -1) return new Statistik(null);
        if(chip_nr != -1 && loadPferd(new PferdID(chip_nr)) == null) return new Statistik(null);
        if(svnr != -1 && loadJockey(new JockeyID(svnr)) == null) return new Statistik(null);
        List<Rennergebnis> rennergebnisList = searchRennergebnis(new RennergebnisCondition(-1,chip_nr,svnr,-1,-1,-1,-1));
        for(Rennergebnis rennergebnis : rennergebnisList) {
            logger.info(rennergebnis.toString());
        }
        for(Rennergebnis rennergebnis : rennergebnisList) {
            if(statistik.containsKey(rennergebnis.getPlatz())) {
                statistik.put(rennergebnis.getPlatz(),statistik.get(rennergebnis.getPlatz())+1);
            } else {
                statistik.put(rennergebnis.getPlatz(),1);
            }
        }
        Map<Integer,Integer> filledStatistik = new HashMap<>();
        int cnt = 0;
        while(statistik.size() != 0) {
            cnt++;
            if(statistik.containsKey(cnt)) {
                filledStatistik.put(cnt,statistik.get(cnt));
                statistik.remove(cnt);
            } else {
                filledStatistik.put(cnt,0);
            }
        }
        return new Statistik(filledStatistik);
    }

}
