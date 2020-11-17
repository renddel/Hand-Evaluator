public enum Suit {
    S("SPADES"),
    H("HEARTHS"),
    D("DIAMONDS"),
    C("CLUBS");

    private final String value;
    private Suit(final String value){
        this.value=value;
    }
    public String getValue(){
        return value;
    }
}