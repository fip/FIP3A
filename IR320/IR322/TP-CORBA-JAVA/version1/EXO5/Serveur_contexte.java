


package tpcorba.exo5;

import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.PortableServer.*;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Serveur_contexte
	{

	public static void main(String args[]) throws IOException
		{ 

	       	try {
                	ORB orb = ORB.init(args, null);
                        POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

                        poa.the_POAManager().activate();


			etudiantImpl e1 = new etudiantImpl("Dupont", "Robert");
			org.omg.CORBA.Object refetu1 = poa.servant_to_reference(e1);

			etudiantImpl e2 = new etudiantImpl("Smith", "John");
			org.omg.CORBA.Object refetu2 = poa.servant_to_reference(e2);

			etudiantImpl e3 = new etudiantImpl("Lesmemealamaison", "Leon");
			org.omg.CORBA.Object refetu3 = poa.servant_to_reference(e3);

			etudiantImpl e4 = new etudiantImpl("Quatre", "Henri");
			org.omg.CORBA.Object refetu4 = poa.servant_to_reference(e4);

			etudiantImpl e5 = new etudiantImpl("Ouille", "Jacques");
			org.omg.CORBA.Object refetu5 = poa.servant_to_reference(e5);



			org.omg.CORBA.Object obj = null;
	             	obj = orb.resolve_initial_references("NameService");
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

   
                	NameComponent[] nc1Name = new NameComponent[1];
       	        	nc1Name[0] = new NameComponent();
               		nc1Name[0].id = "contexte1";
               		nc1Name[0].kind = "";
                	NamingContext nc1 = nc.bind_new_context(nc1Name);
                       	System.out.println("contexte1 est publie ");

               		NameComponent[] nc2Name = new NameComponent[1];
       	        	nc2Name[0] = new NameComponent();
      	 		nc2Name[0].id = "contexte2";
              		nc2Name[0].kind = "";
       	        	NamingContext nc2 = nc1.bind_new_context(nc2Name);
                 	System.out.println("contexte2 est publie ");



  			NameComponent[] a1Name = new NameComponent[1];
      	        	a1Name[0] = new NameComponent();
               		a1Name[0].id = "etudiant1";
               		a1Name[0].kind = "article";
                	nc.bind(a1Name, refetu1);
       	        	nc1.bind(a1Name, refetu1);
               	        System.out.println("etudiant1 est publie ");

                	NameComponent[] a2Name = new NameComponent[1];
       	        	a2Name[0] = new NameComponent();
               		a2Name[0].id = "etudiant2";
               		a2Name[0].kind = "article";
       	 		nc1.bind(a2Name, refetu2);
               	        System.out.println("etudiant2 est publie ");

       	  		NameComponent[] a3Name = new NameComponent[1];
               		a3Name[0] = new NameComponent();
               		a3Name[0].id = "etudiant3";
               		a3Name[0].kind = "article";
               		nc2.bind(a3Name, refetu3);
                       	System.out.println("etudiant3 est publie ");

               		NameComponent[] a4Name = new NameComponent[1];
               		a4Name[0] = new NameComponent();
               		a4Name[0].id = "etudiant4";
               		a4Name[0].kind = "article";
               		nc2.bind(a4Name, refetu4);
                       	System.out.println("etudiant4 est publie ");


                       	NameComponent[] nc3Name = new NameComponent[1];
                       	nc3Name[0] = new NameComponent();
                       	nc3Name[0].id = "contexte3";
                       	nc3Name[0].kind = "";
                       	NamingContext nc3 = nc.bind_new_context(nc3Name);
                       	System.out.println("contexte3 est publie ");
	
                       	NameComponent[] nc4Name = new NameComponent[1];
                       	nc4Name[0] = new NameComponent();
                       	nc4Name[0].id = "contexte4";
                       	nc4Name[0].kind = "";
                       	NamingContext nc4 = nc3.bind_new_context(nc4Name);
                       	System.out.println("contexte4 est publie ");


       	        	NameComponent[] a5Name = new NameComponent[3];
       	        	a5Name[0] = new NameComponent();
       	        	a5Name[0].id = "contexte3";
       	        	a5Name[0].kind = "";
       	        	a5Name[1] = new NameComponent();
       	        	a5Name[1].id = "contexte4";
       	        	a5Name[1].kind = "";
	         	a5Name[2] = new NameComponent();
      	         	a5Name[2].id = "etudiant5";
      	         	a5Name[2].kind = "article";
 	              	nc.bind(a5Name,refetu5);
       	                System.out.println("etudiant5 est publie ");
	

	  		System.out.println("Le serveur est pret ");
			orb.run();
			System.exit(0);
	       		}

        	        catch(AlreadyBound ex)
                	        {
                        	System.out.println("AlreadyBound sur bind");
	                        System.exit(1);
        	                }
                	catch(CannotProceed ex)
                        	{
                       	 	System.out.println("CannotProceed sur bind");
                        	System.exit(1);
                        	}
	                catch(InvalidName ex)
        	                {
                	        System.out.println("InvalidName sur bind");
                        	System.exit(1);
                     		}
	                catch(NotFound ex)
        	                {
                	        System.out.println("NotFound sur bind");
                       	 	System.exit(1);
                        	}
	                catch(org.omg.PortableServer.POAManagerPackage.AdapterInactive ex)
        	                {
                	        System.out.println("org.omg.PortableServer.POAManagerPackage.AdapterInactive sur POA activate");
                       	 	System.exit(1);
                        	}
	                catch(org.omg.PortableServer.POAPackage.WrongPolicy ex)
        	                {
                	        System.out.println("org.omg.PortableServer.POAPackage.WrongPolicy sur poa.servant_to_reference");
                       	 	System.exit(1);
                        	}
	                catch(org.omg.PortableServer.POAPackage.ServantNotActive ex)
        	                {
                	        System.out.println("org.omg.PortableServer.POAPackage.ServantNotActive sur poa.servant_to_reference");
                       	 	System.exit(1);
                        	}
	                catch(org.omg.CORBA.ORBPackage.InvalidName ex)
        	                {
                	        System.out.println("org.omg.CORBA.ORBPackage.InvalidName sur POA narrow");
                       	 	System.exit(1);
                        	}
		}


	}



