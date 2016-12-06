#include <cassert>
#include <stdexcept>
#include <iostream>
#include <unistd.h>
#include <string>

#include "xmlrpc-c/base.hpp"
#include "xmlrpc-c/registry.hpp"
#include "xmlrpc-c/server_abyss.hpp"

using namespace std;

#define SLEEP(seconds) sleep(seconds);


class makeSong : public xmlrpc_c::method {
public:
    makeSong() {
        // signature and help strings are documentation -- the client
        // can query this information with a system.methodSignature and
        // system.methodHelp RPC.
        this->_signature = "s:sss";
            // method's result and two arguments are integers
        this->_help = "get a song from a client";
    }
    void
    execute(xmlrpc_c::paramList const& paramList,
            xmlrpc_c::value *   const  retvalP) {
        
        string const songName(paramList.getString(0));
        string const artistName(paramList.getString(1));
        string const albumName(paramList.getString(2));
        paramList.verifyEnd(3);

        cout << songName << endl;
        cout << artistName << endl;
        cout << albumName << endl;
        string const returnMessage = "Hello Human, I Am Server";
        *retvalP = xmlrpc_c::value_string(returnMessage);
    }
};



int 
main(int           const, 
     const char ** const) {

    try {
        xmlrpc_c::registry myRegistry;

        xmlrpc_c::methodPtr const makeSongP(new makeSong);

        myRegistry.addMethod("makeSong", makeSongP);
        
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
