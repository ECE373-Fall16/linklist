#include "List.h"
#include <string>
#include <iostream>

using namespace std;

//Constructor
List::List(){
    head = NULL;
}

    List::songNode* List::makeSong(string URI){
    List::songNode *newNode = new songNode;
    newNode->songURI = URI;
}

bool List::isEmpty(){
    if(head==NULL)
        return true;
}
string List::deleteSongFirst(){
	string temp = "";
	if(head==NULL){
	cout << "wtf head null"<<endl;
	temp = "spotify:track:6M14BiCN00nOsba4JaYsHW";
	}else if(head->nextSong==end){
	cout<<"head gonna null"<<endl;
 	temp= head->songURI;
	 head = NULL;
	
}
	else{
	cout <<"head not null"<<endl;
	List::songNode *newSong = head->nextSong;
	temp = head->songURI;
	delete head;
	head = newSong;
	
}return temp;
}
void List::addSongFront(string URI){
    List::songNode* newSong = makeSong(URI);
        //Insert the new song, push the old head forward
        newSong->prevSong = NULL;
        newSong->nextSong = head;
        head = newSong;
    }

void List::addSongBack(string URI){
   // List::songNode* temp = head;
    List::songNode* newSong = makeSong(URI);
	if(head ==NULL){
		newSong->prevSong = NULL;
		newSong->nextSong = NULL;
		head = newSong;
		end = newSong;
		cout <<"head NULL YO"<<endl;
	}
	else{
	cout << "not head good"<<endl;
	end->nextSong= newSong;
	end = newSong;
	}
}
//void List::addSongBack(string URI){
//	List::songNode* newSong = makeSong(URI);
//	if(head== NULL){
//	head = newSong;
//	cout << "added song as head"
//	}
//	else{
	










void List::change(int pos1, int pos2){
    List::songNode* temp1 = head;
    int index = 1;
    while(index < pos1){
        if(temp1->nextSong != NULL)
        temp1 = temp1->nextSong;
        index++;
    }
         
    index = 1;
    List::songNode* temp2 = head;
    while(index < pos2){
        if(temp2->nextSong != NULL)
        temp2 = temp2->nextSong;
        index++;
    }
    
    List::songNode* exchange = makeSong(temp1->songURI);
    temp1->songURI = temp2->songURI;
    
    temp2->songURI = exchange->songURI;
    
}

void List::displayList() {
    List::songNode* temp = head;
    cout << "Current Playlist: " << endl;
    int counter = 0;
    while(temp != NULL){
        cout << counter << ")" << endl;
        cout << temp->songURI << endl;

        cout << endl;
        counter++;
        temp = temp->nextSong;
    }
//return counter;
}

int List::getListSize() {
	cout<< "fat list commin at ya soon"<< endl;
	if(head ==NULL){
	cout<<"head null"<< endl;
	return 0;
}else if(head== end){
cout<<"one thing"<<endl;
return 1;
}
	else{
	List::songNode* temp = head;
	int counter = 0;
	cout<<"countin time"<<endl;
	while(temp != end){
	temp = temp->nextSong;
	counter++;
	cout<<"counter"<<endl;
	
	}
	cout<<"list is "<< counter << "long";
	return counter;
}
}
string List::getSongURI(int songNumber) {
	if(head == NULL){
	return "spotify:track:6M14BiCN00nOsba4JaYsHW";
	}else if(head == end){
	cout <<"only one thing"<< endl;
	return head->songURI;
}else
{
	List::songNode* temp = head;
	int counter = 0;
	for(counter; counter < songNumber; counter++){
	temp = temp->nextSong;
	cout << temp->songURI<<endl;
}
	return temp->songURI;
}
}
