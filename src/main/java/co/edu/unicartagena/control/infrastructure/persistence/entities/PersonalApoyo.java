package co.edu.unicartagena.control.infrastructure.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PersonalApoyo")
public class PersonalApoyo implements Serializable {

    @Id
    @Column(name = "idPersonalApoyo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersonalApoyo;

    @JoinColumn(name = "PropiedadHorizontal_idPH", referencedColumnName = "idPropiedadHorizontal")
    @ManyToOne(optional = true)
    private PropiedadHorizontal idPropiedad;

    @Column("email")
    private final String email;

    @Column("pass")
    private final String pass;

    @Column("estado")
    private final boolean estado;

    @Column("rol")
    private final String rol;

    @Column("nombres")
    private final String nombres;

    @Column("numeroDocumento")
    private final String numeroDocumento;

    @Column("tipoDocumento")
    private final String tipoDocumento;

    public PersonalApoyo () {

    }

    public Integer getIdPersonalApoyo() {
        return idPersonalApoyo;
    }

    public void setIdPersonalApoyo(Integer idPersonalApoyo) {
        this.idPersonalApoyo = idPersonalApoyo;
    }

    public PropiedadHorizontal getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(PropiedadHorizontal idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public boolean isEstado() {
        return estado;
    }

    public String getRol() {
        return rol;
    }

    public String getNombres() {
        return nombres;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }
}
