package sepm.ws16.e1327450.domain;

public class Pferd {

    private String chip_nr;
    private String name;
    private String rasse;
    private int alter_jahre;
    private String bild;
    private double min_gesw;
    private double max_gesw;

    public Pferd(String chip_nr, String name, String rasse, int alter_jahre, String bild, double min_gesw, double max_gesw) {
        if(chip_nr.length() != 4 || min_gesw < 40 || max_gesw > 60 || min_gesw > max_gesw) {
            return;
        }
        this.chip_nr = chip_nr;
        this.name = name;
        this.rasse = rasse;
        this.alter_jahre = alter_jahre;
        this.bild = bild;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pferd pferd = (Pferd) o;

        if (alter_jahre != pferd.alter_jahre) return false;
        if (Double.compare(pferd.min_gesw, min_gesw) != 0) return false;
        if (Double.compare(pferd.max_gesw, max_gesw) != 0) return false;
        if (chip_nr != null ? !chip_nr.equals(pferd.chip_nr) : pferd.chip_nr != null) return false;
        if (name != null ? !name.equals(pferd.name) : pferd.name != null) return false;
        if (rasse != null ? !rasse.equals(pferd.rasse) : pferd.rasse != null) return false;
        return bild != null ? bild.equals(pferd.bild) : pferd.bild == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = chip_nr != null ? chip_nr.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (rasse != null ? rasse.hashCode() : 0);
        result = 31 * result + alter_jahre;
        result = 31 * result + (bild != null ? bild.hashCode() : 0);
        temp = Double.doubleToLongBits(min_gesw);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(max_gesw);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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
                ", min_gesw=" + min_gesw +
                ", max_gesw=" + max_gesw +
                '}';
    }

    public boolean isValidPferd() {
        //TODO bild gültig
        if(chip_nr == null || chip_nr.length() != 4 || alter_jahre < 4 || alter_jahre > 30 || min_gesw > max_gesw || min_gesw < 40 || max_gesw > 60 || name == null || rasse == null || bild == null) {
            return false;
        } return true;
    }

}
