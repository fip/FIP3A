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
import java.util.Date;
import javax.ejb.Stateless;
import metier.Convocation;
import metier.GesCandidat;
import metier.core.Candidat;

/**
 *
 * @author user
 */
@Stateless
public class Convoquer implements ConvoquerLocal {
    //protected EntityManager em;
    
    public Convoquer() {
    }

    @Override
    public Collection<Convocation> listerConvocation() {
        GesCandidat c = new GesCandidat();
       
        return c.listerConvocations();
    }

    @Override
    public Boolean accepterConvocation(Convocation convocation) {
        GesCandidat c = new GesCandidat();
        return c.majConvocation(convocation.getId(), "accepter");
    }

    @Override
    public Boolean refuserConvocation(Convocation convocation) {
        GesCandidat c = new GesCandidat();
        return c.majConvocation(convocation.getId(), "refuser");

    }

    @Override
    public Convocation validerConvocation(Convocation convocation) {
        GesCandidat c = new GesCandidat();
        //c.majConvocation(afficherConvocation(convocation.getId()), "accepter");
        return null;
    }

    @Override
    public Convocation afficherConvocation(Long idConvocation) {
        GesCandidat c = new GesCandidat();
        return c.lireConvocation(idConvocation);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Convocation creerConvocation(Candidat candidat, Date date) {
         GesCandidat c = new GesCandidat();
        return c.creerConvocation(date, candidat);
    }

    @Override
    public Convocation creerConvocationEntretien(Long idConvocation, Date date) {
        GesCandidat c = new GesCandidat();
        Boolean majConvocation = c.majConvocation(idConvocation, "refuser","entretien", date);
        if (majConvocation)
            return c.lireConvocation(idConvocation);
        else
            return null;
         
    }
    
}
