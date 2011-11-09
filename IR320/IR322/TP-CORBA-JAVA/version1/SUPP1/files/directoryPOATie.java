package files;

import org.omg.PortableServer.POA;

/**
 *	Generated from IDL interface "directory"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public class directoryPOATie
	extends directoryPOA
{
	private directoryOperations _delegate;

	private POA _poa;
	public directoryPOATie(directoryOperations delegate)
	{
		_delegate = delegate;
	}
	public directoryPOATie(directoryOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public files.directory _this()
	{
		return files.directoryHelper.narrow(_this_object());
	}
	public files.directory _this(org.omg.CORBA.ORB orb)
	{
		return files.directoryHelper.narrow(_this_object(orb));
	}
	public directoryOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(directoryOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		else
		{
			return super._default_POA();
		}
	}
	public int list_files(files.file_listHolder l)
	{
		return _delegate.list_files(l);
	}

	public int number_of_file()
	{
		return _delegate.number_of_file();
	}

	public void delete_file(java.lang.String name) throws files.no_such_file
	{
_delegate.delete_file(name);
	}

	public void open_regular_file(files.regular_fileHolder r, java.lang.String name, files.mode m) throws files.no_such_file,files.invalid_type_file
	{
_delegate.open_regular_file(r,name,m);
	}

	public void create_regular_file(files.regular_fileHolder r, java.lang.String name) throws files.already_exist
	{
_delegate.create_regular_file(r,name);
	}

	public void create_directory(files.directoryHolder f, java.lang.String name) throws files.already_exist
	{
_delegate.create_directory(f,name);
	}

	public void open_directory(files.directoryHolder f, java.lang.String name) throws files.no_such_file,files.invalid_type_file
	{
_delegate.open_directory(f,name);
	}

}
