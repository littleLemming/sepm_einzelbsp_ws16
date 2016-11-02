package sepm.ws16.e1327450.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.Jockey;
import sepm.ws16.e1327450.domain.JockeyCondition;
import sepm.ws16.e1327450.domain.JockeyID;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOImlJockey implements DAOJockey {

    final static Logger logger = LoggerFactory.getLogger(DAOImlJockey.class);
    private Connection connection;

    private String saveStmtS;
    private String loadStmtS;
    private String loadAllStmtS;
    private String deleteStmtS;
    private String updateStmtS;

    public DAOImlJockey(Connection connection) throws PersistenceException {
        logger.info("DAOImlJockey("+connection+")");
        this.connection = connection;
        saveStmtS = "INSERT INTO \"PUBLIC\".\"JOCKEY\"(svnr,können,name,geburtsdatum,gewicht) VALUES (?,?,?,?,?)";
        loadStmtS = "SELECT * FROM JOCKEY WHERE svnr=?";
        loadAllStmtS = "SELECT * FROM JOCKEY";
        deleteStmtS = "DELETE FROM JOCKEY WHERE svnr=?";
        updateStmtS = "UPDATE JOCKEY SET können=?,name=?,geburtsdatum=?,gewicht=? WHERE svnr=?";
    }

    @Override
    public void save(Jockey j) throws PersistenceException {
        if (j == null) return;
        logger.info("save("+j.toString()+")");
        try {
            PreparedStatement loadStmt = connection.prepareStatement(loadStmtS);
            loadStmt.setInt(1,j.getSvnr());
            ResultSet res = loadStmt.executeQuery();
            if (res.next()) return;
            PreparedStatement saveStmt = connection.prepareStatement(saveStmtS);
            saveStmt.setInt(1,j.getSvnr());
            saveStmt.setDouble(2,j.getKoennen());
            saveStmt.setString(3,j.getName());
            saveStmt.setDate(4,j.getGeburtsdatum());
            saveStmt.setInt(5,j.getGewicht());
            saveStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + saveStmtS);
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public Jockey load(JockeyID jockeyID) throws PersistenceException {
        if(jockeyID == null) return null;
        int svnr = jockeyID.getSvnr();
        logger.info("load("+svnr+")");
        try {
            PreparedStatement loadStmt = connection.prepareStatement(loadStmtS);
            loadStmt.setInt(1,svnr);
            ResultSet res = loadStmt.executeQuery();
            if (!res.next()) return null;
            return new Jockey(res.getInt("svnr"),res.getDouble("können"),res.getString("name"),res.getDate("geburtsdatum"),res.getInt("gewicht"));
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + loadStmtS);
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void delete(Jockey j) throws PersistenceException {
        if (j == null) return;
        logger.info("delete("+j.toString()+")");
        try {
            PreparedStatement loadStmt = connection.prepareStatement(loadStmtS);
            loadStmt.setInt(1,j.getSvnr());
            ResultSet res = loadStmt.executeQuery();
            if (!res.next()) return;
            PreparedStatement deleteStmt = connection.prepareStatement(deleteStmtS);
            deleteStmt.setInt(1,j.getSvnr());
            deleteStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + deleteStmtS);
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void update(Jockey j) throws PersistenceException {
        if (j == null) return;
        logger.info("update("+j.toString()+")");
        try {
            PreparedStatement loadStmt = connection.prepareStatement(loadStmtS);
            loadStmt.setInt(1,j.getSvnr());
            ResultSet res = loadStmt.executeQuery();
            if (!res.next()) return;
            PreparedStatement updateStmt = connection.prepareStatement(updateStmtS);
            updateStmt.setDouble(1, j.getKoennen());
            updateStmt.setString(2, j.getName());
            updateStmt.setDate(3, j.getGeburtsdatum());
            updateStmt.setInt(4, j.getGewicht());
            updateStmt.setInt(5, j.getSvnr());
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + updateStmtS);
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public List<Jockey> loadAll() throws PersistenceException {
        logger.info("loadAll()");
        List<Jockey> jockeyList = new ArrayList<>();
        try {
            PreparedStatement loadAllStmt = connection.prepareStatement(loadAllStmtS);
            ResultSet res = loadAllStmt.executeQuery();
            while(res.next()) {
                jockeyList.add(new Jockey(res.getInt("svnr"),res.getDouble("können"),res.getString("name"),res.getDate("geburtsdatum"),res.getInt("gewicht")));
            }
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + loadAllStmtS);
            throw new PersistenceException(e.getMessage());
        }
        return jockeyList;
    }

    @Override
    public List<Jockey> loadCondition(JockeyCondition jockeyCondition) throws PersistenceException {
        double minKönnen = jockeyCondition.getMinKönnen();
        double maxKönnen = jockeyCondition.getMaxKönnen();
        String name = jockeyCondition.getName();
        Date geburtsdatum = jockeyCondition.getGeburtsdatum();
        int minGewicht = jockeyCondition.getMinGewicht();
        int maxGewicht = jockeyCondition.getMaxGewicht();
        logger.info("loadCondition("+minKönnen+","+maxKönnen+","+name+","+geburtsdatum+","+minGewicht+","+maxGewicht+")");
        List<Jockey> jockeyList = new ArrayList<>();
        if(minKönnen == -1 && maxKönnen == -1 && name == null && geburtsdatum == null && minGewicht == -1 && maxGewicht == -1){
            return this.loadAll();
        }
        PreparedStatement loadConditionStmt;
        String sqlQuery ="SELECT * FROM JOCKEY WHERE";
        boolean addedFirst = false;
        if(minKönnen != -1 ){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " können>=" + minKönnen;
            addedFirst = true;
        } if(maxKönnen != -1){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " können<=" + maxKönnen;
            addedFirst = true;
        } if(name != null){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " name='" + name + "'";
            addedFirst = true;
        } if(geburtsdatum != null){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " geburtsdatum='" + geburtsdatum + "'";
            addedFirst = true;
        } if(minGewicht != -1 ){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " gewicht>=" + minGewicht;
            addedFirst = true;
        } if(maxGewicht != -1){
            if(addedFirst) sqlQuery += " AND";
            sqlQuery += " gewicht<=" + maxGewicht;
        }
        try{
            loadConditionStmt = connection.prepareStatement(sqlQuery);
            ResultSet res = loadConditionStmt.executeQuery();
            while(res.next()) {
                jockeyList.add(new Jockey(res.getInt("svnr"),res.getDouble("können"),res.getString("name"),res.getDate("geburtsdatum"),res.getInt("gewicht")));
            }
        }catch(SQLException e){
            logger.error("could not execute db-request: " + sqlQuery);
            throw new PersistenceException(e.getMessage());
        }
        return jockeyList;
    }

    @Override
    public JockeyID getFreeSvnr() throws PersistenceException {
        logger.info("getFreeSvnr()");
        int svnr = loadAll().size();
        while (load(new JockeyID(svnr)) != null) svnr ++;
        return new JockeyID(svnr);
    }

    @Override
    public boolean isFreeSvnr(JockeyID jockeyID) throws PersistenceException {
        if(jockeyID == null) return false;
        int svnr = jockeyID.getSvnr();
        logger.info("isFreeSvnr("+svnr+")");
        return load(jockeyID) == null;
    }
}
