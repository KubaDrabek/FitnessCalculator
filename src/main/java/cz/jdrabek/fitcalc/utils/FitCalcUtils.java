package cz.jdrabek.fitcalc.utils;

import cz.jdrabek.fitcalc.entities.CalcMacrosInputEntity;
import cz.jdrabek.fitcalc.entities.CalcMacrosOutputEntity;
import cz.jdrabek.fitcalc.model.ESex;

/**
 * TODO jakub popis
 *
 * <br>Historie: <br>
 * {{SVN-LOG}}
 * @author jakub on 2021-03-12
 */
public class FitCalcUtils {

    private FitCalcUtils() {
        throw new UnsupportedOperationException();
    }

    public static CalcMacrosOutputEntity calcMacros(CalcMacrosInputEntity input) {
        float lbm = input.getWeight() * (100-input.getFat()) / 100;
        float bmr = input.getSex() == ESex.MAN ? 66f + (13.7f * lbm) + (5f * input.getHeight()) - (6.8f * input.getAge())
                : 665f + (9.6f * lbm) + (1.8f * input.getHeight()) - (4.7f * input.getAge());
        float tdee = bmr * input.getActivity().getCoefficient();
        float tdee2 = tdee * input.getSomatotype().getCoefficient();
        float tdee3 = tdee2 * input.getSportGoal().getCoefficient();

        float protein;
        if (input.getMaxProtein() != null && input.getMaxProtein() > 0) {
            protein = input.getMaxProtein();
        } else {
            protein = lbm * 3;
        }

        float carbs;
        if (input.getMaxCarbohydrates() != null && input.getMaxCarbohydrates() > 0) {
            carbs = input.getMaxCarbohydrates();
        } else {
            carbs = ((tdee3 - protein) * 0.65f) / 4f;
        }

        float fat = (tdee3 - (carbs*4f) - (protein*4f)) / 9;

        return CalcMacrosOutputEntity.builder()
                .lbm(lbm)
                .bmr(bmr)
                .tdee(tdee)
                .tdee2(tdee2)
                .tdee3(tdee3)
                .protein(protein)
                .carbohydrates(carbs)
                .fat(fat)
                .build();
    }
}