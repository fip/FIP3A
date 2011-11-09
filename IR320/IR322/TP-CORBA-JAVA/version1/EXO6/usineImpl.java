


package tpcorba.exo6;

import org.omg.CORBA.*;
import java.lang.*;
import org.omg.PortableServer.*;


public class usineImpl extends usinePOA
	{


	private POA poa_;


	public usineImpl(POA poa)
		{
		poa_=poa;
		}


        public etudiant inscription_d_un_etudiant(
                                String nom, String prenom)
		{

		etudiant e=null;

    		try 	{
			etudiantImpl ei = new etudiantImpl(nom, prenom);
			org.omg.CORBA.Object allocated = poa_.servant_to_reference(ei);
                	e=etudiantHelper.narrow(allocated);
                     	}
                catch (Exception ex) 
			{
                    	System.out.println(ex);
                	}

		return  e;
		}



}
