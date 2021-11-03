package model;

import java.util.List;

public class NormalizedDataset {
    private Integer n;      //number of wells
    private Integer k;      //max number of connections between wells and houses
    private Integer h;      //number of houses
    private List<Point> setOfN;  //coordinates of wells
    private List<Point> setOfH;  //coordinates of houses

    @Override
    public String toString() {
        return "NormalizedDataset{\n\t" +
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

    public List<Point> getSetOfN() {
        return setOfN;
    }

    public void setSetOfN(List<Point> setOfN) {
        this.setOfN = setOfN;
    }

    public List<Point> getSetOfH() {
        return setOfH;
    }

    public void setSetOfH(List<Point> setOfH) {
        this.setOfH = setOfH;
    }
}
