
package events;

import org.omg.CORBA.*;
import java.lang.*;


public class PushConsumer_Impl extends _PushConsumerImplBase
{


    public Synchronized_Any current;

    public Synchronized_Bool is_disconnected =
                new Synchronized_Bool(false);



    public PushConsumer_Impl(ORB o)
    {
	current=new Synchronized_Any(o);
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
