package co.edu.unicartagena.control.domain.repositories;

import co.edu.unicartagena.control.domain.entities.PersonalApoyo;

import java.util.Optional;

public interface PersonalApoyoRepository {

    //Search if in DB is any Personal active (estado==true)
    Optional<PersonalApoyo> findPersonalApoyoByIdPHAndRol (Integer idPropiedad, String rol, Boolean estado);

    //Search if exist anything with those parameters
    Optional<PersonalApoyo> findPersonalApoyoByIdPHAndRolAndTipoDocAndNumDoc (Integer idPropiedad, String rol, String tipoDoc, String numDoc);

    PersonalApoyo save (PersonalApoyo personalApoyo);

    //Search by tipoDoc, numDoc, rol and idPropiedad and insert estado
    PersonalApoyo changeEstado (String tipoDoc, String numDoc, String rol, Integer idPropiedad, Boolean estado);

    //Search by tipo an num, and update the others
    PersonalApoyo updatePersonalByEstadoAndEmailAndPass (Boolean estado, String  email, String pass, String tipoDoc, String numDoc);
}
    // Listo, básicamente de los endpoints-controllers (infra-adapter, se adapta al shape de los DTO's)
    // se llaman a los Commands en application (ports, se expone lo que hay en domain)
    // y luego a los services en domain (domain+use cases, the core).