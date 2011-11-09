package files;
/**
 *	Generated from IDL definition of enum "file_type"
 *	@author JacORB IDL compiler 
 */

public final class file_type
	implements org.omg.CORBA.portable.IDLEntity
{
	private int value = -1;
	public static final int _regular_file_type = 0;
	public static final file_type regular_file_type = new file_type(_regular_file_type);
	public static final int _directory_type = 1;
	public static final file_type directory_type = new file_type(_directory_type);
	public int value()
	{
		return value;
	}
	public static file_type from_int(int value)
	{
		switch (value) {
			case _regular_file_type: return regular_file_type;
			case _directory_type: return directory_type;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected file_type(int i)
	{
		value = i;
	}
	java.lang.Object readResolve()
	throws java.io.ObjectStreamException
	{
		return from_int(value());
	}
}
