package model;

public class Dataset {
    private Integer n;      //number of wells
    private Integer k;      //max number of connections between wells and houses
    private Integer h;      //number of houses
    private String setOfN;  //coordinates of wells
    private String setOfH;  //coordinates of houses

    @Override
    public String toString() {
        return "Dataset{\n\t" +
                "n=" + n +
                ", \n\tk=" + k +
                ", \n\th=" + h +
                ", \n\tsetOfN=" + setOfN +
                ", \n\tsetOfH=" + setOfH +
                "\n}";
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public String getSetOfN() {
        return setOfN;
    }

    public void setSetOfN(String setOfN) {
        this.setOfN = setOfN;
    }

    public String getSetOfH() {
        return setOfH;
    }

    public void setSetOfH(String setOfH) {
        this.setOfH = setOfH;
    }
}
