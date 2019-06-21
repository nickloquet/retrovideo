package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Klant;
import java.util.List;
import java.util.Optional;

public interface KlantenService {
    List<Klant> findAll();
    List<Klant> findByString(String familienaam);
    Optional<Klant> findById(int id);
}
