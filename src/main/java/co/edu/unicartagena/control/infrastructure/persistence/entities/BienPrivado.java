package co.edu.unicartagena.control.infrastructure.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BienPrivado")
public class BienPrivado implements Serializable {

    @Id
    @Column(name = "idBienPrivado")
    private Integer idBienPrivado;

    @JoinColumn(name = "PropiedadHorizontal_idPH", referencedColumnName = "idPropiedadHorizontal")
    @ManyToOne(optional = true)
    private PropiedadHorizontal idPropiedad;

    @Column(name = "coeficienteCopropiedad")
    private float coeficienteCopropiedad;

    @Column(name = "matriculaInmobiliaria")
    private String matriculaInmobiliaria;

    @Column(name = "referenciaCatastral")
    private String referenciaCatastral;

    public Integer getIdBienPrivado() {
        return idBienPrivado;
    }

    public void setIdBienPrivado(Integer idBienPrivado) {
        this.idBienPrivado = idBienPrivado;
    }

    public PropiedadHorizontal getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(PropiedadHorizontal idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public float getCoeficienteCopropiedad() {
        return coeficienteCopropiedad;
    }

    public void setCoeficienteCopropiedad(float coeficienteCopropiedad) {
        this.coeficienteCopropiedad = coeficienteCopropiedad;
    }

    public String getMatriculaInmobiliaria() {
        return matriculaInmobiliaria;
    }

    public void setMatriculaInmobiliaria(String matriculaInmobiliaria) {
        this.matriculaInmobiliaria = matriculaInmobiliaria;
    }

    public String getReferenciaCatastral() {
        return referenciaCatastral;
    }

    public void setReferenciaCatastral(String referenciaCatastral) {
        this.referenciaCatastral = referenciaCatastral;
    }
}
