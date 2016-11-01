package sepm.ws16.e1327450.domain;

public class StatistikData {

    private int chip_nr;
    private int svnr;

    public StatistikData(int chip_nr, int svnr) {
        this.chip_nr = chip_nr;
        this.svnr = svnr;
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
