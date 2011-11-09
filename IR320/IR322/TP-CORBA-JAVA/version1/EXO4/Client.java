
package tpcorba.exo4;

import org.omg.CORBA.*;

import java.io.*;

import java.lang.*;

import java.util.*;


public class Client {
    public static void main(String[] args) throws IOException {
        ////////////////////////////////////////////////////
        // On initialise l'ORB
        ////////////////////////////////////////////////////
        // initialize the ORB.
        ORB orb = ORB.init(args, null);

                if(args.length!=0)
                        {
                        System.err.println("utilisation : pas de parametre ");
                        System.exit(1);
                        }

        ////////////////////////////////////////////////////
        // Recuperation de la reference d'objet du serveur
        // Dans cet exemple, la reference est stockee sous
        // la forme d'une chaine de caracteres (IOR) dans un
        // fichier. A ce stade, il est bien sur possible 
        // d'invoquer un service de nommage.
        ////////////////////////////////////////////////////
        String ior = null;

        try {
            String ref = "gestion.ref";
            FileInputStream file = new FileInputStream(ref);
            BufferedReader in = new BufferedReader(new InputStreamReader(file));
            ior = in.readLine();
            file.close();
        } catch (IOException ex) {
            System.err.println("Impossible de lire fichier : `" +
                ex.getMessage() + "'");
            System.exit(1);
        }

        ////////////////////////////////////////////////////
        // Construction d'une reference d'objet, non typee d'abord,
        // puis "cast" en une reference sur l'interface 
        // "calcul"  avec narrow ("cast" dynamique)
        ////////////////////////////////////////////////////
        org.omg.CORBA.Object obj = orb.string_to_object(ior);

        if (obj == null) {
            System.err.println("Erreur sur string_to_object() ");
            throw new RuntimeException();
        }

        gestionabonnes gestion = gestionabonnesHelper.narrow(obj);

        if (gestion== null) {
            System.err.println("Erreur sur narrow() ");
            throw new RuntimeException();
        }

        ////////////////////////////////////////////////////
        // Invocation du serveur
        ////////////////////////////////////////////////////

 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		abonneHolder ref= new abonneHolder();
		String nom, prenom, numero, operation;


		while(true) 
			{	
      			try
				{
	      			System.out.println(" Que faire : 1- creation");
                        	System.out.println("             2- rechercher");
                       	 	System.out.println("             3- resilier");
	      			operation = br.readLine();
	      

				// Creation d'un abonne
				//
	      			if (operation.equals("1"))  
					{
					System.out.print("Numero = " );
					numero = br.readLine();
					System.out.print("Nom = " );
					nom = br.readLine();
					System.out.print("Prenom = " );
					prenom = br.readLine();
					Integer num = new Integer(numero);

					gestion.creation_abonne(num.intValue() ,nom, prenom, ref);
					System.out.println("Creation abonne numero " + numero + ": abonne " + ref.value.nom_prenom());
					}  

					// Recherche d'un abonne
					//
	      			else	if (operation.equals("2"))  
                        			{
						System.out.print("Numero = " );
						numero = br.readLine();
						Integer num = new Integer(numero);

						gestion.rechercher_abonne(num.intValue() ,ref);
						System.out.println("Numero " + numero + ": abonne " + ref.value.nom_prenom());
                        			}

						// Resiliation d'un abonne
						//
	      				else	if (operation.equals("3"))  
                        				{
							System.out.print("Numero = " );
							numero = br.readLine();
							Integer num = new Integer(numero);

							gestion.resilier_abonne(num.intValue());
							System.out.println("Numero " + numero + " resilie");
                        				}
						else System.out.println("  Saisir '1', '2'  ou '3'");
				}
      		catch(IOException ex)
			{
			System.out.println("Erreur lecture ");
			System.exit(1);
			}
      		catch(numeroInconnu ex)
			{
			System.out.println("Exception numeroInconnu");
			}
      		catch(dejaExistant ex)
			{
			System.out.println("Exception dejaExistant");
			}
		}


		}
}

