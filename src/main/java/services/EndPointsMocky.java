package services;

public enum EndPointsMocky {

    V2("/v2"),
    ADDRESS("/5daedf5b320000ec71d95dd7");

    private String point;

    EndPointsMocky(String point) {
         this.point = point;
    }

    public String getPoint() {
        return point;
    }
}
