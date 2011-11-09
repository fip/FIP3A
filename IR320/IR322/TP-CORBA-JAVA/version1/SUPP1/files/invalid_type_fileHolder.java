package files;

/**
 *	Generated from IDL definition of exception "invalid_type_file"
 *	@author JacORB IDL compiler 
 */

public final class invalid_type_fileHolder
	implements org.omg.CORBA.portable.Streamable
{
	public files.invalid_type_file value;

	public invalid_type_fileHolder ()
	{
	}
	public invalid_type_fileHolder(final files.invalid_type_file initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return files.invalid_type_fileHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = files.invalid_type_fileHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		files.invalid_type_fileHelper.write(_out, value);
	}
}
