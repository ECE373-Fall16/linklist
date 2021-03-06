package soundsync.linklist;

/**
 * Created by Chris on 11/16/16.
 */

/***********
 * This is a singleton class, so basically everything is static and the class acts as a wrapper
 * to sheild one single static instance of an XML object, so once it's created one time it will
 * not be created again, it will just pass the first created XML object
 */

import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;

import org.xmlrpc.android.XMLRPCClient;
import org.xmlrpc.android.XMLRPCException;


public class Client {

    private static String hostName = "http://104.196.192.226:8080/RPC2";       //RPC2 means its working
    private static Context context;         //this would hold the context of the calling activity
    private static Client client = null;     //initialize client object as null
    private static XMLRPCClient xml;        //our global xml object
    public static int connectFail=1;       //assume connection is failed (because we haven't connected yet)
    private static int hostId = 1234;       //hard coded host id, will be gernerated by server later
    private static int roomId = -1;              //roomId delivered upon room creation
    private static int userID = 1234;
    private static String joinedRoomName="";


    static Thread clientCreateThread = new Thread(new Runnable() {  //Initialize thread used to connect to server in bg
        public void run() {
            xml = new XMLRPCClient(hostName);       //initialize xmlrpc object as STatic variable "xml"
            try{        //xml.call might error out if we fail to connect
                connectFail = (int)xml.call("connection");  //test connection, get back a 0 if we connected
                System.out.println("Connected with value: " + connectFail); //print success
            }
            catch (Exception e){
                System.out.println("Connection Err: " + e); //if we got here then connection went wrong
                System.out.println("ConnectFail: " + connectFail);  //print error and returned value
                connectFail=1;          //reset connectFail to a predictable value
            }
        }
    });

    private Client(){   //this never gets called outside of here. don't let other classes initialize with this. dont put stuff inside.

    }


    public static Client getClient() {  //THIS is how we initialize this
        if(client == null){
            createClient();
            if(connectFail==0){
                client = new Client();
                System.out.println("Created Client in getClient");
            }
            else{
                System.out.println("createClient failed in getClient \n connectFail= " + connectFail);
                client = null;
                }
        }
        return client;
    }

    public static void createClient(){
        clientCreateThread.start();
        try {
            clientCreateThread.join(10000);
        } catch (InterruptedException e) {
            System.out.println("Client Thread Join Err: "+e);
            connectFail = 2;
        }


    }

    public static void sendLobbyInfo(String name, int size){
        try{
            roomId=(int)xml.call("makeRoom", name, hostId, size);
            System.out.println("Room Id from Send Lobby: "+ roomId);
        }
        catch (Exception e){
            System.out.println("Lobby Create Error: " + e);
        }
    }

    public static void destroyRoom(){
        try {
            if((boolean)xml.call("destroyRoom", roomId)){
                System.out.println("Room destroy success"); //make this a toast or something prolly
            }
        } catch (XMLRPCException e) {
            System.out.println("Room Destroy Error: " + e);
        }
    }

    public static int getRoomId(){
        return roomId;
    }

    public static String joinRoom(int joinID){
        if(connectFail==0) {
            try {
                roomId = joinID;
                joinedRoomName = (String) xml.call("joinRoom", joinID, userID);
                System.out.println("Joined Room: " + joinedRoomName);
            } catch (XMLRPCException e) {
                System.out.println("Failed to join room" + e);
                e.printStackTrace();
            } finally {
                return joinedRoomName;
            }
        }
        else return "not connected to server";
    }

    public static String getJoinedRoomName(){
        return joinedRoomName;
    }

    public static int makeSong(String uri, String name, String artist, String album){
        try {
            if(roomId<0){
                //not in a room
                return 3;   //3==not in a room
            }
            String retValue = (String)xml.call("makeSong", uri, name, artist, album, roomId);
            System.out.println("makeSong Success: " + retValue);
            return 0;   // 0 == success

        } catch (XMLRPCException e) {
            System.out.println("makeSong error: " + e);
            e.printStackTrace();
            return 1; // 1 == xmlrpc exception
        }
    }

    public static String playNext(){
        String next = null;
        try {
            next =(String) xml.call("playNext", roomId);
            System.out.println("Got Next Song: "+next);
        } catch (XMLRPCException e) {
            e.printStackTrace();
        }
        return next;
    }

    public static int getListSize(){
        int size = 0;
        try {
           size = (int) xml.call("getListSize", roomId);
            System.out.println("Got List size: "+size);
        } catch (XMLRPCException e) {
            e.printStackTrace();
            System.out.println("Couldn't get size: "+e);
        }
        return size;
    }


    public static String getSongURI(int songNumber){
        String songURI="";
        try {
            songURI = (String) xml.call("getSongURI", roomId, songNumber);
            System.out.println("[client]URI: "+ songURI);
        } catch (XMLRPCException e) {
            e.printStackTrace();
            System.out.println("songURI error: "+e);
            System.out.println("Song URI error returnd uri: "+songURI);
        }
        return songURI;
    }

    public static String getSongName(int songNumber){
        String songName="";
        try {
            songName = (String) xml.call("getSongName", roomId, songNumber);
            Log.d("[client]getSongName: ",songName);
        } catch (XMLRPCException e) {
            e.printStackTrace();
            Log.d("[client]getSongName er",e.toString());
        }
        return songName;
    }

    public static String getSongArtist(int songNumber){
        String songArtist="";
        try {
            songArtist = (String) xml.call("getSongArtist", roomId, songNumber);
            Log.d("[client]getSongArtist: ",songArtist);
        } catch (XMLRPCException e) {
            e.printStackTrace();
            Log.d("[client]getArtist er",e.toString());
        }
        return songArtist;
    }

    public static String getSongAlbum(int songNumber){
        String songAlbum="";
        try {
            songAlbum = (String) xml.call("getSongAlbum", roomId, songNumber);
            Log.d("[client]getSongAlbum: ",songAlbum);
        } catch (XMLRPCException e) {
            e.printStackTrace();
            Log.d("[client]getAlbum er",e.toString());
        }
        return songAlbum;
    }


}
