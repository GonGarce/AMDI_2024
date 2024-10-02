/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master_detail;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 *
 * @author Agustin
 */
@Entity
@Table(name = "correos", catalog = "AMDI_24", schema = "")
@NamedQueries({
    @NamedQuery(name = "Correos.findAll", query = "SELECT c FROM Correos c"),
    @NamedQuery(name = "Correos.findByContact", query = "SELECT c FROM Correos c WHERE c.contacto = :contacto"),
    @NamedQuery(name = "Correos.findByCorreoId", query = "SELECT c FROM Correos c WHERE c.correoId = :correoId"),
    @NamedQuery(name = "Correos.findByCorreo", query = "SELECT c FROM Correos c WHERE c.correo = :correo")})
public class Correos implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CORREO_ID")
    private Integer correoId;
    @Basic(optional = false)
    @Column(name = "CORREO")
    private String correo;
    @JoinColumn(name = "ID_CONTACTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Contactos contacto;

    public Correos() {
    }

    public Correos(Integer correoId) {
        this.correoId = correoId;
    }

    public Correos(Integer correoId, String correo) {
        this.correoId = correoId;
        this.correo = correo;
    }

    public Integer getCorreoId() {
        return correoId;
    }

    public void setCorreoId(Integer correoId) {
        Integer oldCorreoId = this.correoId;
        this.correoId = correoId;
        changeSupport.firePropertyChange("correoId", oldCorreoId, correoId);
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        String oldCorreo = this.correo;
        this.correo = correo;
        changeSupport.firePropertyChange("correo", oldCorreo, correo);
    }

    public Contactos getIdContacto() {
        return contacto;
    }

    public void setIdContacto(Contactos idContacto) {
        Contactos oldIdContacto = this.contacto;
        this.contacto = idContacto;
        changeSupport.firePropertyChange("idContacto", oldIdContacto, idContacto);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (correoId != null ? correoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Correos)) {
            return false;
        }
        Correos other = (Correos) object;
        if ((this.correoId == null && other.correoId != null) || (this.correoId != null && !this.correoId.equals(other.correoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sources.Correos[ correoId=" + correoId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
