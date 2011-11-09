package files;
/**
 *	Generated from IDL definition of enum "mode"
 *	@author JacORB IDL compiler 
 */

public final class modeHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_enum_tc(files.modeHelper.id(),"mode",new String[]{"read_only","write_append","write_trunc","read_write"});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final files.mode s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static files.mode extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:files/mode:1.0";
	}
	public static mode read (final org.omg.CORBA.portable.InputStream in)
	{
		return mode.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final mode s)
	{
		out.write_long(s.value());
	}
}
