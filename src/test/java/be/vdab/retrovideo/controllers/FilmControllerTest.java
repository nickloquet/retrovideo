package be.vdab.retrovideo.controllers;

import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.sessions.Mandje;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FilmControllerTest {
    @Mock private FilmService filmService;
    @Mock private Mandje mandje;
    private FilmController controller;

    @Before
    public void before(){
        controller = new FilmController(filmService, mandje);
    }

    @Test public void filmGebruiktFilm(){
        assertThat(controller.film(1).getViewName()).isEqualTo("film");
    }
    @Test public void filmGeeftAanFilm(){
        assertThat(controller.film(1).getModel().get("film")).isInstanceOf(List.class);
    }
}
