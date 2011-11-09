package files;


/**
 *	Generated from IDL interface "file_list"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public class _file_listStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements files.file_list
{
	private String[] ids = {"IDL:files/file_list:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = files.file_listOperations.class;
	public boolean next_one(files.directory_entryHolder e)
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "next_one", true);
				files.directory_entryHelper.write(_os,e.value);
				_is = _invoke(_os);
				boolean _result = _is.read_boolean();
				e.value = files.directory_entryHelper.read(_is);
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "next_one", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			file_listOperations _localServant = (file_listOperations)_so.servant;
			boolean _result;			try
			{
			_result = _localServant.next_one(e);
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
