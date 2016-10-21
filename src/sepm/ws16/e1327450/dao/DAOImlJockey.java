package sepm.ws16.e1327450.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.Jockey;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOImlJockey implements DAOJockey {

    final static Logger logger = LoggerFactory.getLogger(DAOImlJockey.class);
    private Connection connection;

    private PreparedStatement saveStmt;
    private PreparedStatement loadStmt;
    private PreparedStatement loadAllStmt;
    private PreparedStatement deleteStmt;
    private PreparedStatement updateStmt;
    private PreparedStatement getFreeSvnrStmt;
    private PreparedStatement isFreeSvnrStmt;

    public DAOImlJockey(Connection connection) throws PersistenceException {
        logger.info("DAOImlJockey("+connection+")");
        this.connection = connection;
        try {
            saveStmt = connection.prepareStatement("INSERT INTO \"PUBLIC\".\"JOCKEY\"(svnr,können,name,geburtsdatum,gewicht) VALUES (?,?,?,?,?)");
            loadStmt = connection.prepareStatement("SELECT * FROM JOCKEY WHERE svnr=?");
            loadAllStmt = connection.prepareStatement("SELECT * FROM JOCKEY");
            deleteStmt = connection.prepareStatement("DELETE FROM JOCKEY WHERE svnr=?");
            updateStmt = connection.prepareStatement("UPDATE JOCKEY SET können=?,name=?,geburtsdatum=?,gewicht=? WHERE svnr=?");
        } catch (SQLException e) {
            logger.error("could not prepare statements");
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void save(Jockey j) throws PersistenceException {
        if (j == null) return;
        logger.info("save("+j.toString()+")");
        try {
            loadStmt.setInt(1,j.getSvnr());
            ResultSet res = loadStmt.executeQuery();
            if (res.next()) return;
            saveStmt.setInt(1,j.getSvnr());
            saveStmt.setInt(2,j.getKönnen());
            saveStmt.setString(3,j.getName());
            saveStmt.setDate(4,j.getGeburtsdatum());
            saveStmt.setInt(5,j.getGewicht());
            saveStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + saveStmt.toString());
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public Jockey load(int svnr) throws PersistenceException {
        logger.info("load("+svnr+")");
        try {
            loadStmt.setInt(1,svnr);
            ResultSet res = loadStmt.executeQuery();
            if (!res.next()) return null;
            return new Jockey(res.getInt("svnr"),res.getInt("können"),res.getString("name"),res.getDate("geburtsdatum"),res.getInt("gewicht"));
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + loadStmt.toString());
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void delete(Jockey j) throws PersistenceException {
        if (j == null) return;
        logger.info("delete("+j.toString()+")");
        try {
            loadStmt.setInt(1,j.getSvnr());
            ResultSet res = loadStmt.executeQuery();
            if (!res.next()) return;
            deleteStmt.setInt(1,j.getSvnr());
            deleteStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + deleteStmt.toString());
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void update(Jockey j) throws PersistenceException {
        if (j == null) return;
        logger.info("update("+j.toString()+")");
        try {
            loadStmt.setInt(1,j.getSvnr());
            ResultSet res = loadStmt.executeQuery();
            if (!res.next()) return;
            saveStmt.setInt(1,j.getKönnen());
            saveStmt.setString(2,j.getName());
            saveStmt.setDate(3,j.getGeburtsdatum());
            saveStmt.setInt(4,j.getGewicht());
            saveStmt.setInt(5,j.getSvnr());
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + updateStmt.toString());
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public List<Jockey> loadAll() throws PersistenceException {
        logger.info("loadAll()");
        List<Jockey> jockeyList = new ArrayList<>();
        try {
            ResultSet res = loadAllStmt.executeQuery();
            while(res.next()) {
                jockeyList.add(new Jockey(res.getInt("svnr"),res.getInt("können"),res.getString("name"),res.getDate("geburtsdatum"),res.getInt("gewicht")));
            }
        } catch (SQLException e) {
            logger.error("could not execute db-request: " + loadAllStmt.toString());
            throw new PersistenceException(e.getMessage());
        }
        return jockeyList;
    }

    @Override
    public List<Jockey> loadCondition(int minKönnen, int maxKönnen, String name, Date geburtsdatum, int minGewicht, int maxGewicht) throws PersistenceException {
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
                jockeyList.add(new Jockey(res.getInt("svnr"),res.getInt("können"),res.getString("name"),res.getDate("geburtsdatum"),res.getInt("gewicht")));
            }
        }catch(SQLException e){
            logger.error("could not execute db-request: " + sqlQuery);
            throw new PersistenceException(e.getMessage());
        }
        return jockeyList;
    }

    @Override
    public int getFreeSvnr() throws PersistenceException {
        logger.info("getFreeSvnr()");
        int svnr = loadAll().size();
        while (load(svnr) != null) svnr ++;
        return svnr;
    }

    @Override
    public boolean isFreeSvnr(int svnr) throws PersistenceException {
        logger.info("isFreeSvnr("+svnr+")");
        return load(svnr) != null;
    }
}
