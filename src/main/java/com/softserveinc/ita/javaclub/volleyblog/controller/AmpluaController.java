package com.softserveinc.ita.javaclub.volleyblog.controller;

import com.softserveinc.ita.javaclub.volleyblog.model.Amplua;
import com.softserveinc.ita.javaclub.volleyblog.service.AmpluaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        List<Amplua> allAmplua = ampluaService.getAllAmplua();
        return ResponseEntity.ok(allAmplua);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Amplua> findById(@PathVariable int id) {
        Amplua amplua = ampluaService.findById(id);
        if (amplua == null) {
            return new ResponseEntity("No amplua with ampluaId = " + id, HttpStatus.NOT_ACCEPTABLE);
        } else return ResponseEntity.ok(amplua);
    }

    @PostMapping()
    public ResponseEntity<Amplua> saveNewAmplua(@RequestBody Amplua amplua) {
        if ((amplua.getAmpluaId() != null) && (amplua.getAmpluaId() !=0)) {
            return new ResponseEntity("Redundant parameter: ampluaId must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        Amplua newAmplua = ampluaService.save(amplua);
        return ResponseEntity.ok(newAmplua);
    }

    @PutMapping()
    public ResponseEntity<Amplua> updateAmplua(@RequestBody Amplua amplua) {
        if ((amplua.getAmpluaId() == null) || (amplua.getAmpluaId() ==0)) {
            return new ResponseEntity("Missing parameter: ampluaId must be not null", HttpStatus.NOT_ACCEPTABLE);
        }
        Amplua newAmplua = ampluaService.save(amplua);
        return ResponseEntity.ok(newAmplua);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAmplua(@PathVariable int id) {
        Amplua amplua = ampluaService.findById(id);
        if (amplua == null) {
            return new ResponseEntity("No amplua with ampluaId = " + id, HttpStatus.NOT_ACCEPTABLE);
        } else {
            ampluaService.deleteById(id);
            return ResponseEntity.ok("Amplua with ID = " + id + " was deleted");
        }
    }
}
