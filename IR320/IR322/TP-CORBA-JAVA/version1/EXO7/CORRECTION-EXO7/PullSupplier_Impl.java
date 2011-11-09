

package events;

import org.omg.CORBA.*;
import java.lang.*;


public class PullSupplier_Impl extends _PullSupplierImplBase
{

    public Synchronized_Any current;

    public Synchronized_Bool is_disconnected =
                new Synchronized_Bool(false);



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
