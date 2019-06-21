package be.vdab.retrovideo.services;

import be.vdab.retrovideo.domain.Klant;
import be.vdab.retrovideo.repositories.KlantenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultKlantenService implements KlantenService{
    private final KlantenRepository klantenRepository;

    public DefaultKlantenService(KlantenRepository klantenRepository) {
        this.klantenRepository = klantenRepository;
    }

    @Override public List<Klant> findAll(){
        return klantenRepository.findAll();
    }
    @Override public List<Klant> findByString(String familienaam){
        return klantenRepository.findByString(familienaam);
    }
    @Override public Optional<Klant> findById(int id){
        return klantenRepository.findById(id);
    }
}
