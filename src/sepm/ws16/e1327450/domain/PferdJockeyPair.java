package sepm.ws16.e1327450.domain;

public class PferdJockeyPair {

    Pferd pferd;
    Jockey jockey;

    public PferdJockeyPair(Pferd pferd, Jockey jockey) {
        this.pferd = pferd;
        this.jockey = jockey;
    }

    public Jockey getJockey() {
        return jockey;
    }

    public void setJockey(Jockey jockey) {
        this.jockey = jockey;
    }

    public Pferd getPferd() {
        return pferd;
    }

    public void setPferd(Pferd pferd) {
        this.pferd = pferd;
    }

}
