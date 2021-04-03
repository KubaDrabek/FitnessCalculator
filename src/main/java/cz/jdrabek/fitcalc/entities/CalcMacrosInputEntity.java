package cz.jdrabek.fitcalc.entities;

import cz.jdrabek.fitcalc.model.EActivityLevel;
import cz.jdrabek.fitcalc.model.ESex;
import cz.jdrabek.fitcalc.model.ESomatotype;
import cz.jdrabek.fitcalc.model.ESportGoal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * TODO jakub popis
 *
 * <br>Historie: <br>
 * {{SVN-LOG}}
 * @author jakub on 2021-03-12
 */
@Data
@Builder
@AllArgsConstructor
public class CalcMacrosInputEntity {

    private Float weight;
    private Float fat;
    private Float age;
    private Float height;
    private ESex sex;
    private EActivityLevel activity;
    private ESomatotype somatotype;

    private Integer maxProtein;
    private Integer maxCarbohydrates;

    private ESportGoal sportGoal;

    public CalcMacrosInputEntity() {
        sex = ESex.WOMAN;
        activity = EActivityLevel.MEDIUM;
        somatotype = ESomatotype.MESOMORPH;
        sportGoal = ESportGoal.FAT_LOSS;
    }
}