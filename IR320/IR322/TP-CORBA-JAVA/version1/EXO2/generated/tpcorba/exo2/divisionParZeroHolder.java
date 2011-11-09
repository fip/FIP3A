package tpcorba.exo2;

/**
 *	Generated from IDL definition of exception "divisionParZero"
 *	@author JacORB IDL compiler 
 */

public final class divisionParZeroHolder
	implements org.omg.CORBA.portable.Streamable
{
	public tpcorba.exo2.divisionParZero value;

	public divisionParZeroHolder ()
	{
	}
	public divisionParZeroHolder(final tpcorba.exo2.divisionParZero initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return tpcorba.exo2.divisionParZeroHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = tpcorba.exo2.divisionParZeroHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		tpcorba.exo2.divisionParZeroHelper.write(_out, value);
	}
}
