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

import java.util.Collection;
import metier.core.Employe;
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
public interface IOffre {

    public Offre creerOffre(String titre, String mission, Integer salaire);

    public Offre creerOffre(String titre, String mission, Integer salaire, Employe employe);
    
    public Offre creerOffre(Offre offre);

    public Offre lireOffre(Long id);

    public Boolean majOffre(Offre offre);

    public Boolean supprimerOffre(Offre offre);

    public Collection<Offre> listerOffres();
}
