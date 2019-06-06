package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.domain.Genre;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.GenreService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GenreControllerTest {
    @Mock private GenreService genreService;
    @Mock private FilmService filmService;
    private GenreController controller;

    @Before public void before(){
        when(genreService.findById(1)).thenReturn(Optional.of(
                new Genre(1, "test")));
        controller = new GenreController(genreService, filmService);
    }
    @Test public void genreGebruiktThymeleafGenres(){
        assertThat(controller.genres().getViewName()).isEqualTo("genres");
    }
    @Test public void genreGeeftDoorAanThymeleaf(){
        assertThat(controller.genres().getModel().get("genres")).isInstanceOf(List.class);
    }

}
