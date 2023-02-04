public enum CaseURL {
    // @TODO complete this id list

    // On the website it shows spaces inbetween the text (on firefox anyway)
    // but once you copy the url here you'll see the true format
    // eg. https://steamcommunity.com/market/listings/730/Operation%20Bravo%20Case
    // spaces are seen as "%20".
    // only keep the text after "730/"
    FRACTURE("Fracture%20Case"),
    BRAVO("Operation%20Bravo%20Case"),
    RECOIL("Recoil%20Case"),
    DREAMS_NIGHTMARES("Dreams%20%26%20Nightmares%20Case"),
    CLUTCH("Clutch%20Case"),
    SPECTRUM_2("Spectrum%202"),
    SNAKEBITE("Snakebite%Case"),
    CHROMA_3("Chroma%203%20Case"),
    CHROMA_2("Chroma%202%20Case"),
    BROKEN_FANG("Operation%20Broken%20Fang%20Case"),
    PHOENIX("Operation%20Phoenix%20Weapon%20Case"),
    BREAKOUT("Operation%20Breakout%20Weapon%20Case");






    CaseURL(String url) {
        this.url = url;
    }
    private final String url;
    public String getURL() {
        return "https://steamcommunity.com/market/listings/730/" + url;
    }
}
