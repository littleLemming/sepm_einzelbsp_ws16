package sepm.ws16.e1327450.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rennergebnis {

    final static Logger logger = LoggerFactory.getLogger(Rennergebnis.class);

    private int renn_id;
    private Pferd pferd;
    private Jockey jockey;
    private double dgeschw;
    private double pgeschw;
    private double glueck;
    private double koennen_b;
    private int platz;

    public Rennergebnis(int renn_id, Pferd pferd, Jockey jockey, double dgeschw, double pgeschw, double glueck, double koennen_b, int platz) {
        if(pferd != null && jockey != null) logger.info("Rennergebnis("+renn_id+","+pferd.toString()+","+jockey.toString()+","+dgeschw+","+pgeschw+","+glueck+","+koennen_b+","+platz+")");
        this.renn_id = renn_id;
        this.pferd = pferd;
        this.dgeschw = dgeschw;
        this.jockey = jockey;
        this.pgeschw = pgeschw;
        this.glueck = glueck;
        this.koennen_b = koennen_b;
        this.platz = platz;
    }

    public static Logger getLogger() {
        return logger;
    }

    public int getRenn_id() {
        return renn_id;
    }

    public void setRenn_id(int renn_id) {
        this.renn_id = renn_id;
    }

    public Pferd getPferd() {
        return pferd;
    }

    public void setPferd(Pferd pferd) {
        this.pferd = pferd;
    }

    public Jockey getJockey() {
        return jockey;
    }

    public void setJockey(Jockey jockey) {
        this.jockey = jockey;
    }

    public double getDgeschw() {
        return dgeschw;
    }

    public void setDgeschw(double dgeschw) {
        this.dgeschw = dgeschw;
    }

    public double getPgeschw() {
        return pgeschw;
    }

    public void setPgeschw(double pgeschw) {
        this.pgeschw = pgeschw;
    }

    public double getGlueck() {
        return glueck;
    }

    public void setGlueck(double glueck) {
        this.glueck = glueck;
    }

    public double getKoennen_b() {
        return koennen_b;
    }

    public void setKoennen_b(double koennen_b) {
        this.koennen_b = koennen_b;
    }

    public int getPlatz() {
        return platz;
    }

    public void setPlatz(int platz) {
        this.platz = platz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rennergebnis that = (Rennergebnis) o;

        if (renn_id != that.renn_id) return false;
        if (Double.compare(that.dgeschw, dgeschw) != 0) return false;
        if (Double.compare(that.pgeschw, pgeschw) != 0) return false;
        if (Double.compare(that.glueck, glueck) != 0) return false;
        if (Double.compare(that.koennen_b, koennen_b) != 0) return false;
        if (platz != that.platz) return false;
        if (pferd != null ? !pferd.equals(that.pferd) : that.pferd != null) return false;
        return jockey != null ? jockey.equals(that.jockey) : that.jockey == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = renn_id;
        result = 31 * result + (pferd != null ? pferd.hashCode() : 0);
        result = 31 * result + (jockey != null ? jockey.hashCode() : 0);
        temp = Double.doubleToLongBits(dgeschw);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pgeschw);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(glueck);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(koennen_b);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + platz;
        return result;
    }

    @Override
    public String toString() {
        return "Rennergebnis{" +
                "renn_id=" + renn_id +
                ", pferd=" + pferd +
                ", jockey=" + jockey +
                ", dgeschw=" + dgeschw +
                ", pgeschw=" + pgeschw +
                ", glueck=" + glueck +
                ", koennen_b=" + koennen_b +
                ", platz=" + platz +
                '}';
    }

}
