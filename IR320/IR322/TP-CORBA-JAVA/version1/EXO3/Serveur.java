package tpcorba.exo3;

import org.omg.CORBA.*;

import org.omg.PortableServer.*;

import java.io.*;
import java.lang.*;
import java.util.*;


public class Serveur {
    public static void main(String[] args) throws IOException {
        
        try {
            //init ORB
            ORB orb = ORB.init(args, null);

            //init POA
            POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            poa.the_POAManager().activate();

            ////////////////////////////////////////////////////////////////
            allocateurImpl allocimpl = new allocateurImpl(poa);
            org.omg.CORBA.Object alloc = poa.servant_to_reference(allocimpl);

            try {
                String calc_ref = orb.object_to_string(alloc);
                String refFile = "cpt.ref";
                PrintWriter out = new PrintWriter(new FileOutputStream(refFile));
                out.println(calc_ref);
                out.close();
            } catch (IOException ex) {
                System.err.println(
                    "Impossible d'ecrire la reference dans cpt.ref");
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
