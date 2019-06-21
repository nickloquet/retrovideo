package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.forms.ZoekKlantForm;
import be.vdab.retrovideo.services.KlantenService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
@RequestMapping("/klanten")
public class KlantenController {
    private final KlantenService klantenService;

    public KlantenController(KlantenService klantenService) {
        this.klantenService = klantenService;
    }

    @GetMapping("klanten/form")
    public ModelAndView zoekKlantForm(){
        return new ModelAndView("klanten").addObject(new ZoekKlantForm(null));
    }
    @GetMapping
    public ModelAndView zoekKlant(@Valid ZoekKlantForm form, Errors errors){
        ModelAndView modelAndView = new ModelAndView("klanten");
        if(errors.hasErrors()){
            return modelAndView;
        }
        return modelAndView.addObject("klanten", klantenService.findByString(form.getNaam()));
    }

}
