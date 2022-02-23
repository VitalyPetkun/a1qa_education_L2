package services.dataBaseUnionReporting;

public enum AuthorParameters {

    AUTHOR_NAME("authorName"),
    AUTHOR_LOGIN("authorLogin"),
    AUTHOR_EMAIL("authorEmail");

    private String parameter;

    AuthorParameters(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
