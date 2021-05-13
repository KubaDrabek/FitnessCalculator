package cz.jdrabek.fitcalc.model;

import java.util.Arrays;
import java.util.List;

/**
 * Weight units enum used in FitCalc
 *
 * @author jdrabek
 */
public enum EWeightUnits {

    KG("kg"),
    LB("lb"),

    G("g"),
    OZ("oz");

    private String mark;

    EWeightUnits(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public static List<EWeightUnits> getBasicUnits() {
        return Arrays.asList(KG, LB);
    }

    public static List<EWeightUnits> getSmallUnits() {
        return Arrays.asList(G, OZ);
    }
}