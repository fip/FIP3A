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
import javax.persistence.Query;
import metier.core.Employe;
import metier.core.Offre;
import systeme.JpaUtils;

/**
 * La classe GesOffre permet la gestion des offres d'emploi, stage...
 * 
 * @version 1.0 
 * @author Yohann lepage
 * @author Alexandre Besnard
 * @author Jonathan Morfin
 * @author Quentin Rousseau
 */
public class GesOffre implements IOffre {

    private EntityManagerFactory emf;
    private EntityManager em = entityManager();

    public static final EntityManager entityManager() {
        return JpaUtils.get().createEntityManager();
    }
    
    /* 
     * Constructeur GesOffre
     */
    public GesOffre() {
       // emf = Persistence.createEntityManagerFactory("GestionRH-ejbPU");
       //em = entityManager();
        
    }
    
    /*
     * creerOffre permet la création d'une offre dans le systeme
     * @param titre
     *      le titre de l'offre
     * @param mission
     *      la description de la mission
     * @param
     *      le salaire proposé
     * @return l'offre créée
     */
    @Override
    public Offre creerOffre(String titre, String mission, Integer salaire) {
        Offre offre = new Offre(titre, mission, salaire);
        em.getTransaction().begin();
        em.persist(offre);
        em.getTransaction().commit();
        return offre;
    }
    
    /*
     * lireOffre permet la lecture d'une offre
     * @param id
     *      l'id de l'offre
     * @return
     *      l'offre recherchée
     */
    @Override
    public Offre lireOffre(Long id) {
        Offre offre = em.find(Offre.class, id);
        return offre;
    }
    
    /*
     * majOffre permet de mettre à jour une offre
     * @param offre
     *      l'offre que l'on souhaite modifier
     * @return l'offre qui a été modifiée
     */
    @Override
    public Boolean majOffre(Offre offre) {
        em.getTransaction().begin();
        Offre merge = em.merge(offre);
        em.getTransaction().commit();
        if (merge == null )
            return false;
        else
            return true;
        
    }
    
    /*
     * supprimerOffre permet de supprimer une offre du système
     * @param offre
     *      l'offre que l'on souhaite supprimer
     * @return 1 si l'offre à été supprimée
     */
    @Override
    public Boolean supprimerOffre(Offre offre) {
        em.getTransaction().begin();
        em.remove(offre);
        em.getTransaction().commit();
        return true;
    }
    
    /*
     * listerOffre permet de lister les offres présentes dans le système
     * @return la collection des offres dans le système
     */
    @Override
    public Collection<Offre> listerOffres() {
        Query query = em.createQuery("SELECT e FROM Offre e");
        return (Collection<Offre>) query.getResultList();
    }
    
    /*
     * creerOffre permet de creer une offre
     * @param titre
     *      le titre de l'offre
     * @mission
     *      la description de la mission
     * @salaire
     *      le salaire proposé
     * @employe
     *      l'employe qui créé l'offre
     * @return l'offre créée
     * 
     */
    @Override
    public Offre creerOffre(String titre, String mission, Integer salaire, Employe employe) {
        Offre offre = new Offre(titre, mission, salaire, employe);
        em.getTransaction().begin();
        em.persist(offre);
        em.getTransaction().commit();
        return offre;
    }

    @Override
    public Offre creerOffre(Offre offre) {
        em.getTransaction().begin();
        em.persist(offre);
        em.getTransaction().commit();
        return offre;   
    }
}
