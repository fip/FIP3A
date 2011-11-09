package files;

/**
 *	Generated from IDL interface "regular_file"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */


public interface regular_fileOperations
{
	/* constants */
	/* operations  */
	int read(int size, org.omg.CORBA.StringHolder data) throws files.invalid_operation,files.end_of_file;
	int write(int size, java.lang.String data) throws files.invalid_operation;
	void seek(int new_offset) throws files.invalid_offset,files.invalid_operation;
	void close();
}
