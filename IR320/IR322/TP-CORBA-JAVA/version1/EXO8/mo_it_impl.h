
#ifndef MO_IT_IMPL
#define MO_IT_IMPL

#include <OB/CORBA.h>
#include <mo_it_skel.h>
#include <iostream.h>


class mo_it_impl : public mon_module_mon_interface_skel {

public:
	mo_it_impl(double init);

	virtual void modifier(const double s);
	virtual void consulter(double& s);

private:

	double val;

};


#endif


