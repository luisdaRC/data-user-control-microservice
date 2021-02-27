package co.edu.unicartagena.control.application.commands.propiedad;

import co.edu.unicartagena.control.application.builders.BienPrivadoBuilder;
import co.edu.unicartagena.control.application.builders.PersonaBuilder;
import co.edu.unicartagena.control.application.commands.Command;
import co.edu.unicartagena.control.application.dtos.PersonaListDTO;
import co.edu.unicartagena.control.domain.entities.BienPrivado;
import co.edu.unicartagena.control.domain.entities.Persona;
import co.edu.unicartagena.control.domain.services.PHService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
@Component
public class UpdatePersonasYBienesCommand implements Command<List<Persona>,List<PersonaListDTO>> {

    private final PHService phService;
    List<Persona> personas = new ArrayList<Persona>();
    List<BienPrivado> bienPrivados = new ArrayList<BienPrivado>();

    //Make the test with Sets to see if when adding a new object it accept them
    Set<Persona> setPersonas = new HashSet<>();
    Set<BienPrivado> setBienes = new HashSet<>();

    @Autowired
    public UpdatePersonasYBienesCommand(PHService phService) {this.phService = phService;}

    public List<Persona> ejecutar(List <PersonaListDTO> personaListDTOS){
        log.debug("Ejecutando el comando: UpdatePersonasYBienes");

        for(PersonaListDTO p:personaListDTOS){
            System.out.println("Command: "+p);
            personas.add(PersonaBuilder.crearPersonaDesdePersonaList(p));
            bienPrivados.add(BienPrivadoBuilder.crearBienPrivadoDesdeDTO(p.getBienPrivado()));
        }

        setPersonas.addAll(personas);
        setBienes.addAll(bienPrivados);
        //Let's see if this serves. Search in google to be consciuos of what to expect



        //I'll consider only "PROPIETARIO" y "RESIDENTE" roles because others don't matter in this project





        return phService.updateData(personas, bienPrivados);
    }
}
