package tpcorba.exo1;

import org.omg.PortableServer.POA;

/**
 *	Generated from IDL interface "calcul"
 *	@author JacORB IDL compiler V 2.2, 7-May-2004
 */

public class calculPOATie
	extends calculPOA
{
	private calculOperations _delegate;

	private POA _poa;
	public calculPOATie(calculOperations delegate)
	{
		_delegate = delegate;
	}
	public calculPOATie(calculOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public tpcorba.exo1.calcul _this()
	{
		return tpcorba.exo1.calculHelper.narrow(_this_object());
	}
	public tpcorba.exo1.calcul _this(org.omg.CORBA.ORB orb)
	{
		return tpcorba.exo1.calculHelper.narrow(_this_object(orb));
	}
	public calculOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(calculOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		else
		{
			return super._default_POA();
		}
	}
	public void incrementer(org.omg.CORBA.IntHolder data)
	{
_delegate.incrementer(data);
	}

	public void decrementer(org.omg.CORBA.IntHolder data)
	{
_delegate.decrementer(data);
	}

}
