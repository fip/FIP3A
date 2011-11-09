package files;

import org.omg.PortableServer.POA;

/**
 *	Generated from IDL interface "regular_file"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public class regular_filePOATie
	extends regular_filePOA
{
	private regular_fileOperations _delegate;

	private POA _poa;
	public regular_filePOATie(regular_fileOperations delegate)
	{
		_delegate = delegate;
	}
	public regular_filePOATie(regular_fileOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public files.regular_file _this()
	{
		return files.regular_fileHelper.narrow(_this_object());
	}
	public files.regular_file _this(org.omg.CORBA.ORB orb)
	{
		return files.regular_fileHelper.narrow(_this_object(orb));
	}
	public regular_fileOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(regular_fileOperations delegate)
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
	public int read(int size, org.omg.CORBA.StringHolder data) throws files.invalid_operation,files.end_of_file
	{
		return _delegate.read(size,data);
	}

	public void seek(int new_offset) throws files.invalid_offset,files.invalid_operation
	{
_delegate.seek(new_offset);
	}

	public void close()
	{
_delegate.close();
	}

	public int write(int size, java.lang.String data) throws files.invalid_operation
	{
		return _delegate.write(size,data);
	}

}
