/*
 * GestionRH 
 *
 * Copyright (C) Maisel Team
 * 
 * GestionRH is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 2 of the License,
 * or (at your option) any later version.
 *
 * GestionRH  is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cobertura; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */
package metier.core;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import metier.Convocation;
import metier.Dossier;

/**
 * 
 * 
 * @version 1.0 
 * @author Yohann lepage
 * @author Alexandre Besnard
 * @author Jonathan Morfin
 * @author Quentin Rousseau
 */
@Entity
@Table(name="candidats")
public class Candidat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CAN_SEQ")
    @SequenceGenerator(name="CAN_SEQ", sequenceName="CAN_SEQ", allocationSize=1)
    private Long id;

    /**
     * Le nom
     */
   private String nom;
   
   /**
    * Le prénom
    */
   private String prenom;

   private String cv;
   
   private String ldm;

    public Candidat(String nom, String prenom,String cv,String ldm) {
        this.nom = nom;
        this.prenom = prenom;
        this.convocations = null;
        this.dossiers = null;
        this.cv = cv;
        this.ldm=ldm;
    }
    
    public Candidat(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Candidat() {
       
    }

    public Collection<Convocation> getConvocations() {
        return convocations;
    }

    public void setConvocations(Collection<Convocation> convocations) {
        this.convocations = convocations;
    }

    public Collection<Dossier> getDossiers() {
        return dossiers;
    }

    public void setDossiers(Collection<Dossier> dossiers) {
        this.dossiers = dossiers;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getLdm() {
        return ldm;
    }

    public void setLdm(String ldm) {
        this.ldm = ldm;
    }
   

   /**
    * Les convocations
    */
    @OneToMany(mappedBy = "candidat")
   private Collection<Convocation> convocations;
   
   /**
    * Les Dossiers
    */
    @OneToMany(mappedBy = "candidat")
   private Collection<Dossier> dossiers;
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Candidat)) {
            return false;
        }
        Candidat other = (Candidat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Candidat{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + '}';
    }


    
}
