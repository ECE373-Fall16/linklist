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
        string const serverUrl("http://104.196.192.226:8080/RPC2");
        string const methodName("makeSong");
	char songName[30];
	cin.getline(songName,30);
	char artistName[30];
	cin.getline(artistName,30);
	char albumName[30];
	cin.getline(albumName,30); 
	myClient.call(serverUrl, methodName, "sss", &result, songName, artistName, albumName);
        string const serverResponse((xmlrpc_c::value_string(result)));
        cout << serverResponse << endl;

    } catch (exception const& e) {
        cerr << "Client threw error: " << e.what() << endl;
    } catch (...) {
        cerr << "Client threw unexpected error." << endl;
    }

    return 0;
}
