

package tpcorba.exo6;


import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

import java.lang.*;
import java.util.*;
import java.io.*;




public class Init
	{


	public static void main(String args[]) throws IOException
		{ 

		ORB orb = ORB.init(args, null);


		org.omg.CORBA.Object obj = null;


 		try
                	{
			obj = orb.resolve_initial_references("NameService");
                	}
                catch(org.omg.CORBA.ORBPackage.InvalidName ex)
                	{
                    	System.out.println("'NameService' inaccessible");
                    	System.exit(1);
                	}

       		if(obj == null)
            		{
                	System.out.println("Reference nil sur `NameService'");
                	System.exit(1);
            		}



            	NamingContext nc = NamingContextHelper.narrow(obj);
            	if(nc == null)
            		{
                	System.out.println("Reference type nil sur `NameService'");
                	System.exit(1);
            		}

    
                try
                        {

                        NameComponent[] aName = new NameComponent[1];
                        aName[0] = new NameComponent();
                        aName[0].id = "formations professionnelles";
                        aName[0].kind = "usine";

                        obj=nc.resolve(aName);



                        }
                catch(CannotProceed ex)
                        {
                        System.out.println("CannotProceed sur resolve");
                        System.exit(1);
                        }
                catch(InvalidName ex)
                        {
                        System.out.println("InvalidName sur resolve");
                        System.exit(1);
                        }
                catch(NotFound ex)
                        {
                        System.out.println("NotFound sur resolve");
                        System.exit(1);
                        }


		usine us = usineHelper.narrow(obj);
		if(us==null)
			{
			System.err.println("Erreur sur narrow() ");
			System.exit(0);
			}



		// Creation des objets et publication vers le serveur 
		// de nom
		//
		etudiant e1=us.inscription_d_un_etudiant("e1-nom", "e1-prenom");
		etudiant e2=us.inscription_d_un_etudiant("e2-nom", "e2-prenom");
		etudiant e3=us.inscription_d_un_etudiant("e3-nom", "e3-prenom");
		etudiant e4=us.inscription_d_un_etudiant("e4-nom", "e4-prenom");
		etudiant e5=us.inscription_d_un_etudiant("e5-nom", "e5-prenom");
		etudiant e6=us.inscription_d_un_etudiant("e6-nom", "e6-prenom");


		***
 
		System.exit(0);
		}
}

