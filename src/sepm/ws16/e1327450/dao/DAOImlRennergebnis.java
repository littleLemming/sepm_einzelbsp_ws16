package sepm.ws16.e1327450.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOImlRennergebnis implements  DAORennergebnis {

    final static Logger logger = LoggerFactory.getLogger(DAOImlRennergebnis.class);
    private Connection connection;
    private DAOPferd daoPferd;
    private DAOJockey daoJockey;
    private PreparedStatement saveStmt;
    private PreparedStatement loadStmt;
    private PreparedStatement loadAllStmt;

    public DAOImlRennergebnis(Connection connection, DAOPferd daoPferd, DAOJockey daoJockey) throws PersistenceException {
        logger.info("DAOImlRennergebnis("+connection+")");
        this.connection = connection;
        this.daoPferd = daoPferd;
        this.daoJockey = daoJockey;
        try {
            saveStmt = connection.prepareStatement("INSERT INTO \"PUBLIC\".\"RENNERGEBNIS\"(renn_id,chip_nr,svnr,dgeschw,pgeschw,glueck,koennen_b,platz) VALUES (?,?,?,?,?,?,?,?)");
            loadStmt = connection.prepareStatement("SELECT * FROM RENNERGEBNIS WHERE renn_id=? AND chip_nr=? AND svnr=?");
            loadAllStmt = connection.prepareStatement("SELECT * FROM RENNERGEBNIS");
        } catch (SQLException e) {
            logger.error("could not prepare statements");
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void save(Rennergebnis r) throws PersistenceException {
        if (r == null) return;
        logger.info("save("+r.toString()+")");
        try {
            if(load(new RennergebnisID(r.getRenn_id(),r.getPferd().getChip_nr(),r.getJockey().getSvnr())) != null) return;
            if(loadCondition(r.getRenn_id(),r.getPferd().getChip_nr(),-1,-1,-1,-1,-1).size() != 0) return;
            if(loadCondition(r.getRenn_id(),-1,r.getJockey().getSvnr(),-1,-1,-1,-1).size() != 0) return;
            saveStmt.setInt(1,r.getRenn_id());
            saveStmt.setInt(2,r.getPferd().getChip_nr());
            saveStmt.setInt(3,r.getJockey().getSvnr());
            saveStmt.setDouble(4,r.getDgeschw());
            saveStmt.setDouble(5,r.getPgeschw());
            saveStmt.setDouble(6,r.getGlueck());
            saveStmt.setDouble(7,r.getKoennen_b());
            saveStmt.setInt(8,r.getPlatz());
            saveStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + saveStmt.toString());
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public Rennergebnis load(RennergebnisID rennergebnisID) throws PersistenceException {
        int renn_id = rennergebnisID.getRenn_id();
        int chip_nr = rennergebnisID.getChip_nr();
        int svnr = rennergebnisID.getSvnr();
        logger.info("load("+renn_id+","+chip_nr+","+svnr+")");
        try {
            loadStmt.setInt(1,renn_id);
            loadStmt.setInt(2,chip_nr);
            loadStmt.setInt(3,svnr);
            ResultSet res = loadStmt.executeQuery();
            Pferd pferd = daoPferd.load(new PferdID(chip_nr));
            Jockey jockey = daoJockey.load(new JockeyID(svnr));
            if (!res.next()) { return null; }
            return new Rennergebnis(res.getInt("renn_id"),pferd,jockey,res.getDouble("dgeschw"),res.getDouble("pgeschw"),res.getDouble("glueck"),res.getDouble("koennen_b"),res.getInt("platz"));
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + loadStmt.toString());
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public List<Rennergebnis> loadAll() throws PersistenceException {
        logger.info("loadAll()");
        List<Rennergebnis> rennergebnisList = new ArrayList<>();
        try {
            ResultSet res = loadAllStmt.executeQuery();
            while(res.next()) {
                Pferd pferd = daoPferd.load(new PferdID(res.getInt("chip_nr")));
                Jockey jockey = daoJockey.load(new JockeyID(res.getInt("svnr")));
                rennergebnisList.add(new Rennergebnis(res.getInt("renn_id"),pferd,jockey,res.getDouble("dgeschw"),res.getDouble("pgeschw"),res.getDouble("glueck"),res.getDouble("koennen_b"),res.getInt("platz")));
            }
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + loadAllStmt.toString());
            throw new PersistenceException(e.getMessage());
        }
        return rennergebnisList;
    }

    @Override
    public List<Rennergebnis> loadCondition(int renn_id, int chip_nr, int svnr, double min_gesw, double max_gesw, int min_platz, int max_platz) throws PersistenceException {
        logger.info("loadCondition("+renn_id+","+chip_nr+","+svnr+","+min_gesw+","+max_gesw+","+min_platz+","+max_platz+")");
        List<Rennergebnis> rennergebnisList = new ArrayList<>();
        if(renn_id == -1 && chip_nr == -1 && svnr == -1 && min_gesw == -1 && max_gesw == -1 && min_platz == -1 && max_platz == -1){
            return this.loadAll();
        }
        PreparedStatement loadConditionStmt;
        String sqlQuery ="SELECT * FROM RENNERGEBNIS WHERE";
        boolean addedFirst = false;
        if(renn_id != -1 ){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " renn_id=" + renn_id;
            addedFirst = true;
        } if(chip_nr != -1){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " chip_nr=" + chip_nr;
            addedFirst = true;
        } if(svnr != -1){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " svnr=" + svnr;
            addedFirst = true;
        } if(min_gesw != -1 ){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " dgeschw>=" + min_gesw;
            addedFirst = true;
        } if(max_gesw != -1){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " dgeschw<=" + max_gesw;
        } if(min_platz != -1 ){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " platz>=" + min_platz;
            addedFirst = true;
        } if(max_platz != -1){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " platz<=" + max_platz;
        }
        try{
            loadConditionStmt = connection.prepareStatement(sqlQuery);
            ResultSet res = loadConditionStmt.executeQuery();
            while(res.next()) {
                Pferd pferd = daoPferd.load(new PferdID(res.getInt("chip_nr")));
                Jockey jockey = daoJockey.load(new JockeyID(res.getInt("svnr")));
                rennergebnisList.add(new Rennergebnis(res.getInt("renn_id"),pferd,jockey,res.getDouble("dgeschw"),res.getDouble("pgeschw"),res.getDouble("glueck"),res.getDouble("koennen_b"),res.getInt("platz")));
            }
        }catch(SQLException e){
            logger.error("could not execute db-request: " + sqlQuery);
            throw new PersistenceException(e.getMessage());
        }
        return rennergebnisList;
    }

    @Override
    public int getFreeRenn_id() throws PersistenceException {
        logger.info("getFreeRenn_id()");
        int renn_id = loadAll().size();
        List<Rennergebnis> rennergebnisList = loadAll();
        for(Rennergebnis rennergebnis : rennergebnisList) {
            if(renn_id == rennergebnis.getRenn_id()) renn_id++;
        }
        return renn_id;
    }

    @Override
    public boolean isFreeRenn_id(RennID rennID) throws PersistenceException {
        int renn_id = rennID.getRenn_id();
        logger.info("isFreeRenn_id("+renn_id+")");
        List<Rennergebnis> rennergebnisList = loadAll();
        for(Rennergebnis rennergebnis : rennergebnisList) {
            if(renn_id == rennergebnis.getRenn_id()) return false;
        }
        return true;
    }
}
