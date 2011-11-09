
#include <OB/CORBA.h>
#include <OB/Util.h>

#include <mo_it_impl.h>

#include <stdlib.h>
#include <errno.h>
#include <iostream.h>
#include <fstream.h>


int main(int argc, char* argv[], char*[])
{
    try
    {
	//
	// Initialisation environnement CORBA
	//
	CORBA_ORB_var orb = CORBA_ORB_init(argc, argv);
	CORBA_BOA_var boa = orb->BOA_init(argc, argv);
	
	//
	// Creation/activation de l'objet
	//
	mon_module_mon_interface_var p = new mo_it_impl(0.0);
	boa->obj_is_ready(p, CORBA_ImplementationDef::_nil());

	
	//
	// Publication de la reference CORBA
	//
	CORBA_String_var s = orb->object_to_string(p);
	
	const char* refFile = "objet.ref";
	ofstream out(refFile);
	if(out.fail())
	{
	    cerr << argv[0] << ": can't open `" << refFile << "': "
		 << strerror(errno) << endl;
	    return 1;
	}
	
	out << s << endl;
	out.close();
	
	//
	// Lancement du serveur
	//
	cout<<"Le serveur est pret"<<endl;
	boa->impl_is_ready(CORBA_ImplementationDef::_nil());

    }
    catch(CORBA_SystemException& ex)
    {
	OBPrintException(ex);
	return 1;
    }

    return 0;
}

