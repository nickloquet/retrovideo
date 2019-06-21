package be.vdab.retrovideo.forms;

import javax.validation.constraints.NotBlank;

public class ZoekKlantForm {
    @NotBlank
    private String naam;

    public ZoekKlantForm(String naam){
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }
}
