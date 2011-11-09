

package tpcorba;

import org.omg.CORBA.*;
import java.lang.*;



public class usine_Impl extends _usineImplBase
	{


	// Reference sur la BOA
	//
	protected  BOA boa_ ;


	// Nombre maximal d'objets que l'usine
	// peut creer
	//
	protected  int taille;

	
	// Etat des objets CORBA geres par l'usine
	//
	***


	// Constructeur
	//
	public  usine_Impl(BOA boa, int t)
		{
		boa_=boa;
		taille=t;
		}




	*****

	}



