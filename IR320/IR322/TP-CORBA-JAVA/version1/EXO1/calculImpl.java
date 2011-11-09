
package tpcorba.exo1;

import org.omg.CORBA.*;
import java.lang.*;


public class calculImpl extends calculPOA
	{

	public void incrementer(IntHolder data)
		{
		System.out.println("Invocation de incrementer()");

		data.value=data.value+1;
		}


	public void decrementer(IntHolder data)
		{
		System.out.println("Invocation de decrementer()");

		data.value=data.value-1;
		}


	}
