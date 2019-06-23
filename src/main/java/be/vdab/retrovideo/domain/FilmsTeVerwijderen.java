package be.vdab.retrovideo.domain;

import java.util.ArrayList;

public class FilmsTeVerwijderen {
    private ArrayList<Integer> films;

    public FilmsTeVerwijderen(ArrayList<Integer> films) {
        this.films = films;
    }

    public ArrayList<Integer> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<Integer> films) {
        this.films = films;
    }
}
