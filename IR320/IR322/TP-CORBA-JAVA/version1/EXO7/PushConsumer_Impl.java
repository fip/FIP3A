
package events;

import org.omg.CORBA.*;
import java.lang.*;


public class PushConsumer_Impl extends _PushConsumerImplBase
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
    public PushConsumer_Impl(ORB o, Any any)
    {
     	current=new Synchronized_Any(o);
	current.set(any);
    }	  



    public void push(Any a)
        throws disconnected
    {
	if (is_disconnected.get()) 
		throw new disconnected();

	current.set(a);
    }

    
    public void disconnect_push_consumer()
    {
        is_disconnected.set(true);
    }


    
}
