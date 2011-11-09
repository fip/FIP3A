package tpcorba.exo2;

import org.omg.CORBA.*;

import org.omg.PortableServer.*;

import java.io.*;

import java.lang.*;

import java.util.*;


public class Serveur {
    public static void main(String[] args) throws IOException {
        
        try {
            ORB orb = ORB.init(args, null);
            POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            poa.the_POAManager().activate();


            calculImpl calcImpl = new calculImpl();

            org.omg.CORBA.Object calc = poa.servant_to_reference(calcImpl);

            try {
                String calc_ref = orb.object_to_string(calc);
                String refFile = "calcul.ref";
                PrintWriter out = new PrintWriter(new FileOutputStream(refFile));
                out.println(calc_ref);
                out.close();
            } catch (IOException ex) {
                System.err.println(
                    "Impossible d'ecrire la reference dans calcul.ref");
                System.exit(1);
            }

            System.out.println("Le serveur est pret ");

            orb.run();

            System.exit(0);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
