#include <OB/CORBA.h>
#include <mo_it_impl.h>

#include <iostream.h>


mo_it_impl::mo_it_impl(double init)
{
	val=init;
}


void mo_it_impl::modifier(const double s)
{
    cout << "modifier()" << endl;
    val=s;
}

void mo_it_impl::consulter(double& s)
{
    cout << "consulter()" << endl;
    s=val;
}



