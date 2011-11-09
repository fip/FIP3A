

package events;

import org.omg.CORBA.*;
import java.lang.*;


public class Synchronized_Any
{

    private ORB orb_;
    private Any any;


    public Synchronized_Any(ORB o )
    {
    	orb_=o;
    	any=orb_.create_any();
    }	  


    ///////////////////////////////////////
    // Extraction d'un type primitif IDL
    ///////////////////////////////////////

    public synchronized String extract_string()
    {
        return new String(any.extract_string());
    }

    public synchronized long extract_long()
    {
        return any.extract_long();
    }

    public synchronized double extract_double()
    {
        return any.extract_double();
    }

    ///////////////////////////////////////
    // Insertion d'un type primitif IDL
    ///////////////////////////////////////

    public synchronized void insert_string(String s)
    {   
        any.insert_string(new String(s));
    }
    
    public synchronized void insert_long(int l)
    {
        any.insert_long(l);
    }

    public synchronized void insert_double(double d)
    {
        any.insert_double(d);
    }


    ///////////////////////////////////////
    // Affectation/copie depuis un Any
    ///////////////////////////////////////

    public synchronized void set(Any a)
    {
    	Any duplicated_any=orb_.create_any();

 	TypeCode type=a.type();
        int kind = type.kind().value();

        switch(kind)
        {
            case org.omg.CORBA.TCKind._tk_long:
                duplicated_any.insert_long(a.extract_long());
                break;
            case org.omg.CORBA.TCKind._tk_string:
                duplicated_any.insert_string(new String(a.extract_string()));
                break;
            case org.omg.CORBA.TCKind._tk_double:
                duplicated_any.insert_double(a.extract_double());
                break;
	}

	any=duplicated_any;
    }


    ///////////////////////////////////////
    // Consultation/copie vers un Any
    ///////////////////////////////////////

    public synchronized Any get()
    {
    	Any duplicated_any=orb_.create_any();

 	TypeCode type=any.type();
        int kind = type.kind().value();

        switch(kind)
        {
            case org.omg.CORBA.TCKind._tk_long:
                duplicated_any.insert_long(any.extract_long());
                break;
            case org.omg.CORBA.TCKind._tk_string:
                duplicated_any.insert_string(new String(any.extract_string()));
                break;
            case org.omg.CORBA.TCKind._tk_double:
                duplicated_any.insert_double(any.extract_double());
                break;
	}

	return duplicated_any;
    }




}
