package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.repositories.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultFilmService implements FilmService{
    private final FilmRepository filmRepository;
    public DefaultFilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override public List<Film> findAll(){
        return filmRepository.findAll();
    }
    @Override public Optional<Film> findById(long id){
        return filmRepository.findById(id);
    }
    @Override public Optional<Film> findByGenre(long genreid){
        return filmRepository.findByGenre(genreid);
    }
}
