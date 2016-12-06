package soundsync.linklist;

/**
 * Created by Chris on 11/16/16.
 */

import android.content.Context;
import android.provider.Settings;

import org.xmlrpc.android.XMLRPCClient;



public class Client {

    private static String hostName = "http://104.196.192.226:8080";
    private static Context context;
    private static Client client = null;
    private static XMLRPCClient xml;
    private static int connectFail=1;

    private Client(){

    }

    public static Client getClient() {
        if(client == null){
            createClient();
           // if(connectFail==0){
                client = new Client();

           // }
        }
        return client;
    }

    public static void createClient(){
        try{
            xml = new XMLRPCClient(hostName);
            xml.call("connect");
            connectFail=0;
            }
        catch (Exception e){
            System.out.println("Connection Err: " + e);
            connectFail=1;
        }

    }

    public static void sendLobbyInfo(String name, int size){
        try{
            xml.call("createLobby", name, size);
        }
        catch (Exception e){
            System.out.println("Lobby Create Error: " + e);
        }
    }

}
