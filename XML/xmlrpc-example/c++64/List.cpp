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
	if(head != NULL){
    string temp = head->songURI;
    head = head->nextSong;   
    return temp;
	}
	if(head == NULL){
	return"spotify:track:6M14BiCN00nOsba4JaYsHW";
	}
}
void List::addSongFront(string URI){
    List::songNode* newSong = makeSong(URI);
        //Insert the new song, push the old head forward
        newSong->prevSong = NULL;
        newSong->nextSong = head;
        head = newSong;
    }

void List::addSongBack(string URI){
    List::songNode* temp = head;
    List::songNode* newSong = makeSong(URI);
	if(head ==NULL){
		newSong->prevSong = NULL;
		newSong->nextSong =head;
		head = newSong;
	}
	else{
        while(temp->nextSong != NULL) {
            temp = temp->nextSong;
        }
        temp->nextSong = newSong;
        newSong->prevSong = temp;
    }
    }
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
    int counter = 1;
    while(temp != NULL){
        cout << counter << ")" << endl;
        cout << temp->songURI << endl;

        cout << endl;
        counter++;
        temp = temp->nextSong;
    }
}
