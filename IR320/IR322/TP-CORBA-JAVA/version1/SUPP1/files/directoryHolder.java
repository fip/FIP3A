package files;

/**
 *	Generated from IDL interface "directory"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class directoryHolder	implements org.omg.CORBA.portable.Streamable{
	 public directory value;
	public directoryHolder()
	{
	}
	public directoryHolder (final directory initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return directoryHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = directoryHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		directoryHelper.write (_out,value);
	}
}
