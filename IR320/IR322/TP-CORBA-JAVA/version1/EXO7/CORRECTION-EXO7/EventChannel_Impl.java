
package events;

import org.omg.CORBA.*;
import java.lang.*;


public class EventChannel_Impl  extends _EventChannelImplBase
{


    private String att_name;
    private boolean att_supplier_is_connected;


   // Nombre de PushConsumer
    //
    protected int nbPushConsumer;


    // Les objets PushConsumer
    //
    protected  PushConsumer [] PushConsumerListe;

   
    protected PushSupplier supplier;


    // Reference sur la BOA
    //
    protected  BOA boa_ ;




    public EventChannel_Impl(String n, BOA boa)
    {
	boa_=boa;
	att_name=n;
    	att_supplier_is_connected=false;
    }

    public String name()
    {
	return att_name;
    }

    public boolean supplierConnected()
    {
    	return att_supplier_is_connected;
    }

    public void connect_PullConsumer(PullConsumer remote,
                         PullSupplierHolder into_channel)
    {
    }

    public void connect_PullSupplier(PullSupplier remote,
                         PullConsumerHolder into_channel)
        throws alreadyConnected
    {
    }

    public void connect_PushConsumer(PushConsumer remote,
                         PushSupplierHolder into_channel)
    {
    }

    public void connect_PushSupplier(PushSupplier remote,
                         PushConsumerHolder into_channel)
        throws alreadyConnected
    {
    }

    public void connect_EventChannel(EventChannel supplier)
        throws alreadyConnected
    {
    }


}




