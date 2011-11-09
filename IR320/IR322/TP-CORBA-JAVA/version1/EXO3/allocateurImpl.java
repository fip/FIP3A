package tpcorba.exo3;

import org.omg.CORBA.*;
import java.lang.*;
import org.omg.PortableServer.*;


public class allocateurImpl extends allocateurPOA
	{


	// Reference sur la POA
	//
	protected  POA poa_;


	public  allocateurImpl(POA poa)
	{
		poa_=poa;
			
	}

	public void nouveau_compte(compte cpt, string titulaire, long numero_compte) 
	{
		allocateurImpl allocimpl = new allocateurImpl(poa);
                org.omg.CORBA.Object alloc = poa.servant_to_reference(allocimpl);
			

	}


}
