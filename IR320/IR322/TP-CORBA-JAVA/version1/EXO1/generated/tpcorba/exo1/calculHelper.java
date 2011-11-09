package tpcorba.exo1;


/**
 *	Generated from IDL interface "calcul"
 *	@author JacORB IDL compiler V 2.2, 7-May-2004
 */

public final class calculHelper
{
	public static void insert (final org.omg.CORBA.Any any, final tpcorba.exo1.calcul s)
	{
			any.insert_Object(s);
	}
	public static tpcorba.exo1.calcul extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:tpcorba/exo1/calcul:1.0", "calcul");
	}
	public static String id()
	{
		return "IDL:tpcorba/exo1/calcul:1.0";
	}
	public static calcul read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object());
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final tpcorba.exo1.calcul s)
	{
		_out.write_Object(s);
	}
	public static tpcorba.exo1.calcul narrow(final java.lang.Object obj)
	{
		if (obj instanceof tpcorba.exo1.calcul)
		{
			return (tpcorba.exo1.calcul)obj;
		}
		else if (obj instanceof org.omg.CORBA.Object)
		{
			return narrow((org.omg.CORBA.Object)obj);
		}
		throw new org.omg.CORBA.BAD_PARAM("Failed to narrow in helper");
	}
	public static tpcorba.exo1.calcul narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (tpcorba.exo1.calcul)obj;
		}
		catch (ClassCastException c)
		{
			if (obj._is_a("IDL:tpcorba/exo1/calcul:1.0"))
			{
				tpcorba.exo1._calculStub stub;
				stub = new tpcorba.exo1._calculStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
			}
		}
		throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
	}
	public static tpcorba.exo1.calcul unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (tpcorba.exo1.calcul)obj;
		}
		catch (ClassCastException c)
		{
				tpcorba.exo1._calculStub stub;
				stub = new tpcorba.exo1._calculStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
		}
	}
}
