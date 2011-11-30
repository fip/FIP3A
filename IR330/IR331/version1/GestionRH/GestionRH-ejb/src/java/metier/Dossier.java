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
package metier;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import metier.core.Candidat;
import metier.core.Offre;

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
public class Dossier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String etat;
    private String avisRecruteur;
    
    @ManyToOne
    private Candidat candidat;
    @ManyToOne
    private Offre offre;

    public Dossier() {
    }

    public Dossier(String etat, Candidat candidat, Offre offre) {
        this.etat = etat;
        this.avisRecruteur = "Non étudié";
        this.candidat = candidat;
        this.offre = offre;
    }

    public String getAvisRecruteur() {
        return avisRecruteur;
    }

    public void setAvisRecruteur(String avisRecruteur) {
        this.avisRecruteur = avisRecruteur;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dossier other = (Dossier) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.etat == null) ? (other.etat != null) : !this.etat.equals(other.etat)) {
            return false;
        }
        if ((this.avisRecruteur == null) ? (other.avisRecruteur != null) : !this.avisRecruteur.equals(other.avisRecruteur)) {
            return false;
        }
        if (this.offre != other.offre && (this.offre == null || !this.offre.equals(other.offre))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.etat != null ? this.etat.hashCode() : 0);
        hash = 23 * hash + (this.avisRecruteur != null ? this.avisRecruteur.hashCode() : 0);
        hash = 23 * hash + (this.offre != null ? this.offre.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Dossier{" + "id=" + id + ", etat=" + etat + ", avisRecruteur=" + avisRecruteur + ", offre=" + offre + '}';
    }

    
}

