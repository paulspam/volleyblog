package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.model.Amplua;

import java.util.List;

public interface AmpluaService {
    List<Amplua> getAllAmplua();

    Amplua findById(int id);

    Amplua save(Amplua amplua);

    void deleteById(int id);
}
