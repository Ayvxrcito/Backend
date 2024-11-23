package pe.edu.trentino.matricula.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor

public class TareaDto {
    private String titulo;
    private String descripcion;
    private LocalDateTime fecha;
    private String estado;
}
