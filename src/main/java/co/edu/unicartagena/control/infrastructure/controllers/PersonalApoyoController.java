package co.edu.unicartagena.control.infrastructure.controllers;

import co.edu.unicartagena.control.application.commands.personalapoyo.*;
import co.edu.unicartagena.control.application.commands.propiedad.ObtenerPHCommand;
import co.edu.unicartagena.control.application.dtos.PersonalApoyoDTO;
import co.edu.unicartagena.control.application.dtos.PropiedadHorizontalDTO;
import co.edu.unicartagena.control.application.dtos.UserRequestDTO;
import co.edu.unicartagena.control.domain.exceptions.BusinessException;
import co.edu.unicartagena.control.infrastructure.configuration.EncodePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/personal-apoyo")
public class PersonalApoyoController {

    RegistrarPersonalCommand registrarPersonalCommand;
    ExisteRevisorEnPHCommand existeRevisorEnPHCommand;
    ExisteSecretaryEnPHCommand existeSecretaryEnPHCommand;
    ObtenerPHCommand obtenerPHCommand;
    PatchPersonalCommand patchPersonalCommand;
    IniciarSesionCommand iniciarSesionCommand;
    EncodePassword encode;

    @Autowired
    public PersonalApoyoController() {
    }

    @PostMapping(value = "/revisor", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonalApoyoDTO registrarRevisor(@RequestBody PersonalApoyoDTO personalApoyoDTO) {
        try {
            PersonalApoyoDTO personalApoyoDTO1 = encode.encodePassword(personalApoyoDTO);
            return registrarPersonalCommand.ejecutar(personalApoyoDTO1);
        } catch (Exception e){
            throw new BusinessException("Ocurrió un error al registrar el revisor");
        }
    }

    @PostMapping(value = "/secretary", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonalApoyoDTO registrarSecretario(@RequestBody PersonalApoyoDTO personalApoyoDTO) {
        try {
            PersonalApoyoDTO personalApoyoDTO1 = encode.encodePassword(personalApoyoDTO);
            return registrarPersonalCommand.ejecutar(personalApoyoDTO1);
        } catch (Exception e){
            throw new BusinessException("Ocurrió un error al registrar el secretario");
        }
    }

    @GetMapping(value = "/revisor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> existeRevisorEnPH(
            @RequestParam(name = "idPropiedadHorizontal") String idPropiedad) {

        boolean exist = existeRevisorEnPHCommand.ejecutar(idPropiedad);

        Map<Object, Object> model = new HashMap<>();
        model.put("existeRevisor", exist);
        return ResponseEntity.ok().body(model);
    }

    @GetMapping(value = "/secretary", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> existeSecretaryEnPH(
            @RequestParam(name = "idPropiedadHorizontal") String idPropiedad) {

        boolean exist = existeSecretaryEnPHCommand.ejecutar(idPropiedad);

        Map<Object, Object> model = new HashMap<>();
        model.put("existeSecretary", exist);
        return ResponseEntity.ok().body(model);
    }

    @PatchMapping(value = "/patch", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonalApoyoDTO patchPersonal(@RequestBody PersonalApoyoDTO personalApoyoDTO) {
        try {
            PersonalApoyoDTO personalApoyoDTO1 = encode.encodePassword(personalApoyoDTO);
            return patchPersonalCommand.ejecutar(personalApoyoDTO1);
        } catch (Exception e){
            throw new BusinessException("El usuario no está registrado en el sistema");
        }
    }

    @PostMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> iniciarSesion(UserRequestDTO body) {
        try {
            UserRequestDTO user = encode.encodePassword(body);
            PersonalApoyoDTO personal = iniciarSesionCommand.ejecutar(user);

            String idPh = String.valueOf(personal.getIdPropiedad());
            PropiedadHorizontalDTO ph = obtenerPHCommand.ejecutar(idPh);

            Map<Object, Object> model = new HashMap<>();
            model.put("email", personal.getEmail());
            model.put("nombres", personal.getNombres());
            model.put("rol", personal.getRol());
            model.put("idPropiedadHorizontal", idPh);
            model.put("nombrePH", ph.getNombre());

            return ResponseEntity
                    .ok()
                    .body(model);
        } catch (Exception e){

            return ResponseEntity
                    .badRequest()
                    .body("Error al iniciar sesión");
        }
    }

}
