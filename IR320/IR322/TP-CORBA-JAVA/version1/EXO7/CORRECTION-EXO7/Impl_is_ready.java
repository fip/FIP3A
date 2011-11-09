

package events;

import org.omg.CORBA.*;
import java.lang.*;



public class Impl_is_ready extends Thread {

    private BOA boa_;

    public Impl_is_ready(BOA b) {
        boa_=b;
    }

    public void run() {

        System.out.println("Le serveur est pret");
  	boa_.impl_is_ready(null);

    }
}



