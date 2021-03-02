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
    public PersonalApoyoController(RegistrarPersonalCommand registrarPersonalCommand,
            ExisteRevisorEnPHCommand existeRevisorEnPHCommand,
            ExisteSecretaryEnPHCommand existeSecretaryEnPHCommand,
            ObtenerPHCommand obtenerPHCommand,
            PatchPersonalCommand patchPersonalCommand,
            IniciarSesionCommand iniciarSesionCommand,
            EncodePassword encode) {

        this.registrarPersonalCommand = registrarPersonalCommand;
        this.existeRevisorEnPHCommand = existeRevisorEnPHCommand;
        this.existeSecretaryEnPHCommand = existeSecretaryEnPHCommand;
        this.obtenerPHCommand = obtenerPHCommand;
        this.patchPersonalCommand = patchPersonalCommand;
        this.iniciarSesionCommand = iniciarSesionCommand;
        this.encode = encode;
    }

    @PostMapping(value = "/revisor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registrarRevisor(@RequestBody PersonalApoyoDTO personalApoyoDTO) {
        try {
            System.out.println("Primero");
            System.out.println(personalApoyoDTO);
            PersonalApoyoDTO personalApoyoDTO1 = encode.encodePassword(personalApoyoDTO);
            System.out.println("Pasa encode");
            return ResponseEntity.ok()
                    .body(registrarPersonalCommand.ejecutar(personalApoyoDTO1));

        } catch (Exception e){
            throw new BusinessException("Ocurrió un error al registrar el revisor");
            //return ResponseEntity.status(500).body("Fallo al registrar Revisor");
        }
    }

    @PostMapping(value = "/secretary", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registrarSecretario(@RequestBody PersonalApoyoDTO personalApoyoDTO) {
        try {
            PersonalApoyoDTO personalApoyoDTO1 = encode.encodePassword(personalApoyoDTO);
            return ResponseEntity.ok()
                    .body(registrarPersonalCommand.ejecutar(personalApoyoDTO1));

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
    public ResponseEntity<Object> patchPersonal(@RequestBody PersonalApoyoDTO personalApoyoDTO) {
        //Averiguar por qué el update en el repository no está funcionando
    //    try {
            System.out.println("EN patchPersonal Controller");
            System.out.println(personalApoyoDTO);
            return ResponseEntity.ok()
                    .body(patchPersonalCommand.ejecutar(personalApoyoDTO));

    /*    } catch (Exception e){
            throw new BusinessException("El usuario no está registrado en el sistema");
        }*/
    }

    @PostMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> iniciarSesion(@RequestBody UserRequestDTO body) {
        //Entra al catch
 //       try {
        System.out.println("En body: "+body);
            UserRequestDTO user = encode.encodePassword(body);
        System.out.println("La password encodeada pa comparar con la de la DB: "+user.getPassword());
        // Los encodes son diferentes xD
            PersonalApoyoDTO personal = iniciarSesionCommand.ejecutar(user);

            String idPh = String.valueOf(personal.getIdPropiedadHorizontal());
            PropiedadHorizontalDTO ph = obtenerPHCommand.ejecutar(idPh);

            Map<Object, Object> model = new HashMap<>();
            model.put("email", personal.getDataPersonal().getEmail());
            model.put("nombres", personal.getDataPersonal().getNombres());
            model.put("rol", personal.getRol());
            model.put("idPropiedadHorizontal", personal.getIdPropiedadHorizontal());
            model.put("nombrePH", ph.getNombre());

            return ResponseEntity
                    .ok()
                    .body(model);
/*        } catch (Exception e){

            return ResponseEntity
                    .badRequest()
                    .body("Error al iniciar sesión");
        }*/
    }

}
