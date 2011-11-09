

package events;


import org.omg.CORBA.*;
import java.lang.*;


public class PullConsumer_Impl extends _PullConsumerImplBase
{

    public Synchronized_Bool is_disconnected=
		new Synchronized_Bool(false);


    public void disconnect_pull_consumer()
    {
	is_disconnected.set(true);
    }


}
