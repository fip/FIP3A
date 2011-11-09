
package tpcorba;

import org.omg.CORBA.*;
import java.lang.*;
import java.util.*;
import java.io.*;



public class Client
	{


	public static void main(String args[]) throws IOException 
		{ 


		////////////////////////////////////////////////////
		// On initialise l'ORB
		////////////////////////////////////////////////////

		Properties props = System.getProperties();
		props.put("org.omg.CORBA.ORBClass", "com.ooc.CORBA.ORB");
		props.put("org.omg.CORBA.ORBSingletonClass",
			"com.ooc.CORBA.ORBSingleton");
		System.setProperties(props);

		ORB orb = ORB.init(args, props);



		if(args.length!=1)
			{
			System.err.println("utilisation : Client nom-calculette");
			System.exit(1);
			}


		////////////////////////////////////////////////////
		// Recuperation de la reference d'objet du serveur
		// Dans cet exemple, la reference est stockee sous
		// la forme d'une chaine de caractere (IOR) dans un
		// fichier. A ce stade, il est bien sur possible 
		// d'invoquer un service de nommage.
		////////////////////////////////////////////////////
      
		String ior = null;
		try
			{
			String ref = "usine.ref";
			FileInputStream file = new FileInputStream(ref);
			BufferedReader in = new BufferedReader(new InputStreamReader(file));
			ior = in.readLine();
			file.close();
			}
		catch(IOException ex)
			{
			System.err.println("Impossible de lire fichier : `" +
				ex.getMessage() + "'");
			System.exit(1);
			}
      

		////////////////////////////////////////////////////
		// Construction d'une reference d'objet, non type d'abord,
		// puis "cast" en une reference sur l'interface 
		// "calcul"  avec narrow ("cast" dynamique)
		////////////////////////////////////////////////////
	
		org.omg.CORBA.Object obj = orb.string_to_object(ior);
		if(obj==null)
			{
			System.err.println("Erreur sur string_to_object() ");
			throw new RuntimeException();
			}
      

		tpcorba.usine us = tpcorba.usineHelper.narrow(obj);
		if(us==null)
			{
			System.err.println("Erreur sur narrow() ");
			throw new RuntimeException();
			}


		////////////////////////////////////////////////////
		// Invocations du serveur
		////////////////////////////////////////////////////

		char car;
		tpcorba.calcul calc=null;

		System.out.println(" Que faire ?");
		System.out.println("     cre(a)tion,  d(e)struction");
		System.out.println("    (c)onnecter, (d)econnecter ");
		System.out.println("     e(f)fectuer les calculs sur la memoire");

		while(true)
			{
      		
			try
				{

				car = (char)System.in.read();
				switch(car)
					{
					case 'c': 
						{
						calculHolder calcRef = new calculHolder(null);
						us.connecter(calcRef,args[0]);
						calc=calcRef.value;

						break;
						}
					case 'd': 
						{
						us.deconnecter(args[0]);
						calc=null;

						break;
						}
					case 'a': 
						{
						calculHolder calcRef = new calculHolder(null);
						us.creation(calcRef,args[0]);
						calc=calcRef.value;

						break;
						}
					case 'e': 
						{
						us.suppression(args[0]);
						calc=null;

						break;
						}
					case 'f': 
						{
						calc.ajouteMemoire(10);

						System.out.println("Valeur de la memoire : " + calc.memoire());
						break;
						}
					case '\n': 
						{
						break;
						}
					default: System.out.println("  Saisir 'c', 'd', 'a', 'e' ou 'f'");
					}
				}

                	catch(IOException ex)
				{
                        	System.out.println("Erreur lecture commande (char)");
				}
			catch(calculatriceEnUtilisation ex)
				{
				System.err.println("Exception : calculatriceEnUtilisation " );
				}
			catch(calculatriceDejaExistante ex)
				{
				System.err.println("Exception : calculatriceDejaExistante " );
				}
			catch(calculatriceInconnue ex)
				{
				System.err.println("Exception : calculatriceInconnue  " );
				}
			catch(aucunUtilisateur ex)
				{
				System.err.println("Exception : aucunUtilisateur " );
				}
			catch(plusDePlace ex)
				{
				System.err.println("Exception :  plusDePlace" );
				}
                	catch(NullPointerException ex)
				{
                        	System.out.println("Vous devez connecter ou creer la calculatrice avant de l'utiliser");
				}
			}
		}
}

