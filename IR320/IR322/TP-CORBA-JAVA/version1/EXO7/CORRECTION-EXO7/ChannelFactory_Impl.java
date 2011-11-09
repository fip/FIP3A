
package events;

import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import java.lang.*;
import java.util.*;


public class ChannelFactory_Impl extends _ChannelFactoryImplBase
{

    // Reference sur la BOA
    //
    protected  BOA boa_ ;


    // Nombre courant d'objets dans l'usine
    //
    protected int nb;


    // Memorise les objets crees par l'usine
    //
    protected  EventChannel [] liste;



    public ChannelFactory_Impl(BOA b, int t)
    {
	taille=t;
	boa_=b;
	nb=0;
	liste = new annuaire.abonne [taille];
    }
    

    public void get_channel(EventChannelHolder ref,
                String name)
    {

	// Recherche : si l'objet n'exite pas, on le 
	// cree
	//
	int i=0;
	boolean ok=false;
        while(i<nb)
        	{
        	if(name.equals(liste[i].name()))
			{
			ok=true;
			break;
			}
        	else    i++;
        	}

	if (ok)
		ref.value=liste[i];
	else	{
		ref.value = new EventChannel_Impl(name);
		boa_.obj_is_ready(ref.value);

		if (nb==taille-1)
			{
			System.err.println("get_channel : creation channel impossible");
			System.exit(0);
			}
		liste[nb]=ref.value;
		nb=nb+1;
		}
		
    }


}
