package files;

/**
 *	Generated from IDL definition of exception "no_such_file"
 *	@author JacORB IDL compiler 
 */

public final class no_such_fileHolder
	implements org.omg.CORBA.portable.Streamable
{
	public files.no_such_file value;

	public no_such_fileHolder ()
	{
	}
	public no_such_fileHolder(final files.no_such_file initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return files.no_such_fileHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = files.no_such_fileHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		files.no_such_fileHelper.write(_out, value);
	}
}
