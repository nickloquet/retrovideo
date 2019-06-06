package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Genre;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository {
    List<Genre> findAll();
    Optional<Genre> findById(long id);
    Optional<Genre> findByNaam(String naam);
}
