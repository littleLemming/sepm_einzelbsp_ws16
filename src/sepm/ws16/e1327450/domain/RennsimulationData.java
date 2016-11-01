package sepm.ws16.e1327450.domain;

import java.util.Map;

public class RennsimulationData {

    private int renn_id;
    private Map<Pferd, Jockey> participants;

    public RennsimulationData(int renn_id, Map<Pferd, Jockey> participants) {
        this.renn_id = renn_id;
        this.participants = participants;
    }

    public int getRenn_id() {
        return renn_id;
    }

    public void setRenn_id(int renn_id) {
        this.renn_id = renn_id;
    }

    public Map<Pferd, Jockey> getParticipants() {
        return participants;
    }

    public void setParticipants(Map<Pferd, Jockey> participants) {
        this.participants = participants;
    }
}
