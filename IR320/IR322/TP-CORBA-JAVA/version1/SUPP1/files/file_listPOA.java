package files;

/**
 *	Generated from IDL interface "file_list"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */


public abstract class file_listPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, files.file_listOperations
{
	static private final java.util.Hashtable m_opsHash = new java.util.Hashtable();
	static
	{
		m_opsHash.put ( "next_one", new java.lang.Integer(0));
	}
	private String[] ids = {"IDL:files/file_list:1.0"};
	public files.file_list _this()
	{
		return files.file_listHelper.narrow(_this_object());
	}
	public files.file_list _this(org.omg.CORBA.ORB orb)
	{
		return files.file_listHelper.narrow(_this_object(orb));
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
			case 0: // next_one
			{
				files.directory_entryHolder _arg0= new files.directory_entryHolder();
				_arg0._read (_input);
				_out = handler.createReply();
				_out.write_boolean(next_one(_arg0));
				files.directory_entryHelper.write(_out,_arg0.value);
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
