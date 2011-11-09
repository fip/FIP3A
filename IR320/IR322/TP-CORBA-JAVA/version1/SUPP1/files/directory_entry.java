package files;

/**
 *	Generated from IDL definition of struct "directory_entry"
 *	@author JacORB IDL compiler 
 */

public final class directory_entry
	implements org.omg.CORBA.portable.IDLEntity
{
	public directory_entry(){}
	public java.lang.String name = "";
	public files.file_type type;
	public directory_entry(java.lang.String name, files.file_type type)
	{
		this.name = name;
		this.type = type;
	}
}
