package cz.jdrabek.fitcalc.model;

public enum ESomatotype {

    ECTOMORPH("Ektomorf", 1.1f),
    ECTO_MEZOMORPH("Ekto-mezomorf", 1.05f),
    MESOMORPH("Mezomorf", 1.0f),
    ENDO_MESOMORPH("Endo-mezomorf", 0.95f),
    ENDOMORPH("Endomorf", 0.9f);

    ESomatotype(String displayValue, float coefficient) {
        this.displayValue = displayValue;
        this.coefficient = coefficient;
    }

    private String displayValue;
    private float coefficient;

    public String getDisplayValue() {
        return displayValue;
    }

    public float getCoefficient() {
        return coefficient;
    }
}
