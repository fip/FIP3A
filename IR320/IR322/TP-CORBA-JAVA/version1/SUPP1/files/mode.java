package files;
/**
 *	Generated from IDL definition of enum "mode"
 *	@author JacORB IDL compiler 
 */

public final class mode
	implements org.omg.CORBA.portable.IDLEntity
{
	private int value = -1;
	public static final int _read_only = 0;
	public static final mode read_only = new mode(_read_only);
	public static final int _write_append = 1;
	public static final mode write_append = new mode(_write_append);
	public static final int _write_trunc = 2;
	public static final mode write_trunc = new mode(_write_trunc);
	public static final int _read_write = 3;
	public static final mode read_write = new mode(_read_write);
	public int value()
	{
		return value;
	}
	public static mode from_int(int value)
	{
		switch (value) {
			case _read_only: return read_only;
			case _write_append: return write_append;
			case _write_trunc: return write_trunc;
			case _read_write: return read_write;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected mode(int i)
	{
		value = i;
	}
	java.lang.Object readResolve()
	throws java.io.ObjectStreamException
	{
		return from_int(value());
	}
}
