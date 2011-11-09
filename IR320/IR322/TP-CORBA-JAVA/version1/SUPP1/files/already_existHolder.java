package files;

/**
 *	Generated from IDL definition of exception "already_exist"
 *	@author JacORB IDL compiler 
 */

public final class already_existHolder
	implements org.omg.CORBA.portable.Streamable
{
	public files.already_exist value;

	public already_existHolder ()
	{
	}
	public already_existHolder(final files.already_exist initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return files.already_existHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = files.already_existHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		files.already_existHelper.write(_out, value);
	}
}
