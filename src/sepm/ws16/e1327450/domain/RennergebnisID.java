package sepm.ws16.e1327450.domain;

public class RennergebnisID {

    int renn_id;
    int chip_nr;
    int svnr;

    public RennergebnisID(int renn_id, int chip_nr, int svnr) {
        this.renn_id = renn_id;
        this.chip_nr = chip_nr;
        this.svnr = svnr;
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
}
