

package tpcorba.exo5;


import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

import java.lang.*;
import java.util.*;
import java.io.*;




public class Client
	{


	public static void main(String args[]) throws IOException
		{ 

		ORB orb = ORB.init(args, null);


		org.omg.CORBA.Object obj = null;


		// ETAPE 1
		//
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



		// ETAPE 2
		//
            	NamingContext nc = NamingContextHelper.narrow(obj);
            	if(nc == null)
            		{
                	System.out.println("Reference type nil sur `NameService'");
                	System.exit(1);
            		}

    
 		try
                	{


			// ETAPE 3
			//
   			NameComponent[] aName = new NameComponent[1];
                	aName[0] = new NameComponent();
                	aName[0].id = "etudiant1";
                	aName[0].kind = "donnee";


			// ETAPE 4
			//
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




		// ETAPE 5
		//
		etudiant e1 = etudiantHelper.narrow(obj);
		if(e1==null)
			{
			System.err.println("Erreur sur narrow() ");
			System.exit(0);
			}



		System.out.println("Etudiant1 : " + e1.nom() + " " + e1.prenom());

		System.exit(0);
		}
}

