package sepm.ws16.e1327450.domain;

import java.util.Map;

public class Statistik {

    private Map<Integer,Integer> statistik;

    public Statistik(Map<Integer, Integer> statistik) {
        this.statistik = statistik;
    }

    public Map<Integer, Integer> getStatistik() {
        return statistik;
    }

    public void setStatistik(Map<Integer, Integer> statistik) {
        this.statistik = statistik;
    }
}
