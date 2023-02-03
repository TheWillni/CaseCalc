public enum CaseURL {
    // @TODO complete this id list

    // On the website it shows spaces inbetween the text (on firefox anyway)
    // but once you copy the url here you'll see the true format
    // eg. https://steamcommunity.com/market/listings/730/Operation%20Bravo%20Case
    // spaces are seen as "%20".
    // only keep the text after "730/"
    FRACTURE("Fracture%20Case"),
    BRAVO("Operation%20Bravo%20Case");

    CaseURL(String url) {
        this.url = url;
    }
    private final String url;
    public String getURL() {
        return "https://steamcommunity.com/market/listings/730/" + url;
    }
}
