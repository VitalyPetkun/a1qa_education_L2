package services;

import utils.ConfigManager;

public enum Uri {

    MOCKY_URI(ConfigManager.getTestDataValue("mockyUri"));

    private String uri;

    Uri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
