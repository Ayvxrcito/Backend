package pe.edu.trentino.matricula.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.trentino.matricula.dto.TareaDto;
import pe.edu.trentino.matricula.repositories.TareaRepository;
import pe.edu.trentino.matricula.response.ResponseDto;
import pe.edu.trentino.matricula.services.TareaService;
@RequiredArgsConstructor
@Service

public class TareaServiceImpl implements TareaService {

    private final TareaRepository tareaRepository;

    /**
     * @param tareaDto
     * @return
     */
    @Override
    public ResponseDto crearTarea(TareaDto tareaDto) {
        
        return null;
    }
}
