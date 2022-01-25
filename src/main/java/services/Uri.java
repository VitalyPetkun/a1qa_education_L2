package services;

import utils.ConfigManager;

public enum Uri {

    JSON_PLACEHOLDER_URI(ConfigManager.getTestDataValue("jsonPlaceholderUri"));

    private String uri;

    Uri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
