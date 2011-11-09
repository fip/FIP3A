package files;
/**
 *	Generated from IDL definition of enum "mode"
 *	@author JacORB IDL compiler 
 */

public final class modeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public mode value;

	public modeHolder ()
	{
	}
	public modeHolder (final mode initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return modeHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = modeHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		modeHelper.write (out,value);
	}
}
