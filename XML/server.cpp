#include <cassert>
#include <stdexcept>
#include <iostream>
#include <unistd.h>
#include <string>

#include "xmlrpc-c/base.hpp"
#include "xmlrpc-c/registry.hpp"
#include "xmlrpc-c/server_abyss.hpp"
#include "List.h"
#include "Room.h"
using namespace std;
int count = 0;
Room roomArray[100];
int rooms = 0;
#define SLEEP(seconds) sleep(seconds);
////////////////`/////////////////////////////George's Methods inside/////////////////////////////////////////////////
class makeRoom : public xmlrpc_c::method{
public:
	makeRoom() {
		this->_signature = "i:sii";
		this->_help = "host initializes a room others can join";
		}
		void
		execute(xmlrpc_c::paramList const& paramList,
			xmlrpc_c::value *   const  retvalP) {

	string const roomName(paramList.getString(0));
	int    const userID(paramList.getInt(1));
	int    const roomSize(paramList.getInt(2));
	paramList.verifyEnd(3);
	cout << '\n' << '\n';
	cout << "Creating Room: " << roomName << endl;
	cout << "of Size: " << roomSize << endl;
	cout << "with host: " << userID << endl;
	cout << endl;
	while(roomArray[count % 100].isUsed()){
	count = count + 1;
	}
	int    const returnMessage = count % 100;
	Room temp(roomName,userID,roomSize);
	roomArray[returnMessage] = temp;
	count = count + 1;
	*retvalP = xmlrpc_c::value_int(returnMessage);
	rooms =	rooms +1;
}
	
};
class joinRoom : public xmlrpc_c::method {
public:
	joinRoom() {
		this->_signature ="s:ii";
		this->_help = "user joins an already initialized room";
		}
		void
		execute(xmlrpc_c::paramList const& paramList,
			xmlrpc_c::value *   const  retvalP) {
	string returnMessage;
	int    const roomID (paramList.getInt(0));
	int    const userID (paramList.getInt(1));
	paramList.verifyEnd(2);
	cout << '\n' << '\n';
	int temp = roomID;
	if(rooms >0){
	while(!roomArray[temp%100].isUsed()){
	temp = temp + 1;
	cout << temp%100<< '\n';
	}
	cout << "no seg yet" << endl;
	roomArray[temp%100].addUser(userID);
	cout << "User: " << userID << endl;
	cout << "has connected to room" << temp << endl;
	cout << endl;
	returnMessage =roomArray[temp%100].getRoomName();//TODO error correction/////getroom eventually?
}else{
returnMessage ="There aint no rooms you rogue agent, host something instead";
}
	*retvalP = xmlrpc_c::value_string(returnMessage);
	}
};
class playNext : public xmlrpc_c::method {
public:
	playNext() {
		this->_signature ="s:i";
		this->_help ="asks for a new song from the list";
	}
	void
	execute(xmlrpc_c::paramList const& paramList,
		xmlrpc_c::value *   const  retvalP) {

	int    const roomID (paramList.getInt(0));
	paramList.verifyEnd(1);
	cout << endl << endl;
	cout << "Next song Begun for room" <<roomID<< endl;
	cout << endl;
	string const returnMessage = roomArray[roomID%100].playlist.deleteSongFirst();//TODO//if delete first returns string
	cout << "in room " <<roomID<< endl;
//	cout << returnMessage << endl;
	*retvalP = xmlrpc_c::value_string(returnMessage);
	}
};
class destroyRoom : public xmlrpc_c::method {
public:
	destroyRoom() {
		this->_signature = "n:i";
		this->_help = "host tells server to bop the room";
	}
	void
	execute(xmlrpc_c::paramList const& paramList,
		xmlrpc_c::value *   const  retvalP) {

	int    const roomID (paramList.getInt(0));
	paramList.verifyEnd(1);
	cout << '\n' << '\n';
	cout << "Room to be Destroyed: " << roomID << endl;
	cout << endl;
	roomArray[roomID%100].destroyRoom();
	int const returnMessage = 0;
	*retvalP = xmlrpc_c::value_int(returnMessage);
	//???prangent return value or nah?!
	}
};
	
