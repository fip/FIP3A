

package events;


import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import java.util.*;
import java.io.*;
import java.lang.*;





public class Run_EventChannel 
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



		ChannelFactory_Impl factory  = new ChannelFactory_Impl(boa);
		boa.obj_is_ready(factory, null);


		//
		// Publication de l'objet
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


                	nc.bind(aName, factory);
       			}


                catch(CannotProceed ex)
                        {
                        System.out.println("CannotProceed sur bind");
                        System.exit(1);
                        }
                catch(InvalidName ex)
                        {
                        System.out.println("InvalidName sur bind");
                        System.exit(1);
                        }
	        catch(AlreadyBound ex)
        	        {
	                System.out.println("AlreadyBound sur bind");
	                System.exit(1);
                	}
                catch(NotFound ex)
                        {
                        System.out.println("NotFound sur bind");
                        System.exit(1);
                        }


		boa.impl_is_ready(null);


		}


	}



