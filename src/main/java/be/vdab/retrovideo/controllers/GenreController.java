package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class GenreController {
    private final GenreService genreService;
    private final FilmService filmService;

    public GenreController(GenreService genreService, FilmService filmService) {
        this.genreService = genreService;
        this.filmService = filmService;
    }

    @GetMapping
    public ModelAndView genres(){
        return new ModelAndView("genres", "genres", genreService.findAll());
    }

    @GetMapping("{genreid}")
    public ModelAndView films(@PathVariable long genreid){
        return new ModelAndView("genres", "films", filmService.findByGenre(genreid));
    }
    @GetMapping("films/{id}")
    public ModelAndView film(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("film");
        filmService.findByGenre(id).ifPresent(film -> modelAndView.addObject(film));
        return modelAndView;
    }

}
