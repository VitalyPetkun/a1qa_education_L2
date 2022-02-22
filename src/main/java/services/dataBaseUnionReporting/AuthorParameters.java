package services.dataBaseUnionReporting;

import static services.ConfigVariables.*;

public enum AuthorParameters {

    AUTHOR_NAME(VITALY_PETKUN_NAME.getVariable()),
    AUTHOR_LOGIN(VITALY_PETKUN_LOGIN.getVariable()),
    AUTHOR_EMAIL(VITALY_PETKUN_EMAIL.getVariable());

    private String parameter;

    AuthorParameters(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
