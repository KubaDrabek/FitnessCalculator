package cz.jdrabek.fitcalc.services.localization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import cz.jdrabek.fitcalc.exception.FitCalcRuntimeException;

/**
 * Localization service
 *
 * @author jdrabek
 */
@Service
public class LocalizationService {

    private static final Locale DEFAULT_LOCALE = new Locale("cs", "CZ");

    @Autowired
    private Environment env;

    private Map<String, Locale> supportedLanguages;

    @Bean("supportedLanguages")
    public Collection<Locale> getSupportedLanguages() {
        if (supportedLanguages == null) {
            String[] suppLang = env.getProperty("fitcalc.languages.supported", String[].class);
            if (suppLang == null || suppLang.length == 0) {
                throw new FitCalcRuntimeException("No supported language configured");
            }

            supportedLanguages = new HashMap<>();
            for (String sp : suppLang) {
                String[] lang = sp.split("_");
                supportedLanguages.put(lang[0], new Locale(lang[0], lang[1]));
            }
        }

        return supportedLanguages.values();
    }

    @Bean("setLanguage")
    public Locale getSetLanguage() {
        return supportedLanguages.get(LocaleContextHolder.getLocale().getLanguage());
    }

    public static Locale getDefaultLanguage() {
        return DEFAULT_LOCALE;
    }

}