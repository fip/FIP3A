package files;
/**
 *	Generated from IDL definition of enum "file_type"
 *	@author JacORB IDL compiler 
 */

public final class file_typeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public file_type value;

	public file_typeHolder ()
	{
	}
	public file_typeHolder (final file_type initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return file_typeHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = file_typeHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		file_typeHelper.write (out,value);
	}
}
