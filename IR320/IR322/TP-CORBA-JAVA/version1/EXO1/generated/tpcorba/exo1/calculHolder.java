package tpcorba.exo1;

/**
 *	Generated from IDL interface "calcul"
 *	@author JacORB IDL compiler V 2.2, 7-May-2004
 */

public final class calculHolder	implements org.omg.CORBA.portable.Streamable{
	 public calcul value;
	public calculHolder()
	{
	}
	public calculHolder (final calcul initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return calculHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = calculHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		calculHelper.write (_out,value);
	}
}
