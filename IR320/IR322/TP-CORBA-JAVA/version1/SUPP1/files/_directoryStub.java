package files;


/**
 *	Generated from IDL interface "directory"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public class _directoryStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements files.directory
{
	private String[] ids = {"IDL:files/directory:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = files.directoryOperations.class;
	public int list_files(files.file_listHolder l)
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "list_files", true);
				files.file_listHelper.write(_os,l.value);
				_is = _invoke(_os);
				int _result = _is.read_long();
				l.value = files.file_listHelper.read(_is);
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "list_files", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			directoryOperations _localServant = (directoryOperations)_so.servant;
			int _result;			try
			{
			_result = _localServant.list_files(l);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return _result;
		}

		}

	}

	public int number_of_file()
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request("_get_number_of_file",true);
				_is = _invoke(_os);
				return _is.read_long();
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				this._releaseReply(_is);
			}
		}

		else
		{
		org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "_get_number_of_file", _opsClass);
		if( _so == null )
			throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			directoryOperations _localServant = (directoryOperations)_so.servant;
			int _result;
		try
		{
			_result = _localServant.number_of_file();
		}
		finally
		{
			_servant_postinvoke(_so);
		}
		return _result;
		}
		}

	}

	public void delete_file(java.lang.String name) throws files.no_such_file
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "delete_file", true);
				_os.write_string(name);
				_is = _invoke(_os);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:files/no_such_file:1.0"))
				{
					throw files.no_such_fileHelper.read(_ax.getInputStream());
				}
				else 
					throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "delete_file", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			directoryOperations _localServant = (directoryOperations)_so.servant;
			try
			{
			_localServant.delete_file(name);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return;
		}

		}

	}

	public void open_regular_file(files.regular_fileHolder r, java.lang.String name, files.mode m) throws files.no_such_file,files.invalid_type_file
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "open_regular_file", true);
				files.regular_fileHelper.write(_os,r.value);
				_os.write_string(name);
				files.modeHelper.write(_os,m);
				_is = _invoke(_os);
				r.value = files.regular_fileHelper.read(_is);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:files/no_such_file:1.0"))
				{
					throw files.no_such_fileHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:files/invalid_type_file:1.0"))
				{
					throw files.invalid_type_fileHelper.read(_ax.getInputStream());
				}
				else 
					throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "open_regular_file", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			directoryOperations _localServant = (directoryOperations)_so.servant;
			try
			{
			_localServant.open_regular_file(r,name,m);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return;
		}

		}

	}

	public void create_regular_file(files.regular_fileHolder r, java.lang.String name) throws files.already_exist
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "create_regular_file", true);
				files.regular_fileHelper.write(_os,r.value);
				_os.write_string(name);
				_is = _invoke(_os);
				r.value = files.regular_fileHelper.read(_is);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:files/already_exist:1.0"))
				{
					throw files.already_existHelper.read(_ax.getInputStream());
				}
				else 
					throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "create_regular_file", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			directoryOperations _localServant = (directoryOperations)_so.servant;
			try
			{
			_localServant.create_regular_file(r,name);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return;
		}

		}

	}

	public void create_directory(files.directoryHolder f, java.lang.String name) throws files.already_exist
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "create_directory", true);
				files.directoryHelper.write(_os,f.value);
				_os.write_string(name);
				_is = _invoke(_os);
				f.value = files.directoryHelper.read(_is);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:files/already_exist:1.0"))
				{
					throw files.already_existHelper.read(_ax.getInputStream());
				}
				else 
					throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "create_directory", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			directoryOperations _localServant = (directoryOperations)_so.servant;
			try
			{
			_localServant.create_directory(f,name);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return;
		}

		}

	}

	public void open_directory(files.directoryHolder f, java.lang.String name) throws files.no_such_file,files.invalid_type_file
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "open_directory", true);
				files.directoryHelper.write(_os,f.value);
				_os.write_string(name);
				_is = _invoke(_os);
				f.value = files.directoryHelper.read(_is);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:files/no_such_file:1.0"))
				{
					throw files.no_such_fileHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:files/invalid_type_file:1.0"))
				{
					throw files.invalid_type_fileHelper.read(_ax.getInputStream());
				}
				else 
					throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "open_directory", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			directoryOperations _localServant = (directoryOperations)_so.servant;
			try
			{
			_localServant.open_directory(f,name);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return;
		}

		}

	}

}
