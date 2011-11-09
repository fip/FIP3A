

package events;

import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import java.util.*;
import java.io.*;
import java.lang.*;



public class Runc_PushConsumer
	{
	public static void main(String args[]) throws IOException
		{ 


		Properties props = System.getProperties();
		props.put("org.omg.CORBA.ORBClass", "com.ooc.CORBA.ORB");
		props.put("org.omg.CORBA.ORBSingletonClass",
			"com.ooc.CORBA.ORBSingleton");
		System.setProperties(props);


		ORB orb = ORB.init(args, props);
		BOA boa = ((com.ooc.CORBA.ORB)orb).BOA_init(args, props);

                if(args.length!=1)
                        {
                        System.err.println("utilisation : Runc_PushConsumer nom-du-canal  ");
                        System.exit(1);
                        }


		//
		// Creation de l'instance et du serveur
		//
		PushConsumer_Impl consumer = new PushConsumer_Impl(orb);
		boa.obj_is_ready(consumer,null);
  		
		new Impl_is_ready(boa).start();



		//
		// Recherche du canal
		//
		org.omg.CORBA.Object obj = null;

 		try
                	{
                    	obj = orb.resolve_initial_references("NameService");
                	}
           	catch(org.omg.CORBA.ORBPackage.InvalidName ex)
                        {
                        System.out.println("'NameService' inaccessible");
                        System.exit(1);
                        }

       		if(obj == null)
            		{
                	System.out.println("Reference nil sur `NameService'");
                	System.exit(1);
            		}



            	NamingContext nc = NamingContextHelper.narrow(obj);
            	if(nc == null)
            		{
                	System.out.println("Reference type nil sur `NameService'");
                	System.exit(1);
            		}

    
 		try
               		{

   			NameComponent[] aName = new NameComponent[1];
       	        	aName[0] = new NameComponent();
               		aName[0].id = "ChannelFactory";
               		aName[0].kind = "events";

                	obj=nc.resolve(aName);


                	}
                catch(CannotProceed ex)
       	        	{
               	    	System.out.println("CannotProceed sur resolve");
                    	System.exit(1);
       	        	}
                catch(InvalidName ex)
       	        	{
                    	System.out.println("InvalidName sur resolve");
                    	System.exit(1);
               		}
                catch(NotFound ex)
      	         	{
                    	System.out.println("NotFound sur resolve");
                    	System.exit(1);
                	}



		// Enregistrement dans le canal
                //
                events.ChannelFactory factory = events.ChannelFactoryHelper.narrow(obj);
                if(factory==null)
                        {
                        System.err.println("Erreur sur narrow() ");
                        System.exit(0);
                        }

                EventChannelHolder ch = new EventChannelHolder();
                PushSupplierHolder supplier = new PushSupplierHolder();

                factory.get_channel(ch, args[1]);
                ch.value.connect_PushConsumer(consumer,
                         supplier);



                // Boucle principale
                //
                while(!consumer.is_disconnected.get())
                        {

                        System.out.println("Message recu : " + consumer.current.extract_string() );

                        try {
                                Thread.sleep(1000);
                        } catch (InterruptedException e) {}

                        }



		}


	}


