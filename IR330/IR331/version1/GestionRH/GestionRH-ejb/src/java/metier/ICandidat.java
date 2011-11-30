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
import java.util.Date;
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
public interface ICandidat {

    /**
     * Lire les infos d'un candidat à partir de son Id
     * @return Candidat
     */
    public Candidat creerCandidat(String prenom, String nom, String cv, String ldm);
    
    public Candidat creerCandidat(String prenom, String nom);
    
    public Candidat lireCandidat(Long idCandidat);
    
    public Candidat lireCandidat(String nom, String prenom);
            
    public Convocation lireConvocation(Long idConvocation);
    
    /**
     * Mettre à jour un Candidat
     * @param candidat Le Candidat
     * @return Le Candidat
     */
    public Boolean majCandidat(Candidat candidat);
    
    /**
     * Supprime un Candidat
     * @param candidat Le Candidat à Supprimer
     * @return True si le candidat a bien été supprimé
     */
    public Boolean suppprimerCandidat(Candidat candidat);
    
    /**
     * Créer une convocation
     * @param date Date de la convocation
     * @param candidat Le Candidat à convoquer
     * @return  La convocation
     */
    public metier.Convocation creerConvocation(Date date, Candidat candidat);
    
    /**
     * Supprime une convocation
     * @param convocation la convocation à supprimer
     * @return true si la convocation est bien supprimée
     */
    public Boolean supprimerConvocation(Convocation convocation);
    
    /**
     * Créer un Dossier
     * @param etat Etat du dossier
     * @param cv Cv 
     * @param ldm
     * @param avisRecruteur
     * @param candidat
     * @param offre
     * @return 
     */
    public Dossier creerDossier(String etat, Candidat candidat, Offre offre);
 
    public Dossier lireDossier(Long idDossier);
    
    public Boolean majDossier(Dossier dossier);
    public Boolean majDossier(Long id, String etat);
    
    public Boolean supprimerDossier(Dossier dossier);
    
    public Boolean majConvocation(Convocation convocation);
    public Boolean majConvocation(Long id, String validation);
    public Boolean majConvocation(Long id, String validation, String type);
    public Boolean majConvocation(Long id, String validation, String type, Date date);
    
    public Collection<Candidat> listerCandidats();
    
    public Collection<Dossier> listerDossiers();
    public Collection<Convocation> listerConvocations();
}
