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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@Table(name="offres")
public class Offre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OFF_SEQ")
    @SequenceGenerator(name="OFF_SEQ", sequenceName="OFF_SEQ", allocationSize=1)
    private Long id;
    
    private String titre;
    private String mission;
    private Integer salaire;
    
    @ManyToOne
    private Employe employe;
    
    public Offre(){}

    public Offre(String titre, String mission, Integer salaire) {
        this.titre = titre ;
        this.mission = mission ;
        this.salaire = salaire ;
    }
    
    public Offre(String titre, String mission, Integer salaire, Employe employe) {
        this.titre = titre ;
        this.mission = mission ;
        this.salaire = salaire ;
        this.employe = employe;
    }

    
    
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
        if (!(object instanceof Offre)) {
            return false;
        }
        Offre other = (Offre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", titre=" + titre + ", mission=" + mission + ", salaire=" + salaire + '}';
    }

    public String getMission() {
        return mission;
    }

    public Integer getSalaire() {
        return salaire;
    }

    public String getTitre() {
        return titre;
    }
    
    


    
}
