package files;

/**
 *	Generated from IDL definition of exception "invalid_operation"
 *	@author JacORB IDL compiler 
 */

public final class invalid_operationHolder
	implements org.omg.CORBA.portable.Streamable
{
	public files.invalid_operation value;

	public invalid_operationHolder ()
	{
	}
	public invalid_operationHolder(final files.invalid_operation initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return files.invalid_operationHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = files.invalid_operationHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		files.invalid_operationHelper.write(_out, value);
	}
}
