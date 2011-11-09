package files;

/**
 *	Generated from IDL interface "file_list"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class file_listHolder	implements org.omg.CORBA.portable.Streamable{
	 public file_list value;
	public file_listHolder()
	{
	}
	public file_listHolder (final file_list initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return file_listHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = file_listHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		file_listHelper.write (_out,value);
	}
}
