package sepm.ws16.e1327450.domain;

import java.sql.Date;

public class JockeyCondition {

    double minKönnen;
    double maxKönnen;
    String name;
    Date geburtsdatum;
    int minGewicht;
    int maxGewicht;

    public JockeyCondition(double minKönnen, double maxKönnen, String name, Date geburtsdatum, int minGewicht, int maxGewicht) {
        this.minKönnen = minKönnen;
        this.maxKönnen = maxKönnen;
        this.name = name;
        this.geburtsdatum = geburtsdatum;
        this.maxGewicht = maxGewicht;
        this.minGewicht = minGewicht;
    }

    public double getMinKönnen() {
        return minKönnen;
    }

    public void setMinKönnen(double minKönnen) {
        this.minKönnen = minKönnen;
    }

    public double getMaxKönnen() {
        return maxKönnen;
    }

    public void setMaxKönnen(double maxKönnen) {
        this.maxKönnen = maxKönnen;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinGewicht() {
        return minGewicht;
    }

    public void setMinGewicht(int minGewicht) {
        this.minGewicht = minGewicht;
    }

    public int getMaxGewicht() {
        return maxGewicht;
    }

    public void setMaxGewicht(int maxGewicht) {
        this.maxGewicht = maxGewicht;
    }

}
