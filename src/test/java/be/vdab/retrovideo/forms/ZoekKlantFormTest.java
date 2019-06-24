package be.vdab.retrovideo.forms;

import org.junit.Before;
import org.junit.Test;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.assertj.core.api.Assertions.assertThat;

public class ZoekKlantFormTest {
    private static final String TEKST = "blabla";
    private Validator validator;

    @Before public void before(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test public void naamOK(){
        assertThat(validator.validateValue(ZoekKlantForm.class, "naam", TEKST)).isEmpty();
    }
    @Test public void naamMoetIngevuldZijn(){
        assertThat(validator.validateValue(ZoekKlantForm.class, "naam", null)).isNotEmpty();
    }
}
