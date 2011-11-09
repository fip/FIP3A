
#include <OB/CORBA.h>
#include <OB/Util.h>

#include <mo_it.h>

#include <stdlib.h>
#include <errno.h>
#include <fstream.h>
#include <iostream.h>



int
main(int argc, char* argv[])
{
    try
    {

	//
	// Initialisation de l'environnement CORBA
	//
	CORBA_ORB_var orb = CORBA_ORB_init(argc, argv);
	
	if (argc!=2)
		{
		cout<<"usage : client valeur"<<endl;
		exit(0);
		}

	//
	// Recuperation de la reference
	//
	const char* refFile = "objet.ref";
	ifstream in; 
	in.open(refFile);
	if(in.fail())
	{
	    cerr << argv[0] << ": can't open `" << refFile << "': "
		 << strerror(errno) << endl;
	    return 1;
	}
	
	char s[1000];
	in >> s;
	

	// 
	// Decodage reference CORBA
	//
	CORBA_Object_var obj = orb->string_to_object(s);
	assert(!CORBA_is_nil(obj));
	

	//
	// Creation du stub/souche
	//
	mon_module_mon_interface_var it = mon_module_mon_interface::_narrow(obj);
	assert(!CORBA_is_nil(it));
	

	//
	// Invocations 
	//
	double lu;
    	it->consulter(lu);
	cout<<"Valeur precedente = " <<lu<<endl;
    	it->modifier(atol(argv[1]));


	
    }
    catch(CORBA_SystemException& ex)
    {
	OBPrintException(ex);
	return 1;
    }

    return 0;
}



