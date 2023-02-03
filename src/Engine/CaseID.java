public enum CaseID {
    // @TODO complete this id list
    PHOENIX(1),
    BREAKOUT(2);

    CaseID(int ID) {
        this.ID = ID;
    }
    private final int ID;
    public int getId() {
        return ID;
    }
}
