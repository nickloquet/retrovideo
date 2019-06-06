package be.vdab.retrovideo.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Reservatie {
    private final long klantid;
    private final long filmid;
    @DateTimeFormat(style="S-")
    private final Date reservatie;

    public Reservatie(long klantid, long filmid, Date reservatie) {
        this.klantid = klantid;
        this.filmid = filmid;
        this.reservatie = reservatie;
    }

    public long getKlantid() {
        return klantid;
    }
    public long getFilmid() {
        return filmid;
    }
    public Date getReservatie() {
        return reservatie;
    }
}
