package co.edu.unicartagena.control.domain.repositories;

import co.edu.unicartagena.control.domain.entities.BienPrivado;

import java.util.List;
import java.util.Optional;

public interface BienPrivadoRepository {
    BienPrivado save(BienPrivado bienPrivado);

    Optional<List<BienPrivado>> findByIdPropiedad(Integer idPropiedad);

    Optional<Integer> updateCoeficiente(Integer idBienPrivado, Float coeficiente);

}
