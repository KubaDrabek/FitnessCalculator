package cz.jdrabek.fitcalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import cz.jdrabek.fitcalc.entities.CalcMacrosInputEntity;
import cz.jdrabek.fitcalc.entities.CalcMacrosOutputEntity;
import cz.jdrabek.fitcalc.services.localization.LocalizationService;

/**
 * TODO jakub popis
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
        return "macronutrients_calc";
    }

}