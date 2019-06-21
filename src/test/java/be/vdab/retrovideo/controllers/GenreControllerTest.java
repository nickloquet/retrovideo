package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.GenreService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GenreControllerTest {
    @Mock private GenreService genreService;
    @Mock private FilmService filmService;
    private GenreController controller;

    @Before public void before(){
        controller = new GenreController(genreService, filmService);
    }
    @Test public void genreGebruiktThymeleafGenres(){
        assertThat(controller.filmsByGenre("oorlog").getViewName()).isEqualTo("genres");
    }
    @Test public void genreGeeftDoorAanThymeleaf(){
        assertThat(controller.filmsByGenre("oorlog").getModel().get("genres")).isInstanceOf(List.class);
    }

}
