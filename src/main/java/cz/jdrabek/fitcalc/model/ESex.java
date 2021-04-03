package cz.jdrabek.fitcalc.model;

public enum ESex {

    WOMAN("Žena"),
    MAN("Muž");

    ESex(String displayValue) {
        this.displayValue = displayValue;
    }

    private String displayValue;

    public String getDisplayValue() {
        return displayValue;
    }
}
