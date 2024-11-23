package pe.edu.trentino.matricula;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.trentino.matricula.models.Grado;
import pe.edu.trentino.matricula.models.NivelEducativo;
import pe.edu.trentino.matricula.models.Seccion;
import pe.edu.trentino.matricula.repositories.GradoRepository;
import pe.edu.trentino.matricula.repositories.NivelRepository;
import pe.edu.trentino.matricula.repositories.SeccionRepository;

import java.util.List;

@Configuration
public class DataSeeder {
    @Bean
    public CommandLineRunner initDatabase(
            NivelRepository nivelRepository,
            GradoRepository gradoRepository,
            SeccionRepository seccionRepository

    ){
        return args -> {
            // Verificar si ya existen datos
            if(nivelRepository.count() == 0) {
                NivelEducativo nivel1 = new NivelEducativo("Primaria");
                NivelEducativo nivel2 = new NivelEducativo("Secundaria");

                nivelRepository.saveAll(List.of(nivel1, nivel2));

                Grado grado1 = new Grado("primero", nivel1);
                Grado grado2 = new Grado("segundo", nivel1);
                Grado grado3 = new Grado("terceno", nivel1);
                Grado grado4 = new Grado("cuarto", nivel1);
                Grado grado5 = new Grado("quinto", nivel1);
                Grado grado6 = new Grado("sexto", nivel1);

                Grado grado7 = new Grado("primero", nivel2);
                Grado grado8 = new Grado("segundo", nivel2);
                Grado grado9 = new Grado("tercero", nivel2);
                Grado grado10 = new Grado("cuarto", nivel2);
                Grado grado11 = new Grado("quinto", nivel2);
                gradoRepository.saveAll(
                        List.of(
                                grado1,
                                grado2,
                                grado3,
                                grado4,
                                grado5, grado6, grado7,grado8,grado9,grado10,grado11)
                        );

                Seccion seccion1 = new Seccion("A");
                Seccion seccion2 = new Seccion("B");
                Seccion seccion3 = new Seccion("C");
                Seccion seccion4 = new Seccion("D");

                seccionRepository.saveAll(List.of(seccion1, seccion2, seccion3,seccion4));
            }

        };
    }
}
