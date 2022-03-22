package com.softserveinc.ita.javaclub.volleyblog.controller;

import com.softserveinc.ita.javaclub.volleyblog.model.Amplua;
import com.softserveinc.ita.javaclub.volleyblog.service.AmpluaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/amplua")
public class AmpluaController {


    private final AmpluaService ampluaService;

    public AmpluaController(AmpluaService ampluaService) {
        this.ampluaService = ampluaService;
    }

    @GetMapping()
    public ResponseEntity<List<Amplua>> findAll() {
        List<Amplua> allAmplua = ampluaService.findAll();
        return ResponseEntity.ok(allAmplua);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Amplua> findById(@PathVariable int id) {
        Amplua amplua = ampluaService.findById(id);
        if (amplua == null) {
            return new ResponseEntity("IN AmpluaController.findById - No amplua with ampluaId = " + id, HttpStatus.NOT_ACCEPTABLE);
        } else return ResponseEntity.ok(amplua);
    }

    @PostMapping()
    public ResponseEntity<Amplua> save(@RequestBody Amplua amplua) {
        if ((amplua.getAmpluaId() != null) && (amplua.getAmpluaId() !=0)) {
            return new ResponseEntity("IN AmpluaController.save - Redundant parameter: ampluaId must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        Amplua newAmplua = ampluaService.save(amplua);
        return ResponseEntity.ok(newAmplua);
    }

    @PutMapping()
    public ResponseEntity<Amplua> update(@RequestBody Amplua amplua) {
        if ((amplua.getAmpluaId() == null) || (amplua.getAmpluaId() ==0)) {
            return new ResponseEntity("IN AmpluaController.update - Missing parameter: ampluaId must be not null", HttpStatus.NOT_ACCEPTABLE);
        }
        Amplua updatedAmplua = null;
        try {
            updatedAmplua = ampluaService.update(amplua);
        } catch (AccessDeniedException e) {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (updatedAmplua != null) {
            return ResponseEntity.ok(updatedAmplua);
        } else {
            return new ResponseEntity("Amplua not updated", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        Amplua amplua = ampluaService.findById(id);
        if (amplua == null) {
            return new ResponseEntity("IN AmpluaController.deleteById - No amplua with ampluaId = " + id, HttpStatus.NOT_ACCEPTABLE);
        } else {
            ampluaService.deleteById(id);
            return ResponseEntity.ok("Amplua with ID = " + id + " was deleted");
        }
    }
}
