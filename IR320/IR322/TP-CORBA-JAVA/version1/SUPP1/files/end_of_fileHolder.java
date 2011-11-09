package files;

/**
 *	Generated from IDL definition of exception "end_of_file"
 *	@author JacORB IDL compiler 
 */

public final class end_of_fileHolder
	implements org.omg.CORBA.portable.Streamable
{
	public files.end_of_file value;

	public end_of_fileHolder ()
	{
	}
	public end_of_fileHolder(final files.end_of_file initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return files.end_of_fileHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = files.end_of_fileHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		files.end_of_fileHelper.write(_out, value);
	}
}
