package soundsync.linklist;

/**
 * Created by Chris on 11/16/16.
 */

import android.content.Context;
import android.widget.Toast;

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
            XmlRpcClient server = new XmlRpcClient(hostName);
            Vector params = new Vector();

            params.addElement(new String("hello"));
            params.addElement(new Integer(5));
            params.addElement(new Integer(7));

           Vector returnValue = (Vector) server.execute("sample.add", params);

            int size = ((Vector) returnValue).size();
            Integer intValue = (Integer) returnValue.get(0);
            Double doubleValue = (Double) returnValue.get(1);
            String stringValue = (String) returnValue.get(2);
            System.out.println("The first array value is " + intValue + " the second array value is " + doubleValue + " and the third array value is " + stringValue);

            return 0;
        } catch (Exception exception) {
            System.err.println("Client: " + exception);
            return -1;
        }
    }

}
