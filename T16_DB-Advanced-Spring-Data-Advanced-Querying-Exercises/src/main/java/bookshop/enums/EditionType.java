package bookshop.enums;

public enum EditionType {
    NORMAL(1),
    PROMO(0),
    GOLD(2);

    private int value;

    EditionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
