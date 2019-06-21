package be.vdab.retrovideo.domain;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public class Reservatie {
    private final int klantid;
    private final int filmid;
    @DateTimeFormat(style="S-")
    private final LocalDate reservatie;

    public Reservatie(int klantid, int filmid, LocalDate reservatie) {
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
    public LocalDate getReservatie() {
        return reservatie;
    }
}
