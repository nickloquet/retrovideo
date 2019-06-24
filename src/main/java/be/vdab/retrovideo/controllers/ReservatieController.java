package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.domain.Film;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    public ModelAndView bevestigen(@PathVariable("id") int id) {
        int aantal = mandje.getIds().size();
        ModelAndView modelAndView = new ModelAndView("reserveren");
        modelAndView.addObject("aantal", aantal);
        klantenService.findById(id).ifPresent(klant -> modelAndView.addObject("klant", klant));
        return modelAndView;
    }

    public List<Film> reservatie(int klantid) {
        List<Film> films = new ArrayList<>();
        for (int filmId : mandje.getIds()) {
            Film film = filmService.findById(filmId).get();
            if (film.getVoorraad() >= film.getGereserveerd()) {
                Reservatie reservatie = new Reservatie(klantid, filmId, LocalDateTime.now());
                filmService.findById(filmId).ifPresent(filmpje -> filmService.update(filmpje));
                reservatieService.create(reservatie);
            } else {
                filmService.findById(filmId).ifPresent(filmpje -> films.add(filmpje));
            }
            return films;
        }
        return Collections.emptyList();
    }

    @PostMapping("/{id}/bevestig")
    public String bevestigd(@PathVariable("id") int id) {
        return "redirect:/reserveren/{id}/rapport";
    }
    @GetMapping("/{id}/rapport")
    public ModelAndView rapport(@PathVariable("id") int id){
        List<Film> films = reservatie(id);
        ModelAndView modelAndView =  new ModelAndView("rapport","mislukt", films);
        return modelAndView;
    }
}
