package sepm.ws16.e1327450.domain;


public class RennID {

    private int renn_id;

    public RennID(int renn_id) {
        this.renn_id = renn_id;
    }

    public int getRenn_id() {
        return renn_id;
    }

    public void setRenn_id(int renn_id) {
        this.renn_id = renn_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RennID rennID = (RennID) o;

        return renn_id == rennID.renn_id;

    }

    @Override
    public int hashCode() {
        return renn_id;
    }
}
