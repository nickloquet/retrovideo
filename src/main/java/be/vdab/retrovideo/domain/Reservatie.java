package be.vdab.retrovideo.domain;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

public class Reservatie {
    private final int klantid;
    private final int filmid;
    @DateTimeFormat(style="S-")
    private final LocalDateTime reservatie;

    public Reservatie(int klantid, int filmid, LocalDateTime reservatie) {
        this.klantid = klantid;
        this.filmid = filmid;
        this.reservatie = reservatie;
    }

    public int getKlantid() {
        return klantid;
    }
    public int getFilmid() {
        return filmid;
    }
    public LocalDateTime getReservatie() {
        return reservatie;
    }
}
