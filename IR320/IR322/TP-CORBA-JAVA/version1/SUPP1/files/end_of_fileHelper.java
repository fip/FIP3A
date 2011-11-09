package files;


/**
 *	Generated from IDL definition of exception "end_of_file"
 *	@author JacORB IDL compiler 
 */

public final class end_of_fileHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_exception_tc(files.end_of_fileHelper.id(),"end_of_file",new org.omg.CORBA.StructMember[0]);
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final files.end_of_file s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static files.end_of_file extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:files/end_of_file:1.0";
	}
	public static files.end_of_file read (final org.omg.CORBA.portable.InputStream in)
	{
		files.end_of_file result = new files.end_of_file();
		if (!in.read_string().equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id");
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final files.end_of_file s)
	{
		out.write_string(id());
	}
}
