package files;

/**
 *	Generated from IDL interface "directory"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */


public interface directoryOperations
{
	/* constants */
	/* operations  */
	int number_of_file();
	void open_regular_file(files.regular_fileHolder r, java.lang.String name, files.mode m) throws files.no_such_file,files.invalid_type_file;
	void open_directory(files.directoryHolder f, java.lang.String name) throws files.no_such_file,files.invalid_type_file;
	void create_regular_file(files.regular_fileHolder r, java.lang.String name) throws files.already_exist;
	void create_directory(files.directoryHolder f, java.lang.String name) throws files.already_exist;
	void delete_file(java.lang.String name) throws files.no_such_file;
	int list_files(files.file_listHolder l);
}
