

package events;


import org.omg.CORBA.*;
import java.lang.*;


public class PullConsumer_Impl extends _PullConsumerImplBase
{


    // Boolean permettant de savoir si le producteur
    // a fait une demande de deconnexion
    //
    public Synchronized_Bool is_disconnected=
		new Synchronized_Bool(false);


    public void disconnect_pull_consumer()
    {
	is_disconnected.set(true);
    }


}
