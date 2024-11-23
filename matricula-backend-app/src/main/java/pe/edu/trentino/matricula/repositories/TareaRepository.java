package pe.edu.trentino.matricula.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.trentino.matricula.models.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

}
