package com.example.demo.Patron;

import com.example.demo.GlobalExceptionHandler.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/patron")
public class PatronController {

    private final PatronService PatronService;

    @Autowired
    public PatronController(com.example.demo.Patron.PatronService patronService) {
        PatronService = patronService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPatrons() {
        try {
            List<Patron> patrons = PatronService.getAllPatrons();
            return new ResponseEntity<>(patrons, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("patrons not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatronById(@PathVariable Long id) {
        try {
            Patron patrons = PatronService.getPatronById(id);
            return new ResponseEntity<>(patrons, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("patrons not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> addPatron(@Valid @RequestBody Patron patron) {
        try {
            PatronService.addNewPatron(patron);
            return new ResponseEntity<>("Patron created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create Patron: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatron(@PathVariable Long id) {
        try {
            PatronService.DeletePatron(id);
            return new ResponseEntity<>("Patron deleted successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Patron not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePatron(@PathVariable Long id, @RequestBody Patron patronDetails) {
        try {
            PatronService.updatePatron(id, patronDetails);
            return new ResponseEntity<>("Patron updated successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Patron not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
