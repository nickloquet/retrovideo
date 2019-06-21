package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/mandje")
public class MandjeController {
    private final Mandje mandje;
    private final FilmService filmService;

    public MandjeController(Mandje mandje, FilmService filmService) {
        this.mandje = mandje;
        this.filmService = filmService;
    }

    @GetMapping public ModelAndView toonMandje(){
        List<Film> films = filmService.findAll();
        ModelAndView modelAndView = new ModelAndView("mandje").addObject("films", films);
        if(mandje.isGevuld()){
            modelAndView.addObject("filmsInMandje",
                    films.stream().filter(film -> mandje.bevat(film.getId()))
                    .collect(Collectors.toList()));
        }
        return modelAndView;
    }

    @PostMapping public String verwijder(List<Integer> ids){
        for(int id: ids){
            mandje.remove(id);
        }
        return "redirect:/mandje";
    }
}
