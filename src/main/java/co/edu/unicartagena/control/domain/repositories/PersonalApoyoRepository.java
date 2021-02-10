package co.edu.unicartagena.control.domain.repositories;

import co.edu.unicartagena.control.domain.entities.PersonalApoyo;

import java.util.Optional;

public interface PersonalApoyoRepository {

    Optional<PersonalApoyo> findPersonalApoyoByIdPHAndRol (Integer idPropiedad, String rol, Boolean estado);
    PersonalApoyo save (PersonalApoyo personalApoyo);
    PersonalApoyo update(String tipoDoc, String numDoc, String rol, Integer idPropiedad, Boolean estado);
}
    // Listo, básicamente de los endpoints-controllers (infra-adapter, se adapta al shape de los DTO's)
    // se llaman a los Commands en application (ports, se expone lo que hay en domain)
    // y luego a los services en domain (domain+use cases, the core).