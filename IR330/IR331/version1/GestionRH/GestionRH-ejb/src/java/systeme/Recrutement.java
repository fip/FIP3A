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
import metier.core.Candidat;

/**
 *
 * @author user
 */
@Stateless
public class Recrutement implements RecrutementLocal {

    @Override
    public Boolean qualifier(Dossier dossier) {
        GesCandidat c = new GesCandidat();
        return c.majDossier(dossier.getId(),"qualifié");
    }

    @Override
    public Boolean valider(Dossier dossier) {
        GesCandidat c = new GesCandidat();
        return c.majDossier(dossier.getId(),"validé");
    }

    @Override
    public Boolean refuser(Dossier dossier) {
        GesCandidat c = new GesCandidat();
        return c.majDossier(dossier.getId(),"refusé");
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Collection<Dossier> listerDossiers() {
        GesCandidat c = new GesCandidat();
        return c.listerDossiers();
    }
    
    @Override
    public Dossier afficherDossier(Long id) {
        GesCandidat c = new GesCandidat();
        return c.lireDossier(id);
    }

    @Override
    public Candidat afficherCandidat(Long idCandidat) {
        GesCandidat c = new GesCandidat();
        return c.lireCandidat(idCandidat);
    }

    @Override
    public Candidat afficherCandidat(String nom, String prenom) {
        GesCandidat c = new GesCandidat();
        return c.lireCandidat(nom,prenom);
    }
    
}
