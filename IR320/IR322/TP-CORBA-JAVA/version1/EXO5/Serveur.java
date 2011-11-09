

package tpcorba.exo5;

import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.PortableServer.*;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Serveur 
	{
	public static void main(String args[]) throws IOException
		{ 

      	     	try {
			ORB orb = ORB.init(args, null);
			POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            		poa.the_POAManager().activate();

			etudiantImpl etu = new etudiantImpl("Dupont", "Robert");
			org.omg.CORBA.Object refetu = poa.servant_to_reference(etu);

   		

			// ETAPE 1
			//
  			org.omg.CORBA.Object obj=orb.resolve_initial_references("NameService");
	
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
    

			// ETAPE 3
			//
			NameComponent[] aName = new NameComponent[1];
       		        aName[0] = new NameComponent();
       		        aName[0].id = "etudiant1";
	       	        aName[0].kind = "donnee";


			// ETAPE 4
			//
               		nc.rebind(aName, refetu);



	  		System.out.println("Le serveur est pret ");
  			orb.run();
      
  
			System.exit(0);
		}

                catch(CannotProceed ex)
                        {
                        System.out.println("CannotProceed sur bind");
                        System.exit(1);
                        }
                catch(InvalidName ex)
                        {
                        System.out.println("InvalidName sur bind ou recherche NameService");
                        System.exit(1);
                        }
                catch(NotFound ex)
                        {
                        System.out.println("NotFound sur bind");
                        System.exit(1);
                        }
            	catch (Exception e) 
			{
            		System.out.println(e);
       	    		}

	}

}

