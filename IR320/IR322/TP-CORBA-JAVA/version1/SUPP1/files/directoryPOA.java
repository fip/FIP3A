package files;

/**
 *	Generated from IDL interface "directory"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */


public abstract class directoryPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, files.directoryOperations
{
	static private final java.util.Hashtable m_opsHash = new java.util.Hashtable();
	static
	{
		m_opsHash.put ( "list_files", new java.lang.Integer(0));
		m_opsHash.put ( "_get_number_of_file", new java.lang.Integer(1));
		m_opsHash.put ( "delete_file", new java.lang.Integer(2));
		m_opsHash.put ( "open_regular_file", new java.lang.Integer(3));
		m_opsHash.put ( "create_regular_file", new java.lang.Integer(4));
		m_opsHash.put ( "create_directory", new java.lang.Integer(5));
		m_opsHash.put ( "open_directory", new java.lang.Integer(6));
	}
	private String[] ids = {"IDL:files/directory:1.0"};
	public files.directory _this()
	{
		return files.directoryHelper.narrow(_this_object());
	}
	public files.directory _this(org.omg.CORBA.ORB orb)
	{
		return files.directoryHelper.narrow(_this_object(orb));
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // list_files
			{
				files.file_listHolder _arg0= new files.file_listHolder();
				_arg0._read (_input);
				_out = handler.createReply();
				_out.write_long(list_files(_arg0));
				files.file_listHelper.write(_out,_arg0.value);
				break;
			}
			case 1: // _get_number_of_file
			{
			_out = handler.createReply();
			_out.write_long(number_of_file());
				break;
			}
			case 2: // delete_file
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				delete_file(_arg0);
			}
			catch(files.no_such_file _ex0)
			{
				_out = handler.createExceptionReply();
				files.no_such_fileHelper.write(_out, _ex0);
			}
				break;
			}
			case 3: // open_regular_file
			{
			try
			{
				files.regular_fileHolder _arg0= new files.regular_fileHolder();
				_arg0._read (_input);
				java.lang.String _arg1=_input.read_string();
				files.mode _arg2=files.modeHelper.read(_input);
				_out = handler.createReply();
				open_regular_file(_arg0,_arg1,_arg2);
				files.regular_fileHelper.write(_out,_arg0.value);
			}
			catch(files.no_such_file _ex0)
			{
				_out = handler.createExceptionReply();
				files.no_such_fileHelper.write(_out, _ex0);
			}
			catch(files.invalid_type_file _ex1)
			{
				_out = handler.createExceptionReply();
				files.invalid_type_fileHelper.write(_out, _ex1);
			}
				break;
			}
			case 4: // create_regular_file
			{
			try
			{
				files.regular_fileHolder _arg0= new files.regular_fileHolder();
				_arg0._read (_input);
				java.lang.String _arg1=_input.read_string();
				_out = handler.createReply();
				create_regular_file(_arg0,_arg1);
				files.regular_fileHelper.write(_out,_arg0.value);
			}
			catch(files.already_exist _ex0)
			{
				_out = handler.createExceptionReply();
				files.already_existHelper.write(_out, _ex0);
			}
				break;
			}
			case 5: // create_directory
			{
			try
			{
				files.directoryHolder _arg0= new files.directoryHolder();
				_arg0._read (_input);
				java.lang.String _arg1=_input.read_string();
				_out = handler.createReply();
				create_directory(_arg0,_arg1);
				files.directoryHelper.write(_out,_arg0.value);
			}
			catch(files.already_exist _ex0)
			{
				_out = handler.createExceptionReply();
				files.already_existHelper.write(_out, _ex0);
			}
				break;
			}
			case 6: // open_directory
			{
			try
			{
				files.directoryHolder _arg0= new files.directoryHolder();
				_arg0._read (_input);
				java.lang.String _arg1=_input.read_string();
				_out = handler.createReply();
				open_directory(_arg0,_arg1);
				files.directoryHelper.write(_out,_arg0.value);
			}
			catch(files.no_such_file _ex0)
			{
				_out = handler.createExceptionReply();
				files.no_such_fileHelper.write(_out, _ex0);
			}
			catch(files.invalid_type_file _ex1)
			{
				_out = handler.createExceptionReply();
				files.invalid_type_fileHelper.write(_out, _ex1);
			}
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
