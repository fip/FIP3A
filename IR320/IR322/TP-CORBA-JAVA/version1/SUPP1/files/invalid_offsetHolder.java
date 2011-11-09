package files;

/**
 *	Generated from IDL definition of exception "invalid_offset"
 *	@author JacORB IDL compiler 
 */

public final class invalid_offsetHolder
	implements org.omg.CORBA.portable.Streamable
{
	public files.invalid_offset value;

	public invalid_offsetHolder ()
	{
	}
	public invalid_offsetHolder(final files.invalid_offset initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return files.invalid_offsetHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = files.invalid_offsetHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		files.invalid_offsetHelper.write(_out, value);
	}
}
