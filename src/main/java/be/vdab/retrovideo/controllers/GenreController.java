package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.domain.Genre;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/")
public class GenreController {
    private List<Genre> genres;
    private List<Film> films;
    private final FilmService filmService;

    public GenreController(GenreService genreService, FilmService filmService) {
        genres = genreService.findAll();
        this.filmService = filmService;
    }


    @GetMapping
    public ModelAndView genres() {
        return new ModelAndView("genres", "genres", genres);
    }
    @GetMapping("{genre}")
    public ModelAndView filmsByGenre(@PathVariable("genre") String naam) {
        for (Genre genre : genres) {
            if(naam.equals(genre.getNaam())){
                ModelAndView modelAndView = new ModelAndView("genres", "genres", genres);
                films = filmService.findByGenre(genre.getId());
                modelAndView.addObject("films", films);
                modelAndView.addObject("genre", naam);
                return modelAndView;
            }
        }
        return genres();
    }
}
