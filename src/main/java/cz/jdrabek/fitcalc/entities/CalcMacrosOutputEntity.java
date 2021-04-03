package cz.jdrabek.fitcalc.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class CalcMacrosOutputEntity {

    private Float lbm;
    private Float bmr;
    private Float tdee;
    private Float tdee2;
    private Float tdee3;

    private Float protein;
    private Float carbohydrates;
    private Float fat;

}