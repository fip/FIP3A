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
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import metier.Convocation;
import metier.Dossier;
import metier.GesCandidat;
import metier.GesEmploye;
import metier.GesOffre;
import metier.core.Candidat;
import metier.core.Employe;
import metier.core.Offre;

/**
 *
 * @author user
 */
@Singleton
public class GestionRH implements GestionRHLocal {

    //public final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionRH-ejbPU");
    //public final EntityManager em = emf.createEntityManager();

        private EntityManagerFactory emf;
    private EntityManager em = entityManager();

    public static final EntityManager entityManager() {
        return JpaUtils.get().createEntityManager();
    }
    public GestionRH() {
    }


    @Override
    public Boolean peuplerBase(Integer nombre) {
        
        

        GesEmploye e = new GesEmploye();
        String prenomEmp = "lePrenomEmp";
        String nomEmp = "leNomEmp";
        String poste = "lePosteEmp";

        GesOffre o = new GesOffre();

        String titre = "leTitre";
        String mission = "laMission";
        Integer salaire = 35000;

        GesCandidat c = new GesCandidat();
        String prenomCan = "lePrenomCan";
        String nomCan = "leNomCan";
        
        
        for(Convocation convocation: c.listerConvocations()){
            c.supprimerConvocation(convocation);
        }
        for(Dossier dossier: c.listerDossiers()){
            c.supprimerDossier(dossier);
        }
        
        for(Offre offre: o.listerOffres()) {
            o.supprimerOffre(offre);
        }
        
        for(Candidat candidat: c.listerCandidats()) {
            c.suppprimerCandidat(candidat);
        }
        
        for(Employe employe: e.listerEmployes()) {
            e.supprimerEmploye(employe);
        }
        
        Query q1 = em.createNativeQuery("SELECT setval('off_seq', 1);");
        List resultList1 = q1.getResultList();
        Query q2 = em.createNativeQuery("SELECT setval('can_seq', 1);");
        List resultList2 = q2.getResultList();
        Query q3 = em.createNativeQuery("SELECT setval('emp_seq', 1);");
        List resultList3 = q3.getResultList();
        Query q4 = em.createNativeQuery("SELECT setval('hibernate_sequence', 1);");
        List resultList4 = q4.getResultList();
                


        for (int i = 0; i < nombre; i++) {
            o.creerOffre(titre + i, mission + i, salaire + i,e.creerEmploye(prenomEmp + i, nomEmp + i, poste + i));
           // c.creerCandidat(prenomCan + i, nomCan + i);
            
        }
        return true;
    }

}
