package sepm.ws16.e1327450.domain;

public class PferdCondition {

    private String name;
    private int min_alter;
    private int max_alter;
    private double min_min_gesw;
    private double max_min_gesw;
    private double min_max_gesw;
    private double max_max_gesw;

    public PferdCondition(String name, int min_alter, int max_alter, double min_min_gesw, double max_min_gesw, double min_max_gesw, double max_max_gesw) {
        this.name = name;
        this.max_alter = max_alter;
        this.min_alter = min_alter;
        this.min_min_gesw = min_min_gesw;
        this.max_min_gesw = max_min_gesw;
        this.min_max_gesw = min_max_gesw;
        this.max_max_gesw = max_max_gesw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMin_alter() {
        return min_alter;
    }

    public void setMin_alter(int min_alter) {
        this.min_alter = min_alter;
    }

    public int getMax_alter() {
        return max_alter;
    }

    public void setMax_alter(int max_alter) {
        this.max_alter = max_alter;
    }

    public double getMin_min_gesw() {
        return min_min_gesw;
    }

    public void setMin_min_gesw(double min_min_gesw) {
        this.min_min_gesw = min_min_gesw;
    }

    public double getMax_min_gesw() {
        return max_min_gesw;
    }

    public void setMax_min_gesw(double max_min_gesw) {
        this.max_min_gesw = max_min_gesw;
    }

    public double getMin_max_gesw() {
        return min_max_gesw;
    }

    public void setMin_max_gesw(double min_max_gesw) {
        this.min_max_gesw = min_max_gesw;
    }

    public double getMax_max_gesw() {
        return max_max_gesw;
    }

    public void setMax_max_gesw(double max_max_gesw) {
        this.max_max_gesw = max_max_gesw;
    }
}
