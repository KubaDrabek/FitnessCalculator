package cz.jdrabek.fitcalc.model;

public enum ESportGoal {

    FAT_LOSS("Shazování tuků", 0.85f),
    HOLD_MUSCLE("Udržování svalové hmoty", 1f),
    GAIN_MUSCLE("Nabírání svalové hmoty", 1.1f);


    ESportGoal(String displayValue, float coefficient) {
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
