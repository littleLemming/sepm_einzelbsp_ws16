package sepm.ws16.e1327450.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.Pferd;
import sepm.ws16.e1327450.domain.PferdCondition;
import sepm.ws16.e1327450.domain.PferdID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOImlPferd implements DAOPferd {

    final static Logger logger = LoggerFactory.getLogger(DAOImlPferd.class);
    private Connection connection;

    private String saveStmtS;
    private String loadStmtS;
    private String loadStmtAS;
    private String loadAllStmtS;
    private String deleteStmtS;
    private String updateStmtS;

    public DAOImlPferd(Connection connection) throws PersistenceException {
        logger.info("DAOImlPferd("+connection+")");
        this.connection = connection;
        saveStmtS = "INSERT INTO \"PUBLIC\".\"PFERD\"(chip_nr,name,rasse,alter_jahre,bild,min_gesw,max_gesw,deleted) VALUES (?,?,?,?,?,?,?,?)";
        loadStmtS = "SELECT * FROM PFERD WHERE chip_nr=? AND DELETED=?";
        loadStmtAS = "SELECT * FROM PFERD WHERE chip_nr=?";
        loadAllStmtS = "SELECT * FROM PFERD WHERE DELETED=?";
        deleteStmtS = "UPDATE PFERD SET DELETED=true WHERE chip_nr=?";
        updateStmtS = "UPDATE PFERD SET name=?,rasse=?,alter_jahre=?,bild=?,min_gesw=?,max_gesw=? WHERE chip_nr=?";
}

    @Override
    public void save(Pferd p) throws PersistenceException {
        if (p == null) return;
        logger.info("save("+p.toString()+")");
        try {
            PreparedStatement loadStmt = connection.prepareStatement(loadStmtS);
            loadStmt.setInt(1,p.getChip_nr());
            loadStmt.setBoolean(2,false);
            ResultSet res = loadStmt.executeQuery();
            if (res.next()) return;
            PreparedStatement saveStmt = connection.prepareStatement(saveStmtS);
            saveStmt.setInt(1,p.getChip_nr());
            saveStmt.setString(2,p.getName());
            saveStmt.setString(3,p.getRasse());
            saveStmt.setInt(4,p.getAlter_jahre());
            saveStmt.setString(5,p.getBild());
            saveStmt.setDouble(6,p.getMin_gesw());
            saveStmt.setDouble(7,p.getMax_gesw());
            saveStmt.setBoolean(8,false);
            saveStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + saveStmtS);
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public Pferd load(PferdID pferdID) throws PersistenceException {
        if(pferdID == null) return null;
        int chip_nr = pferdID.getChip_nr();
        logger.info("load("+chip_nr+")");
        try {
            PreparedStatement loadStmt = connection.prepareStatement(loadStmtS);
            loadStmt.setInt(1,chip_nr);
            loadStmt.setBoolean(2,false);
            ResultSet res = loadStmt.executeQuery();
            if (!res.next()) return null;
            return new Pferd(res.getInt("chip_nr"),res.getString("name"),res.getString("rasse"),res.getInt("alter_jahre"),res.getString("bild"),res.getDouble("min_gesw"),res.getDouble("max_gesw"));
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + loadStmtS);
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void delete(Pferd p) throws PersistenceException {
        if (p == null) return;
        logger.info("delete("+p.toString()+")");
        try {
            PreparedStatement loadStmt = connection.prepareStatement(loadStmtS);
            loadStmt.setInt(1,p.getChip_nr());
            loadStmt.setBoolean(2,false);
            ResultSet res = loadStmt.executeQuery();
            if (!res.next()) return;
            PreparedStatement deleteStmt = connection.prepareStatement(deleteStmtS);
            deleteStmt.setInt(1,p.getChip_nr());
            deleteStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + deleteStmtS);
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void update(Pferd p) throws PersistenceException {
        if (p == null) return;
        logger.info("update("+p.toString()+")");
        try {
            PreparedStatement loadStmt = connection.prepareStatement(loadStmtS);
            loadStmt.setInt(1,p.getChip_nr());
            loadStmt.setBoolean(2,false);
            ResultSet res = loadStmt.executeQuery();
            if (!res.next()) return;
            PreparedStatement updateStmt = connection.prepareStatement(updateStmtS);
            updateStmt.setString(1,p.getName());
            updateStmt.setString(2,p.getRasse());
            updateStmt.setInt(3,p.getAlter_jahre());
            updateStmt.setString(4,p.getBild());
            updateStmt.setDouble(5,p.getMin_gesw());
            updateStmt.setDouble(6,p.getMax_gesw());
            updateStmt.setInt(7,p.getChip_nr());
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + updateStmtS);
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public List<Pferd> loadAll() throws PersistenceException {
        logger.info("loadAll()");
        List<Pferd> pferdList = new ArrayList<>();
        try {
            PreparedStatement loadAllStmt = connection.prepareStatement(loadAllStmtS);
            loadAllStmt.setBoolean(1,false);
            ResultSet res = loadAllStmt.executeQuery();
            while(res.next()) {
                if(res.getString("name") != null) pferdList.add(new Pferd(res.getInt("chip_nr"),res.getString("name"),res.getString("rasse"),res.getInt("alter_jahre"),res.getString("bild"),res.getInt("min_gesw"),res.getInt("max_gesw")));
            }
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + loadAllStmtS);
            throw new PersistenceException(e.getMessage());
        }
        return pferdList;
    }

    @Override
    public List<Pferd> loadCondition(PferdCondition pferdCondition) throws PersistenceException {
        String name = pferdCondition.getName();
        int min_alter = pferdCondition.getMin_alter();
        int max_alter = pferdCondition.getMax_alter();
        double min_min_gesw = pferdCondition.getMin_min_gesw();
        double max_min_gesw = pferdCondition.getMax_min_gesw();
        double min_max_gesw = pferdCondition.getMin_max_gesw();
        double max_max_gesw = pferdCondition.getMax_max_gesw();
        logger.info("loadCondition("+name+","+min_alter+","+max_alter+","+min_min_gesw+","+max_min_gesw+","+min_max_gesw+","+max_max_gesw+")");
        List<Pferd> pferdList = new ArrayList<>();
        if(name == null && min_alter == -1 && max_alter == -1 && min_min_gesw == -1 && max_min_gesw == -1 && min_max_gesw == -1 && max_max_gesw == -1){
            return this.loadAll();
        }
        PreparedStatement loadConditionStmt;
        String sqlQuery ="SELECT * FROM PFERD WHERE";
        boolean addedFirst = false;
        if(name != null){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " name='" + name + "'";
            addedFirst = true;
        } if(min_alter != -1 ){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " alter_jahre>=" + min_alter;
            addedFirst = true;
        } if(max_alter != -1){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " alter_jahre<=" + max_alter;
            addedFirst = true;
        } if(min_min_gesw != -1 ){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " min_gesw>=" + min_min_gesw;
            addedFirst = true;
        } if(max_min_gesw != -1){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " min_gesw<=" + max_min_gesw;
        } if(min_max_gesw != -1 ){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " max_gesw>=" + min_max_gesw;
            addedFirst = true;
        } if(max_max_gesw != -1){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " max_gesw<=" + max_max_gesw;
        }
        sqlQuery += " AND deleted=false";
        try{
            loadConditionStmt = connection.prepareStatement(sqlQuery);
            ResultSet res = loadConditionStmt.executeQuery();
            while(res.next()) {
                pferdList.add(new Pferd(res.getInt("chip_nr"),res.getString("name"),res.getString("rasse"),res.getInt("alter_jahre"),res.getString("bild"),res.getInt("min_gesw"),res.getInt("max_gesw")));
            }
        }catch(SQLException e){
            logger.error("could not execute db-request: " + sqlQuery);
            throw new PersistenceException(e.getMessage());
        }
        return pferdList;
    }

    @Override
    public PferdID getFreeChip_Nr() throws PersistenceException {
        logger.info("getFreeSvnr()");
        int chip_nr = 0;
        try {
            PreparedStatement loadAllStmt = connection.prepareStatement(loadAllStmtS);
            loadAllStmt.setBoolean(1, false);
            ResultSet res = loadAllStmt.executeQuery();
            while(res.next()) {
                chip_nr++;
            }
        } catch(SQLException e) {
            logger.error("could not execute db-request: " + loadAllStmtS);
            throw new PersistenceException(e.getMessage());
        }
        try {
            PreparedStatement loadAllStmt = connection.prepareStatement(loadAllStmtS);
            loadAllStmt.setBoolean(1, true);
            ResultSet res = loadAllStmt.executeQuery();
            while(res.next()) {
                chip_nr++;
            }
        } catch(SQLException e) {
            logger.error("could not execute db-request: " + loadAllStmtS);
            throw new PersistenceException(e.getMessage());
        }
        while (true) {
            try {
                PreparedStatement loadStmt = connection.prepareStatement(loadStmtAS);
                loadStmt.setInt(1,chip_nr);
                ResultSet res = loadStmt.executeQuery();
                if (!res.next()) return new PferdID(chip_nr);
                chip_nr ++;
            } catch (SQLException e) {
                logger.error("could not execute db-request: " + loadStmtAS);
                throw new PersistenceException(e.getMessage());
            }
        }
    }

    @Override
    public boolean isFreeChip_Nr(PferdID pferdID) throws PersistenceException {
        if(pferdID == null) return false;
        int chip_nr = pferdID.getChip_nr();
        logger.info("isFreeChip_Nr("+chip_nr+")");
        try {
            PreparedStatement loadStmt = connection.prepareStatement(loadStmtAS);
            loadStmt.setInt(1,chip_nr);
            ResultSet res = loadStmt.executeQuery();
            if (!res.next()) return true;
            return false;
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + loadStmtAS);
            throw new PersistenceException(e.getMessage());
        }
    }
}
