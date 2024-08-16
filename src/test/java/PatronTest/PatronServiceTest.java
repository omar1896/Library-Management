package PatronTest;

import com.example.demo.GlobalExceptionHandler.EntityNotFoundException;
import com.example.demo.Patron.Patron;
import com.example.demo.Patron.PatronRepository;
import com.example.demo.Patron.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatronServiceTest {

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private PatronService patronService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPatrons_shouldReturnListOfPatrons() {
        Patron patron1 = new Patron(1L, "John Doe", "123456");
        Patron patron2 = new Patron(2L, "Jane Smith", "789101");

        when(patronRepository.findAll()).thenReturn(Arrays.asList(patron1, patron2));

        assertEquals(2, patronService.getAllPatrons().size());
    }

    @Test
    void getPatronById_whenPatronExists_shouldReturnPatron() {
        Patron patron = new Patron(1L, "John Doe", "123456");
        when(patronRepository.findById(anyLong())).thenReturn(Optional.of(patron));

        Patron foundPatron = patronService.getPatronById(1L);
        assertNotNull(foundPatron);
        assertEquals("John Doe", foundPatron.getName());
    }

    @Test
    void getPatronById_whenPatronDoesNotExist_shouldThrowException() {
        when(patronRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> patronService.getPatronById(1L));
    }

    @Test
    void addNewPatron_shouldSavePatron() {
        Patron patron = new Patron(1L, "John Doe", "123456");
        patronService.addNewPatron(patron);
        verify(patronRepository, times(1)).save(patron);
    }

    @Test
    void deletePatron_shouldDeletePatron() {
        Patron patron = new Patron(1L, "John Doe", "123456");
        when(patronRepository.findById(anyLong())).thenReturn(Optional.of(patron));

        patronService.DeletePatron(1L);
        verify(patronRepository, times(1)).delete(patron);
    }

    @Test
    void updatePatron_shouldUpdatePatron() {
        Patron patron = new Patron(1L, "John Doe", "123456");
        Patron updatedPatron = new Patron(1L, "Jane Smith", "789101");

        when(patronRepository.findById(anyLong())).thenReturn(Optional.of(patron));

        patronService.updatePatron(1L, updatedPatron);
        assertEquals("Jane Smith", patron.getName());
        verify(patronRepository, times(1)).save(patron);
    }
}
