package sepm.ws16.e1327450.domain;

public class RennergebnisCondition {

    private int renn_id;
    private int chip_nr;
    private int svnr;
    private double min_gesw;
    private double max_gesw;
    private int min_platz;
    private int max_platz;

    public RennergebnisCondition(int renn_id, int chip_nr, int svnr, double min_gesw, double max_gesw, int min_platz, int max_platz) {
        this.renn_id = renn_id;
        this.chip_nr = chip_nr;
        this.svnr = svnr;
        this.min_gesw = min_gesw;
        this.max_gesw = max_gesw;
        this.min_platz = min_platz;
        this.max_platz = max_platz;
    }

    public int getRenn_id() {
        return renn_id;
    }

    public void setRenn_id(int renn_id) {
        this.renn_id = renn_id;
    }

    public int getChip_nr() {
        return chip_nr;
    }

    public void setChip_nr(int chip_nr) {
        this.chip_nr = chip_nr;
    }

    public int getSvnr() {
        return svnr;
    }

    public void setSvnr(int svnr) {
        this.svnr = svnr;
    }

    public double getMin_gesw() {
        return min_gesw;
    }

    public void setMin_gesw(double min_gesw) {
        this.min_gesw = min_gesw;
    }

    public double getMax_gesw() {
        return max_gesw;
    }

    public void setMax_gesw(double max_gesw) {
        this.max_gesw = max_gesw;
    }

    public int getMin_platz() {
        return min_platz;
    }

    public void setMin_platz(int min_platz) {
        this.min_platz = min_platz;
    }

    public int getMax_platz() {
        return max_platz;
    }

    public void setMax_platz(int max_platz) {
        this.max_platz = max_platz;
    }

}
