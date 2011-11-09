package tpcorba.exo1;

import org.omg.CORBA.*;

import java.io.*;

import java.lang.*;

import java.util.*;


public class Client {
    public static void main(String[] args) throws IOException {
        ////////////////////////////////////////////////////
        // On initialise l'ORB
        ////////////////////////////////////////////////////
        ORB orb = ORB.init(args, null);

        if (args.length != 1) {
            System.err.println("utilisation : Client nombre");
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
            String ref = "calcul.ref";
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
        // Construction d'une reference d'objet, non type d'abord,
        // puis "cast" en une reference sur l'interface 
        // "calcul"  avec narrow (generation d'une souche)
        ////////////////////////////////////////////////////
        org.omg.CORBA.Object obj = orb.string_to_object(ior);

        if (obj == null) {
            System.err.println("Erreur sur string_to_object() ");
            throw new RuntimeException();
        }

        calcul calc = calculHelper.narrow(obj);

        if (calc == null) {
            System.err.println("Erreur sur narrow() ");
            throw new RuntimeException();
        }

        ////////////////////////////////////////////////////
        // Invocation du serveur
        ////////////////////////////////////////////////////
        char car;

        Integer param = new Integer(args[0]);

        IntHolder resultat = new IntHolder(param.intValue());

        try {
            System.out.println(" Que faire (incrementer ou decrementer ; saisir i ou d)?  ");
            car = (char) System.in.read();

            if (car == 'i') {
                calc.incrementer(resultat);

                System.out.println(" Valeur incrementee = " + resultat.value);
            } else if (car == 'd') {
                calc.decrementer(resultat);

                System.out.println(" Valeur decrementee = " + resultat.value);
            } else {
                System.out.println("  Saisir 'i' ou 'd'");
            }
        } catch (IOException ex) {
            System.out.println("Erreur lecture commande (char)");
            System.exit(1);
        }

        System.exit(0);
    }
}
