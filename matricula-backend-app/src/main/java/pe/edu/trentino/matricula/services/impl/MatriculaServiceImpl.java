package pe.edu.trentino.matricula.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pe.edu.trentino.matricula.config.HandlerException;
import pe.edu.trentino.matricula.dto.DetalleMatriculaDto;
import pe.edu.trentino.matricula.dto.DetalleMatriculaResponseDto;
import pe.edu.trentino.matricula.dto.MatriculaDto;
import pe.edu.trentino.matricula.dto.MatriculaDtoRequest;
import pe.edu.trentino.matricula.models.Alumno;
import pe.edu.trentino.matricula.models.Matricula;
import pe.edu.trentino.matricula.repositories.*;
import pe.edu.trentino.matricula.response.PaginatedResponseDto;
import pe.edu.trentino.matricula.response.ResponseDto;
import pe.edu.trentino.matricula.services.MatriculaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final AlumnoRepository alumnoRepository;
    private final GradoRepository gradoRepository;
    private final SeccionRepository seccionRepository;
    private final NivelRepository nivelRepository;
    private final ApoderadoRepository apoderadoRepository;
    private final PagoRepository pagoRepository;

    @Override
    public ResponseDto matricularAlumno(MatriculaDtoRequest matriculaDto) {
        var response = new ResponseDto();
        try {
            // Obtener datos del alumno
            Alumno alumno = alumnoRepository.findById(matriculaDto.getAlumnoId()).orElseThrow(() -> new HandlerException(HttpStatus.NOT_FOUND, "Alumno no encontrado"));

            int anioActual = LocalDate.now().getYear();
             Matricula matricula = new Matricula();
             matricula.setCodigo(generarCodigoMatricula(
                     alumno.getNombres(),
                     alumno.getApellidos(),
                     alumno.getDni(),
                     anioActual
             ));

            matricula.setCodigo(matricula.getCodigo());
            matricula.setApoderado(apoderadoRepository.findById(matriculaDto.getApoderadoId()).orElseThrow(()-> new HandlerException(HttpStatus.NOT_FOUND, "Apoderado no encontrado")));
            matricula.setSeccion(seccionRepository.findById(matriculaDto.getSeccionId()).orElseThrow(()-> new HandlerException(HttpStatus.NOT_FOUND, "Seccion no encontrado")));
            matricula.setNivel(nivelRepository.findById(matriculaDto.getNivelId()).orElseThrow(()-> new HandlerException(HttpStatus.NOT_FOUND, "Nivel educativo no encontrado")));
            matricula.setGrado(gradoRepository.findById(matriculaDto.getGradoId()).orElseThrow(()-> new HandlerException(HttpStatus.NOT_FOUND, "Grado no encontrado")));
            matricula.setFechaMatricula(LocalDateTime.now());
            matricula.setSituacion(matriculaDto.getSituacion());
            matricula.setProcedencia(matriculaDto.getProcedencia());
            matricula.setParentesco(matriculaDto.getParentesco());
            matricula.setInstitucionProcedencia(matriculaDto.getInstitucionProcedencia());
            matricula.setCostoMatricula(matriculaDto.getCostoMatricula());
            matricula.setCostoMensualidad(matriculaDto.getCostoMensualidad());
            matricula.setDescuentoMensualidad(matriculaDto.getDescuentoMensualidad());
            matricula.setMensualidadFinal(matriculaDto.getMensualidadFinal());

            // Guarda la data en base de datos
            matriculaRepository.save(matricula);

            response.setStatus(200);
            response.setMessage("Alumno(a) " + alumno.getNombres() + " " + alumno.getApellidos() + " Matriculado correctamente");
        } catch (Exception e) {
            throw new HandlerException(HttpStatus.NOT_FOUND, "El Alumno(a) ya se encuentra matriculado");
        }
        return response;
    }

    @Override
    public PaginatedResponseDto<MatriculaDto> obtenerMatricula(String nombre, int page, int perPage) {
        Pageable pageable = PageRequest.of(page - 1, perPage);
        Page<MatriculaDto> matriculaPage = matriculaRepository.buscarMatriculaPorNombredeAlumno(nombre, pageable);
        return new PaginatedResponseDto<>(
                matriculaPage.getContent(),
                page,
                perPage,
                matriculaPage.getTotalElements()
        );
    }

    @Override
    public List<DetalleMatriculaDto> mostrarDetalleMatricula(String codigo) {
        Matricula matricula = matriculaRepository.findByCodigo(codigo)
                .orElseThrow(() -> new HandlerException(
                        HttpStatus.NOT_FOUND, "No se encontró ninguna matricula con el código proporcionado")
                );
        return List.of();
    }

    @Override
    public List<DetalleMatriculaResponseDto> mostrarEstudianteCodigo(String codigo) {
        return List.of();
    }


    private String generarCodigoMatricula(String nombre, String apellidos, String dni, int anio) {
        String inicialNombre = !nombre.isEmpty() ? nombre.substring(0, 1).toUpperCase() : "";
        String inicialApellido = !apellidos.isEmpty() ? apellidos.split(" ")[0].substring(0, 1).toUpperCase() : "";
        return inicialNombre + inicialApellido + dni + anio;
    }
}
