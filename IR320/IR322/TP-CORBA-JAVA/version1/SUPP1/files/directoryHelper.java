package files;


/**
 *	Generated from IDL interface "directory"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class directoryHelper
{
	public static void insert (final org.omg.CORBA.Any any, final files.directory s)
	{
			any.insert_Object(s);
	}
	public static files.directory extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:files/directory:1.0", "directory");
	}
	public static String id()
	{
		return "IDL:files/directory:1.0";
	}
	public static directory read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object());
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final files.directory s)
	{
		_out.write_Object(s);
	}
	public static files.directory narrow(final java.lang.Object obj)
	{
		if (obj instanceof files.directory)
		{
			return (files.directory)obj;
		}
		else if (obj instanceof org.omg.CORBA.Object)
		{
			return narrow((org.omg.CORBA.Object)obj);
		}
		throw new org.omg.CORBA.BAD_PARAM("Failed to narrow in helper");
	}
	public static files.directory narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (files.directory)obj;
		}
		catch (ClassCastException c)
		{
			if (obj._is_a("IDL:files/directory:1.0"))
			{
				files._directoryStub stub;
				stub = new files._directoryStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
			}
		}
		throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
	}
	public static files.directory unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (files.directory)obj;
		}
		catch (ClassCastException c)
		{
				files._directoryStub stub;
				stub = new files._directoryStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
		}
	}
}
