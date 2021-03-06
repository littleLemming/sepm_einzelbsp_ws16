package sepm.ws16.e1327450.domain;

import java.sql.Date;

public class Jockey {

    private int svnr;
    private double koennen;
    private String name;
    private Date geburtsdatum;
    private int gewicht;

    public Jockey(int svnr, double koennen, String name, Date geburtsdatum, int gewicht) {
        this.svnr = svnr;
        this.koennen = koennen;
        this.name = name;
        this.geburtsdatum = geburtsdatum;
        this.gewicht = gewicht;
    }

    public int getSvnr() {
        return svnr;
    }

    public void setSvnr(int svnr) {
        this.svnr = svnr;
    }

    public double getKoennen() {
        return koennen;
    }

    public void setKoennen(double koennen) {
        this.koennen = koennen;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jockey jockey = (Jockey) o;

        if (svnr != jockey.svnr) return false;
        if (Double.compare(jockey.koennen, koennen) != 0) return false;
        if (gewicht != jockey.gewicht) return false;
        if (name != null ? !name.equals(jockey.name) : jockey.name != null) return false;
        return geburtsdatum != null ? geburtsdatum.equals(jockey.geburtsdatum) : jockey.geburtsdatum == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = svnr;
        temp = Double.doubleToLongBits(koennen);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (geburtsdatum != null ? geburtsdatum.hashCode() : 0);
        result = 31 * result + gewicht;
        return result;
    }

    @Override
    public String toString() {
        return "Jockey{" +
                "svnr=" + svnr +
                ", koennen=" + koennen +
                ", name='" + name + '\'' +
                ", geburtsdatum=" + geburtsdatum +
                ", gewicht=" + gewicht +
                '}';
    }

    public boolean isValidJockey() {
        if(gewicht < 40 || geburtsdatum == null || name == null || svnr < 0) {
            return false;
        } return true;
    }

}
