package PatronTest;

import com.example.demo.GlobalExceptionHandler.EntityNotFoundException;
import com.example.demo.Patron.Patron;
import com.example.demo.Patron.PatronController;
import com.example.demo.Patron.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatronControllerTest {

    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPatrons_shouldReturnPatronsList() {
        Patron patron1 = new Patron(1L, "John Doe", "123456");
        Patron patron2 = new Patron(2L, "Jane Smith", "789101");

        when(patronService.getAllPatrons()).thenReturn(Arrays.asList(patron1, patron2));

        ResponseEntity<?> response = patronController.getAllPatrons();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, ((List<Patron>) response.getBody()).size());
    }

    @Test
    void getPatronById_shouldReturnPatronWhenExists() {
        Patron patron = new Patron(1L, "John Doe", "123456");

        when(patronService.getPatronById(anyLong())).thenReturn(patron);

        ResponseEntity<?> response = patronController.getPatronById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John Doe", ((Patron) response.getBody()).getName());
    }

    @Test
    void getPatronById_shouldReturnNotFoundWhenPatronDoesNotExist() {
        when(patronService.getPatronById(anyLong())).thenThrow(new EntityNotFoundException("Patron not found"));

        ResponseEntity<?> response = patronController.getPatronById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void addPatron_shouldReturnCreatedStatus() {
        Patron patron = new Patron(1L, "John Doe", "123456");

        ResponseEntity<String> response = patronController.addPatron(patron);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Patron created successfully", response.getBody());
    }

    @Test
    void deletePatron_shouldReturnSuccessWhenPatronExists() {
        ResponseEntity<String> response = patronController.deletePatron(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updatePatron_shouldReturnSuccessWhenPatronExists() {
        Patron patron = new Patron(1L, "Jane Smith", "789101");

        ResponseEntity<String> response = patronController.updatePatron(1L, patron);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
