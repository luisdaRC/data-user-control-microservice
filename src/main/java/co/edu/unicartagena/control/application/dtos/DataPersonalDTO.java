package co.edu.unicartagena.control.application.dtos;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
public class DataPersonalDTO {

    private String email;
    private String password;
    private String nombres;
    private String numeroDocumento;
    private String tipoDocumento;
}
