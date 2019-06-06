package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Film;
import java.util.List;
import java.util.Optional;

public interface FilmRepository {
    List<Film> findAll();
    Optional<Film> findById(long id);
    Optional<Film> findByGenre(long genreid);
}
