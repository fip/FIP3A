package tpcorba.exo1;

/**
 *	Generated from IDL interface "calcul"
 *	@author JacORB IDL compiler V 2.2, 7-May-2004
 */


public abstract class calculPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, tpcorba.exo1.calculOperations
{
	static private final java.util.Hashtable m_opsHash = new java.util.Hashtable();
	static
	{
		m_opsHash.put ( "incrementer", new java.lang.Integer(0));
		m_opsHash.put ( "decrementer", new java.lang.Integer(1));
	}
	private String[] ids = {"IDL:tpcorba/exo1/calcul:1.0"};
	public tpcorba.exo1.calcul _this()
	{
		return tpcorba.exo1.calculHelper.narrow(_this_object());
	}
	public tpcorba.exo1.calcul _this(org.omg.CORBA.ORB orb)
	{
		return tpcorba.exo1.calculHelper.narrow(_this_object(orb));
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
			case 0: // incrementer
			{
				org.omg.CORBA.IntHolder _arg0= new org.omg.CORBA.IntHolder();
				_arg0._read (_input);
				_out = handler.createReply();
				incrementer(_arg0);
				_out.write_long(_arg0.value);
				break;
			}
			case 1: // decrementer
			{
				org.omg.CORBA.IntHolder _arg0= new org.omg.CORBA.IntHolder();
				_arg0._read (_input);
				_out = handler.createReply();
				decrementer(_arg0);
				_out.write_long(_arg0.value);
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
