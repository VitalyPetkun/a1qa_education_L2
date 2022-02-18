package services.dataBaseUnionReporting;

public enum AuthorParameters {

    VITALY_PETKUN_NAME("Vitaly Petkun"),
    VITALY_PETKUN_LOGIN("Vitaly"),
    VITALY_PETKUN_EMAIL("p.u.d.i.n.g.69.v@gmail.com");

    private String parameter;

    AuthorParameters(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
