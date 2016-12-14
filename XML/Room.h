#ifndef ROOM
#define ROOM

#include <string>
#include <iostream>
#include "List.h"
class Room {
private:
	string roomName;
	int hostID;
	int roomSize;
	bool used;
public:
int *userList;
List playlist;
//constructor
Room();
Room(string rName, int rID, int size);
string getRoomName();
int getHostID();
void addUser(int newID);
void displayUsers();
bool isUsed();
void destroyRoom();
};
#endif
