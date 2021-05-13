package cz.jdrabek.fitcalc.model;

/**
 * Height unit enum
 *
 * @author jdrabek
 */
public enum EHeightUnit {

    CM("cm"),
    IN("inch");

    EHeightUnit(String mark) {
        this.mark = mark;
    }

    private String mark;

    public String getMark() {
       return mark;
    }

}
