package files;


/**
 *	Generated from IDL interface "regular_file"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public class _regular_fileStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements files.regular_file
{
	private String[] ids = {"IDL:files/regular_file:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = files.regular_fileOperations.class;
	public int read(int size, org.omg.CORBA.StringHolder data) throws files.invalid_operation,files.end_of_file
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "read", true);
				_os.write_long(size);
				_os.write_string(data.value);
				_is = _invoke(_os);
				int _result = _is.read_long();
				data.value = _is.read_string();
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:files/invalid_operation:1.0"))
				{
					throw files.invalid_operationHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:files/end_of_file:1.0"))
				{
					throw files.end_of_fileHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "read", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			regular_fileOperations _localServant = (regular_fileOperations)_so.servant;
			int _result;			try
			{
			_result = _localServant.read(size,data);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return _result;
		}

		}

	}

	public void seek(int new_offset) throws files.invalid_offset,files.invalid_operation
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "seek", true);
				_os.write_long(new_offset);
				_is = _invoke(_os);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:files/invalid_offset:1.0"))
				{
					throw files.invalid_offsetHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:files/invalid_operation:1.0"))
				{
					throw files.invalid_operationHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "seek", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			regular_fileOperations _localServant = (regular_fileOperations)_so.servant;
			try
			{
			_localServant.seek(new_offset);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return;
		}

		}

	}

	public void close()
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "close", true);
				_is = _invoke(_os);
				return;
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "close", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			regular_fileOperations _localServant = (regular_fileOperations)_so.servant;
			try
			{
			_localServant.close();
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return;
		}

		}

	}

	public int write(int size, java.lang.String data) throws files.invalid_operation
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "write", true);
				_os.write_long(size);
				_os.write_string(data);
				_is = _invoke(_os);
				int _result = _is.read_long();
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:files/invalid_operation:1.0"))
				{
					throw files.invalid_operationHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "write", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			regular_fileOperations _localServant = (regular_fileOperations)_so.servant;
			int _result;			try
			{
			_result = _localServant.write(size,data);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return _result;
		}

		}

	}

}
