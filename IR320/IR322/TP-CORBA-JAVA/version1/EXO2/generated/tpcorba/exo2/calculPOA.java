package tpcorba.exo2;

/**
 *	Generated from IDL interface "calcul"
 *	@author JacORB IDL compiler V 2.2, 7-May-2004
 */


public abstract class calculPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, tpcorba.exo2.calculOperations
{
	static private final java.util.Hashtable m_opsHash = new java.util.Hashtable();
	static
	{
		m_opsHash.put ( "incrementer", new java.lang.Integer(0));
		m_opsHash.put ( "ajouteMemoire", new java.lang.Integer(1));
		m_opsHash.put ( "multiplieMemoire", new java.lang.Integer(2));
		m_opsHash.put ( "decrementer", new java.lang.Integer(3));
		m_opsHash.put ( "_get_memoire", new java.lang.Integer(4));
		m_opsHash.put ( "diviseMemoire", new java.lang.Integer(5));
	}
	private String[] ids = {"IDL:tpcorba/exo2/calcul:1.0"};
	public tpcorba.exo2.calcul _this()
	{
		return tpcorba.exo2.calculHelper.narrow(_this_object());
	}
	public tpcorba.exo2.calcul _this(org.omg.CORBA.ORB orb)
	{
		return tpcorba.exo2.calculHelper.narrow(_this_object(orb));
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
			case 1: // ajouteMemoire
			{
				double _arg0=_input.read_double();
				_out = handler.createReply();
				ajouteMemoire(_arg0);
				break;
			}
			case 2: // multiplieMemoire
			{
				double _arg0=_input.read_double();
				_out = handler.createReply();
				multiplieMemoire(_arg0);
				break;
			}
			case 3: // decrementer
			{
				org.omg.CORBA.IntHolder _arg0= new org.omg.CORBA.IntHolder();
				_arg0._read (_input);
				_out = handler.createReply();
				decrementer(_arg0);
				_out.write_long(_arg0.value);
				break;
			}
			case 4: // _get_memoire
			{
			_out = handler.createReply();
			_out.write_double(memoire());
				break;
			}
			case 5: // diviseMemoire
			{
			try
			{
				double _arg0=_input.read_double();
				_out = handler.createReply();
				diviseMemoire(_arg0);
			}
			catch(tpcorba.exo2.divisionParZero _ex0)
			{
				_out = handler.createExceptionReply();
				tpcorba.exo2.divisionParZeroHelper.write(_out, _ex0);
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
