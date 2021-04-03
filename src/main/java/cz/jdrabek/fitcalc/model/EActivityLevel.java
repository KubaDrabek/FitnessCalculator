package cz.jdrabek.fitcalc.model;

public enum EActivityLevel {

    MILDLY("Mírná", "Žádný trénink, sedavé zaměstnání", 1.2f),
    LIGHT("Lehká", "Neplánované aktivity, cca 2 tréninky týdně", 1.4f),
    MEDIUM("Střední", "Aktivní životní styl, 3-4 tréninky týdně", 1.6f),
    INTENSE("Intenzivní", "Manuální práce a 5-6 tréninků týdně", 2.0f);

    EActivityLevel(String displayValue, String description, float coefficient) {
        this.displayValue = displayValue;
        this.description = description;
        this.coefficient = coefficient;
    }

    private String displayValue;
    private String description;
    private float coefficient;

    public String getDisplayValue() {
        return displayValue;
    }

    public String getDescription() {
        return description;
    }

    public String getFullDescription() {
        return displayValue + " - " + description;
    }

    public float getCoefficient() {
        return coefficient;
    }
}
