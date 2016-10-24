package sepm.ws16.e1327450.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.Jockey;
import sepm.ws16.e1327450.domain.Pferd;
import sepm.ws16.e1327450.domain.Rennergebnis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DAOImlRennergebnis implements  DAORennergebnis {

    final static Logger logger = LoggerFactory.getLogger(DAOImlRennergebnis.class);
    private Connection connection;
    private DAOPferd daoPferd;
    private DAOJockey daoJockey;
    private PreparedStatement saveStmt;
    private PreparedStatement loadStmt;
    private PreparedStatement loadAllStmt;
    private PreparedStatement deleteStmt;
    private PreparedStatement updateStmt;

    public DAOImlRennergebnis(Connection connection, DAOPferd daoPferd, DAOJockey daoJockey) throws PersistenceException {
        logger.info("DAOImlRennergebnis("+connection+")");
        this.connection = connection;
        this.daoPferd = daoPferd;
        this.daoJockey = daoJockey;
        try {
            saveStmt = connection.prepareStatement("INSERT INTO \"PUBLIC\".\"RENNERGEBNIS\"(renn_id,chip_nr,svnr,geschw,platz) VALUES (?,?,?,?,?)");
            loadStmt = connection.prepareStatement("SELECT * FROM RENNERGEBNIS WHERE renn_id=? AND chip_nr=? AND svnr=?");
            loadAllStmt = connection.prepareStatement("SELECT * FROM RENNERGEBNIS");
            deleteStmt = connection.prepareStatement("DELETE FROM RENNERGEBNIS WHERE renn_id=? AND chip_nr=? AND svnr=?");
            updateStmt = connection.prepareStatement("UPDATE RENNERGEBNIS SET geschw=?, platz=? WHERE renn_id=? AND chip_nr=? AND svnr=?");
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
            loadStmt.setInt(1,r.getRenn_id());
            loadStmt.setString(2,r.getPferd().getChip_nr());
            loadStmt.setInt(3,r.getJockey().getSvnr());
            ResultSet res = loadStmt.executeQuery();
            if (res.next()) return;
            saveStmt.setInt(1,r.getRenn_id());
            saveStmt.setString(2,r.getPferd().getChip_nr());
            saveStmt.setInt(3,r.getJockey().getSvnr());
            saveStmt.setDouble(4,r.getGeschw());
            saveStmt.setInt(5,r.getPlatz());
            saveStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + saveStmt.toString());
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public Rennergebnis load(int renn_id, String chip_nr, int svnr) throws PersistenceException {
        logger.info("load("+renn_id+","+chip_nr+","+svnr+")");
        try {
            loadStmt.setInt(1,renn_id);
            loadStmt.setString(2,chip_nr);
            loadStmt.setInt(3,svnr);
            ResultSet res = loadStmt.executeQuery();
            Pferd pferd = daoPferd.load(chip_nr);
            Jockey jockey = daoJockey.load(svnr);
            if (!res.next()) { return null; }
            return new Rennergebnis(res.getInt("renn_id"),pferd,jockey,res.getDouble("geschw"),res.getInt("platz"));
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + loadStmt.toString());
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void delete(Rennergebnis r) throws PersistenceException {
        logger.info("delete("+r.toString()+")");
    }

    @Override
    public void update(Rennergebnis r) throws PersistenceException {
        logger.info("update("+r.toString()+")");
    }

    @Override
    public List<Rennergebnis> loadAll() throws PersistenceException {
        logger.info("loadAll()");
        return null;
    }

    @Override
    public List<Rennergebnis> loadCondition(int renn_id, String chip_nr, int svnr, double min_gesw, double max_gesw, int min_platz, int max_platz) throws PersistenceException {
        logger.info("loadCondition("+renn_id+","+chip_nr+","+svnr+","+min_gesw+","+max_gesw+","+min_platz+","+max_platz+")");
        return null;
    }

    @Override
    public int getFreeRenn_id() throws PersistenceException {
        logger.info("getFreeRenn_id()");
        return 0;
    }

    @Override
    public boolean isFreeRenn_id(int renn_id) throws PersistenceException {
        logger.info("isFreeRenn_id("+renn_id+")");
        return false;
    }
}
