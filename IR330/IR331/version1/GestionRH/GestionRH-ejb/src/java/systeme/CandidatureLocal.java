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
import javax.ejb.Local;
import metier.Dossier;
import metier.core.Candidat;
import metier.core.Offre;

/**
 *
 * @author user
 */
@Local
public interface CandidatureLocal {

    public Collection<Offre> listerOffre();

    public Offre afficherOffre(Long idOffre);

    public Boolean deposerOffre(Offre offre);
    
    public Candidat creerCandidat(String prenom, String nom);
    
    public Dossier creerDossier(Candidat candidat, Offre offre);

    java.util.Collection <Dossier> listerDossiers(Long idCandidat);
}
