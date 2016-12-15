#include "List.h"
#include <string>
#include <iostream>

using namespace std;

//Constructor
List::List(){
    	head = NULL;
	size = 0;
}

List::songNode* List::makeSong(string songURI,string Name,string Artist,string Album){
    List::songNode *newNode = new List::songNode;
    newNode->songURI = songURI;
    newNode->songName = Name;
    newNode->songArtist = Artist;
    newNode->songAlbum = Album;
    newNode->nextSong = NULL;
}

bool List::isEmpty(){
    if(head==NULL)
        return true;
}

string List::deleteSongFirst(){
	string temp = "";
	if(head==NULL){
	cout << "wtf head null"<<endl;
	temp = "spotify:track:2cmRpmO04TLaKPzmAzySYZ";
	}else if(head==end){
	cout<<"head gonna null"<<endl;
 	temp= head->songURI;
	 head = NULL;
	size = size -1;
}
	else{
	cout <<"head not null"<<endl;
	List::songNode *newSong = head->nextSong;
	temp = head->songURI;
	delete head;
	head = newSong;
	size = size - 1;
}
	return temp;
}

void List::addSongBack(string songURI,string Name,string Artist,string Album){
	struct songNode *newSong, *currentSong;
	newSong = makeSong(songURI,Name,Artist,Album);
	size = size +1 ;
	if(head == NULL){
	head = newSong;
	end = newSong;
	currentSong = newSong;
	head->nextSong = NULL;
	}
	else
	{
	currentSong = head;
	
	while(currentSong->nextSong != NULL) {
	currentSong = currentSong->nextSong;
	cout << "Got Through" << endl;
	cout << currentSong << endl;
	}
	currentSong->nextSong = newSong;
	 end = newSong;
	end->nextSong = NULL;
}
}
int List::getListSize(){
return size;
}
//int List::getListSize() {
//	if(head ==NULL){
//	cout<<"head null"<< endl;
//	return 0;
//}else if(head == end){
//cout<<"one thing"<<endl;
//return 1;
//}
//	else{
//	List::songNode* temp = head;
//	int counter = 0;
//	cout<<"countin time"<<endl;
//	while(temp != end){
//	temp = temp->nextSong;
//	counter++;
//	cout<<"counter"<<endl;
//	
//	}
//	cout<<"list is "<< counter << "long";
//	return counter;
//}
//}


string List::getSongURI(int songNumber) {
	if(head == NULL){
	return "spotify:track:6M14BiCN00nOsba4JaYsHW";
	}else if(head==end){
	cout <<"Only One Song"<< endl;
	return head->songURI;
}else{
	List::songNode* temp = head;
	for(int counter = 0; counter<songNumber%size; counter++){
	temp = temp->nextSong;
}
	return temp->songURI;
}
}

string List::getSongName(int songNumber) {
        if(head == NULL){
        return "No Songs In Queue";
        }else if(head==end){
        cout <<"Only One Song"<< endl;
        return head->songName;
}else{
        List::songNode* temp = head;
        for(int counter = 0; counter<songNumber%size; counter++){
        temp = temp->nextSong;
}
        return temp->songName;
}
}
string List::getSongArtist(int songNumber) {
        if(head == NULL){
        return "LinkList";
        }else if(head==end){
        cout <<"Only One Song"<< endl;
        return head->songArtist;
}else{
        List::songNode* temp = head;
        for(int counter = 0; counter<songNumber%size; counter++){
        temp = temp->nextSong;
}
        return temp->songArtist;
}
}
string List::getSongAlbum(int songNumber) {
        if(head == NULL){
        return "SoundSync";
        }else if(head==end){
        cout <<"Only One Song"<< endl;
        return head->songAlbum;
}else{
        List::songNode* temp = head;
        for(int counter = 0; counter<songNumber%size; counter++){
        temp = temp->nextSong;
}
        return temp->songAlbum;
}
}






















