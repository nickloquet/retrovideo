package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Klant;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcKlantenRepository implements KlantenRepository{
    private final JdbcTemplate template;
    private final RowMapper<Klant> klantMapper = (result, rowNum)->
            new Klant(result.getInt("id"),
                    result.getString("familienaam"),
                    result.getString("voornaam"),
                    result.getString("straatNummer"),
                    result.getString("postcode"),
                    result.getString("gemeente"));

    public JdbcKlantenRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override public List<Klant> findAll(){
        String sql = "select * from klanten";
        return template.query(sql, klantMapper);
    }
    @Override public List<Klant> findByString(String familienaam){
        String sql = "select * from klanten where familienaam LIKE ('%" + familienaam + "%') order by familienaam";
        return template.query(sql, klantMapper);
    }
    @Override public Optional<Klant> findById(int id){
        try {
            String sql = "select * from klanten where id = ?";
            return Optional.of(template.queryForObject(sql, klantMapper, id));
        }catch(IncorrectResultSizeDataAccessException ex){
            return Optional.empty();
        }
    }
}
