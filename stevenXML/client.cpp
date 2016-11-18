#include <cstdlib>
#include <string>
#include <iostream>
#include <xmlrpc-c/girerr.hpp>
#include <xmlrpc-c/base.hpp>
#include <xmlrpc-c/client_simple.hpp>

using namespace std;

int
main(int argc, char **) {

    if (argc-1 > 0) {
        cerr << "This program has no arguments" << endl;
        exit(1);
    }

    try {
        xmlrpc_c::clientSimple myClient;
        xmlrpc_c::value result;
        string const serverUrl("http://localhost:8080/RPC2");
        string const methodName("makeSong");

        myClient.call(serverUrl, methodName, "sss", &result, "This will display the song.", "This will display the artist.", "This will display the album.");
        
        string const serverResponse((xmlrpc_c::value_string(result)));
        cout << serverResponse << endl;

    } catch (exception const& e) {
        cerr << "Client threw error: " << e.what() << endl;
    } catch (...) {
        cerr << "Client threw unexpected error." << endl;
    }

    return 0;
}
