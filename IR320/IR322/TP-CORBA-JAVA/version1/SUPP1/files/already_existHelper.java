package files;


/**
 *	Generated from IDL definition of exception "already_exist"
 *	@author JacORB IDL compiler 
 */

public final class already_existHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_exception_tc(files.already_existHelper.id(),"already_exist",new org.omg.CORBA.StructMember[0]);
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final files.already_exist s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static files.already_exist extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:files/already_exist:1.0";
	}
	public static files.already_exist read (final org.omg.CORBA.portable.InputStream in)
	{
		files.already_exist result = new files.already_exist();
		if (!in.read_string().equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id");
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final files.already_exist s)
	{
		out.write_string(id());
	}
}
