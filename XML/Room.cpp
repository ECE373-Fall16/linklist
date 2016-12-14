#include "List.h"
#include "Room.h"
#include <iostream>
#include <string>

Room::Room(){
roomName = "";
hostID = 0;
roomSize = 0;
userList = 0;
List playlist();
used = false;
}

Room::Room(string rName, int hID, int size){
	used = true;
	roomName = rName;
	hostID = hID;
	roomSize = size;
	userList = new int[roomSize];
	userList[0]=hostID;
	int j=1;
	while(j<roomSize){
	userList[j]=0;
	j++;
	List playlist();
	}
	}

void Room::addUser(int newID){
	int i = 1;
	while(i < roomSize){
		if(userList[i]==0){
		userList[i]=newID;
		break;
	}
	i++;
}
}

int Room::getHostID(){
	return hostID;
}
string Room::getRoomName(){
	return roomName;
}

void Room::displayUsers(){
	int i =0;
	while(i < roomSize){
	std::cout << userList[i]<< '\n';
	i++;
}
}
bool Room::isUsed(){
return used;
}
void Room::destroyRoom(){
used = false;
}


