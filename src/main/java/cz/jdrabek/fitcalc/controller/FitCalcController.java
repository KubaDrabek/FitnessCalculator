package cz.jdrabek.fitcalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import cz.jdrabek.fitcalc.entities.CalcMacrosInputEntity;
import cz.jdrabek.fitcalc.entities.CalcMacrosOutputEntity;
import cz.jdrabek.fitcalc.model.EHeightUnit;
import cz.jdrabek.fitcalc.model.EWeightUnits;
import cz.jdrabek.fitcalc.services.localization.LocalizationService;

/**
 * Basic controller
 *
 * @author jdrabek
 */
@Controller
public class FitCalcController {

    @Autowired
    private LocalizationService localizationService;

    @GetMapping({"/", "/homepage"})
    public String homePage(@ModelAttribute CalcMacrosInputEntity calcMacrosInputEntity, @ModelAttribute CalcMacrosOutputEntity calcMacrosOutputEntity,
            Model model) {
        model.addAttribute("setLanguage", localizationService.getSetLanguage());
        return "homepage";
    }

    @GetMapping("/macronutrientscalc")
    public String macronutrientsCalc(@ModelAttribute CalcMacrosInputEntity calcMacrosInputEntity, @ModelAttribute CalcMacrosOutputEntity calcMacrosOutputEntity,
            Model model) {
        model.addAttribute("calcMacrosInput", calcMacrosInputEntity);
        model.addAttribute("calcMacrosOutput", calcMacrosOutputEntity);
        model.addAttribute("setLanguage", localizationService.getSetLanguage());

        model.addAttribute("basicWeightUnits", LocalizationService.isUsedImperialUnits(localizationService.getSetLanguage()) ? EWeightUnits.LB : EWeightUnits.KG);
        model.addAttribute("smallWeightUnits", LocalizationService.isUsedImperialUnits(localizationService.getSetLanguage()) ? EWeightUnits.OZ : EWeightUnits.G);
        model.addAttribute("heightUnits", LocalizationService.isUsedImperialUnits(localizationService.getSetLanguage()) ? EHeightUnit.IN : EHeightUnit.CM);

        return "macronutrients_calc";
    }

}