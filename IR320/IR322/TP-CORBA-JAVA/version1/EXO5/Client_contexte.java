

package tpcorba.exo5;


import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

import java.lang.*;
import java.util.*;
import java.io.*;




public class Client_contexte
	{


	public static void main(String args[]) throws IOException
		{ 

		ORB orb = ORB.init(args, null);

		org.omg.CORBA.Object obj=null, obj1 = null, 
				obj2 = null, obj3 = null,
				obj4 = null, obj5 = null;

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


   			NameComponent[] l1Name = new NameComponent[1];
                	l1Name[0] = new NameComponent();
                	l1Name[0].id = "etudiant1";
                	l1Name[0].kind = "article";
                	obj1=nc.resolve(l1Name);


                      	NameComponent[] l1bisName = new NameComponent[1];
                        l1bisName[0] = new NameComponent();
                        l1bisName[0].id = "contexte1";
                        l1bisName[0].kind = "";
                        org.omg.CORBA.Object nc1Obj = nc.resolve(l1bisName);
			NamingContext nc1 = NamingContextHelper.narrow(nc1Obj);
                	if(nc1 == null)
                    		throw new RuntimeException();
                        obj1 = nc1.resolve(l1Name);


                      	NameComponent[] l2Name = new NameComponent[1];
                	l2Name[0] = new NameComponent();
                        l2Name[0].id = "etudiant2";
                        l2Name[0].kind = "article";
                        obj2 = nc1.resolve(l2Name);


             		NameComponent[] l3Name = new NameComponent[3];
                	l3Name[0] = new NameComponent();
                	l3Name[0].id = "contexte1";
                	l3Name[0].kind = "";
               	 	l3Name[1] = new NameComponent();
                	l3Name[1].id = "contexte2";
                	l3Name[1].kind = "";
                	l3Name[2] = new NameComponent();
                	l3Name[2].id = "etudiant3";
                	l3Name[2].kind = "article";
                	obj3 = nc.resolve(l3Name);


                	l3Name[2].id = "etudiant4";
                	l3Name[2].kind = "article";
                	obj4 = nc.resolve(l3Name);


             		NameComponent[] l5Name = new NameComponent[1];
                	l5Name[0] = new NameComponent();
                	l5Name[0].id = "contexte3";
                	l5Name[0].kind = "";
                        org.omg.CORBA.Object nc5Obj = nc.resolve(l5Name);
                        NamingContext nc5 = NamingContextHelper.narrow(nc5Obj);
                        if(nc1 == null)
                                throw new RuntimeException();

                	l5Name[0].id = "contexte4";
                        nc5Obj = nc5.resolve(l5Name);
                        nc5 = NamingContextHelper.narrow(nc5Obj);
                        if(nc1 == null)
                                throw new RuntimeException();

                      	NameComponent[] l5bisName = new NameComponent[1];
                	l5bisName[0] = new NameComponent();
                        l5bisName[0].id = "etudiant5";
                        l5bisName[0].kind = "article";
                        obj5 = nc5.resolve(l5bisName);


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




		etudiant e1 = etudiantHelper.narrow(obj1);
		if(e1==null)
			{
			System.err.println("Erreur sur narrow() ");
			System.exit(0);
			}
		etudiant e2 = etudiantHelper.narrow(obj2);
		if(e2==null)
			{
			System.err.println("Erreur sur narrow() ");
			System.exit(0);
			}
		etudiant e3 = etudiantHelper.narrow(obj3);
		if(e3==null)
			{
			System.err.println("Erreur sur narrow() ");
			System.exit(0);
			}
		etudiant e4 = etudiantHelper.narrow(obj4);
		if(e4==null)
			{
			System.err.println("Erreur sur narrow() ");
			System.exit(0);
			}
		etudiant e5 = etudiantHelper.narrow(obj5);
		if(e5==null)
			{
			System.err.println("Erreur sur narrow() ");
			System.exit(0);
			}



		System.out.println("Etudiant1 : " + e1.nom() + " " + e1.prenom());
		System.out.println("Etudiant2 : " + e2.nom() + " " + e2.prenom());
		System.out.println("Etudiant3 : " + e3.nom() + " " + e3.prenom());
		System.out.println("Etudiant4 : " + e4.nom() + " " + e4.prenom());
		System.out.println("Etudiant5 : " + e5.nom() + " " + e5.prenom());


		System.exit(0);
		}
}

