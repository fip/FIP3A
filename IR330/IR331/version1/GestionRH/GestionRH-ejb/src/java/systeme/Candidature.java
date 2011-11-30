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
package systeme;

import java.util.Collection;
import javax.ejb.Stateless;
import metier.Dossier;
import metier.GesCandidat;
import metier.GesOffre;
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
@Stateless
public class Candidature implements CandidatureLocal {


    public Candidature() {
    }

    @Override
    public Collection<Offre> listerOffre() {
        GesOffre o = new GesOffre();
        return o.listerOffres();
    }
    
    @Override
    public Collection <Dossier> listerDossiers(Long idCandidat) {
        GesCandidat o = new GesCandidat();
        return (Collection<Dossier>) o.lireDossier(idCandidat);
    }
        

    @Override
    public Offre afficherOffre(Long idOffre) {
        GesOffre o = new GesOffre();
        return o.lireOffre(idOffre);
    }

    @Override
    public Boolean deposerOffre(Offre offre) {
        GesOffre o = new GesOffre();
        if(o.creerOffre(offre)== null) {
            return false;
        } else {
         return true;
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Candidat creerCandidat(String prenom, String nom) {
        GesCandidat c = new GesCandidat();
        return c.creerCandidat(prenom, nom);
    }

    @Override
    public Dossier creerDossier(Candidat candidat, Offre offre) {
        GesCandidat c = new GesCandidat();
        return c.creerDossier("créé", candidat, offre);
    }
}
