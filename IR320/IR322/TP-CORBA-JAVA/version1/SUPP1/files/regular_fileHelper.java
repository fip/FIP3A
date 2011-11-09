package files;


/**
 *	Generated from IDL interface "regular_file"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class regular_fileHelper
{
	public static void insert (final org.omg.CORBA.Any any, final files.regular_file s)
	{
			any.insert_Object(s);
	}
	public static files.regular_file extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:files/regular_file:1.0", "regular_file");
	}
	public static String id()
	{
		return "IDL:files/regular_file:1.0";
	}
	public static regular_file read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object());
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final files.regular_file s)
	{
		_out.write_Object(s);
	}
	public static files.regular_file narrow(final java.lang.Object obj)
	{
		if (obj instanceof files.regular_file)
		{
			return (files.regular_file)obj;
		}
		else if (obj instanceof org.omg.CORBA.Object)
		{
			return narrow((org.omg.CORBA.Object)obj);
		}
		throw new org.omg.CORBA.BAD_PARAM("Failed to narrow in helper");
	}
	public static files.regular_file narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (files.regular_file)obj;
		}
		catch (ClassCastException c)
		{
			if (obj._is_a("IDL:files/regular_file:1.0"))
			{
				files._regular_fileStub stub;
				stub = new files._regular_fileStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
			}
		}
		throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
	}
	public static files.regular_file unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (files.regular_file)obj;
		}
		catch (ClassCastException c)
		{
				files._regular_fileStub stub;
				stub = new files._regular_fileStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
		}
	}
}
