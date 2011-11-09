package files;

/**
 *	Generated from IDL interface "regular_file"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */


public abstract class regular_filePOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, files.regular_fileOperations
{
	static private final java.util.Hashtable m_opsHash = new java.util.Hashtable();
	static
	{
		m_opsHash.put ( "read", new java.lang.Integer(0));
		m_opsHash.put ( "seek", new java.lang.Integer(1));
		m_opsHash.put ( "close", new java.lang.Integer(2));
		m_opsHash.put ( "write", new java.lang.Integer(3));
	}
	private String[] ids = {"IDL:files/regular_file:1.0"};
	public files.regular_file _this()
	{
		return files.regular_fileHelper.narrow(_this_object());
	}
	public files.regular_file _this(org.omg.CORBA.ORB orb)
	{
		return files.regular_fileHelper.narrow(_this_object(orb));
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
			case 0: // read
			{
			try
			{
				int _arg0=_input.read_long();
				org.omg.CORBA.StringHolder _arg1= new org.omg.CORBA.StringHolder();
				_arg1._read (_input);
				_out = handler.createReply();
				_out.write_long(read(_arg0,_arg1));
				_out.write_string(_arg1.value);
			}
			catch(files.invalid_operation _ex0)
			{
				_out = handler.createExceptionReply();
				files.invalid_operationHelper.write(_out, _ex0);
			}
			catch(files.end_of_file _ex1)
			{
				_out = handler.createExceptionReply();
				files.end_of_fileHelper.write(_out, _ex1);
			}
				break;
			}
			case 1: // seek
			{
			try
			{
				int _arg0=_input.read_long();
				_out = handler.createReply();
				seek(_arg0);
			}
			catch(files.invalid_offset _ex0)
			{
				_out = handler.createExceptionReply();
				files.invalid_offsetHelper.write(_out, _ex0);
			}
			catch(files.invalid_operation _ex1)
			{
				_out = handler.createExceptionReply();
				files.invalid_operationHelper.write(_out, _ex1);
			}
				break;
			}
			case 2: // close
			{
				_out = handler.createReply();
				close();
				break;
			}
			case 3: // write
			{
			try
			{
				int _arg0=_input.read_long();
				java.lang.String _arg1=_input.read_string();
				_out = handler.createReply();
				_out.write_long(write(_arg0,_arg1));
			}
			catch(files.invalid_operation _ex0)
			{
				_out = handler.createExceptionReply();
				files.invalid_operationHelper.write(_out, _ex0);
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
