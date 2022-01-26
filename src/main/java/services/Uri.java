package services;

import utils.PropertiesManager;

public enum Uri {

    VK_URI(PropertiesManager.getTestDataValue("vkUri"));

    private String uri;

    Uri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
