package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.model.Amplua;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

import static com.softserveinc.ita.javaclub.volleyblog.security.constants.Permissions.MANAGE_AMPLUA;

public interface AmpluaService {
    List<Amplua> getAllAmplua();

    Amplua findById(int id);

    @PreAuthorize(MANAGE_AMPLUA)
    Amplua save(Amplua amplua);

    @PreAuthorize(MANAGE_AMPLUA)
    Amplua update(Amplua amplua);

    @PreAuthorize(MANAGE_AMPLUA)
    void deleteById(int id);
}
