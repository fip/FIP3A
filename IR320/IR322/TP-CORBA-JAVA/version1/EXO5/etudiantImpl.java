

package tpcorba.exo5;

import org.omg.CORBA.*;
import java.lang.*;


public class etudiantImpl extends etudiantPOA
	{


	private String att_nom;
	private String att_prenom;


	public etudiantImpl(String nom, String prenom)
		{
		att_nom=nom;
		att_prenom=prenom;
		}

	public String nom()
		{
		return att_nom;
		}

	public String prenom()
		{
		return att_prenom;
		}

	}
