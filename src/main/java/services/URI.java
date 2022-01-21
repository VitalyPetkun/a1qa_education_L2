package services;

public enum URI {

    BASE_URI("https://jsonplaceholder.typicode.com");

    private String uri;

    URI(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
