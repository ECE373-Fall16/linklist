#include <cstdlib>
#include <string>
#include <iostream>
#include <xmlrpc-c/girerr.hpp>
#include <xmlrpc-c/base.hpp>
#include <xmlrpc-c/client_simple.hpp>

using namespace std;

int
main(int argc, char **) {

    if (argc-1 < 0) {
        cerr << "This program has no arguments" << endl;
        exit(1);
    }

    try {
        xmlrpc_c::clientSimple myClient;
        xmlrpc_c::value result;
	xmlrpc_c::value result2;
	xmlrpc_c::value result3;
	xmlrpc_c::value result4;
	xmlrpc_c::value result5;
	xmlrpc_c::value result6;
	xmlrpc_c::value result7;
	xmlrpc_c::value result8;

        string const serverUrl("http://104.196.192.226:8080/RPC2");
        string const methodName("makeSong");
	char songName[30];
	cin.getline(songName,30); 
	myClient.call(serverUrl, methodName, "si", &result, songName,1);
        string const serverResponse((xmlrpc_c::value_string(result)));
        cout << serverResponse << endl;
	cout << '\n' << '\n';
	
	cout << "Now attempting connection" << endl;
	myClient.call(serverUrl, "connection",&result2);
	int const serverResponse2((xmlrpc_c::value_int(result2)));
	cout <<serverResponse2 <<endl;
//////////////////////////////////////////////////
	int user = 88;
	int room = 1234;
	cout << "Now attempting makeRoom" << endl;
	myClient.call(serverUrl, "makeRoom","sii",&result3,songName,room,user);
	int const serverResponse3((xmlrpc_c::value_int(result3)));
	cout << serverResponse3 << endl;
	cout << '\n' << '\n';	

	cout << "Now attempting joinRoom" << endl;
	myClient.call(serverUrl, "joinRoom","ii", &result4,room,user);
	string const serverResponse4((xmlrpc_c::value_string(result4)));
	cout << "Connected to Server Name:" << endl;
	cout << serverResponse4 << endl;
	cout << '\n' << '\n';

	cout << "Now attempting playNext" << endl;
	myClient.call(serverUrl,"playNext","i",&result5,room);
	string const serverResponse5((xmlrpc_c::value_string(result5)));
	cout << serverResponse5 << endl;
	cout << '\n' << '\n';

	cout << "Now attempting destroyRoom" << endl;
	myClient.call(serverUrl,"destroyRoom","i",&result6,room);
	cout << "No response expected" << endl;
	cout << '\n' <<	 '\n';

	cout << "Now attempting getListSize" << endl;
	myClient.call(serverUrl,"getListSize","i",&result7,room);
	int const serverResponse7((xmlrpc_c::value_int(result7)));
	cout << serverResponse7 << endl;
	cout <<'\n'<<'\n';

	cout << "Now attempting getSongURI" << endl;
	myClient.call(serverUrl,"getSongURI","ii",&result8,0,serverResponse7 - 1);
	string const serverResponse8((xmlrpc_c::value_string(result8)));
	cout << serverResponse8 << endl;
	cout <<'\n'<<'\n';

    } catch (exception const& e) {
        cerr << "Client threw error: " << e.what() << endl;
    } catch (...) {
        cerr << "Client threw unexpected error." << endl;
    }

    return 0;
}
