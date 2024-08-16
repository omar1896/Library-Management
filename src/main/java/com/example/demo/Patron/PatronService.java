package com.example.demo.Patron;

import com.example.demo.GlobalExceptionHandler.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {

    private final PatronRepository patronRepository;

    @Autowired
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();

    }

    public Patron getPatronById(Long PatronId) {

        return patronRepository.findById(PatronId).
                orElseThrow(() -> new EntityNotFoundException("Patron with ID " + PatronId + " not found"));

    }

    public void addNewPatron(Patron patron) {
        patronRepository.save(patron);
    }

    public void DeletePatron(Long PatronId) {
        Patron patron = patronRepository.findById(PatronId).
                orElseThrow(() -> new EntityNotFoundException("Patron with ID " + PatronId + " not found"));
        patronRepository.delete(patron);
    }

    public void updatePatron(Long existedPatronId, Patron updatedPatron) {
        Patron patron = patronRepository.findById(existedPatronId).orElseThrow(() -> new EntityNotFoundException("Patron with ID " + existedPatronId + " not found"));
        if (patron != null) {
            patron.setName(updatedPatron.getName());
            patron.setContactInformation(updatedPatron.getContactInformation());
            patronRepository.save(patron);
        }
    }
}
