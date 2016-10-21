package sepm.ws16.e1327450.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.Pferd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOImlPferd implements DAOPferd {

    final static Logger logger = LoggerFactory.getLogger(DAOImlPferd.class);
    private Connection connection;

    private PreparedStatement saveStmt;
    private PreparedStatement loadStmt;
    private PreparedStatement loadAllStmt;
    private PreparedStatement deleteStmt;
    private PreparedStatement updateStmt;

    public DAOImlPferd(Connection connection) throws PersistenceException {
        logger.info("DAOImlPferd("+connection+")");
        this.connection = connection;
        try {
            saveStmt = connection.prepareStatement("INSERT INTO \"PUBLIC\".\"PFERD\"(chip_nr,name,rasse,alter_jahre,bild,min_gesw,max_gesw) VALUES (?,?,?,?,?,?,?)");
            loadStmt = connection.prepareStatement("SELECT * FROM PFERD WHERE chip_nr=?");
            loadAllStmt = connection.prepareStatement("SELECT * FROM PFERD");
            deleteStmt = connection.prepareStatement("DELETE FROM PFERD WHERE chip_nr=?");
            updateStmt = connection.prepareStatement("UPDATE PFERD SET name=?,rasse=?,alter_jahre=?,bild=?,min_gesw=?,max_gesw=? WHERE chip_nr=?");
        } catch (SQLException e) {
            logger.error("could not prepare statements");
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void save(Pferd p) throws PersistenceException {
        if (p == null) return;
        logger.info("save("+p.toString()+")");
        try {
            loadStmt.setString(1,p.getChip_nr());
            ResultSet res = loadStmt.executeQuery();
            if (res.next()) return;
            saveStmt.setString(1,p.getChip_nr());
            saveStmt.setString(2,p.getName());
            saveStmt.setString(3,p.getRasse());
            saveStmt.setInt(4,p.getAlter_jahre());
            saveStmt.setString(5,p.getBild());
            saveStmt.setInt(6,p.getMin_gesw());
            saveStmt.setInt(7,p.getMax_gesw());
            saveStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + saveStmt.toString());
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public Pferd load(String chip_nr) throws PersistenceException {
        logger.info("load("+chip_nr+")");
        try {
            loadStmt.setString(1,chip_nr);
            ResultSet res = loadStmt.executeQuery();
            if (!res.next()) return null;
            return new Pferd(res.getString("chip_nr"),res.getString("name"),res.getString("rasse"),res.getInt("alter_jahre"),res.getString("bild"),res.getInt("min_gesw"),res.getInt("max_gesw"));
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + loadStmt.toString());
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void delete(Pferd p) throws PersistenceException {
        if (p == null) return;
        logger.info("delete("+p.toString()+")");
        try {
            loadStmt.setString(1,p.getChip_nr());
            ResultSet res = loadStmt.executeQuery();
            if (!res.next()) return;
            deleteStmt.setString(1,p.getChip_nr());
            deleteStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + deleteStmt.toString());
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void update(Pferd p) throws PersistenceException {
        if (p == null) return;
        logger.info("update("+p.toString()+")");
        try {
            loadStmt.setString(1,p.getChip_nr());
            ResultSet res = loadStmt.executeQuery();
            logger.info("HERE");
            if (!res.next()) return;
            updateStmt.setString(1,p.getName());
            updateStmt.setString(2,p.getRasse());
            updateStmt.setInt(3,p.getAlter_jahre());
            updateStmt.setString(4,p.getBild());
            updateStmt.setInt(5,p.getMin_gesw());
            updateStmt.setInt(6,p.getMax_gesw());
            updateStmt.setString(7,p.getChip_nr());
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + updateStmt.toString());
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public List<Pferd> loadAll() throws PersistenceException {
        logger.info("loadAll()");
        List<Pferd> pferdList = new ArrayList<>();
        try {
            ResultSet res = loadAllStmt.executeQuery();
            while(res.next()) {
                pferdList.add(new Pferd(res.getString("chip_nr"),res.getString("name"),res.getString("rasse"),res.getInt("alter_jahre"),res.getString("bild"),res.getInt("min_gesw"),res.getInt("max_gesw")));
            }
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + loadAllStmt.toString());
            throw new PersistenceException(e.getMessage());
        }
        return pferdList;
    }

    @Override
    public List<Pferd> loadCondition(String name, int min_alter, int max_alter, int min_min_gesw, int max_min_gesw, int min_max_gesw, int max_max_gesw) throws PersistenceException {
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
        try{
            loadConditionStmt = connection.prepareStatement(sqlQuery);
            ResultSet res = loadConditionStmt.executeQuery();
            while(res.next()) {
                pferdList.add(new Pferd(res.getString("chip_nr"),res.getString("name"),res.getString("rasse"),res.getInt("alter_jahre"),res.getString("bild"),res.getInt("min_gesw"),res.getInt("max_gesw")));
            }
        }catch(SQLException e){
            logger.error("could not execute db-request: " + sqlQuery);
            throw new PersistenceException(e.getMessage());
        }
        return pferdList;
    }

    @Override
    public String getFreeChip_Nr() throws PersistenceException {
        logger.info("getFreeChip_Nr()");
        int chip_nr = loadAll().size();
        String chip_nr_str = ""+chip_nr;
        while(chip_nr_str.length() < 4) {
            chip_nr_str = "0"+chip_nr_str;
        }
        while (load(chip_nr_str) != null) {
            chip_nr ++;
            chip_nr_str = ""+chip_nr;
            while(chip_nr_str.length() < 4) {
                chip_nr_str = "0"+chip_nr_str;
            }
        }
        return chip_nr_str;
    }

    @Override
    public boolean isFreeChip_Nr(String chip_nr) throws PersistenceException {
        logger.info("isFreeChip_Nr("+chip_nr+")");
        return load(chip_nr) == null;
    }
}
