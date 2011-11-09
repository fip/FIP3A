package tpcorba.exo2;


/**
 *	Generated from IDL definition of exception "divisionParZero"
 *	@author JacORB IDL compiler 
 */

public final class divisionParZeroHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_exception_tc(tpcorba.exo2.divisionParZeroHelper.id(),"divisionParZero",new org.omg.CORBA.StructMember[0]);
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final tpcorba.exo2.divisionParZero s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static tpcorba.exo2.divisionParZero extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:tpcorba/exo2/divisionParZero:1.0";
	}
	public static tpcorba.exo2.divisionParZero read (final org.omg.CORBA.portable.InputStream in)
	{
		tpcorba.exo2.divisionParZero result = new tpcorba.exo2.divisionParZero();
		if (!in.read_string().equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id");
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final tpcorba.exo2.divisionParZero s)
	{
		out.write_string(id());
	}
}
