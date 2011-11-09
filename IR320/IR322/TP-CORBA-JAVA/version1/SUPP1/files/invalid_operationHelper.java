package files;


/**
 *	Generated from IDL definition of exception "invalid_operation"
 *	@author JacORB IDL compiler 
 */

public final class invalid_operationHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_exception_tc(files.invalid_operationHelper.id(),"invalid_operation",new org.omg.CORBA.StructMember[0]);
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final files.invalid_operation s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static files.invalid_operation extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:files/invalid_operation:1.0";
	}
	public static files.invalid_operation read (final org.omg.CORBA.portable.InputStream in)
	{
		files.invalid_operation result = new files.invalid_operation();
		if (!in.read_string().equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id");
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final files.invalid_operation s)
	{
		out.write_string(id());
	}
}
