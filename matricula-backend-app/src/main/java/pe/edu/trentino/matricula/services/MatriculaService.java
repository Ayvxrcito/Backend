package pe.edu.trentino.matricula.services;

import pe.edu.trentino.matricula.dto.DetalleMatriculaDto;
import pe.edu.trentino.matricula.dto.DetalleMatriculaResponseDto;
import pe.edu.trentino.matricula.dto.MatriculaDto;
import pe.edu.trentino.matricula.dto.MatriculaDtoRequest;
import pe.edu.trentino.matricula.response.PaginatedResponseDto;
import pe.edu.trentino.matricula.response.ResponseDto;

import java.util.List;

public interface MatriculaService {
    ResponseDto matricularAlumno(MatriculaDtoRequest matriculaDto);
    PaginatedResponseDto<MatriculaDto> obtenerMatricula(String nombre, int page, int perPage);
    List<DetalleMatriculaDto> mostrarDetalleMatricula(String codigo);
    List<DetalleMatriculaResponseDto> mostrarEstudianteCodigo(String codigo);
}
