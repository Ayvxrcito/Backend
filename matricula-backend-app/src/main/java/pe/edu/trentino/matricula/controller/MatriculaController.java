package pe.edu.trentino.matricula.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.trentino.matricula.dto.MatriculaDtoRequest;
import pe.edu.trentino.matricula.response.ResponseDto;
import pe.edu.trentino.matricula.services.MatriculaService;

@RequiredArgsConstructor
@RestController
@CrossOrigin()
public class MatriculaController {
    private final MatriculaService matriculaService;

    @PostMapping("/matricular-alumno")
    public ResponseDto crearBanco(@RequestBody MatriculaDtoRequest dto) {
        return  matriculaService.matricularAlumno(dto);
    }
}
