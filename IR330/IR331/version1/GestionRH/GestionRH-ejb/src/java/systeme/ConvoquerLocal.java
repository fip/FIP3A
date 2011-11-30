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
import javax.ejb.Local;
import metier.Convocation;
import metier.core.Candidat;

/**
 * 
 * 
 * @version 1.0 
 * @author Yohann lepage
 * @author Alexandre Besnard
 * @author Jonathan Morfin
 * @author Quentin Rousseau
 */
@Local
public interface ConvoquerLocal {
    public Collection<Convocation> listerConvocation();
    public Convocation creerConvocation(Candidat candidat, Date date);
    public Convocation creerConvocationEntretien(Long idConvocation, Date date);
    public Boolean accepterConvocation(Convocation convocation);
    public Boolean refuserConvocation(Convocation convocation);
    public Convocation validerConvocation(Convocation convocation);
    public Convocation afficherConvocation(Long idConvocation);
}
