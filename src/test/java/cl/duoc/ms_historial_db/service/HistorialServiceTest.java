package cl.duoc.ms_historial_db.service;

import cl.duoc.ms_historial_db.exception.HistorialNotFoundException;
import cl.duoc.ms_historial_db.model.dto.HistorialUpdateDTO;
import cl.duoc.ms_historial_db.model.entity.Historial;
import cl.duoc.ms_historial_db.repository.HistorialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HistorialServiceTest {

    @Mock
    private HistorialRepository historialRepository;

    @InjectMocks
    private HistorialService historialService;

    private Historial historial;

    @BeforeEach
    void setUp() {
        historial = new Historial();
        historial.setId(1L);
        historial.setPacienteId(10L);
        historial.setCitaId(20L);
        historial.setFecha("2026-08-01");
        historial.setDiagnostico("Resfrío común");
        historial.setObservaciones("Reposo 3 días");
    }

    @Test
    void findAll_retornaTodosLosHistoriales() {
        when(historialRepository.findAll()).thenReturn(List.of(historial));

        List<Historial> resultado = historialService.findAll();

        assertThat(resultado).containsExactly(historial);
    }

    @Test
    void findById_retornaElHistorial_cuandoExiste() {
        when(historialRepository.findById(1L)).thenReturn(Optional.of(historial));

        Historial resultado = historialService.findById(1L);

        assertThat(resultado).isEqualTo(historial);
    }

    @Test
    void findById_lanzaExcepcion_cuandoNoExiste() {
        when(historialRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> historialService.findById(99L))
                .isInstanceOf(HistorialNotFoundException.class);
    }

    @Test
    void findByPacienteId_delegaEnElRepositorio() {
        when(historialRepository.findByPacienteId(10L)).thenReturn(historial);

        Historial resultado = historialService.findByPacienteId(10L);

        assertThat(resultado).isEqualTo(historial);
        verify(historialRepository).findByPacienteId(10L);
    }

    @Test
    void findByCitaId_delegaEnElRepositorio() {
        when(historialRepository.findByCitaId(20L)).thenReturn(historial);

        Historial resultado = historialService.findByCitaId(20L);

        assertThat(resultado).isEqualTo(historial);
        verify(historialRepository).findByCitaId(20L);
    }

    @Test
    void registerHistorial_guardaYRetornaElHistorial() {
        when(historialRepository.save(historial)).thenReturn(historial);

        Historial resultado = historialService.registerHistorial(historial);

        assertThat(resultado).isEqualTo(historial);
        verify(historialRepository).save(historial);
    }

    @Test
    void eliminarHistorial_eliminaCuandoExiste() {
        when(historialRepository.existsById(1L)).thenReturn(true);

        historialService.eliminarHistorial(1L);

        verify(historialRepository).deleteById(1L);
    }

    @Test
    void eliminarHistorial_lanzaExcepcion_cuandoNoExiste() {
        when(historialRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> historialService.eliminarHistorial(99L))
                .isInstanceOf(HistorialNotFoundException.class);

        verify(historialRepository, never()).deleteById(any());
    }

    @Test
    void actualizarHistorial_actualizaYPersisteLosDatos_cuandoExiste() {
        HistorialUpdateDTO update = new HistorialUpdateDTO(1L, 10L, 20L, "2026-08-02", "Gripe", "Reposo 5 días");
        when(historialRepository.findById(1L)).thenReturn(Optional.of(historial));
        when(historialRepository.save(historial)).thenReturn(historial);

        Historial resultado = historialService.actualizarHistorial(1L, update);

        assertThat(resultado.getDiagnostico()).isEqualTo("Gripe");
        assertThat(resultado.getObservaciones()).isEqualTo("Reposo 5 días");
        verify(historialRepository, times(1)).save(historial);
    }

    @Test
    void actualizarHistorial_lanzaExcepcion_cuandoNoExiste() {
        HistorialUpdateDTO update = new HistorialUpdateDTO(99L, 10L, 20L, "2026-08-02", "Gripe", "Reposo 5 días");
        when(historialRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> historialService.actualizarHistorial(99L, update))
                .isInstanceOf(HistorialNotFoundException.class);

        verify(historialRepository, never()).save(any());
    }
}
