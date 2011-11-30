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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import metier.core.Candidat;

import metier.core.Offre;
import org.hibernate.Criteria;
import systeme.JpaUtils;

/**
 * La classe GesCandiadt permet la gestion des des candidats dans le système
 * 
 * @version 1.0 
 * @author Yohann lepage
 * @author Alexandre Besnard
 * @author Jonathan Morfin
 * @author Quentin Rousseau
 */
public class GesCandidat implements ICandidat {

    private EntityManagerFactory emf;
    private EntityManager em = entityManager();

    public static final EntityManager entityManager() {
        return JpaUtils.get().createEntityManager();
    }
    
    /*
     * Constructeur par défaut
     */

    public GesCandidat() {
    }

    /*
     * creerCandidat permet la création d'un candidat
     * @param prenom
     *      le prenom du candidat
     * @param nom
     *      le nom du candidat
     * @param cv
     *      le cv du candidat
     * @param ldm
     *      la lettre de motivation du candidat
     * @return le candidat créé
     * 
     */
    @Override
    public Candidat creerCandidat(String prenom, String nom, String cv, String ldm) {
        Candidat candidat = new Candidat(prenom, nom, cv, ldm);
        em.getTransaction().begin();
        em.persist(candidat);
        em.getTransaction().commit();
        return candidat;
    }

    @Override
    public Candidat creerCandidat(String prenom, String nom) {
        Candidat candidat = new Candidat(prenom, nom);
        em.getTransaction().begin();
        em.persist(candidat);
        em.getTransaction().commit();
        return candidat;
    }
    /*
     * lireCandidat permet de renvoyer un candidat à partir de son id
     * @param idCandidat
     *      l'id du candidat
     * @return le candidat
     */

    @Override
    public Candidat lireCandidat(Long idCandidat) {
        return em.find(Candidat.class, idCandidat);
    }
    
    @Override
    public Candidat lireCandidat(String nom, String prenom) 
    {
        Query q = em.createQuery("FROM Candidat WHERE nom like '" + nom + "' OR prenom like '" + prenom +"'");
        return (Candidat) q.getSingleResult();
    }
    

