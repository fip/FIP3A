package files;

/**
 *	Generated from IDL definition of struct "directory_entry"
 *	@author JacORB IDL compiler 
 */

public final class directory_entryHolder
	implements org.omg.CORBA.portable.Streamable
{
	public files.directory_entry value;

	public directory_entryHolder ()
	{
	}
	public directory_entryHolder(final files.directory_entry initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return files.directory_entryHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = files.directory_entryHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		files.directory_entryHelper.write(_out, value);
	}
}
