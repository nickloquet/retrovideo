package be.vdab.retrovideo.domain;

import java.math.BigDecimal;

public class Film {
    private final long id;
    private final long genreid;
    private final String titel;
    private int voorraad;
    private int gereserveerd;
    private BigDecimal prijs;

    public Film(long id, long genreid, String titel, int voorraad, int gereserveerd, BigDecimal prijs) {
        this.id = id;
        this.genreid = genreid;
        this.titel = titel;
        this.voorraad = voorraad;
        this.gereserveerd = gereserveerd;
        this.prijs = prijs;
    }

    public long getId() {
        return id;
    }
    public long getGenreid() {
        return genreid;
    }
    public String getTitel() {
        return titel;
    }
    public int getVoorraad() {
        return voorraad;
    }
    public int getGereserveerd() {
        return gereserveerd;
    }
    public BigDecimal getPrijs() {
        return prijs;
    }
}
