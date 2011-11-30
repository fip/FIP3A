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

/**
 * 
 * 
 * @version 1.0 
 * @author Yohann lepage
 * @author Alexandre Besnard
 * @author Jonathan Morfin
 * @author Quentin Rousseau
 */
public interface IEmploye {

    /**
     * Creer un employe
     * 
     * @param prenom : le prenom de l'employe
     * @param poste : le pôste de l'employe
     * @param nom : le nom de l'employe
     * @return Employe
     */
    public Employe creerEmploye(String prenom, String nom, String poste);

    /**
     * Lire un employe
     * 
     * @param id : l'id de l'employe
     * @return Employe
     */
    public Employe lireEmploye(Long id);

    /**
     * Mettre a jour un employe
     * 
     * @param employe : l'employe a mettre a jour
     * @return Employe
     */
    public Boolean majEmploye(Employe employe);

    /**
     * Supprimer un employe
     * 
     * @param employe : l'employe a supprimer
     * @return Boolean
     */
    public Boolean supprimerEmploye(Employe employe);

    public Collection<Employe> listerEmployes();
}
