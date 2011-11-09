package tpcorba.exo3;

import org.omg.CORBA.*;

import java.io.*;
import java.lang.*;
import java.util.*;


public class Client {
    public static void main(String[] args) throws IOException {
        ////////////////////////////////////////////////////
        ORB orb = ORB.init(args, null);

        if(args.length!=0)
               {
               System.err.println("utilisation : pas de parametre ");
               System.exit(1);
               }

        ////////////////////////////////////////////////////
        String ior = null;

        try {
            String ref = "cpt.ref";
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
        org.omg.CORBA.Object obj = orb.string_to_object(ior);

        if (obj == null) {
            System.err.println("Erreur sur string_to_object() ");
            throw new RuntimeException();
        }

        allocateur alloc= allocateurHelper.narrow(obj);

        if (alloc == null) {
            System.err.println("Erreur sur narrow() ");
            throw new RuntimeException();
        }

        ////////////////////////////////////////////////////
        // Invocation du serveur
        ////////////////////////////////////////////////////

        // Creation de deux comptes
        //
        compteHolder Hcpt1 = new compteHolder();
        compte cpt1;

        alloc.nouveau_compte(Hcpt1,"toto",1);

        cpt1=Hcpt1.value;

        System.out.println("titulaire cpt1 = " + cpt1.titulaire());

	***

        System.exit(0);
    }
}
