package files;

/**
 *	Generated from IDL interface "regular_file"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class regular_fileHolder	implements org.omg.CORBA.portable.Streamable{
	 public regular_file value;
	public regular_fileHolder()
	{
	}
	public regular_fileHolder (final regular_file initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return regular_fileHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = regular_fileHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		regular_fileHelper.write (_out,value);
	}
}
