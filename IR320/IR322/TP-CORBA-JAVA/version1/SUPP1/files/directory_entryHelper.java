package files;


/**
 *	Generated from IDL definition of struct "directory_entry"
 *	@author JacORB IDL compiler 
 */

public final class directory_entryHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(files.directory_entryHelper.id(),"directory_entry",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("type", files.file_typeHelper.type(), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final files.directory_entry s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static files.directory_entry extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:files/directory_entry:1.0";
	}
	public static files.directory_entry read (final org.omg.CORBA.portable.InputStream in)
	{
		files.directory_entry result = new files.directory_entry();
		result.name=in.read_string();
		result.type=files.file_typeHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final files.directory_entry s)
	{
		out.write_string(s.name);
		files.file_typeHelper.write(out,s.type);
	}
}
