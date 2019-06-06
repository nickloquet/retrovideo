package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Film;
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
            new Film(result.getLong("id"), result.getLong("genreid"),
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
    public Optional<Film> findById(long id) {
        try {
            String sql = "select id, genreid, titel, voorraad, gereserveerd, prijs from films where id=?";
            return Optional.of(template.queryForObject(sql, filmMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Film> findByGenre(long genreid) {
        try {
            String sql = "select id, genreid, titel, voorraad, gereserveerd, prijs from films where genreid=?";
            return Optional.of(template.queryForObject(sql, filmMapper, genreid));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }
}
