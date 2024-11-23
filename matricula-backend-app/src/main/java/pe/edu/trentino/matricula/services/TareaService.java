package pe.edu.trentino.matricula.services;

import pe.edu.trentino.matricula.dto.TareaDto;
import pe.edu.trentino.matricula.response.ResponseDto;

public interface TareaService {
    ResponseDto crearTarea(TareaDto tareaDto);
}
