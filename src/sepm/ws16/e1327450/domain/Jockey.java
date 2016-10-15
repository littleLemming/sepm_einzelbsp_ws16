package sepm.ws16.e1327450.domain;

import java.sql.Date;

public class Jockey {

    private int svnr;
    private int können;
    private String name;
    private Date geburtsdatum;
    private int gewicht;
    private boolean del;

    public Jockey(int svnr, int können, String name, Date geburtsdatum, int gewicht, boolean del) {
        this.svnr = svnr;
        this.können = können;
        this.name = name;
        this.geburtsdatum = geburtsdatum;
        this.gewicht = gewicht;
        this.del = del;
    }

    public int getSvnr() {
        return svnr;
    }

    public void setSvnr(int svnr) {
        this.svnr = svnr;
    }

    public int getKönnen() {
        return können;
    }

    public void setKönnen(int können) {
        this.können = können;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jockey jockey = (Jockey) o;

        if (svnr != jockey.svnr) return false;
        if (können != jockey.können) return false;
        if (gewicht != jockey.gewicht) return false;
        if (del != jockey.del) return false;
        if (name != null ? !name.equals(jockey.name) : jockey.name != null) return false;
        return geburtsdatum != null ? geburtsdatum.equals(jockey.geburtsdatum) : jockey.geburtsdatum == null;

    }

    @Override
    public int hashCode() {
        int result = svnr;
        result = 31 * result + können;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (geburtsdatum != null ? geburtsdatum.hashCode() : 0);
        result = 31 * result + gewicht;
        result = 31 * result + (del ? 1 : 0);
        return result;
    }
}
