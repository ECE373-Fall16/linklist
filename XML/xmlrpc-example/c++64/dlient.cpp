#include <cstdlib>
#include <string>
#include <iostream>
#include <xmlrpc-c/girerr.hpp>
#include <xmlrpc-c/base.hpp>
#include <xmlrpc-c/client_simple.hpp>

using namespace std;

int
main(int argc, char**){

	if(argc-1 < 0){
	cerr << "NO ARGOS PLS"<< endl;
	exit(1);
	}

try{
	xmlrpc_c::clientSimple myClient;
	xmlrpc_c::value result0;
	xmlrpc_c::value result1;
	xmlrpc_c::value result2;

	string const serverURL("http://104.196.192.226:8080/RPC2");
	cout << "adding 40  rooms" << endl;
	for(int a = 0; a < 40; a++){
		myClient.call(serverURL,"makeRoom","sii",&result0,"rooms",5,a);
		int const serverResponse((xmlrpc_c::value_int(result0)));
		cout << "room number" << a << " created" << endl;
	

	}


}catch(exception const& e){
	cerr << "boom 1" << e.what() << endl;
}catch(...){
	cerr << "unexpected boom2" << endl;
}























}
