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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import metier.core.Employe;


/**
 * La classe GesEmploye permet la gestion des employées
 * 
 * @version 1.0 
 * @author Yohann lepage
 * @author Alexandre Besnard
 * @author Jonathan Morfin
 * @author Quentin Rousseau
 */
public class GesEmploye implements IEmploye {

    public final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionRH-ejbPU");
    public final EntityManager em = emf.createEntityManager();

    /*
     * Constructeur de GesEmploye
     */
    public GesEmploye() {
        // emf = Persistence.createEntityManagerFactory("GestionRH-ejbPU");
        //em = emf.createEntityManager();
    }

    /*
     * creerEmploye permet la création d'un employé
     * @param prenom
     *      le prénom de l'employé
     * @param nom
     *      le nom de l'employé
     * @poste
     *      le poste de l'employé
     * @return L'Employe créé
     */
    @Override
    public Employe creerEmploye(String prenom, String nom, String poste) {
        Employe employe = new Employe(prenom, nom, poste);
        em.getTransaction().begin();
        em.persist(employe);
        em.getTransaction().commit();
        return employe;
    }

    /*
     * lireEmploye permet de retrouver un Employe à partir de son id
     * @param
     *      l'id de l'Employe
     * @return
     *      l'Employe
     */
    @Override
    public Employe lireEmploye(Long id) {
        return em.find(Employe.class, id);
    }

    /*
     * majEmploye permet de mettre à jour un Employe
     * @param employe
     *      l'Employe que l'on veut mettre à jour
     * @return
     *      l'Employe modifié
     */
    @Override
    public Boolean majEmploye(Employe employe) {
        em.getTransaction().begin();
        Employe merge = em.merge(employe);
        em.getTransaction().commit();
        if (merge == null) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * supprimerEmploye permet la suppression d'un Employe
     * @param employe
     *      l'employé que l'onsouhaite supprimer
     * @return 1 si l'employé à été supprimé
     */
    //TODO vérifier la valeur de retour
    @Override
    public Boolean supprimerEmploye(Employe employe) {
        em.getTransaction().begin();
        em.remove(employe);
        em.getTransaction().commit();
        return true;
    }

    /*
     * listerEmployes permet de lister les employes
     * @return la collection d'Employe
     */
    @Override
    public Collection<Employe> listerEmployes() {
        Query query = em.createQuery("SELECT e FROM Employe e");
        return (Collection<Employe>) query.getResultList();
    }
}
