package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Reservatie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcReservatieRepository implements ReservatieRepository{
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;

    public JdbcReservatieRepository(JdbcTemplate template) {
        this.template = template;
        this.insert = new SimpleJdbcInsert(template);
        insert.withTableName("reservaties");
    }

    @Override
    public void create(Reservatie reservatie){
        Map<String, Object> kolomwaarden = new HashMap<>();
        kolomwaarden.put("klantid", reservatie.getKlantid());
        kolomwaarden.put("filmid", reservatie.getFilmid());
        kolomwaarden.put("reservatie", reservatie.getReservatie());
        insert.execute(kolomwaarden);
    }
}
