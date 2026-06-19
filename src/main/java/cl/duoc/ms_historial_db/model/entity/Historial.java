package cl.duoc.ms_historial_db.model.entity;

import cl.duoc.ms_historial_db.model.dto.HistorialUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "historial")

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_paciente", nullable = false)
    private Long pacienteId;

    @Column(name = "id_cita", nullable = false)
    private Long citaId;

    @Column(nullable = false)
    private String fecha;

    @Column(columnDefinition = "TEXT")
    private String diagnostico;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    public void actualizarHistorial(HistorialUpdateDTO historial) {
        if (historial.getPacienteId() != null) {
            setPacienteId(historial.getPacienteId());
        }

        if (historial.getCitaId() != null) {
            setCitaId(historial.getCitaId());
        }

        if (historial.getFecha() != null) {
            setFecha(historial.getFecha());
        }

        if (historial.getDiagnostico() != null) {
            setDiagnostico(historial.getDiagnostico());
        }

        if (historial.getObservaciones() != null) {
            setObservaciones(historial.getObservaciones());
        }
    }
}
