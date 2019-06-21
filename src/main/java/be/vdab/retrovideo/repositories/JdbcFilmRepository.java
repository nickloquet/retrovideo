package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.exceptions.FilmNietGevondenException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcFilmRepository implements FilmRepository {
    private final JdbcTemplate template;
    private final RowMapper<Film> filmMapper = (result, rowNum) ->
            new Film(result.getInt("id"), result.getInt("genreid"),
                    result.getString("titel"), result.getInt("voorraad"),
                    result.getInt("gereserveerd"), result.getBigDecimal("prijs"));

    public JdbcFilmRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Film> findAll() {
        String sql = "select id, genreid, titel, voorraad, gereserveerd, prijs from films order by titel";
        return template.query(sql, filmMapper);
    }
    @Override
    public Optional<Film> findById(int id) {
        try {
            String sql = "select id, genreid, titel, voorraad, gereserveerd, prijs from films where id=?";
            return Optional.of(template.queryForObject(sql, filmMapper, id));
        }catch(IncorrectResultSizeDataAccessException ex){
            return Optional.empty();
        }
    }
    @Override
    public List<Film> findByGenre(int genreid) {
            String sql = "select id, genreid, titel, voorraad, gereserveerd, prijs from films where genreid=?";
            return template.query(sql, filmMapper, genreid);
        }

    @Override
    public void update(Film film){
        String sql = "update films set voorraad=?, gereserveerd=? where id=?";
        if(template.update(sql, film.getVoorraad() -1, film.getGereserveerd() +1, film.getId())==0){
            throw new FilmNietGevondenException();
            }
        }
}
