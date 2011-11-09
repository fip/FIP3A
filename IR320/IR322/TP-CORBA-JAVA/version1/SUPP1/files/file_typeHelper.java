package files;
/**
 *	Generated from IDL definition of enum "file_type"
 *	@author JacORB IDL compiler 
 */

public final class file_typeHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_enum_tc(files.file_typeHelper.id(),"file_type",new String[]{"regular_file_type","directory_type"});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final files.file_type s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static files.file_type extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:files/file_type:1.0";
	}
	public static file_type read (final org.omg.CORBA.portable.InputStream in)
	{
		return file_type.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final file_type s)
	{
		out.write_long(s.value());
	}
}
