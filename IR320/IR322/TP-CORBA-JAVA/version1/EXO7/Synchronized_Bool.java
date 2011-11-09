

package events;

import java.lang.*;


public class Synchronized_Bool
{

    private boolean bool;

    public Synchronized_Bool(boolean b)
    {
	bool=b;
    }

    public synchronized boolean get()
    {
        return bool;
    }

    public synchronized void set(boolean b)
    {
        bool=b;
    }


}



