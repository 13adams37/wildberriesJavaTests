package test_data;

public enum Urls {
    MAIN_PAGE("https://www.wildberries.ru/");

    private final String url;

    Urls(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
