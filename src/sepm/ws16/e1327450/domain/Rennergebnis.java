package sepm.ws16.e1327450.domain;

public class Rennergebnis {

    private int renn_id;
    private Pferd pferd;
    private Jockey jockey;
    private double geschw;
    private int platz;

    public Rennergebnis(int renn_id, Pferd pferd, Jockey jockey, double geschw, int platz) {
        this.renn_id = renn_id;
        this.pferd = pferd;
        this.jockey = jockey;
        this.geschw = geschw;
        this.platz = platz;
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

    public double getGeschw() {
        return geschw;
    }

    public void setGeschw(double geschw) {
        this.geschw = geschw;
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
        if (Double.compare(that.geschw, geschw) != 0) return false;
        if (platz != that.platz) return false;
        if (pferd.getClass() != that.pferd.getClass()) return false;
        if (jockey.getClass() != that.jockey.getClass()) return false;

        Pferd p0 = (Pferd) pferd;
        Pferd p1 = (Pferd) that.pferd;
        Jockey j0 = (Jockey) jockey;
        Jockey j1 = (Jockey) that.jockey;

        if (p0 != null && p1 != null && !p0.equals(p1)) return false;
        if (j0 != null && j1 != null && !j0.equals(j1)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = renn_id;
        result = 31 * result + (pferd != null ? pferd.hashCode() : 0);
        result = 31 * result + (jockey != null ? jockey.hashCode() : 0);
        temp = Double.doubleToLongBits(geschw);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + platz;
        return result;
    }

    @Override
    public String toString() {
        return "Rennergebnis{" +
                "renn_id=" + renn_id +
                ", pferd=" + pferd.toString() +
                ", jockey=" + jockey.toString() +
                ", geschw=" + geschw +
                ", platz=" + platz +
                '}';
    }
}
