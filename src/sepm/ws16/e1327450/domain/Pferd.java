package sepm.ws16.e1327450.domain;

public class Pferd {

    private String chip_nr;
    private String name;
    private String rasse;
    private int alter_jahre;
    private String bild;
    private boolean del;
    private int min_gesw;
    private int max_gesw;

    public Pferd(String chip_nr, String name, String rasse, int alter_jahre, String bild, boolean del, int min_gesw, int max_gesw) {
        if(chip_nr.length() != 4 || min_gesw < 40 || max_gesw > 60 || min_gesw > max_gesw) {
            return;
        }
        this.chip_nr = chip_nr;
        this.name = name;
        this.rasse = rasse;
        this.alter_jahre = alter_jahre;
        this.bild = bild;
        this.del = del;
        this.min_gesw = min_gesw;
        this.max_gesw = max_gesw;
    }

    public String getChip_nr() {
        return chip_nr;
    }

    public void setChip_nr(String chip_nr) {
        this.chip_nr = chip_nr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRasse() {
        return rasse;
    }

    public void setRasse(String rasse) {
        this.rasse = rasse;
    }

    public int getAlter_jahre() {
        return alter_jahre;
    }

    public void setAlter_jahre(int alter_jahre) {
        this.alter_jahre = alter_jahre;
    }

    public String getBild() {
        return bild;
    }

    public void setBild(String bild) {
        this.bild = bild;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public int getMin_gesw() {
        return min_gesw;
    }

    public void setMin_gesw(int min_gesw) {
        this.min_gesw = min_gesw;
    }

    public int getMax_gesw() {
        return max_gesw;
    }

    public void setMax_gesw(int max_gesw) {
        this.max_gesw = max_gesw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pferd pferd = (Pferd) o;

        if (alter_jahre != pferd.alter_jahre) return false;
        if (del != pferd.del) return false;
        if (min_gesw != pferd.min_gesw) return false;
        if (max_gesw != pferd.max_gesw) return false;
        if (chip_nr != null ? !chip_nr.equals(pferd.chip_nr) : pferd.chip_nr != null) return false;
        if (name != null ? !name.equals(pferd.name) : pferd.name != null) return false;
        if (rasse != null ? !rasse.equals(pferd.rasse) : pferd.rasse != null) return false;
        return bild != null ? bild.equals(pferd.bild) : pferd.bild == null;
    }

    @Override
    public int hashCode() {
        int result = chip_nr != null ? chip_nr.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (rasse != null ? rasse.hashCode() : 0);
        result = 31 * result + alter_jahre;
        result = 31 * result + (bild != null ? bild.hashCode() : 0);
        result = 31 * result + (del ? 1 : 0);
        result = 31 * result + min_gesw;
        result = 31 * result + max_gesw;
        return result;
    }

    @Override
    public String toString() {
        return "Pferd{" +
                "chip_nr='" + chip_nr + '\'' +
                ", name='" + name + '\'' +
                ", rasse='" + rasse + '\'' +
                ", alter_jahre=" + alter_jahre +
                ", bild='" + bild + '\'' +
                ", del=" + del +
                ", min_gesw=" + min_gesw +
                ", max_gesw=" + max_gesw +
                '}';
    }
}
