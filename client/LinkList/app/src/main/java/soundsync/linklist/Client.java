package soundsync.linklist;

/**
 * Created by Chris on 11/16/16.
 */

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import org.xmlrpc.android.XMLRPCClient;

import java.io.Serializable;
import java.util.*;
import org.apache.xmlrpc.*;

public class Client implements Serializable {

    String hostName;
    Context context;

    public Client(String hostName){
        this.hostName = hostName;
    }

    public int createClient() {
        try {
            XMLRPCClient client = new XMLRPCClient("http://104.196.192.226:8080");
            Vector params = new Vector();

            params.addElement(new String("hello"));
            params.addElement(new Integer(5));
            params.addElement(new Integer(7));

            client.call("add",params);

           /*Vector returnValue = (Vector) server.execute("sample.add", params);

            int size = ((Vector) returnValue).size();
            Integer intValue = (Integer) returnValue.get(0);
            Double doubleValue = (Double) returnValue.get(1);
            String stringValue = (String) returnValue.get(2);
            System.out.println("The first array value is " + intValue + " the second array value is " + doubleValue + " and the third array value is " + stringValue);
*/
            return 0;
        } catch (Exception exception) {
            System.out.println("Client: " + exception);
            return -1;
        }
    }

}
