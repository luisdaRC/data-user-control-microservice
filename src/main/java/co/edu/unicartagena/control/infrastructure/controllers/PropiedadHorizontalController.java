package co.edu.unicartagena.control.infrastructure.controllers;

import co.edu.unicartagena.control.application.commands.propiedad.ExistePHCommand;
import co.edu.unicartagena.control.application.commands.propiedad.RegistrarPHCommand;
import co.edu.unicartagena.control.application.commands.propiedad.UpdatePersonasYBienesCommand;
import co.edu.unicartagena.control.application.dtos.PersonaListDTO;
import co.edu.unicartagena.control.application.dtos.PropiedadHorizontalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/propiedad-horizontal")
public class PropiedadHorizontalController {

    // Los métodos del core llaman services y las del access los GET son con querys y POSTS con command
    // Analyze sequence of method or classes calling in both of those projects and determine how can i do
    // to build my own sequence in these components.

    // https://medium.com/@soyjuanmalopez/clean-architecture-en-la-vida-real-9da710e5ad4a
    // https://github.com/soyjuanmalopez/clean-architecture/tree/b80aac9e03be5f21d31ea4589556db39a5f38633
    // Give a sight to these links and follow the flow of the data there,
    // compare with the flow in access control and identify how the hell
    // is inserted the data in the database through repository
    // https://github.com/damienbeaufils/spring-boot-clean-architecture-demo.
    // Test the dummy project by running it.-------------THIS----------------
    RegistrarPHCommand registrarPHCommand;
    ExistePHCommand existePHCommand;
    UpdatePersonasYBienesCommand updatePersonasYBienesCommand;

    @Autowired
    public PropiedadHorizontalController() {}

    /**
     * No es necesario verificar si existe la propiedad dado que la validación está hecho en el front
     * */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registrarPropiedadHorizontal(@RequestBody PropiedadHorizontalDTO propiedadHorizontalDTO){
        return ResponseEntity.ok()
                .body(registrarPHCommand.ejecutar(propiedadHorizontalDTO));
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> existePropiedadHorizontal(
            @RequestParam(name = "idPropiedadHorizontal") String idPropiedad){

        boolean exist = existePHCommand.ejecutar(idPropiedad);

        Map<Object,Object> model = new HashMap<>();
        model.put("existePH", exist);
        return ResponseEntity.ok().body(model);
    }

    /**
     * Caso 3: Que se tengan registros y hayan menos en la lista del core (eliminados).
     * (In this case, the records in database will be the same because any information
     * about the person deleted would be needed in the future, regarding to votes).
     * */
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updatePersonasYBienes(@RequestBody List<PersonaListDTO> personaListDTO){
        return ResponseEntity.ok().body(updatePersonasYBienesCommand.ejecutar(personaListDTO));
    }

    //Keep coding, starting by the next group of posts and gets from:
    //https://github.com/luisdaRC/votations-frontend/blob/feature/revisor/src/app/services/sgph/propiedad-horizontal.service.ts

    //Keep with the implementation of PersonalApoyo login endpoint. Taking into account jwt settings and so on (regarding to core).

    //Dedicate all the effort to login analysis and implementation in the core. The above!


}
