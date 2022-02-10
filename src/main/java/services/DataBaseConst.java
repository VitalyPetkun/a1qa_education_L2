package services;

public enum DataBaseConst {

    TEST_TABLE("test"),
    AUTHOR_ID("");

    private String constant;

    DataBaseConst(String constant) {
        this.constant = constant;
    }

    public String getConst() {
        return constant;
    }
}