    /*
     * majCandidat permet de mettre à jour un candidat
     * @param candidat
     *      le candidat que l'onsouhaite mettre à jour
     * @return le candidat mis à jour
     */
    @Override
    public Boolean majCandidat(Candidat candidat) {
        em.getTransaction().begin();
        Candidat merge = em.merge(candidat);
        em.getTransaction().commit();
        if (merge == null) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * supprimerCandidat permet la suppression d'un candidat
     * @param candidat
     *      le candidat à supprimer
     * @return 1 si le candidat est supprimé
     */
    @Override
    public Boolean suppprimerCandidat(Candidat candidat) {
        em.getTransaction().begin();
        em.remove(candidat);
        em.getTransaction().commit();
        return true;
    }

    /*
     * creerConvocation permet de creer une convocation à l'attention du candidat
     * @param date
     *      la date de la convocation
     * @param candidat
     *      le candidat à convoquer
     * @return la convocation créée
     */
    @Override
    public metier.Convocation creerConvocation(Date date, Candidat candidat) {
        Convocation c = new Convocation(date, candidat);
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        return em.find(Convocation.class, c.getId());
    }

    /*
     * supprimerConvocation permet la suppression d'une convocation
     * @param convocation
     *      la convocation à supprimer
     * @return 1 si la convocation à été supprimée
     */
    @Override
    public Boolean supprimerConvocation(Convocation convocation) {
        em.getTransaction().begin();
        em.remove(convocation);
        em.getTransaction().commit();
        return true;
    }

    /*
     * creerDossier permet la creation d'un dossier
     * @param etat
     *      l'état du dossier
     * @param candidat
     *      le candidat concerné
     * @param offre
     *      l'offre concernée
     * @return le dossier créé
     */
    @Override
    public Dossier creerDossier(String etat, Candidat candidat, Offre offre) {
        Dossier d = new Dossier(etat, candidat, offre);
        em.getTransaction().begin();
        em.persist(d);
        em.getTransaction().commit();
        return em.find(Dossier.class, d.getId());
    }

    /*
     * lireDossier permet de lire un dossier à partir de son id
     * @param idDossier
     *      l'id du dossier à lire
     * @return le dossier lu
     */
    @Override
    public Dossier lireDossier(Long idDossier) {
        return em.find(Dossier.class, idDossier);
    }

    /*
     * majDossier permet de mettre à jour un dossier dans le systeme
     * @param dossier
     *      le dossier à mettre à jour
     * @return
     *      le dossier mis à jour
     */
    @Override
    public Boolean majDossier(Dossier dossier) {
        em.getTransaction().begin();
        Dossier merge = em.merge(dossier);
        em.getTransaction().commit();
        if (merge == null) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * majDossier permet de mettre à jour un dossier dans le systeme
     * @param dossier
     *      le dossier à mettre à jour
     * @return
     *      le dossier mis à jour
     */
    @Override
    public Boolean majDossier(Long id, String eta) {
        em.getTransaction().begin();
        Dossier dossier = lireDossier(id);
        dossier.setEtat(eta);
        em.getTransaction().commit();
        if (dossier == null) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * supprimerDossier permet la suppression d'un dossier
     * @param dossier
     *      le dossier à supprimer
     * @return  1 si le dossier est supprimé
     */
    @Override
    public Boolean supprimerDossier(Dossier dossier) {
        em.getTransaction().begin();
        em.remove(dossier);
        em.getTransaction().commit();
        return true;
    }

    /*
     * majConvocation permet de mettre à jour une convocation
     * @param convocation
     *      la convocation à mettre à jour
     * @return la convocation mise à jour
     */
    @Override
    public Boolean majConvocation(Convocation convocation) {
        em.getTransaction().begin();
        Convocation merge = em.merge(convocation);
        em.getTransaction().commit();
        if (merge == null) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * listerCandidats permet de lister les candidats du système
     * @return la collection de candidats
     */
    @Override
    public Collection<Candidat> listerCandidats() {
        Query query = em.createQuery("SELECT e FROM Candidat e");
        return (Collection<Candidat>) query.getResultList();
    }

    /*
     * listerDossier permet de lister les dossiers du système
     * @return la collecction de dossiers
     */
    @Override
    public Collection<Dossier> listerDossiers() {
        Query query = em.createQuery("SELECT e FROM Dossier e ORDER BY e.id");
        return (Collection<Dossier>) query.getResultList();
    }

    @Override
    public Convocation lireConvocation(Long idConvocation) {
        return em.find(Convocation.class, idConvocation);
    }

    @Override
    public Collection<Convocation> listerConvocations() {
        Query query = em.createQuery("SELECT e FROM Convocation e");
        System.out.println("gesCandiatlisterConvocation");
        //System.out.print(query.getResultList());
        return (Collection<Convocation>) query.getResultList();
    }

    @Override
    public Boolean majConvocation(Long id, String validation) {
        em.getTransaction().begin();
        Convocation convocation = lireConvocation(id);
        if (validation.equals("accepter")) {
            convocation.setValidation(Boolean.TRUE);
        } else {
            convocation.setValidation(Boolean.FALSE);
        }

        em.getTransaction().commit();

        if (convocation == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean majConvocation(Long id, String validation, String type) {
        em.getTransaction().begin();
        Convocation convocation = lireConvocation(id);
        if (convocation == null) {
            return false;
        }
        //System.out.print(convocation);
        if (validation.equals("accepter")) {
            convocation.setValidation(Boolean.TRUE);
        } else {
            convocation.setValidation(Boolean.FALSE);
        }

        convocation.setType(type);

        em.getTransaction().commit();


        return true;

    }

    @Override
    public Boolean majConvocation(Long id, String validation, String type, Date date) {
        em.getTransaction().begin();
        Convocation convocation = lireConvocation(id+1);
       // if (convocation == null) {
        //    return false;
       // }

        convocation.setType(type);
        convocation.setDatec(date);
        //convocation.setValidation(Boolean.FALSE);

        if (validation.equals("accepter")) {
            convocation.setValidation(Boolean.TRUE);
        } else {
            convocation.setValidation(Boolean.FALSE);
        }

        Convocation merge = em.merge(convocation);
        System.out.print("####\n date : "+merge.getDatec());
        em.getTransaction().commit();


        return true;
    }
}
