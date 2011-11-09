

package events;

import org.omg.CORBA.*;
import java.lang.*;


public class PullSupplier_Impl extends _PullSupplierImplBase
{


    // Valeur du dernier evenement receptionne
    //
    public Synchronized_Any current;

    // Boolean permettant de savoir si le producteur
    // a fait une demande de deconnexion
    //
    public Synchronized_Bool is_disconnected =
                new Synchronized_Bool(false);



    // Constructeur : une reference sur l'ORB est necessaire
    // pour la manipulation des types Any.
    // Le deuxieme parametre permet d'initialiser la variable
    // Any encapsulee dans PullSupplier_Impl.
    //
    public PullSupplier_Impl(ORB o, Any any)
    {
	current=new Synchronized_Any(o);
	current.set(any);
    }	  


    public void disconnect_pull_supplier()
    {
        is_disconnected.set(true);
    }

 
    public Any pull()
        throws disconnected
    {


	if (is_disconnected.get())
		throw new disconnected();


	return current.get();
    }

    
}
