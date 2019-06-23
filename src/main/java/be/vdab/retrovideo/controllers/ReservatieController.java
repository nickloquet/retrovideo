package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.domain.Klant;
import be.vdab.retrovideo.domain.Reservatie;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.KlantenService;
import be.vdab.retrovideo.services.ReservatieService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/reserveren")
public class ReservatieController {
    private KlantenService klantenService;
    private FilmService filmService;
    private ReservatieService reservatieService;
    private Mandje mandje;

    public ReservatieController(KlantenService klantenService, FilmService filmService, ReservatieService reservatieService, Optional<Klant> klant, Mandje mandje) {
        this.klantenService = klantenService;
        this.filmService = filmService;
        this.reservatieService = reservatieService;
        this.mandje = mandje;
    }

    @GetMapping("/{id}")
    public ModelAndView bevestigen(@PathVariable("id") int id){
        int aantal = mandje.getIds().size();
        ModelAndView modelAndView = new ModelAndView("reserveren");
        modelAndView.addObject("aantal", aantal);
        klantenService.findById(id).ifPresent(klant -> modelAndView.addObject("klant", klant));
        return modelAndView;
    }

    public void reservatie(int klantid){
        for(int filmId : mandje.getIds()){
            Reservatie reservatie = new Reservatie(klantid, filmId, LocalDate.now());
            filmService.findById(filmId).ifPresent(film -> filmService.update(film));
            reservatieService.create(reservatie);
        }
    }

    @PostMapping("/{id}/bevestig")
    public String bevestigd(@PathVariable ("id") int id){
        //nog controle of voorraad > gereserveerd
        reservatie(id);
        //else filmid en titel opslagen als 'mislukt'
        return "redirect:/rapport";
    }
}
