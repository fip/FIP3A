

package events;

import org.omg.CORBA.*;
import java.lang.*;



public class PushSupplier_Impl extends _PushSupplierImplBase
{

    // Boolean permettant de savoir si le consommateur
    // a fait une demande de deconnexion
    //
    public Synchronized_Bool is_disconnected=
		new Synchronized_Bool(false);


    public void disconnect_push_supplier()
    {
	is_disconnected.set(true);
    }


}

