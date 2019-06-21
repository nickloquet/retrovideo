package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/film")
public class FilmController {
    private final FilmService filmService;
    private Mandje mandje;

    public FilmController(FilmService filmService, Mandje mandje) {
        this.filmService = filmService;
        this.mandje = mandje;
    }

    @GetMapping("{id}")
    public ModelAndView film(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("film");
        filmService.findById(id).ifPresent(film -> modelAndView.addObject("film", film));
        return modelAndView;
    }

    @PostMapping("{id}/mandje")
    public String toevoegen(@PathVariable("id") int id){
        if(!mandje.bevat(id)) {
            mandje.voegToe(id);
        }
            return "redirect:/mandje";
    }
}