//////////////////////////////////////////George's Methods above/////////////////////////////////////////////////////
class makeSong : public xmlrpc_c::method {
public:
    makeSong() {
        // signature and help strings are documentation -- the client
        // can query this information with a system.methodSignature and
        // system.methodHelp RPC.
        this->_signature = "s:si";
            // method's result and two arguments are integers
        this->_help = "get a song from a client";
    }
    void
    execute(xmlrpc_c::paramList const& paramList,
            xmlrpc_c::value *   const  retvalP) {
        
        string const songURI(paramList.getString(0));
        int    const roomID(paramList.getInt(1));
        paramList.verifyEnd(2);
	cout << '\n' << '\n';
	cout << "Received Song:" << '\n';
        cout << songURI << endl;
	cout << "Received roomID:" << '\n';
        cout << roomID  << endl;
	roomArray[roomID %100].playlist.addSongBack(songURI);////////////seg faults sometimes TODO	

	cout << endl;
        string const returnMessage = "Hello Human, I Am Server";
        *retvalP = xmlrpc_c::value_string(returnMessage);
    }
};


class connection : public xmlrpc_c::method {
public:
    connection() {
        // signature and help strings are documentation -- the client
        // can query this information with a system.methodSignature and
        // system.methodHelp RPC.
        this->_signature = "i:n";
            // method's result and two arguments are integers
        this->_help = "Tests connection between client and server.";
    }
    void
    execute(xmlrpc_c::paramList const& paramList,
            xmlrpc_c::value *   const  retvalP) {
	paramList.verifyEnd(0);
	cout <<" connection called" << endl;
	int const returnOK = 0;	        
	*retvalP = xmlrpc_c::value_int(returnOK);
}
};

class getListSize : public xmlrpc_c::method {
public:
	getListSize() {
	this->_signature = "i:i";
	this->_help = "Returns size of the playlist in specified room.";
}

void execute(xmlrpc_c::paramList const& paramList, xmlrpc_c::value * const retvalP) {
	int roomID = paramList.getInt(0);
	paramList.verifyEnd(1);
	int const returnMessage = roomArray[roomID %100].playlist.getListSize();
	cout << "we gave em a number yo"<< endl;
	*retvalP = xmlrpc_c::value_int(returnMessage);
}
};

class getSongURI : public xmlrpc_c::method {
public:
	getSongURI() {
	this->_signature = "s:ii";
	this->_help = "Returns track URI given location in list.";
}

void  execute(xmlrpc_c::paramList const& paramList, xmlrpc_c::value * retvalP) {
	int roomID = paramList.getInt(0);
	int songNum = paramList.getInt(1);
	paramList.verifyEnd(2);
	cout << "Yo it's ya boy" << endl;
	string returnMessage = roomArray[roomID].playlist.getSongURI(songNum);
}
};

int
main(int const, const char ** const) {

try {
        xmlrpc_c::registry myRegistry;
	///////////////////////////////////George's Methods inside//////////////////////////////////////////////
	xmlrpc_c::methodPtr const makeRoomP(new makeRoom);
	xmlrpc_c::methodPtr const joinRoomP(new joinRoom);
	xmlrpc_c::methodPtr const playNextP(new playNext);
	xmlrpc_c::methodPtr const destroyRoomP(new destroyRoom);

	///////////////////////////////////George's Methods above///////////////////////////////////////////////
        xmlrpc_c::methodPtr const makeSongP(new makeSong);
	xmlrpc_c::methodPtr const connectP(new connection);
	xmlrpc_c::methodPtr const getListSizeP(new getListSize);
	xmlrpc_c::methodPtr const getSongURIP(new getSongURI);
	///////////////////////////////////George's Methods inside//////////////////////////////////////////////
	myRegistry.addMethod("makeRoom",makeRoomP);
	myRegistry.addMethod("joinRoom",joinRoomP);
	myRegistry.addMethod("playNext",playNextP);
	myRegistry.addMethod("destroyRoom",destroyRoomP);
	myRegistry.addMethod("getListSize",getListSizeP);
	myRegistry.addMethod("getSongURI",getSongURIP);
	//////////////////////////////////George's Methods above////////////////////////////////////////////////
	myRegistry.addMethod("makeSong", makeSongP);
	myRegistry.addMethod("connection", connectP);        
        xmlrpc_c::serverAbyss myAbyssServer(
            xmlrpc_c::serverAbyss::constrOpt()
            .registryP(&myRegistry)
            .portNumber(8080));
        
        myAbyssServer.run();
        // xmlrpc_c::serverAbyss.run() never returns
        assert(false);
    } catch (exception const& e) {
        cerr << "Something failed.  " << e.what() << endl;
    }
    return 0;
}
