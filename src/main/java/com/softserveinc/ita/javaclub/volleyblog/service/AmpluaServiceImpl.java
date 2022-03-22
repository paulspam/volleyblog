package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.model.Amplua;
import com.softserveinc.ita.javaclub.volleyblog.repository.AmpluaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmpluaServiceImpl implements AmpluaService {
    private final AmpluaRepository ampluaRepository;

    public AmpluaServiceImpl(AmpluaRepository ampluaRepository) {
        this.ampluaRepository = ampluaRepository;
    }

    public List<Amplua> findAll;

    @Override
    public List<Amplua> findAll() {
        return ampluaRepository.findAll();
    }

    @Override
    public Amplua findById(int id) {
        return ampluaRepository.findById(id).orElse(null);
    }

    @Override
    public Amplua save(Amplua amplua) {
        return ampluaRepository.save(amplua);
    }

    @Override
    public Amplua update(Amplua amplua) {
        return ampluaRepository.save(amplua);
    }

    @Override
    public void deleteById(int id) {
        ampluaRepository.deleteById(id);

    }


}
