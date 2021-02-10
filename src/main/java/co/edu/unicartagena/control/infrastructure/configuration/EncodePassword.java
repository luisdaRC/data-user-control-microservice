package co.edu.unicartagena.control.infrastructure.configuration;
import co.edu.unicartagena.control.application.dtos.PersonalApoyoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodePassword {

    @Autowired
    PasswordEncoder passwordEncoder;

    public EncodePassword(){}

    public PersonalApoyoDTO encodePassword(PersonalApoyoDTO personalApoyoDTO){
        return PersonalApoyoDTO.builder()
                .idPropiedad(personalApoyoDTO.getIdPropiedad())
                .email(personalApoyoDTO.getEmail())
                .pass(passwordEncoder.encode(personalApoyoDTO.getPass()))
                .estado(personalApoyoDTO.getEstado())
                .rol(personalApoyoDTO.getRol())
                .nombres(personalApoyoDTO.getNombres())
                .numeroDocumento(personalApoyoDTO.getNumeroDocumento())
                .tipoDocumento(personalApoyoDTO.getTipoDocumento())
                .build();

    }
}
