package co.edu.unicartagena.control.application.commands.propiedad;

import co.edu.unicartagena.control.application.commands.Command;
import co.edu.unicartagena.control.application.dtos.PersonaDTO;
import co.edu.unicartagena.control.application.dtos.PersonaListDTO;
import co.edu.unicartagena.control.domain.entities.BienPrivado;
import co.edu.unicartagena.control.domain.entities.Persona;
import co.edu.unicartagena.control.domain.services.PHService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class UpdatePersonasYBienesCommand implements Command<List<Persona>,List<PersonaListDTO>> {

    private final PHService phService;
    List<Persona> personas;
    List<BienPrivado> bienPrivados;

    @Autowired
    public UpdatePersonasYBienesCommand(PHService phService) {this.phService = phService;}

    public List<Persona> ejecutar(List <PersonaListDTO> personaListDTOS){
        log.debug("Ejecutando el comando: UpdatePersonasYBienes");

        for(PersonaListDTO p:personaListDTOS){
            personas.add(Persona.builder().idPersona(p.getIdPersona())
                            .idBienPrivado(p.getBienPrivadoDTO().getId())
                            .nombres(p.getNombres())
                            .apellidos(p.getApellidos())
                            .numeroDocumento(p.getNumeroDocumento())
                            .tipoDocumento(p.getTipoDocumento())
                            .rol(p.getRol()).build());

            bienPrivados.add(BienPrivado.builder().id(p.getBienPrivadoDTO().getId())
                            .idPropiedad(p.getBienPrivadoDTO().getIdPropiedad())
                            .coeficienteCopropiedad(1)
                            .matriculaInmobiliaria(p.getBienPrivadoDTO().getMatriculaInmobiliaria())
                            .referenciaCatastral(p.getBienPrivadoDTO().getReferenciaCatastral())
                            .build());
        }

        return phService.updateData(personas, bienPrivados);
    }
}
