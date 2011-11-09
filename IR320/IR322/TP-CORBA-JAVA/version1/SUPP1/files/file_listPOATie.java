package files;

import org.omg.PortableServer.POA;

/**
 *	Generated from IDL interface "file_list"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public class file_listPOATie
	extends file_listPOA
{
	private file_listOperations _delegate;

	private POA _poa;
	public file_listPOATie(file_listOperations delegate)
	{
		_delegate = delegate;
	}
	public file_listPOATie(file_listOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public files.file_list _this()
	{
		return files.file_listHelper.narrow(_this_object());
	}
	public files.file_list _this(org.omg.CORBA.ORB orb)
	{
		return files.file_listHelper.narrow(_this_object(orb));
	}
	public file_listOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(file_listOperations delegate)
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
	public boolean next_one(files.directory_entryHolder e)
	{
		return _delegate.next_one(e);
	}

}
