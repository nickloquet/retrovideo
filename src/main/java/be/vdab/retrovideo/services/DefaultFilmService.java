package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.repositories.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultFilmService implements FilmService{
    private final FilmRepository filmRepository;
    public DefaultFilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override public List<Film> findAll(){
        return filmRepository.findAll();
    }
    @Override public Optional<Film> findById(int id){
        return filmRepository.findById(id);
    }
    @Override public List<Film> findByGenre(int genreid){
        return filmRepository.findByGenre(genreid);
    }
    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void update(Film film){ filmRepository.update(film);}
}
