
package events;

import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import java.lang.*;
import java.util.*;
import java.io.*;


public class AnyTest
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


    		Any any=orb.create_any();
         	any.insert_string(new String("coucou"));
                System.out.println("String = " + any.extract_string());

         	any.insert_long(100);
                System.out.println("long = " + any.extract_long());
		
         	any.insert_double(100.50);
                System.out.println("double = " + any.extract_double());

	}


}
